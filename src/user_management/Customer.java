package user_management;

import system.*;
import java.util.ArrayList;


public class Customer extends PhysicalUser implements PeopleToNotify{
	private boolean spamAgreement;
	private FidelityCard fidelityCard;
	private ArrayList<String> notifications;  //Contains all the unread notifications
	private int notificationNumber;
	private ArrayList<Order> historyOfOrders;
	private String contactForOffers;
	
	
	/*
	* constructor: it he fidelity card by default is the basic one
	* the spamAgreement is false by default
	*/
	public Customer(String name, String userName, String passWord, String phone,
			String email, int[] adress) {
		super(name, userName, passWord, phone, email, adress);
		this.spamAgreement = false;
		this.fidelityCard = new BasicFidelityCard();
		this.notifications = new ArrayList<String>();
		this.notificationNumber = 0;
		this.notifications.add("You have 0 notification");
		this.contactForOffers = "email";
		this.historyOfOrders = new ArrayList<Order>();
	}

	//getters
	public boolean getSpamAgreement(){
		return spamAgreement;
	}

	public FidelityCard getFidelityCard(){
		return fidelityCard;
	}

	public ArrayList<Order> getHistoryOfOrders() {
		return historyOfOrders;
	}

	public String getNotifications(){
		String notificationString = this.contactForOffers+"\r\n"+this.notifications.get(0)+"\r\n";
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
	
	public String getContactForOffers(){
		return this.contactForOffers;
	}

	//setters
	public void setSpamAgreement(boolean spamAgreement){

		this.spamAgreement = spamAgreement;
	}

	public void setFidelityCard(String name) throws FidelityCardDoesNotExistException {
		//If the current fidelity card is a point fidelity card, the points are lost 
		if (this.fidelityCard instanceof PointFidelityCard){
			System.out.println("You lost the points on your point fidelity card");
		if (name.equalsIgnoreCase("basic fidelity card")||name.equalsIgnoreCase("basicfidelitycard")){
			if (this.fidelityCard instanceof BasicFidelityCard){
				System.out.println("You already have a basic fidelity card.");
			}
			else{
				this.fidelityCard = new BasicFidelityCard();
			}
		}
		
		else if (name.equalsIgnoreCase("lottery fidelity card")||name.equalsIgnoreCase("lotteryfidelitycard")){
			System.out.println("a");
			if (this.fidelityCard instanceof LotteryFidelityCard){
				System.out.println("You already have a lottery fidelity card.");
			}
			else{
				this.fidelityCard = new LotteryFidelityCard();
			}
		}
		}
		
		else if (name.equalsIgnoreCase("point fidelity card")||name.equalsIgnoreCase("pointfidelitycard")){
			if (this.fidelityCard instanceof PointFidelityCard){
				System.out.println("You already have a point fidelity card.");
			}
			else{
				this.fidelityCard = new PointFidelityCard();
			}
		}
		
		else if (name.equalsIgnoreCase("lottery fidelity card")||name.equalsIgnoreCase("lotteryfidelitycard")){
			if (this.fidelityCard instanceof LotteryFidelityCard){
				System.out.println("You already have a lottery fidelity card.");
			}
			else{
				this.fidelityCard = new LotteryFidelityCard();
			}
		}
		else{
			throw new FidelityCardDoesNotExistException();
		}
	}
	
	public void setContactForOffers(String str) throws KindOfContactDoesNotExistException{
		if(str.equalsIgnoreCase("email")){
			this.contactForOffers = "email";
		}
		else if(str.equalsIgnoreCase("phone")){
			this.contactForOffers = "phone";
		}
		else if(str.equalsIgnoreCase("letter")){
			this.contactForOffers = "letter";
		}
		else{
			throw new KindOfContactDoesNotExistException();
		}
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
	
	//Get the points acquired by fidelity card
	public String getPointsFidelityCard(){
		//If the card is a basic fidelity card or a lottery fidelity card
		if (this.fidelityCard instanceof BasicFidelityCard||this.fidelityCard instanceof LotteryFidelityCard){
			return"This card does not work with points.";
		}
		//If the card is a point fidelity card
		else{
			PointFidelityCard pointFidefiltyCar = (PointFidelityCard) this.fidelityCard;
			return "You have "+pointFidefiltyCar.getPoints()+" points.";
		}
	}
	
	public Order newOrder(String restaurantName) throws RestaurantNotFoundException{
		int restaurantIndex = this.findIDRestaurant(restaurantName);
	
		return new Order(this, this.getMyFoodora().getListRestaurant().get(restaurantIndex));
	}
	
	//Method to find a restaurant by searching his userName
	private int findIDRestaurant(String restaurantName) throws RestaurantNotFoundException{
		int index = -1;
		ArrayList<Restaurant> listRestaurants = this.getMyFoodora().getListRestaurant();
		for (int i = 0; i < listRestaurants.size(); i++) {
			if (listRestaurants.get(i).getName().equalsIgnoreCase(restaurantName)){
				index = i;
			}
		}
		if (index == -1){ //The user has not been found
			throw new RestaurantNotFoundException(restaurantName);
		}			
		return index;  //The user has been found and the index is returned
		}
	
	public void addOrder(Order order){
		this.historyOfOrders.add(order);
	}
	
}
