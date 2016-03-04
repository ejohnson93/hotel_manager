package servlets;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.User;
import models.User.VALIDATE;
import utilities.*;

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
    Date serverStartDate = null;
    
    public void init () throws ServletException {
    	
    	serverStartDate = new Date();
    	
	//	FileInputStream fis = null;
	//	ServletContext sc = this.getServletContext();
		/* Store the user.properties file in the WEB-INF directory.
		   Relative path is converted into the absolute path. */
	//	propFilePath = sc.getRealPath("/WEB-INF/users.properties");
		/*
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
		}*/
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cookie cookie = new Cookie("login", "false");
		response.addCookie(cookie);
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
		
		DatabaseManager dbManager = new DatabaseManager();
		
		VALIDATE v = dbManager.validateUser(user);

	    //VALIDATE v = user.validateUser(prop);

		if(v == VALIDATE.VALID) {
			Cookie login = new Cookie("login", "true");
			response.addCookie(login);
			response.sendRedirect("CustomerHomePage");
			return;
		} else if (v == VALIDATE.INVALID) {
			request.setAttribute("errorPass", "Invalid password, please try again!"); 
            request.getRequestDispatcher("login.jsp").forward( request, response);
            return;
		} else {
			request.setAttribute("errorUser", "Username not found, please try again or click \"Register\" to create a new account!"); 
            request.getRequestDispatcher("login.jsp").forward( request, response);
            return;
		}
	}

}
