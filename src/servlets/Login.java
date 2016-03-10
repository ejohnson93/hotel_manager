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
import javax.servlet.http.HttpSession;

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
    
//    Properties prop = new Properties();
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
		request.getSession().invalidate();
		String cookieName = "username";
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			 for(int i=0; i<cookies.length; i++) {
			 Cookie cookie = cookies[i];
			 if (cookieName.equals(cookie.getName())){
				 request.setAttribute("existingName", cookie.getValue());
			 }
		 }
		} 
		request.getRequestDispatcher("login.jsp").forward( request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name = request.getParameter("username");
		String pass = request.getParameter("password");
		
		User user = new User(name, pass);
		
		HttpSession session = request.getSession(true);
		
		DatabaseManager dbManager = new DatabaseManager();
		
		VALIDATE v = dbManager.validateUser(user);

		if(v == VALIDATE.VALID) {
			if( request.getParameter("remember") != null ){
				Cookie c = new Cookie("username", name);
				response.addCookie(c);
			}
			session.setAttribute("username", name);
			request.getRequestDispatcher("CustomerHomePage").forward( request, response);
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