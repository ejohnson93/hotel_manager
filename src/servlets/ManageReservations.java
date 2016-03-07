package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.Hotel;
import models.HotelReservation;
import models.User;
import utilities.DatabaseManager;

/**
 * Servlet implementation class ManageReservations
 */
public class ManageReservations extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManageReservations() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		DatabaseManager db = new DatabaseManager();
		
		HttpSession session = request.getSession();
		
		if(session == null){
			System.out.println("Session is Null");
		}
		
		String username = (String) session.getAttribute("username");
		
		User u = db.getUserByUsername(username);
		
		System.out.println(u.getId());
		
		db.getUserReservations(u);
		
		for(HotelReservation h: u.getAllReservations()){
			Hotel hotel = db.getHotel(h.getHotelId());
			h.setHotel(hotel);
		}
		
		List<HotelReservation> hr = u.getAllReservations();
		
		request.setAttribute("hrList", hr);
		
		request.getRequestDispatcher("ManageReservations.jsp").forward( request, response);
        return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

}
