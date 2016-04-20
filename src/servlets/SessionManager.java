package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SessionManager
 */
public class SessionManager extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SessionManager() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
/*	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
*/
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    /*
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}*/
	
public static int validateSession(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	try{
	HttpSession session = request.getSession(false);
    if(session == null){
       //valid session doesn't exist
       //do something like send the user to a login screen
    	request.setAttribute("errorPass", "Session Timed out, please Re-login"); 
    	RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
		rd.forward(request, response);
    	return 0;
    }
    if(session.getAttribute("username") == null){
       //no username in session
       //user probably hasn't logged in properly
    	request.setAttribute("errorPass", "User not logged in, please log in"); 
    	RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
		rd.forward(request, response);
    	return 0;
    }
    return 1;
	}catch(Exception e){
		request.setAttribute("errorPass", "Session Timed out, please Re-login"); 
		RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
		rd.forward(request, response);
		return 0;
	}

}

}
