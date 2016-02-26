package models;

public class CreditCard {
	
	private int id;
	private String cardHolderName;
	private String creditCardNumber;
	private double balance;
	private String cardNickname;
	private int userId;
	private String cVV;
	
	public CreditCard(){
		
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCardHolderName() {
		return cardHolderName;
	}

	public void setCardHolderName(String cardHolderName) {
		this.cardHolderName = cardHolderName;
	}

	public String getCreditCardNumber() {
		return creditCardNumber;
	}

	public void setCreditCardNumber(String creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getCardNickname() {
		return cardNickname;
	}

	public void setCardNickname(String cardNickname) {
		this.cardNickname = cardNickname;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getcVV() {
		return cVV;
	}

	public void setcVV(String cVV) {
		this.cVV = cVV;
	}


}
