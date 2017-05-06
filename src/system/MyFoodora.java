package system;

import java.io.FileInputStream;
import java.io.Serializable;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.TreeMap;
import java.util.TreeSet;

import user_management.*;
import restaurant.*;

/** The class MyFoodora is the core part of the system. It stores all values that are important for the computation of prices and profit. 
 * All users have access to the same myFoodora instance. 
 * It provides all methods to compute prices, fees and set a target profit.
 * 
 * @author maxspahn
 * @author jeremyaugot
 *
 */
public class MyFoodora implements Serializable{
	private ManagerFactory managerFactory;
	private CustomerFactory customerFactory;
	private CourierFactory courierFactory;
	private RestaurantFactory restaurantFactory;
	
	private TargetPolicy targetPolicy;
	private DeliveryPolicy deliveryPolicy;	

	private double service_fee;
	private double markup_percentage;
	private double delivery_cost;
	private double targetProfit;
	private int targetCommands;
	
	
	private ArrayList<User> listUsers;
	private ArrayList<Courier> listCourier;
	private ArrayList<Order> completeOrders;
	private ArrayList<Restaurant> listRestaurant;
	private ArrayList<Manager> listManager;
	private ArrayList<Customer> listCustomer;
	
	private TreeSet<FullMealSort> deliveredFullMeals;
	private TreeSet<HalfMealSort> deliveredHalfMeals;
	private TreeSet<SingleItemSort> deliverdSingleItems;
		
	/** Constructor. Creates all the lists and sets the following default values: service_fee = 2, delivery_cost = 3, markUp_percentage = 0.1,
	 * targetProfit = 0, targetCommands = 0.
	 * @throws ClassNotFoundException 
	 * 
	 */
	public MyFoodora(String fileName){
		try{
		FileInputStream database = new FileInputStream(fileName);
		ObjectInputStream in = new ObjectInputStream(database);
		this.listCustomer = (ArrayList<Customer>)in.readObject();
		this.listCourier = (ArrayList<Courier>)in.readObject();
		this.listRestaurant = (ArrayList<Restaurant>)in.readObject();
		this.listManager = (ArrayList<Manager>)in.readObject();
		this.listUsers = (ArrayList<User>)in.readObject();
		this.completeOrders	= (ArrayList<Order>)in.readObject();
		this.service_fee = (Double)in.readObject();
		this.delivery_cost = (Double)in.readObject();
		this.markup_percentage = (Double)in.readObject();
		this.targetProfit = (Double)in.readObject();
		this.targetCommands = (Integer)in.readObject();
		this.deliveryPolicy = (DeliveryPolicy)in.readObject();
		this.targetPolicy = (TargetPolicy)in.readObject();
		this.deliverdSingleItems = (TreeSet<SingleItemSort>)in.readObject();
		this.deliveredFullMeals = (TreeSet<FullMealSort>)in.readObject();
		this.deliveredHalfMeals = (TreeSet<HalfMealSort>)in.readObject();
		}
		catch(FileNotFoundException e){
			this.listCustomer = new ArrayList<Customer>();
			this.listCourier = new ArrayList<Courier>();
			this.listRestaurant = new ArrayList<Restaurant>();
			this.listManager = new ArrayList<Manager>();
			this.setListUsers(new ArrayList<User>());
			this.setCompleteOrders(new ArrayList<Order>());
			this.setService_fee(2.00);
			this.setDelivery_cost(3.0);
			this.setMarkup_percentage(0.1);
			this.setTargetProfit(0);
			this.setTargetCommands(0);
			this.setDeliveryPolicy(new FastestDelivery());
			this.setTargetPolicy(new TargetProfit_DeliveryCost());
			this.setDeliverdSingleItems(new TreeSet<SingleItemSort>());
			this.setDeliveredFullMeals(new TreeSet<FullMealSort>());
			this.setDeliveredHalfMeals(new TreeSet<HalfMealSort>());
			
		}
		catch(IOException e){
			e.printStackTrace();
		}
		catch (ClassNotFoundException e){
			e.printStackTrace();
		}

		finally{
			this.courierFactory = new CourierFactory(this);
			this.managerFactory = new ManagerFactory(this);
			this.customerFactory = new CustomerFactory(this);
			this.restaurantFactory = new RestaurantFactory(this);
		}
	}
	
	public MyFoodora(){
		this.listCustomer = new ArrayList<Customer>();
		this.listCourier = new ArrayList<Courier>();
		this.listRestaurant = new ArrayList<Restaurant>();
		this.listManager = new ArrayList<Manager>();
		this.setListUsers(new ArrayList<User>());
		this.setCompleteOrders(new ArrayList<Order>());
		this.setService_fee(2.00);
		this.setDelivery_cost(3.0);
		this.setMarkup_percentage(0.1);
		this.setTargetProfit(0);
		this.setTargetCommands(0);
		this.setDeliveryPolicy(new FastestDelivery());
		this.setTargetPolicy(new TargetProfit_DeliveryCost());
		this.setDeliverdSingleItems(new TreeSet<SingleItemSort>());
		this.setDeliveredFullMeals(new TreeSet<FullMealSort>());
		this.setDeliveredHalfMeals(new TreeSet<HalfMealSort>());
		this.courierFactory = new CourierFactory(this);
		this.managerFactory = new ManagerFactory(this);
		this.customerFactory = new CustomerFactory(this);
		this.restaurantFactory = new RestaurantFactory(this);
	}
	
	/**
	 * Loading some default data.
	 * 
	 */
	public void load(){
		if(this.getListCustomer().size()==0){ //There is no database at this moment
		this.getCourierFactory().load();
		this.getCustomerFactory().load();
		this.getRestaurantFactory().load();
		this.getManagerFactory().load();
		this.loadOrders();}
	}
		
	
	/**Set fees according to the current targetPolicy, you give two values. Their meaning depends on the targetpolicy.
	 * @param value1 Depending on the target policy: if MarkUp : value1 = service_fee, else value1 = markup_percentage
	 * @param value2 Depending on the target policy: if DeliveryCost : value2 = service_fee, else value2 = delivery_cost
	 * @throws TargetCannotBeFullfilled 
	 * @throws OrderNotCompletException
	 */
	public void changeFeesAccordingToPolicy(double value1, double value2) throws TargetCannotBeFullfilled{
		this.targetPolicy.setTargetPolicy(this, value1, value2);
	}
	
	/**
	 * Compute the price for a given price depending on the targetPolicy. 
	 * @param price The price of an order.
	 * @return The profit for the order.
	 */
	public double computeProfit(double price){
		double profit = price * this.getMarkup_percentage() + this.getService_fee()- this.getDelivery_cost();
		profit = ((double) Math.round(profit * 100))/100;
		return profit;
	}
	
	/**Compute total income of all orders completed in the myFoodora system.
	 * @return The total income.
	 */
	public double computeTotalIncome(){
		double income = 0;
		for (Order order : this.getCompleteOrders()) {
			income += order.getPrice();
		}
		return income;
	}		
	
	/**Compute total profit of all orders completed in the myFoodora system.
	 * @return Total profit.
	 */
	public double computeTotalProfit(){
		double profit = 0;
		for (Order order : this.getCompleteOrders()) {
			profit += order.getProfit();
		}
		return profit;
	}
	
	/** Compute the average price per command.
	 * @return Average price per command.
	 */
	public double getAveragePricePerCommand(){
		double price = 0;
		for (Order order : this.getCompleteOrders()) {
			price += order.getPrice();
		}
		return price/this.getCompleteOrders().size();
		
	}
	
	/**Checks if the given order is complete. The profit is calculated and then 
	 * the order is added to the list of complete orders.
	 * @param order The order to be added to the list of complete orders.
	 * @throws OrderNotCompletException
	 */
	public void addOrderToCompleteOrders(Order order) throws OrderNotCompletException{
		if(order.isComplete()){
			this.computeProfitForOrder(order);
			this.completeOrders.add(order);
		}
		else {
			throw new OrderNotCompletException();
		}
	}
	
	/** Computes and assign the profit to a given order, if it is completed.
	 * @param order The order to evaluate.
	 * @throws OrderNotCompletException 
	 */
	public void computeProfitForOrder(Order order) throws OrderNotCompletException{
		if(order.isComplete()){
			double profit = Math.round(this.computeProfit(order.getPrice()) * 100);
			order.setProfit(profit/100);
		}
		else{
			throw new OrderNotCompletException();
		}
	}
	
	
	public HalfMealSort getHalfMealSort(HalfMeal halfMeal){
		for (HalfMealSort halfMealSort : this.getDeliveredHalfMeals()) {
			if(halfMealSort.getHalfMeal().equals(halfMeal)){
				return halfMealSort;
			}
		}
		return null;
	}
	
	/** Search the fullMealSort object which belongs to the given fullMeal in the list.
	 * @param fullMeal Object to be found.
	 * @return Reference to the FullMealSort object. Null if not found.
	 */
	public FullMealSort getFullMealSort(FullMeal fullMeal){
		for (FullMealSort fullMealSort : this.getDeliveredFullMeals()) {
			if(fullMealSort.getFullMeal().equals(fullMeal)){
				return fullMealSort;
			}
		}
		return null;
	}

	/** Search the singleItemSort object which belongs to the given singleItem in the list.
	 * @param singleItem Object to be found.
	 * @return Reference to the SingleItemSort object. Null if not found.
	 */
	public SingleItemSort getSingleItemSort(SingleItem singleItem){
		for (SingleItemSort singleItemSort : this.getDeliverdSingleItems()) {
			if(singleItemSort.getSingleItem().equals(singleItem)){
				return singleItemSort;
			}
		}
		return null;
	}
	
	/** Closing an order consists of four steps: 1. Compute the profit for the order. 2. Add order to complete orders.
	 * 3. Adds the items of the order to the lists of delivered items, a "item"-Sort object is created if it does not exist, else increment count.
	 * 4. Finish the order, function in Order.
	 * @param order The order to be closed.
	 * @throws OrderNotCompletException
	 */
	public void closeOrder(Order order) throws OrderNotCompletException{
		this.computeProfitForOrder(order);
		this.addOrderToCompleteOrders(order);
		
		for (Meal meal : order.getMeals()) {
			if(meal instanceof HalfMeal){
				HalfMealSort halfMealSort = getHalfMealSort((HalfMeal) meal);
				if(halfMealSort != null){
					halfMealSort.setCount(halfMealSort.getCount() + 1);
				}
				else{
					halfMealSort = new HalfMealSort((HalfMeal) meal, 1);
					this.getDeliveredHalfMeals().add(halfMealSort);
					order.getRestaurant().getDeliveredHalfMeals().add(halfMealSort);
				}
				   
			}
			if(meal instanceof FullMeal){
				FullMealSort fullMealSort = getFullMealSort((FullMeal) meal);
				if(fullMealSort != null){
					fullMealSort.setCount(fullMealSort.getCount() + 1);
				}
				else{
					fullMealSort = new FullMealSort((FullMeal) meal, 1);
					this.getDeliveredFullMeals().add(fullMealSort);
					order.getRestaurant().getDeliveredFullMeals().add(fullMealSort);
					
				}
			}
		}
		for (SingleItem singleItem : order.getSingleItems()) {
			SingleItemSort singleItemSort = getSingleItemSort(singleItem);
			if(singleItemSort != null){
				singleItemSort.setCount(singleItemSort.getCount() + 1);
			}
			else{
				singleItemSort = new SingleItemSort(singleItem, 1);
				this.getDeliverdSingleItems().add(singleItemSort);
				order.getRestaurant().getDeliverdSingleItems().add(singleItemSort);
				}
		}
		order.finishOrder();
	}

	
	/** Calculates income, profit, nbOrders, nbCustomers for a given period.
	 * @param day1 day of start of the period.
	 * @param month1 month of start of period.
	 * @param year1 year of start of period.
	 * @param day2 day of end of period.
	 * @param month2 month of end of period.
	 * @param year2 year of end of period.
	 * @return result which is an array of the double that stores the four values: income, profit, nbOrders, nbCustomers.
	 * @throws OrderNotCompletException
	 */
	public double[] getIncomeForPeriod(int day1, int month1, int year1, int day2, int month2, int year2){
		double income = 0;
		double profit = 0;
		double nbOrders = 0;
		HashSet<Customer> setCustomers =new HashSet<>();
		
		Collections.sort(this.completeOrders);
 		
 		int i = 0;
		int size = this.completeOrders.size();
		
		try {
			while(i<size && this.completeOrders.get(i).getCompleteYear() < year1){
				i++;
			}
			while(i<size &&this.completeOrders.get(i).getCompleteYear() == year1 && this.completeOrders.get(i).getCompleteMonth() < month1){
	 			i++;
	 		}
			while(i<size &&this.completeOrders.get(i).getCompleteMonth() == month1 && this.completeOrders.get(i).getCompleteYear() == year1 && this.completeOrders.get(i).getCompleteDay() < day1){
	 			i++;
	 		}
			
			while(i<size &&this.completeOrders.get(i).getCompleteYear() < year2){
				setCustomers.add(completeOrders.get(i).getCustomer());
	 			profit += this.completeOrders.get(i).getProfit();
	 			income += this.completeOrders.get(i).getPrice();
	 			nbOrders ++;
	 			i++;
	 		}
			
			while(i<size &&this.completeOrders.get(i).getCompleteYear() == year2 && this.completeOrders.get(i).getCompleteMonth() < month2){
	 			nbOrders ++;
				setCustomers.add(completeOrders.get(i).getCustomer());
	 			profit += this.completeOrders.get(i).getProfit();
	 			income += this.completeOrders.get(i).getPrice();
	 			i++;
	 		}
			while(i<size &&this.completeOrders.get(i).getCompleteMonth() == month2 && this.completeOrders.get(i).getCompleteYear() == year2 && this.completeOrders.get(i).getCompleteDay() < day2){
	 			nbOrders ++;
				setCustomers.add(completeOrders.get(i).getCustomer());
	 			profit += this.completeOrders.get(i).getProfit();
	 			income += this.completeOrders.get(i).getPrice();
	 			i++;
	 		}
		} catch (OrderNotCompletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
 		
		double[] result = {income, profit, nbOrders, (double) setCustomers.size()};
 		
 		return result;
		
	}
	
		
	/** Sums the profit for a given month.
	 * @param month 
	 * @param year
	 * @return Total profit of all completed orders for the given month.
	 * @throws OrderNotCompletException
	 */
	public double getProfitForMonth(int month, int year) throws OrderNotCompletException {
		double profit = getIncomeForPeriod(1, month, year, 31, month, year)[1];
		return profit;
	}
	
	/** Sums the income for a given month.
	 * @param month
	 * @param year
	 * @return Total income of all completed orders for a given month.
	 * @throws OrderNotCompletException
	 */
	public double getIncomeForMonth(int month, int year) throws OrderNotCompletException {
		double price = getIncomeForPeriod(1, month, year, 31, month, year)[0];
		return price;
	}

	/** Sums the number of orders for a given month.
	 * @param month
	 * @param year
	 * @return Total number of all completed orders for a given month.
	 * @throws OrderNotCompletException
	 */
	public int getNumberOfOrdersForMonth(int month, int year) throws OrderNotCompletException {
		int nbOrders = (int) getIncomeForPeriod(1, month, year, 31, month, year)[2];
		return nbOrders;
	}
	
	/** Compute income, profit, nbOrders, nbCustomers for the last month.
	 * @return The four values in form of an array.
	 * @throws OrderNotCompletException
	 */
	@SuppressWarnings("deprecation")
	public double[] computeValuesLastMonth(){
		Date date = new Date();
		int month = date.getMonth();
		int year = date.getYear() + 1900;
		if(month == 0){
			year--;
			month = 1;
		}
		return getIncomeForPeriod(01, month, year, 31, month, year);
	}
	
	/** Allocates an Courier to a given order. The system checks if the courier accepts the order, if he does not accept, the courier is added to a temporary refused list.
	 * The next closest courier (according to the delivery policy) is then selected. Then the courier is set to unavailable, and the order is completed.
	 * @param order The order for which a courier is searched.
	 * @throws NoCourierFoundToDeliver
	 */
	public void setCourierToOrder(Order order) throws NoCourierFoundToDeliver{
		int indexCourier = -1;
		ArrayList<Integer> refused = new ArrayList<Integer>();
		boolean accepted = false;
		while(!accepted){
			indexCourier = this.getDeliveryPolicy().allocateCourier(order, this.getListCourier(), refused);
			if(indexCourier == -1){
				throw new NoCourierFoundToDeliver();
			}
			accepted = this.getListCourier().get(indexCourier).acceptOrder(order);
			if(!accepted){
				refused.add(indexCourier);
			}
		}
		order.setComplete(true);
		order.setCourier(this.getListCourier().get(indexCourier));
		order.getCourier().setAvailability(false);
		
	}
	
	/** Load two orders, and set their completeMonth to March.
	 * 
	 */
	public void loadOrders() {
		try {
			Order order = new Order(this.getListCustomer().get(6), this.getListRestaurant().get(2));
			order.AddMealToOrder("classic", 1);
			order.AddSingleItemToOrder("soup",1);
			order.getBill();
			Order order2 = new Order(this.getListCustomer().get(1), this.getListRestaurant().get(1));
			order2.AddMealToOrder("basic",1);
			order2.AddSingleItemToOrder("pineapple",1);
			order2.AddSingleItemToOrder("quiche",1);
			order2.getBill();
			this.getListCourier().get(0).setAcceptProbability(1);
			this.setCourierToOrder(order);
			this.closeOrder(order);
			this.setCourierToOrder(order2);
			this.closeOrder(order2);
			order2.setCompleteMonth(4);
			order.setCompleteMonth(4);
			this.getListCourier().get(0).setAcceptProbability(0.8);
			
		} catch (NoCourierFoundToDeliver e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (OrderNotCompletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ItemDoesNotExist e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	/** Sort the list of Restaurants according to their total selling.
	 * 
	 */
	public void sortRestaurant(){
		Collections.sort(this.getListRestaurant(), new Comparator<Restaurant>() {
			@Override
			public int compare(Restaurant rest1 , Restaurant rest2)
			{
				if(rest1.getTotalSelling() >= rest2.getTotalSelling()){
					return -1;
				}
				else{ return 1;}
			}
		});
		
	}
	
	/** Sort the list of Couriers according to their number of delivered orders.
	 * 
	 */
	public void sortCourier(){
		Collections.sort(this.getListCourier(), new Comparator<Courier>() {
			@Override
			public int compare(Courier cour1 , Courier cour2)
			{
				if(cour1.getCountDeliveredOrder() >= cour2.getCountDeliveredOrder()){
					return -1;
				}
				else{ return 1;}
			}
		});
	}
	
	/** Get the user instance by his name.
	 * @param userName Name of the user to be found.
	 * @return User object.
	 * @throws UserNotFoundException
	 */
	public User getUser(String userName) throws UserNotFoundException{
		boolean found = false;
		for (User user : this.getListUsers()) {
			if(user.getUserName().equals(userName)){
				found = true;
				return user;
			}
		}
		if(!found){throw new UserNotFoundException(userName);}
		return null;
	}
	
	/** Method to login a User that had been registered to the system.
	 * @param userName Name of the user.
	 * @param password His password.
	 * @return The logged user, if the entries were correct, else exception.
	 * @throws WrongUserNameOrPassWordException
	 */
	public User loginUser(String userName, String password) throws WrongUserNameOrPassWordException{
		User loggedUser = null;
		try {
			loggedUser = this.getUser(userName);
			
		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		if(loggedUser != null && loggedUser.getPassWord().equals(password)){
			return loggedUser;
		}
		else {
			throw new WrongUserNameOrPassWordException();
		}
	}
	
	/** Put the list of Restaurants in a proper display format.
	 * @return String with all the restaurants.
	 */
	public String listRestaurantsToString(User user){
		String s = "";
		int i = 1;
		for (Restaurant restaurant : this.getListRestaurant()) {
			s += "Nb : " + i + "\t" + restaurant;
			if(user instanceof Manager){
				s += " Total selling : " + restaurant.getTotalSelling();
			}
			s += "\n";
			i++;
		}
		
		return s;
	}
	
	/** Put the list of Courier in a proper display format.
	 * @return
	 */
	public String listCourierToString(User user){
		String s = "";
		for (Courier courier : this.getListCourier()) {
			s += courier ;
			if(user instanceof Manager){
				s += " Delivered Orders : " + courier.getCountDeliveredOrder();
			}
			s += "\n";
		}
		return s;
	}

	/** Put the list of Customers in a proper display format.
	 * @return
	 */
	public String listCustomerToString(){
		String s = "";
		for (Customer customer : this.getListCustomer()) {
			s += customer + "\n";
		}
		return s;
	}
	

	/**
	 * @return the managerFactory
	 */
	public ManagerFactory getManagerFactory() {
		return managerFactory;
	}

	/**
	 * @param managerFactory the managerFactory to set
	 */
	public void setManagerFactory(ManagerFactory managerFactory) {
		this.managerFactory = managerFactory;
	}

	/**
	 * @return the customerFactory
	 */
	public CustomerFactory getCustomerFactory() {
		return customerFactory;
	}

	/**
	 * @param customerFactory the customerFactory to set
	 */
	public void setCustomerFactory(CustomerFactory customerFactory) {
		this.customerFactory = customerFactory;
	}

	/**
	 * @return the courierFactory
	 */
	public CourierFactory getCourierFactory() {
		return courierFactory;
	}

	/**
	 * @param courierFactory the courierFactory to set
	 */
	public void setCourierFactory(CourierFactory courierFactory) {
		this.courierFactory = courierFactory;
	}

	/**
	 * @return the restaurantFactory
	 */
	public RestaurantFactory getRestaurantFactory() {
		return restaurantFactory;
	}

	/**
	 * @param restaurantFactory the restaurantFactory to set
	 */
	public void setRestaurantFactory(RestaurantFactory restaurantFactory) {
		this.restaurantFactory = restaurantFactory;
	}

	/**
	 * @return the service_fee
	 */
	public double getService_fee() {
		return service_fee;
	}

	/**
	 * @param service_fee the service_fee to set
	 */
	public void setService_fee(double service_fee) {
		this.service_fee = service_fee;
	}

	/**
	 * @return the markup_percentage
	 */
	public double getMarkup_percentage() {
		return markup_percentage;
	}

	/**
	 * @param markup_percentage the markup_percentage to set
	 */
	public void setMarkup_percentage(double markup_percentage) {
		this.markup_percentage = markup_percentage;
	}

	/**
	 * @return the delivery_cost
	 */
	public double getDelivery_cost() {
		return delivery_cost;
	}

	/**
	 * @param delivery_cost the delivery_cost to set
	 */
	public void setDelivery_cost(double delivery_cost) {
		this.delivery_cost = delivery_cost;
	}

	/**
	 * @return the targetProfit
	 */
	public double getTargetProfit() {
		return targetProfit;
	}

	/**
	 * @param targetProfit the targetProfit to set
	 * @throws AccessRefusedException 
	 */
	public void setTargetProfit(double targetProfit) {
		this.targetProfit = targetProfit;
	}

	/**
	 * @return the targetCommands
	 */
	public int getTargetCommands() {
		return targetCommands;
	}

	/**
	 * @param targetCommands the targetCommands to set
	 */
	public void setTargetCommands(int targetCommands) {
		this.targetCommands = targetCommands;
	}

	/**
	 * @return the deliveredFullMeals
	 */
	public TreeSet<FullMealSort> getDeliveredFullMeals() {
		return deliveredFullMeals;
	}

	/**
	 * @param deliveredFullMeals the deliveredFullMeals to set
	 */
	public void setDeliveredFullMeals(TreeSet<FullMealSort> deliveredFullMeals) {
		this.deliveredFullMeals = deliveredFullMeals;
	}

	/**
	 * @return the deliveredHalfMeals
	 */
	public TreeSet<HalfMealSort> getDeliveredHalfMeals() {
		return deliveredHalfMeals;
	}

	/**
	 * @param deliveredHalfMeals the deliveredHalfMeals to set
	 */
	public void setDeliveredHalfMeals(TreeSet<HalfMealSort> deliveredHalfMeals) {
		this.deliveredHalfMeals = deliveredHalfMeals;
	}

	/**
	 * @return the deliverdSingleItems
	 */
	public TreeSet<SingleItemSort> getDeliverdSingleItems() {
		return deliverdSingleItems;
	}

	/**
	 * @param deliverdSingleItems the deliverdSingleItems to set
	 */
	public void setDeliverdSingleItems(TreeSet<SingleItemSort> deliverdSingleItems) {
		this.deliverdSingleItems = deliverdSingleItems;
	}

	/**
	 * @return the targetPolicy
	 */
	public TargetPolicy getTargetPolicy() {
		return targetPolicy;
	}

	/**
	 * @param listRestaurant the listRestaurant to set
	 */
	public void setListRestaurant(ArrayList<Restaurant> listRestaurant) {
		this.listRestaurant = listRestaurant;
	}

	/**
	 * @param listManager the listManager to set
	 */
	public void setListManager(ArrayList<Manager> listManager) {
		this.listManager = listManager;
	}

	/**
	 * @param listCustomer the listCustomer to set
	 */
	public void setListCustomer(ArrayList<Customer> listCustomer) {
		this.listCustomer = listCustomer;
	}

	/**
	 * @return the deliveryPolicy
	 */
	public DeliveryPolicy getDeliveryPolicy() {
		return deliveryPolicy;
	}

	/**
	 * @param deliveryPolicy the deliveryPolicy to set
	 */
	public void setDeliveryPolicy(DeliveryPolicy deliveryPolicy) {
		this.deliveryPolicy = deliveryPolicy;
	}

	/**
	 * @return the listUsers
	 */
	public ArrayList<User> getListUsers() {
		return listUsers;
	}

	/**
	 * @param listUsers the listUsers to set
	 */
	public void setListUsers(ArrayList<User> listUsers) {
		this.listUsers = listUsers;
	}

	/**
	 * @return the listCourier
	 */
	public ArrayList<Courier> getListCourier() {
		return listCourier;
	}

	/**
	 * @param listCourier the listCourier to set
	 */
	public void setListCourier(ArrayList<Courier> listCourier) {
		this.listCourier = listCourier;
	}

	/**
	 * @return the completeOrders
	 */
	public ArrayList<Order> getCompleteOrders() {
		return completeOrders;
	}

	/**
	 * @param completeOrders the completeOrders to set
	 */
	public void setCompleteOrders(ArrayList<Order> completeOrders) {
		this.completeOrders = completeOrders;
	}

	/**
	 * @return the listRestaurant
	 */
	public ArrayList<Restaurant> getListRestaurant() {
		return listRestaurant;
	}

	/**
	 * @return the listManager
	 */
	public ArrayList<Manager> getListManager() {
		return listManager;
	}

	/**
	 * @return the listCustomer
	 */
	public ArrayList<Customer> getListCustomer() {
		return listCustomer;
	}

	/**
	 * @param targetPolicy the targetPolicy to set
	 */
	public void setTargetPolicy(TargetPolicy targetPolicy) {
		this.targetPolicy = targetPolicy;
	}
	
}
