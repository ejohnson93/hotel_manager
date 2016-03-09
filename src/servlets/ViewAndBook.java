package servlets;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Hotel;
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
		int roomId = Integer.parseInt(request.getParameter("roomId"));
		int numRooms = Integer.parseInt(request.getParameter("numRooms"));
		
		DatabaseManager db = new DatabaseManager();
		
		HotelRoom hr = db.getHotelRoom(roomId);
		
		Hotel h = db.getHotel(hr.getHotelId());
		
		h.addRoom(hr);
		
		if(numRooms > hr.getAvailableNum()){
			
			request.setAttribute("hotel", h);
			
			request.setAttribute("checkInDate", request.getParameter("checkInDate"));
			request.setAttribute("checkOutDate", request.getParameter("checkOutDate"));
			
			request.setAttribute("roomId", roomId);
			
			request.setAttribute("requestRooms", numRooms);
			
			request.setAttribute("error", "Not enough available rooms, the max available rooms is " + hr.getAvailableNum());
			
			RequestDispatcher rd = request.getRequestDispatcher("ViewAndBookReservations.jsp");
			rd.forward(request, response);
		}else{
		
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		java.sql.Date checkInDate = null;
		java.sql.Date checkOutDate = null;
		
		
	//	System.out.println(request.getParameter("startDate"));
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
		
		double totalPrice = hr.getPricePerNight() * numRooms * DateHelper.diffInDays(checkInDate, checkOutDate);
		
		totalPrice = totalPrice * 100;
		
		double roundedPrice = (int)totalPrice;
		
		roundedPrice /= 100;
		
		request.setAttribute("hotel", h);
		
		request.setAttribute("numRooms", numRooms);
		
		request.setAttribute("totalPrice", roundedPrice);
		
		request.setAttribute("roomId", request.getParameter("roomId"));
		
		request.setAttribute("checkInDate", request.getParameter("checkInDate"));
		
		request.setAttribute("checkOutDate", request.getParameter("checkOutDate"));
		
		request.setAttribute("numRooms", numRooms);
		
		RequestDispatcher rd = request.getRequestDispatcher("ReservationTransaction.jsp");
		rd.forward(request, response);
		}
		
	}

}
