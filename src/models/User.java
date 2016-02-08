package models;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class User {
	private String username;
	private String password;
	public User(String username, String password){
		this.setUsername(username);
		this.setPassword(password);
	}
	
	public enum VALIDATE {VALID, INVALID, NOTFOUND;}
	
	public boolean addUser(Properties prop, String propFilePath) throws FileNotFoundException, IOException
	{
		String password = prop.getProperty(this.username);
		
	    boolean exists;
	    
	    if (password == null){
			 exists = false;
		 }else {
			 exists =  true;
		 }
	    
		if (!exists){
		    prop.setProperty(this.username, this.password);
		    prop.store(new FileOutputStream(propFilePath), null);
			return true;
		}else{
			return false;
		}
		
	}
	 
	 public VALIDATE validateUser(Properties p){
		 
	    String password = p.getProperty(this.username);  
	    
	    if (password != null){
	    	if ( password.equals(this.password)){
	    		return VALIDATE.VALID;
	    	}else {
	    		return VALIDATE.INVALID;
	    	}
	    }else{
	    	return VALIDATE.NOTFOUND;
	    }
		
	 }

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
