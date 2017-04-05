package user_management;

import java.util.*;

import system.*;

/** Manager is a physical user of the system, it extends PhysicalUser. Manager has access to most of the functions of MyFoodora.
 * A manager has a role, like "CEO". 
 * 
 * @author maxspahn
 * @author jeremyaugot
 */
public class Manager extends PhysicalUser{
	private MyFoodora myFoodora;
	private String role;
	
	/**Constructor. Creates a manager, this function should not be used seperately, look Factory. 
	 * @param name
	 * @param userName
	 * @param passWord
	 * @param phone
	 * @param email
	 * @param adress
	 */
	public Manager(String name, String userName, String passWord, String phone,
			String email, int[] adress) {
		super(name, userName, passWord, phone, email, adress);
		this.role = "";
	}

	/**
	 * @return myFoodora.
	 */
	public MyFoodora getMyFoodora() {
		return myFoodora;
	}	
	
	public String getRole() {
		return role;
	}
	

	/**
	 * @param myFoodora MyFoodora.
	 */
	public void setMyFoodora(MyFoodora myFoodora){
		this.myFoodora = myFoodora;
	}

	/**
	 * @param role String which contains the role of the manager.
	 */
	public void setRole(String role){
		this.role = role;
	}
	
	/**Add a user to the lists of MyFoodora.
	 * @param user User, the user to be added in the lists.
	 */
	public void addUser(User user){
		this.myFoodora.getManagerFactory().addUserToLists(user);
	}
	
	/**Removes a user from the system. A manager can remove any user, only the CEO can remove other managers.
	 * 
	 * @param userName The userName of the user to be removed.
	 * @throws UserNotFoundException
	 */
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
	
	
	/**A manager can activate any user, sets the users status to active.
	 * 
	 * @param userName Name of the user
	 * @throws UserNotFoundException
	 */
	public void activateUser(String userName) throws UserNotFoundException{
		User user = this.getUser(userName);
		user.setActivated(true);
		
	}
	
	/**A manager can disactivate any user, excepted other managers
	 * 
	 * @param userName Name of the user to be disactivated.
	 * @throws UserNotFoundException
	 */
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
	
	
	/**Method to find a user by searching his userName. Throws exception if user is not in the system.
	 * 
	 * @param userName Name of the user stored in a string.
	 * @return index of the user that is searched.
	 * @throws UserNotFoundException
	 */
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
	
	/** Getter for user by the username. Throws exception if user not in system.
	 * @param userName Name of the user.
	 * @return User object of the searched user.
	 * @throws UserNotFoundException
	 */
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


	/**Set the service fee percentage.
	 * @param serviceFee Double service-fee percentage.
	 */
	public void setServiceFee(double serviceFee){
		this.myFoodora.setService_fee(serviceFee);
	}

	/**Set the markup percentage.
	 * @param markup Double markup percentage.
	 */
	public void setMarkup(double markup){
		this.myFoodora.setMarkup_percentage(markup);
	}

	/**Set the delivery cost.
	 * @param deliveryCost Double delivery cost.
	 */
	public void setDeliveryCost(double deliveryCost){
		this.myFoodora.setDelivery_cost(deliveryCost);
	}

	/** Sets the fees, service fees are computed according to the targetProfit.
	 * @param markup_percentage
	 * @param delivery_cost
	 */
	public void setServiceFeeAccordingTargetPolicy(double markup_percentage, double delivery_cost){
		this.myFoodora.setTargetPolicy(new TargetProfit_ServiceFee());
		this.myFoodora.changeFeesAccordingToPolicy(markup_percentage, delivery_cost);
	}
	
	/** Sets the fees, markup_percentage is computed according to the targetProfit.
	 * @param service_fee
	 * @param delivery_cost
	 */
	public void setMarkupAccordingTargetPolicy(double service_fee, double delivery_cost){
		this.myFoodora.setTargetPolicy(new TargetProfit_Markup());
		this.myFoodora.changeFeesAccordingToPolicy(service_fee, delivery_cost);
	}
	
	/** Sets the fees, delivery_cost is computed according to the targetProfit.
	 * @param markup_percentage
	 * @param service_fee
	 */
	public void setDeliveryCostAccordingTargetPolicy(double markup_percentage, double service_fee){
		this.myFoodora.setTargetPolicy(new TargetProfit_DeliveryCost());		
		this.myFoodora.changeFeesAccordingToPolicy(markup_percentage, service_fee);
		}
	
	/** Determine which the most selling restaurant according to the income.
	 * 
	 * @return Most Selling restaurant.
	 */
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
	
	/** Determine which the least selling restaurant according to the income.
	 * 
	 * @return Least selling restaurant.
	 */
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
	
	/** Determine the most active courier according to the number of delivered orders.
	 * 
	 * @return Most active Courier.
	 */
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
	
	/** Determine the most active courier according to the number of delivered orders.
	 * 
	 * @return Least active Courier.
	 */
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
	
	/** Set the delivery policy. Prints an error is delivery policy does not exist.
	 * 
	 * @param deliveryName Name of the policy as a String.
	 */
	public void setDeliveryPolicy(String deliveryName){
		if(deliveryName.equalsIgnoreCase("fastest delivery")||deliveryName.equalsIgnoreCase("fastest")||deliveryName.equalsIgnoreCase("fastestDelivery")){
			this.myFoodora.setDeliveryPolicy(new FastestDelivery());
		}
		else if(deliveryName.equalsIgnoreCase("fair")||deliveryName.equalsIgnoreCase("fair-occupation delivery")||deliveryName.equalsIgnoreCase("fair occupation delivery")||deliveryName.equalsIgnoreCase("fairoccupationdelivery") || deliveryName.equalsIgnoreCase("fairDelivery")){
			this.myFoodora.setDeliveryPolicy(new FairDelivery());
		}
		else{
			System.out.println("This delivery policy does not exist.");
		}
	}
	
	/** Compute total income for a given period. Takes as input the dates of starting and ending of the period.
	 * 
	 * @param day1
	 * @param month1
	 * @param year1
	 * @param day2
	 * @param month2
	 * @param year2
	 * @return Double Array storing the income at index 0 and the profit at index 1.
	 * @throws OrderNotCompletException
	 */
	public double[] computeTotalIncomeAndProfitOverPeriod(int day1, int month1, int year1, int day2, int month2, int year2) throws OrderNotCompletException{
		double[] result = this.myFoodora.getIncomeForPeriod(day1, month1, year1, day2, month2, year2);
		double[] returnedResult = {result[0], result[1]};
		return returnedResult;
	}
	 
	/** Computes the average income per customer for a given time-period.
	 * @param day1
	 * @param month1
	 * @param year1
	 * @param day2
	 * @param month2
	 * @param year2
	 * @return Average for the period, 0 if no command was done in that period.
	 * @throws OrderNotCompletException
	 */
	public double computeIncomePerCustomerOverPeriod(int day1, int month1, int year1, int day2, int month2, int year2) throws OrderNotCompletException{
		double[] result = this.myFoodora.getIncomeForPeriod(day1, month1, year1, day2, month2, year2);
		if (result[3]!=0){
			double incomePerCustomer = result[0]/result[3];
			return incomePerCustomer;}
		else{
			return 0;
		}
	}
	
}
