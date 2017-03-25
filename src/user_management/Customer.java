package user_management;


public class Customer extends PhysicalUser{
	private boolean spamAgreement;
	private FidelityCard fidelityCard;
	
	/*
	* constructor: it he fidelity card by default is the basic one
	* the spamAgreement is false by default
	* 
	*/
	public Customer(String name, String userName, String passWord, String phone,
			String email, int[] adress) {
		super(name, userName, passWord, phone, email, adress);
		this.spamAgreement = false;
		this.fidelityCard = new BasicFidelityCard();
	}

	//getters
	public boolean isSpamAgreement() {
		return spamAgreement;
	}

	public FidelityCard getFidelityCard() {
		return fidelityCard;
	}

	//setters
	public void setSpamAgreement(boolean spamAgreement) {
		this.spamAgreement = spamAgreement;
	}

	public void setFidelityCard(FidelityCard fidelityCard) {
		this.fidelityCard = fidelityCard;
	}
	
	public String toString(){	
		int x = this.getAdress()[0];
		int y = this.getAdress()[1];
		return "ID:"+this.getID()+"/Name:"+this.getName()+"/Surname:"+this.getSurname()+"/Username:"+this.getUserName()
			+"/Password:"+this.getPassWord()+"/Email:"+this.getEmail()+"/Phone:"+this.getPhone()
			+"/Adress:{"+x+";"+y+"}"+"/SpamAgreement"+this.spamAgreement+"/FidelityCard:"+this.fidelityCard;
	}
	
}
