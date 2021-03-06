package servlets;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import models.Amenity;
import models.Hotel;
import models.HotelRoom;
import models.HotelRoomType;
import utilities.DatabaseManager;

/**
 * Servlet implementation class ReservationSearchQuery
 */
public class ReservationSearchQuery extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	static Logger log 
    = Logger.getLogger(ReservationSearchQuery.class.getName());
	
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReservationSearchQuery() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext sc = this.getServletContext();
		String propFilePath = sc.getRealPath("/WEB-INF/lib/log4j.properties");
		PropertyConfigurator.configure(propFilePath);
		
		if(SessionManager.validateSession(request, response) == 0){
			return;
		}
		
		DateFormat format = new SimpleDateFormat("MM/dd/yyy hh:mm a", Locale.ENGLISH);
		java.sql.Date checkInDate = null;
		java.sql.Date checkOutDate = null;
		
		DatabaseManager db = new DatabaseManager();
		
	//	System.out.println(request.getParameter("startDate"));
		try {
			checkInDate = new java.sql.Date(format.parse(request.getParameter("startDate")).getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error("Incorrect check in date format: ", e);
		}
		
		try {
			checkOutDate = new java.sql.Date(format.parse(request.getParameter("endDate")).getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error("Incorrect check out date format: ", e);			
		}
		
		String city = request.getParameter("city");
		int numRooms = 1;
		try{
		numRooms = Integer.parseInt(request.getParameter("numRooms"));
		}
		catch(Exception e){
			e.printStackTrace();
			log.warn("This is a warn message: ", e);
		}
		
		HotelRoomType roomType = new HotelRoomType();
		roomType.setRoomType(request.getParameter("roomType"));
	//	System.out.println(roomType.getRoomType());
		String[] amenities = request.getParameterValues("Amenities");
		List<Amenity> am = new ArrayList<Amenity>();
		
		if(amenities != null){
		
			for(String s: amenities){
				Amenity a = new Amenity();
				//System.out.println(s);
				a.setName(s);
				am.add(a);
			}
		
		}
		
		List<Hotel> hotels = db.searchHotels(checkInDate, checkOutDate, city, numRooms, roomType, am);
		
		for(Hotel h: hotels){
		//	System.out.println(h.getName());
			db.getHotelAmenities(h);
			db.getHotelHotelReviews(h);
			for(HotelRoom r: h.getAllHotelRooms()){
			//	System.out.println(r.getPricePerNight());
			}
		}
		
		request.setAttribute("checkInDate", checkInDate);
		request.setAttribute("checkOutDate", checkOutDate);

		
		request.setAttribute("hotels", hotels);
		
		request.setAttribute("requestRooms", numRooms);
		
		RequestDispatcher rd = request.getRequestDispatcher("ReservationSearchResults.jsp");
		rd.forward(request, response);

		
		
	}

}
