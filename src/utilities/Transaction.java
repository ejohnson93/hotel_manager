package utilities;
import models.CreditCard;

public class Transaction {
	
	public enum CARD_STATUS {VALID, INVALID, BAD_FUNDS;}
	
	public Transaction(){
		
	}
	
	//Think about how this will work with the user credit cards
	
	public CARD_STATUS validateAndUpdateCard(CreditCard c, double price){
		
		
		//Make sure card isn't empty
		if(c.getCardHolderName().isEmpty()){
			return CARD_STATUS.INVALID;
		}
		if(c.getCreditCardNumber().isEmpty() || c.getCreditCardNumber().length() != 16){
			return CARD_STATUS.INVALID;
		}
		if(c.getcVV().isEmpty() || c.getcVV().length() != 3){
			return CARD_STATUS.INVALID;
		}
		
		
		DatabaseManager db = new DatabaseManager();
		
		CreditCard cardOnFile = db.getCreditCardByCardNumber(c.getCreditCardNumber());
		
		if(cardOnFile.getBalance() < price){
			return CARD_STATUS.BAD_FUNDS;
		}
		
		//check the card on file
		if(!c.getCardHolderName().equals(cardOnFile.getCardHolderName())){
			return CARD_STATUS.INVALID;
		}
		if(!c.getCreditCardNumber().equals(cardOnFile.getCreditCardNumber())){
			return CARD_STATUS.INVALID;
		}
		if(!c.getcVV().equals(cardOnFile.getcVV())){
			return CARD_STATUS.INVALID;
		}
		
		cardOnFile.setBalance(cardOnFile.getBalance() - price);
		
		db.updateCreditCard(cardOnFile);
		
		return CARD_STATUS.VALID;
		
	}
	
	public static void refund(CreditCard c, double amount){
		
		DatabaseManager db = new DatabaseManager();
		
		CreditCard card = db.getCreditCardByCardNumber(c.getCreditCardNumber());
		
		card.setBalance(card.getBalance() + amount);
		
		db.updateCreditCard(card);
		
	}

}
