package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.User;
import models.User.VALIDATE;
import utilities.DatabaseManager;

/**
 * Servlet implementation class ManageAccount
 */
public class ManageAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManageAccount() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		DatabaseManager db = new DatabaseManager();
		
		HttpSession session = request.getSession();
		
		if(session == null){
			System.out.println("Session is Null");
		}
		
		String username = (String) session.getAttribute("username");
		
		User u = db.getUserByUsername(username);
		
		request.setAttribute("userInfo", u);
		
		request.getRequestDispatcher("ManageAccount.jsp").forward( request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		DatabaseManager db = new DatabaseManager();
		String username = request.getParameter("username");
		User u = db.getUserByUsername(username);
		String currentPass = request.getParameter("currentPass");
		if(currentPass != null && !currentPass.equals("")){
			u.setPassword(currentPass);
		}
		else{
			request.setAttribute("userInfo", u);
			
			request.setAttribute("noPass", "You must enter your current password before you can update any information");
			
			request.getRequestDispatcher("ManageAccount.jsp").forward( request, response);
			return;
		}
		VALIDATE v = db.validateUser(u);
		System.out.println(v);
		
		if(v == VALIDATE.VALID){
			System.out.println("User was validated");
			String firstName = request.getParameter("firstName");
			if(firstName != null && !firstName.equals("")){
				u.setFirstName(firstName);
			}
			String lastName = request.getParameter("lastName");
			if(lastName != null && !lastName.equals("")){
				u.setLastName(lastName);
			}
			String address1 = request.getParameter("address1");
			if(address1 != null && !address1.equals("")){
				u.setAddressLine1(address1);;
			}
			String address2 = request.getParameter("address2");
			if(address2 != null && !address2.equals("")){
				u.setAddressLine2(address2);;
			}
			String city = request.getParameter("city");
			if(city != null && !city.equals("")){
				u.setCity(city);
			}
			String state = request.getParameter("state");
			if(state != null && !state.equals("")){
				u.setState(state);
			}
			String postalCode = request.getParameter("postalCode");
			if(postalCode != null && !postalCode.equals("")){
				u.setPostalCode(postalCode);
			}
			String newPass = request.getParameter("newPass");
			String confirmPass = request.getParameter("confirmNewPass");
			if((newPass != null && confirmPass != null && !newPass.equals("") && !confirmPass.equals("")) && newPass.equals(confirmPass)){
				u.setPassword(confirmPass);
			}
			db.updateUser(u);
		}
		doGet(request, response);
	}

}
