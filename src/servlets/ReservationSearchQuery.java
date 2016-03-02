package servlets;

import java.io.IOException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Amenity;
import models.Hotel;
import models.HotelRoomType;
import models.User;
import models.User.VALIDATE;
import utilities.DatabaseManager;

/**
 * Servlet implementation class ReservationSearchQuery
 */
public class ReservationSearchQuery extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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
		
		DateFormat format = new SimpleDateFormat("MM/dd/yyy hh:mm a", Locale.ENGLISH);
		java.sql.Date checkInDate = null;
		java.sql.Date checkOutDate = null;
		
		DatabaseManager db = new DatabaseManager();
		
		System.out.println(request.getParameter("startDate"));
		try {
			checkInDate = new java.sql.Date(format.parse(request.getParameter("startDate")).getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			checkOutDate = new java.sql.Date(format.parse(request.getParameter("endDate")).getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String city = request.getParameter("city");
		int	numRooms = Integer.parseInt(request.getParameter("numRooms"));
		HotelRoomType roomType = new HotelRoomType();
		roomType.setRoomType(request.getParameter("roomType"));
		String[] amenities = request.getParameterValues("Amenities");
		List<Amenity> am = new ArrayList<Amenity>();
		
		if(amenities != null){
		
			for(String s: amenities){
				Amenity a = new Amenity();
				a.setName(s);
				am.add(a);
			}
		
		}
		
		List<Hotel> hotels = db.searchHotels(checkInDate, checkOutDate, city, numRooms, roomType, am);
		
		for(Hotel h: hotels){
			System.out.println(h.getName());
		}
		
		response.sendRedirect("ReservationSearchResults.jsp");

		
		
	}

}