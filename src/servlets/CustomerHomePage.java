 package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Amenity;
import models.HotelRoomType;
import utilities.DatabaseManager;

/**
 * Servlet implementation class CustomerHomePage
 */
public class CustomerHomePage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerHomePage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		DatabaseManager db = new DatabaseManager();
		
		List<Amenity> amenities = db.getAllAmenities();
		List<HotelRoomType> roomTypes = db.getAllRoomTypes();
		
		request.setAttribute("amenities", amenities);
		request.setAttribute("roomTypes", roomTypes);
		
		request.getRequestDispatcher("CustomerHomePage.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
