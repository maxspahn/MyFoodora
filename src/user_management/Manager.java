package user_management;

import java.util.*;

import system.MyFoodora;
import system.*;

/**
 * @author maxspahn
 *
 */
public class Manager extends PhysicalUser{
	private MyFoodora myFoodora;
	private String role;
	
	public Manager(String name, String userName, String passWord, String phone,
			String email, int[] adress) {
		super(name, userName, passWord, phone, email, adress);
		this.role = "";
	}

	//getters
	public MyFoodora getMyFoodora() {
		return myFoodora;
	}	
	
	public String getRole() {
		return role;
	}
	
	//setters
	public void setMyFoodora(MyFoodora myFoodora){
		this.myFoodora = myFoodora;
	}

	public void setRole(String role){
		this.role = role;
	}
	
	//A manager can add any user
	public void addUser(User user){
		this.myFoodora.getManagerFactory().addUserToLists(user);
	}
	
	//A manager can remove any user, only the CEO can remove other managers
	public void removeUser(String userName) throws UserNotFoundException{
		
		int userIndex = this.findIDUser(userName);
		
		//If the user to remove is a manager
		if (this.myFoodora.getListUsers().get(userIndex) instanceof Manager){
				
			//If the manager who is acting is the CEO and the manager to remove is not himself
			if((this.getRole().equals("CEO"))&&(!this.myFoodora.getListUsers().get(userIndex).equals(this))){
				this.myFoodora.getListUsers().remove(this.myFoodora.getListUsers().get(userIndex));
			}
				
				
			//If the manager wants to remove himself
			else if(this.myFoodora.getListUsers().get(userIndex).equals(this)){
			System.out.println("You cannot remove yourself.");
			}
				
			//If the manager who is acting is not the CEO
			else{
				System.out.println("You are not allowed to remove another manager.");
			}
			}
			
		//If the user to remove is not a manager, then any manager can do it
		else{
			this.myFoodora.getListUsers().remove(this.myFoodora.getListUsers().get(userIndex));			
			}
		}
	
	
	//A manager can activate any user
	public void activateUser(String userName) throws UserNotFoundException{
		User user = this.getUser(userName);
		user.setActivated(true);
		
	}
	
	//A manager can disactivate any user, excepted other managers
	public void disactivate(String userName) throws UserNotFoundException{
		User user = this.getUser(userName);
				
		//If the user to disactivate is a manager
		if (user instanceof Manager){
			System.out.println("You cannot disactivate a manager");
			}
			
		//If the user to disactivate is not a manager
		else{
			user.setActivated(false);
			}
	}
	
	public String toString(){	
		int x = this.getAdress()[0];
		int y = this.getAdress()[1];
		return "ID:"+this.getID()+"/Name:"+this.getName()+"/Surname:"+this.getSurname()+"/Username:"+this.getUserName()
			+"/Password:"+this.getPassWord()+"/Email:"+this.getEmail()+"/Phone:"+this.getPhone()
			+"/Adress:{"+x+";"+y+"}"+"/Role:"+this.getRole();
	}
	
	
	//Method to find a user by searching his userName
	private int findIDUser(String userName) throws UserNotFoundException{
		int index = -1;
		ArrayList<User> listUser = this.getMyFoodora().getListUsers();
		for (int i = 0; i < listUser.size(); i++) {
			if (listUser.get(i).getUserName().equals(userName)){
				index = i;
			}
		}
		if (index == -1){ //The user has not been found
			throw new UserNotFoundException(userName);
		}			
		return index;  //The user has been found and the index is returned
	}
	
	public User getUser(String userName) throws UserNotFoundException{
		return this.myFoodora.getListUsers().get(this.findIDUser(userName));
	}
	
	/** Sets the targetProfit.
	 * @param targetProfit The value that is targeted for the next month.
	 */
	public void setTargetProfit(double targetProfit){
		this.myFoodora.setTargetProfit(targetProfit);
	}
	
	/** Sets the number of commands expected for the target period.
	 * @param targetCommands Number of commands.
	 */
	public void setTargetCommands(int targetCommands){
		this.myFoodora.setTargetCommands(targetCommands);
	}

	//set the percentage-fee
	public void setServiceFee(double serviceFee){
		this.myFoodora.setService_fee(serviceFee);
	}
	//set the markup percentage
	public void setMarkup(double markup){
		this.myFoodora.setMarkup_percentage(markup);
	}
	//set the delivery cost
	public void setDeliveryCost(double deliveryCost){
		this.myFoodora.setDelivery_cost(deliveryCost);
	}
	
	//set the percentage-fee according the target policy
	public void setServiceFeeAccordingTargetPolicy(double value1, double value2){
		this.myFoodora.setTargetPolicy(new TargetProfit_ServiceFee());
		this.myFoodora.changeFeesAccordingToPolicy(value1, value2);
	}
	
	//set the markup percentage according the target policy
	public void setMarkupAccordingTargetPolicy(double value1, double value2){
		this.myFoodora.setTargetPolicy(new TargetProfit_Markup());
		this.myFoodora.changeFeesAccordingToPolicy(value1, value2);
	}
	
	//set the delivery cost according the target policy
	public void setDeliveryCostAccordingTargetPolicy(double value1, double value2){
		this.myFoodora.setTargetPolicy(new TargetProfit_DeliveryCost());		
		this.myFoodora.changeFeesAccordingToPolicy(value1, value2);
		}
	
	//determine which the most selling restaurant
	public Restaurant mostSellingRestaurant(){
		double maxSelling = this.myFoodora.getListRestaurant().get(0).getTotalSelling();
		Restaurant mostSellingRestaurant = this.myFoodora.getListRestaurant().get(0);
		for(Restaurant rest:this.myFoodora.getListRestaurant()){
			if(rest.getTotalSelling()>maxSelling){
				maxSelling = rest.getTotalSelling();
				mostSellingRestaurant = rest;
			}
		}
		System.out.println("The most selling restaurant is "+mostSellingRestaurant.getName());
		return mostSellingRestaurant;
	}
	
	//determine which the least selling restaurant
	public Restaurant leastSellingRestaurant(){
		Restaurant leastSellingRestaurant = this.myFoodora.getListRestaurant().get(0);
		double minSelling = leastSellingRestaurant.getTotalSelling();
		for(Restaurant rest:this.myFoodora.getListRestaurant()){
			if(rest.getTotalSelling()<minSelling){
				minSelling = rest.getTotalSelling();
				leastSellingRestaurant = rest;
			}
		}
		System.out.println("The least selling restaurant is "+leastSellingRestaurant.getName());
		return leastSellingRestaurant;
		}
	
	//determine the most active courier
	public Courier mostActiveCourier(){
		Courier mostActiveCourier = this.myFoodora.getListCourier().get(0);
		int maxDeliveredOrders = mostActiveCourier.getCountDeliveredOrder();
		for(Courier cour:this.myFoodora.getListCourier()){
			if(cour.getCountDeliveredOrder()>maxDeliveredOrders){
				maxDeliveredOrders = cour.getCountDeliveredOrder();
				mostActiveCourier = cour;
			}
		}
		System.out.println("The most active courier is "+mostActiveCourier.getName()+" "+mostActiveCourier.getSurname());
		return mostActiveCourier;
		
	}
	
	//determine the most active courier
	public Courier leastActiveCourier(){
		Courier leastActiveCourier = this.myFoodora.getListCourier().get(0);
		int minDeliveredOrders = leastActiveCourier.getCountDeliveredOrder();
		for(Courier cour:this.myFoodora.getListCourier()){
			if(cour.getCountDeliveredOrder()<minDeliveredOrders){
				minDeliveredOrders = cour.getCountDeliveredOrder();
				leastActiveCourier = cour;
			}
		}
		System.out.println("The least active courier is "+leastActiveCourier.getName()+" "+leastActiveCourier.getSurname());
		return leastActiveCourier;
			
	}
	
	//set the delivery policy
	public void setDeliveryPolicy(String deliveryName){
		if(deliveryName.equalsIgnoreCase("fastest delivery")||deliveryName.equalsIgnoreCase("fastest")||deliveryName.equalsIgnoreCase("fastestDelivery")){
			this.myFoodora.setDeliveryPolicy(new FastestDelivery());
		}
		else if(deliveryName.equalsIgnoreCase("fair")||deliveryName.equalsIgnoreCase("fair-occupation delivery")||deliveryName.equalsIgnoreCase("fair occupation delivery")||deliveryName.equalsIgnoreCase("fairoccupationdelivery")){
			this.myFoodora.setDeliveryPolicy(new FastestDelivery());
		}
		else{
			System.out.println("This delivery policy does not exist.");
		}
	}
	
}
