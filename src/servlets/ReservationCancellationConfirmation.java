package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.HotelReservation;
import utilities.DatabaseManager;
import utilities.DateHelper;

/**
 * Servlet implementation class ReservationCancellationConfirmation
 */
public class ReservationCancellationConfirmation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReservationCancellationConfirmation() {
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
		// TODO Auto-generated method stub
		String hrid = request.getParameter("hrid");
		DatabaseManager db = new DatabaseManager();
		HotelReservation hr = db.getReservationById(Integer.parseInt(hrid));
		
		db.removeReservation(hr);
		
		request.setAttribute("hr", hr);
		
		double totalPrice = hr.getRoom().getPricePerNight() * hr.getNumRooms() * DateHelper.diffInDays(hr.getCheckInDate(), hr.getCheckOutDate());
		
		totalPrice = totalPrice * 100;
		
		double roundedPrice = (int)totalPrice;
		
		roundedPrice /= 100;
		
		request.setAttribute("price", roundedPrice);
		
		request.getRequestDispatcher("ReservationCancellationConfirmation.jsp").forward( request, response);
	}

}
