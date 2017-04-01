package system;

import java.util.ArrayList;

import java.util.Date;
import java.util.TreeMap;
import java.util.TreeSet;

import user_management.*;
import restaurant.*;

public class MyFoodora {
	
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
		
	public MyFoodora(){
		this.setListUsers(new ArrayList<User>());
		this.listCourier = new ArrayList<Courier>();
		this.listRestaurant = new ArrayList<Restaurant>();
		this.listManager = new ArrayList<Manager>();
		this.listCustomer = new ArrayList<Customer>();
		this.setDeliveryPolicy(new FastestDelivery());
		this.setCompleteOrders(new ArrayList<Order>());
		this.setService_fee(2.00);
		this.setDelivery_cost(3.0);
		this.setMarkup_percentage(0.1);
		this.setTargetPolicy(new TargetProfit_DeliveryCost());
		this.courierFactory = new CourierFactory(this);
		this.managerFactory = new ManagerFactory(this);
		this.customerFactory = new CustomerFactory(this);
		this.restaurantFactory = new RestaurantFactory(this);
		this.setTargetProfit(0);
		this.setTargetCommands(0);
		this.setDeliverdSingleItems(new TreeSet<SingleItemSort>());
		this.setDeliveredFullMeals(new TreeSet<FullMealSort>());
		this.setDeliveredHalfMeals(new TreeSet<HalfMealSort>());
	}
	
	public void load(){
		this.getCourierFactory().load();
		this.getCustomerFactory().load();
		this.getRestaurantFactory().load();
		this.getManagerFactory().load();
		this.loadOrders();
	}
		
	public TargetPolicy getTargetPolicy() {
		return targetPolicy;
	}
	
	public void changeFeesAccordingToPolicy(double value1, double value2) throws OrderNotCompletException{
		this.targetPolicy.setTargetPolicy(this, value1, value2);
	}

	public void setTargetPolicy(TargetPolicy targetPolicy) {
		this.targetPolicy = targetPolicy;
	}
	
	public DeliveryPolicy getDeliveryPolicy() {
		return deliveryPolicy;
	}

	public void setDeliveryPolicy(DeliveryPolicy deliveryPolicy) {
		this.deliveryPolicy = deliveryPolicy;
	}

	public ArrayList<User> getListUsers() {
		return listUsers;
	}

	public void setListUsers(ArrayList<User> listUsers) {
		this.listUsers = listUsers;
	}

	public ArrayList<Courier> getListCourier() {
		return listCourier;
	}

	public void setListCourier(ArrayList<Courier> listCourier) {
		this.listCourier = listCourier;
	}


	public ArrayList<Order> getCompleteOrders() {
		return completeOrders;
	}

	public void setCompleteOrders(ArrayList<Order> completeOrders) {
		this.completeOrders = completeOrders;
	}
	
	public ArrayList<Restaurant> getListRestaurant() {
		return listRestaurant;
	}

	public ArrayList<Manager> getListManager() {
		return listManager;
	}

	public ArrayList<Customer> getListCustomer() {
		return listCustomer;
	}
	
	
	/**
	 * Compute the price for a given price depending on the targetPolicy. 
	 * @param price The price of an order.
	 * @return The profit for the order.
	 */
	public double computeProfit(double price){
		double profit = price * this.getMarkup_percentage() + this.getService_fee()- this.getDelivery_cost();
		return profit;
	}
	
	public double computeTotalIncome(){
		double income = 0;
		for (Order order : this.getCompleteOrders()) {
			income += order.getProfit();
		}
		return income;
	}		
	
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
	
	public void computeProfitForOrder(Order order) throws OrderNotCompletException{
		if(order.isComplete()){
			double profit = Math.round(this.computeProfit(order.getPrice()) * 100);
			order.setProfit(profit/100);
		}
		else{
			throw new OrderNotCompletException();
		}
	}
	
	public int getNbOfHalfMeals(HalfMeal halfMeal){
		int nb = 0;
		for (HalfMealSort halfMealSort : this.getDeliveredHalfMeals()) {
			if(halfMealSort.getHalfMeal().equals(halfMeal)){
				nb = halfMealSort.getCount();
			}
		}
		return nb;
	}
	
	public int getNbOfSingleItems(SingleItem singleItem){
		int nb = 0;
		for (SingleItemSort singleItemSort : this.getDeliverdSingleItems()) {
			if(singleItemSort.getSingleItem().equals(singleItem)){
				nb = singleItemSort.getCount();
			}
		}
		return nb;
	}
	
	public int getNbOfFullMeals(FullMeal fullMeal){
		int nb = 0;
		for(FullMealSort fullMealSort : this.getDeliveredFullMeals()){
			if(fullMealSort.getFullMeal().equals(fullMeal)){
				nb = fullMealSort.getCount();
			}
		}
		return nb;
	}
	
	public HalfMealSort getHalfMealSort(HalfMeal halfMeal){
		for (HalfMealSort halfMealSort : this.getDeliveredHalfMeals()) {
			if(halfMealSort.getHalfMeal().equals(halfMeal)){
				return halfMealSort;
			}
		}
		return null;
	}
	
	public FullMealSort getFullMealSort(FullMeal fullMeal){
		for (FullMealSort fullMealSort : this.getDeliveredFullMeals()) {
			if(fullMealSort.getFullMeal().equals(fullMeal)){
				return fullMealSort;
			}
		}
		return null;
	}
	
	public SingleItemSort getSingleItemSort(SingleItem singleItem){
		for (SingleItemSort singleItemSort : this.getDeliverdSingleItems()) {
			if(singleItemSort.getSingleItem().equals(singleItem)){
				return singleItemSort;
			}
		}
		return null;
	}
	
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

	
	public double[] getIncomeForPeriod(int day1, int month1, int year1, int day2, int month2, int year2) throws OrderNotCompletException{
		double income = 0;
		double profit = 0;
		double nbOrders = 0;
		
		System.out.println("dates " + day1 + "/" + month1 + "/" + year1 + " through " + day2 + "/" + month2 + "/" + year2);
		int i = 0;
		while(this.completeOrders.get(i).getCompleteYear() < year1){
			i++;
		}
		while(this.completeOrders.get(i).getCompleteYear() == year1 && this.completeOrders.get(i).getCompleteMonth() < month1){
			i++;
		}
		while(this.completeOrders.get(i).getCompleteMonth() == month1 && this.completeOrders.get(i).getCompleteYear() == year1 && this.completeOrders.get(i).getCompleteDay() < day1){
			i++;
		}
		while(this.completeOrders.get(i).getCompleteYear() < year2){
			profit += this.completeOrders.get(i).getProfit();
			income += this.completeOrders.get(i).getPrice();
			nbOrders ++;
			i++;
		}
		while(this.completeOrders.get(i).getCompleteYear() == year2 && this.completeOrders.get(i).getCompleteMonth() < month2){
			nbOrders ++;
			profit += this.completeOrders.get(i).getProfit();
			income += this.completeOrders.get(i).getPrice();
			i++;
		}
		while(this.completeOrders.get(i).getCompleteMonth() == month2 && this.completeOrders.get(i).getCompleteYear() == year2 && this.completeOrders.get(i).getCompleteDay() < day2){
			nbOrders ++;
			profit += this.completeOrders.get(i).getProfit();
			income += this.completeOrders.get(i).getPrice();
			i++;
		}
		
		double[] result = {income, profit, nbOrders};
		
		return result;
		
	}
	
		
	public double getProfitForMonth(int month, int year) throws OrderNotCompletException {
		double profit = getIncomeForPeriod(1, month, year, 31, month, year)[1];
		return profit;
	}
	
	public double getIncomeForMonth(int month, int year) throws OrderNotCompletException {
		double price = getIncomeForPeriod(1, month, year, 31, month, year)[0];
		return price;
	}
	
	public int getNumberOfOrdersForMonth(int month, int year) throws OrderNotCompletException {
		int nbOrders = (int) getIncomeForPeriod(1, month, year, 31, month, year)[2];
		return nbOrders;
	}
	
	@SuppressWarnings("deprecation")
	public double[] computeValuesLastMonth() throws OrderNotCompletException{
		Date date = new Date();
		int month = date.getMonth();
		int year = date.getYear() + 1900;
		if(month == 0){
			year--;
			month = 1;
		}
		return getIncomeForPeriod(01, month, year, 31, month, year);
	}
	
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
	
	public void loadOrders() {
		try {
			Order order = new Order(this.getListCustomer().get(6), this.getListRestaurant().get(2));
			order.AddMealToOrder("classic");
			order.AddSingleItemToOrder("soup");
			order.getBill();
			Order order2 = new Order(this.getListCustomer().get(1), this.getListRestaurant().get(1));
			order2.AddMealToOrder("classic");
			order2.AddSingleItemToOrder("pineapple");
			order2.AddSingleItemToOrder("quiche");
			order2.getBill();
			this.setCourierToOrder(order);
			this.setCourierToOrder(order2);
			System.out.println("hello line 441");
			this.closeOrder(order);
			this.closeOrder(order2);
			System.out.println("hello line 111");
			order2.setCompleteMonth(2);
			order.setCompleteMonth(2);
			
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

	public double getService_fee() {
		return service_fee;
	}

	public void setService_fee(double service_fee) {
		this.service_fee = service_fee;
	}

	public double getMarkup_percentage() {
		return markup_percentage;
	}

	public void setMarkup_percentage(double markup_percentage) {
		this.markup_percentage = markup_percentage;
	}

	public double getDelivery_cost() {
		return delivery_cost;
	}

	public void setDelivery_cost(double delivery_cost) {
		this.delivery_cost = delivery_cost;
	}

	public ManagerFactory getManagerFactory() {
		return managerFactory;
	}

	public CustomerFactory getCustomerFactory() {
		return customerFactory;
	}

	public CourierFactory getCourierFactory() {
		return courierFactory;
	}

	public RestaurantFactory getRestaurantFactory() {
		return restaurantFactory;
	}

	public double getTargetProfit() {
		return targetProfit;
	}

	public void setTargetProfit(double targetProfit) {
		this.targetProfit = targetProfit;
	}

	public int getTargetCommands() {
		return targetCommands;
	}

	public void setTargetCommands(int targetCommands) {
		this.targetCommands = targetCommands;
	}

	protected TreeSet<FullMealSort> getDeliveredFullMeals() {
		return deliveredFullMeals;
	}

	protected void setDeliveredFullMeals(TreeSet<FullMealSort> deliveredFullMeals) {
		this.deliveredFullMeals = deliveredFullMeals;
	}

	protected TreeSet<HalfMealSort> getDeliveredHalfMeals() {
		return deliveredHalfMeals;
	}

	protected void setDeliveredHalfMeals(TreeSet<HalfMealSort> deliveredHalfMeals) {
		this.deliveredHalfMeals = deliveredHalfMeals;
	}

	/**
	 * @return
	 */
	protected TreeSet<SingleItemSort> getDeliverdSingleItems() {
		return deliverdSingleItems;
	}

	protected void setDeliverdSingleItems(
			TreeSet<SingleItemSort> deliverdSingleItems) {
		this.deliverdSingleItems = deliverdSingleItems;
	}



	
	

}
