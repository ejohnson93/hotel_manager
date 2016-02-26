package servlets;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utilities.DatabaseManager;

import models.User;

/**
 * Servlet implementation class Register
 */
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
    }
    
    
    Properties prop = new Properties();

	String propFilePath;
	
	ServletContext sc;
	
	Date serverStartDate = null;
    
    public void init () throws ServletException {
    	serverStartDate = new Date();
		loadProp();
    }
    
    private void loadProp(){
	    FileInputStream fis = null;
		
		sc = this.getServletContext();
		/* Store the user.properties file in the WEB-INF directory.
		   Relative path is converted into the absolute path. */
		propFilePath = sc.getRealPath("/WEB-INF/users.properties");
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
		response.sendRedirect("register.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name = request.getParameter("username");
		String pass = request.getParameter("password");
		String mismatch = request.getParameter("matching");
		
		DatabaseManager dbManager = new DatabaseManager();
		
		User user = new User(name, pass);
		
		if (name.isEmpty() || name == null || pass == null || pass.isEmpty() || mismatch == null || mismatch.isEmpty()) {
			request.setAttribute("emptyString", "*Please enter a value for all fields!"); 
            request.getRequestDispatcher("register.jsp").forward( request, response);
            return;
		} else {
            if ( mismatch.equals("notMatching") ){
				request.setAttribute("passError", "*Passwords did not match, please try again!"); 
	            request.getRequestDispatcher("register.jsp").forward( request, response);
	            return;
			}
			else if (mismatch.equals("matching")){
				boolean success = dbManager.addUser(user);
				
				if (success) {
					loadProp();
					response.sendRedirect("login.jsp");
				} else {
					request.setAttribute("nameTaken", "*Username was already taken, please try another one!"); 
		            request.getRequestDispatcher("register.jsp").forward( request, response);
		            return;
					//response.sendRedirect("register.jsp");
				}
			}
		}
	}

}
