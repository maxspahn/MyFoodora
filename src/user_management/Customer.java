package user_management;

import system.*;
import java.util.ArrayList;


public class Customer extends PhysicalUser implements PeopleToNotify{
	private boolean spamAgreement;
	private FidelityCard fidelityCard;
	private ArrayList<String> notifications;  //Contains all the unread notifications
	private int notificationNumber;
	
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
		this.notifications = new ArrayList<String>();
		this.notificationNumber = 0;
		this.notifications.add("You have 0 notification");
	}

	//getters
	public boolean getSpamAgreement() {
		return spamAgreement;
	}

	public FidelityCard getFidelityCard() {
		return fidelityCard;
	}

	public String getNotifications() {
		String notificationString = this.notifications.get(0)+"\r\n";
		if (this.notificationNumber>0){
		for (int i = 1; i < this.notifications.size(); i++) {
			String newNotification = this.notifications.get(i)+"\r\n";
			notificationString +=newNotification;
			
			//Remove the notifications while reading them
			this.notifications.remove(newNotification);
		}
		
		//Reset the number of notifications
		}
		this.notificationNumber = 0;
		this.notifications.set(0, "You have 0 notification");
		return notificationString;
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

	@Override
	public void update(String notification) {
		this.notifications.add(notification);
		this.notificationNumber +=1;
		if (this.notificationNumber==1){
			this.notifications.set(0,"You have 1 notification"); 
		}
		else{
		this.notifications.set(0,"You have "+this.notificationNumber+" notifications");}
	}
	
}
