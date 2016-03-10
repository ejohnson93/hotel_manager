package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Hotel;
import models.HotelRoom;
import utilities.DatabaseManager;

/**
 * Servlet implementation class ReservationSearchResults
 */
public class ReservationSearchResults extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReservationSearchResults() {
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
		
	//	System.out.println("Room Id is " + roomId);
		
		DatabaseManager db = new DatabaseManager();
		
		HotelRoom hr = db.getHotelRoom(roomId);
		
		Hotel h = db.getHotel(hr.getHotelId());
		
		//System.out.println("Selected hotel Id is " + hr.getHotelId());
		
		h.addRoom(hr);
		
		request.setAttribute("hotel", h);
		
		request.setAttribute("checkInDate", request.getParameter("checkInDate"));
		request.setAttribute("checkOutDate", request.getParameter("checkOutDate"));
		
		request.setAttribute("roomId", roomId);
		
		request.setAttribute("requestRooms", numRooms);
		
		if((String)request.getSession().getAttribute("username") == null){
			request.setAttribute("notLoggedIn", "You must be logged in to book a reservation");
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("ViewAndBookReservations.jsp");
		rd.forward(request, response);
	}

}
