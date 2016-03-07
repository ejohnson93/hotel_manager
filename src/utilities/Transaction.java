package utilities;
import models.CreditCard;

public class Transaction {
	
	public Transaction(){
		
	}
	
	//Think about how this will work with the user credit cards
	
	public boolean validateAndUpdateCard(CreditCard c, double price){
		
		
		
		if(c.getCardHolderName().isEmpty()){
			return false;
		}
		if(c.getCreditCardNumber().isEmpty() || c.getCreditCardNumber().length() != 16){
			return false;
		}
		if(c.getcVV().isEmpty() || c.getcVV().length() != 3){
			return false;
		}
		
		if(c.getBalance() < price){
			return false;
		}
		
		DatabaseManager db = new DatabaseManager();
		
		c.setBalance(c.getBalance() - price);
		
		db.updateCreditCard(c);
		
		return true;
		
	}

}
