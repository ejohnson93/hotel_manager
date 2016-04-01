package servlets;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Amenity;
import models.Hotel;
import models.HotelReservation;
import models.HotelRoom;
import utilities.DatabaseManager;
import utilities.DateHelper;

/**
 * Servlet implementation class ViewAndBook
 */
public class ViewAndBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewAndBook() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		boolean allAvailable = true;
		
		String[] hrIds = request.getParameterValues("idList");
		String totalPrice = request.getParameter("totalPrice");
		List<HotelReservation> hrList = new ArrayList<HotelReservation>();
		List<Double> prices = new ArrayList<Double>();
		
		DatabaseManager db = new DatabaseManager();
		
		for(int i = 0; i < hrIds.length; i++){
			
			HotelReservation res = db.getReservationById(Integer.parseInt(hrIds[i]));
		
			HotelRoom hr = db.getHotelRoom(res.getRoom().getId());
			
			Hotel h = db.getHotel(res.getHotelId());
			
			h.addRoom(hr);
			
			hrList.add(res);
			Hotel hotel = db.getHotel(res.getHotelId());
			res.setHotel(hotel);
			double price = (res.getNumRooms() * DateHelper.diffInDays(res.getCheckInDate(), res.getCheckOutDate()) * res.getRoom().getPricePerNight());
			price = price * 100;
			double roundedPrice = (int)price;
			roundedPrice /= 100;
			prices.add(roundedPrice);
			
			if(res.getNumRooms() > hr.getAvailableNum()){
				allAvailable = false;
			}
		
		}
		
		request.setAttribute("totalPrice", totalPrice);
		request.setAttribute("prices", prices);
		request.setAttribute("hrList", hrList);
		
		if (allAvailable){
			RequestDispatcher rd = request.getRequestDispatcher("ReservationTransaction.jsp");
			rd.forward(request, response);
		}else{
			request.setAttribute("insufficientRooms", "The rooms you have requested are no longer available, we're sorry!");
			RequestDispatcher rd = request.getRequestDispatcher("ShoppingCart.jsp");
			rd.forward(request, response);
		}
		
	}

}
