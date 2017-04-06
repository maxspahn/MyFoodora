package user_management;

import system.*;
import java.util.ArrayList;
import java.io.Serializable;

/** Implementation of Customer, extends the PhysicalUser class. Implements the PeopleToNotify interface.
 * A customer can receive notifications sent by restaurants thanks to an observer pattern. 
 * @author maxspahn
 * @author jeremyaugot
 *
 */
public class Customer extends PhysicalUser implements PeopleToNotify, Serializable{
	private boolean spamAgreement;
	private FidelityCard fidelityCard;
	private ArrayList<String> notifications;  //Contains all the unread notifications
	private int notificationNumber;
	private ArrayList<Order> historyOfOrders;
	private String contactForOffers;
	
	/**Constructor.
	 * By default, the fidelity card is a basic one, the customer cannot receive spam 
	 * and ths way to contact her/him is by using the email adress.
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

	/**
	 * @return A string containing all the received notifications.
	 */
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
	
	/**
	 * @param name String which contains the name of the chosen fidelity card.
	 */
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
	
	/**
	 * @param str String which contains the way to contact the customer.
	 */
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
		return "ID:"+this.getID()+"\t : "+this.getName()+", "+this.getSurname()+" Username : "+this.getUserName()
			+" "+this.getEmail()+" "+this.getPhone()
			+"/Adress : { "+x+" ; "+y+" }"+" SpamAgreement : "+this.spamAgreement+" FidelityCard : "+this.fidelityCard;
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
	
	/**
	 * @return A string saying how many points there are on the point fidelity card.
	 */
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
	
	/**Place an order.
	 * @param restaurantName String which contains the name of the restaurant in which the customer wants to order.
	 * @return Order with two arguments: the customer and the name of the restaurant.
	 * @throws RestaurantNotFoundException If the restaurant username does not exist in MyFoodora system.
	 */
	public Order newOrder(String restaurantName, String orderName) throws RestaurantNotFoundException{
		int restaurantIndex = this.findIDRestaurant(restaurantName);
	
		return new Order(this, this.getMyFoodora().getListRestaurant().get(restaurantIndex), orderName);
	}
	
	/**Find a restaurant by searching its username.
	 * @param restaurantName String which contains the name of the restaurant to be found in the list of all the restaurants.
	 * @return index Integer representing the position of the restaurant in the list.
	 * @throws RestaurantNotFoundException If the restaurant name does not exist in MyFoodora system.
	 */
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
	
	
	/**Add an order to the history-of-orders-list.
	 * @param order Order to add in the list
	 */
	public void addOrder(Order order){
		this.historyOfOrders.add(order);
	}

	/**
	 * @return the spamAgreement
	 */
	public boolean getSpamAgreement() {
		return spamAgreement;
	}

	/**
	 * @return the fidelityCard
	 */
	public FidelityCard getFidelityCard() {
		return fidelityCard;
	}

	/**
	 * @return the notificationNumber
	 */
	public int getNotificationNumber() {
		return notificationNumber;
	}

	/**
	 * @return the historyOfOrders
	 */
	public ArrayList<Order> getHistoryOfOrders() {
		return historyOfOrders;
	}

	/**
	 * @return the contactForOffers
	 */
	public String getContactForOffers() {
		return contactForOffers;
	}

	/**
	 * @param spamAgreement the spamAgreement to set
	 */
	public void setSpamAgreement(boolean spamAgreement) {
		this.spamAgreement = spamAgreement;
	}
	
	
	
	
	
}
