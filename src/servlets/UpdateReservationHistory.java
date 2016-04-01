package servlets;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.CreditCard;
import models.Hotel;
import models.HotelReservation;
import utilities.DatabaseManager;
import utilities.Transaction;
import utilities.Transaction.CARD_STATUS;

/**
 * Servlet implementation class TransactionConfirmation
 */
public class UpdateReservationHistory extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateReservationHistory() {
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
		
		String[] hrIds = request.getParameterValues("idList[]");
		
		DatabaseManager db = new DatabaseManager();
		
		for( int i = 0; i < hrIds.length; i++){
			HotelReservation hr = db.getReservationById(Integer.parseInt(hrIds[i]));
			hr.setPaidFor(true);
			db.updateHotelReservation(hr);
			db.decrementAvailableRooms(hr.getRoom().getId(), hr.getNumRooms());
		}
		
	}

}
