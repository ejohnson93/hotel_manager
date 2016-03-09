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
import models.HotelReservation;
import utilities.DatabaseManager;
import utilities.Transaction;
import utilities.Transaction.CARD_STATUS;

/**
 * Servlet implementation class TransactionConfirmation
 */
public class TransactionConfirmation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TransactionConfirmation() {
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
		
		Date now = new Date();
		
		Calendar expDate = Calendar.getInstance();
		expDate.clear();
		expDate.set(Calendar.MONTH, Integer.parseInt(request.getParameter("expMonth")));
		expDate.set(Calendar.YEAR, Integer.parseInt(request.getParameter("expYear")));
		
		Date endDate = expDate.getTime();
		
		if(now.before(endDate)){
			
			CreditCard c = new CreditCard();
			System.out.println(request.getParameter("firstName") + " " + request.getParameter("lastName"));
			c.setCardHolderName(request.getParameter("firstName") + " " + request.getParameter("lastName"));
			c.setCreditCardNumber(request.getParameter("cardNumber"));
			c.setcVV(request.getParameter("securityCode"));
			
			Transaction t = new Transaction();
			
			CARD_STATUS status = t.validateAndUpdateCard(c, Double.parseDouble(request.getParameter("price")));
			
			if(status == CARD_STATUS.VALID){
				System.out.println("Valid");
				
				DatabaseManager db = new DatabaseManager();
				
				HotelReservation hr = new HotelReservation();
				hr.setHotelId(Integer.parseInt(request.getParameter("hotelId")));
				hr.setNumRooms(Integer.parseInt(request.getParameter("numRooms")));
				hr.setRoom(db.getHotelRoom(Integer.parseInt(request.getParameter("roomId"))));
				hr.setUserId(db.getUserByUsername((String)request.getSession().getAttribute("username")).getId());
				
				
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
				
				hr.setCheckInDate(checkInDate);
				hr.setCheckOutDate(checkOutDate);
				
				db.addHotelReservation(hr);
				
				request.getRequestDispatcher("ReservationTransactionConfirmation.jsp").forward(request, response);
			}
			else if(status == CARD_STATUS.INVALID){
				System.out.println("Invalid");
				request.getRequestDispatcher("ReservationTransaction.jsp").forward(request, response);
			}
			else if(status == CARD_STATUS.BAD_FUNDS){
				System.out.println("Bad Funds");
				request.getRequestDispatcher("ReservationTransaction.jsp").forward(request, response);
			}
		}else{
			System.out.println("Bad DATE");
			request.getRequestDispatcher("ReservationTransaction.jsp").forward(request, response);
		}
		
		
		
		
	}

}
