package servlets;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.Hotel;
import models.HotelReservation;
import models.User;
import utilities.DatabaseManager;
import utilities.DateHelper;

/**
 * Servlet implementation class ShoppingCart
 */
public class ShoppingCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShoppingCart() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String insufficientRooms = request.getParameter("insufficientRooms");
		if (insufficientRooms != null){
			request.setAttribute("insufficientRooms", insufficientRooms);
		}
		
		DatabaseManager db = new DatabaseManager();
		
		HttpSession session = request.getSession();
		
		if(session == null){
			System.out.println("Session is Null");
		}
		
		String username = (String) session.getAttribute("username");
		
		User u = db.getUserByUsername(username);
		
		double totalPrice = 0;
		
		db.getUserShoppingCart(u);
		
		List<Double> prices = new ArrayList<Double>();
		for(HotelReservation h: u.getAllReservations()){
			Hotel hotel = db.getHotel(h.getHotelId());
			h.setHotel(hotel);
			double price = (h.getNumRooms() * DateHelper.diffInDays(h.getCheckInDate(), h.getCheckOutDate()) * h.getRoom().getPricePerNight());
			price = price * 100;
			double roundedPrice = (int)price;
			roundedPrice /= 100;
			totalPrice += roundedPrice;
			prices.add(roundedPrice);
		}
		
		List<HotelReservation> hr = u.getAllReservations();
		
		if(hr.isEmpty()){
			request.setAttribute("cartEmpty", "You have not added anything to your shopping cart yet!");
		}
		
		request.setAttribute("hrList", hr);
		request.setAttribute("prices", prices);
		totalPrice = Math.round(totalPrice*100);
		totalPrice /= 100;
		request.setAttribute("totalPrice", totalPrice);
		
		request.getRequestDispatcher("ShoppingCart.jsp").forward( request, response);
        return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		DatabaseManager db = new DatabaseManager();
		
		HotelReservation hr = new HotelReservation();
		hr.setPaidFor(false);
		hr.setHotelId(Integer.parseInt(request.getParameter("hotelId")));
		hr.setNumRooms(Integer.parseInt(request.getParameter("numRooms")));
		hr.setRoom(db.getHotelRoom(Integer.parseInt(request.getParameter("roomId"))));
		hr.setUserId(db.getUserByUsername((String)request.getSession().getAttribute("username")).getId());
		
		
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		java.sql.Date checkInDate = null;
		java.sql.Date checkOutDate = null;
		
		try {
			checkInDate = new java.sql.Date(format.parse(request.getParameter("checkInDate")).getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			checkOutDate = new java.sql.Date(format.parse(request.getParameter("checkOutDate")).getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		hr.setCheckInDate(checkInDate);
		hr.setCheckOutDate(checkOutDate);
		
		String resNum = null;
		synchronized(db) {
		
		resNum = db.addHotelReservation(hr);
		
		}
		
	}

}
