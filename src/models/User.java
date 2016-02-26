package models;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class User {
	private int id;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String addressLine1;
	private String addressLine2;
	private String city;
	private String state;
	private String postalCode;
	private int type;
	private int status;
	private List<CreditCard> creditCards;
	
	public User(String username, String password){
		this.setUsername(username);
		this.setPassword(password);
	}
	
	public enum VALIDATE {VALID, INVALID, NOTFOUND;}
	/*
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
*/
	
	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
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
	
	public List<CreditCard> getAllCreditCards(){
		
		return this.creditCards;
		
	}
	public void addCreditCard(CreditCard c){
		
		this.creditCards.add(c);
		
	}
	public void removeCreditCard(int id){
		
		for(CreditCard c: this.creditCards){
			if(c.getId() == id){
				this.creditCards.remove(c);
			}
		}
		
	}
	public void removeCreditCard(CreditCard c){
		this.creditCards.remove(c);
	}

}
