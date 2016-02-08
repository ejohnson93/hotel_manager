package servlets;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.User;
import models.User.VALIDATE;

/**
 * Servlet implementation class Login
 */
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
    }
    
    Properties prop = new Properties();
    String propFilePath;
    
    public void init () throws ServletException {
		FileInputStream fis = null;
		ServletContext sc = this.getServletContext();
		/* Store the user.properties file in the WEB-INF directory.
		   Relative path is converted into the absolute path. */
		propFilePath = sc.getRealPath("/WEB-INF/users.properties");
		try{
			fis = new FileInputStream(propFilePath);

		    prop.load(fis); 
		    
		} catch (FileNotFoundException e) {

		    System.out.println("FileNotFound");

		} catch (IOException e) {

		    System.out.println("IOEXCeption");

		} finally {

		    if (fis != null) {
		        try {
		            fis.close();
		        }
		        catch (Exception e) {

		            e.printStackTrace();
		        }
		    }
		}
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.sendRedirect("login.jsp");
			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name = request.getParameter("username");
		String pass = request.getParameter("password");
		
		User user = new User(name, pass);

	    VALIDATE v = user.validateUser(prop);

		if(v == VALIDATE.VALID) {
			response.sendRedirect("Welcome.jsp");
		} else /*if (v == VALIDATE.INVALID) */{
			request.setAttribute("errorMessage", "Invalid username or password, please try again or click \"Register\" to create a new account!"); 
            request.getRequestDispatcher("login.jsp").forward( request, response);
            return;
			//response.sendRedirect("login.jsp");
		} /*else {
			response.sendRedirect("register.jsp");
		}*/
	}

}
