package system;

import java.util.ArrayList;
import java.util.Date;

import user_management.*;

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
	
	
	private ArrayList<User> listUsers;
	private ArrayList<Courier> listCourier;
	private ArrayList<Order> completeOrders;
	private ArrayList<Restaurant> listRestaurant;
	private ArrayList<Manager> listManager;
	private ArrayList<Customer> listCustomer;

	
	private double incomeLastMonth;
	private double totalPriceLastMonth;
	private int totalNumberOfOrdersLastMonth;
	
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
	
	public void changeFeesAccordingToPolicy(double value1, double value2){
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
	public double computeProfit(double price){
		double profit = price * this.getMarkup_percentage() + this.getService_fee()- this.getDelivery_cost();
		return profit;
	}


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
	
	public void closeOrder(Order order) throws OrderNotCompletException{
		this.computeProfitForOrder(order);
		this.addOrderToCompleteOrders(order);
		order.finishOrder();
	}
	
	public int getIndexOfFirstCommandInMonth(int month, int year) throws NoOrderInMonth, OrderNotCompletException{
		int index = -1;
		for (int i = 0; i < this.getCompleteOrders().size(); i++) {
			if(this.getCompleteOrders().get(i).getCompleteYear() == year && this.getCompleteOrders().get(i).getCompleteMonth() == month){
				index = i;
				break;
			}
		}
		if(index !=-1){
			return index;
		}
		else{
			throw new NoOrderInMonth(month, year);
		}
	}
	
	public int getIndexOfLastCommandInMonth(int month, int year) throws OrderNotCompletException, NoOrderInMonth{
		int index = -1;
		if(year == this.getCompleteOrders().get(this.getCompleteOrders().size() -1).getCompleteYear() && month == this.getCompleteOrders().get(this.getCompleteOrders().size() -1).getCompleteMonth()){
			index = this.getCompleteOrders().size()-1;
		}
		else{
			if(month == 12){
				month = 0;
				year = year ++;
			}
			index = this.getIndexOfFirstCommandInMonth(month +1, year) -1;
		}
		return index;
	}
		
	public double computeTotalIncomeForMonth(int month, int year) {
		int indexFirstOrder;
		int indexLastOrder;
		double income = 0;
		try {
			indexFirstOrder = getIndexOfFirstCommandInMonth(month, year);
			indexLastOrder= getIndexOfLastCommandInMonth(month, year);
			for (int i = indexFirstOrder; i <= indexLastOrder; i++) {
				income += this.getCompleteOrders().get(i).getProfit();
			}
		} catch (NoOrderInMonth | OrderNotCompletException e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}
		return income;
	}
	
	public double computeTotalPriceForMonth(int month, int year) {
		int indexFirstOrder;
		int indexLastOrder;
		double price = 0;
		try {
			indexFirstOrder = getIndexOfFirstCommandInMonth(month, year);
			indexLastOrder= getIndexOfLastCommandInMonth(month, year);
			for (int i = indexFirstOrder; i <= indexLastOrder; i++) {
				price += this.getCompleteOrders().get(i).getPrice();
			}
		} catch (NoOrderInMonth | OrderNotCompletException e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}
		return price;
	}
	
	public int computeTotalNumberOfCommandsForMonth(int month, int year) {
		int indexFirstOrder;
		int indexLastOrder;
		int number = 0;
		double price = 0;
		try {
			indexFirstOrder = getIndexOfFirstCommandInMonth(month, year);
			indexLastOrder= getIndexOfLastCommandInMonth(month, year);
			number = indexLastOrder - indexFirstOrder + 1;
		} catch (NoOrderInMonth | OrderNotCompletException e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}
		return number;
	}
	
	public double computeTotalIncomeLastMonth(){
		double income = 0;
		Date date = new Date();
		int month = date.getMonth();
		int year = date.getYear() + 1900;
		if(month == 0){
			year--;
			month = 1;
		}
		income = computeTotalIncomeForMonth(month, year);
		this.setTotalPriceLastMonth(computeTotalPriceForMonth(month, year));
		this.setIncomeLastMonth(income);
		this.setTotalNumberOfOrdersLastMonth(this.computeTotalNumberOfCommandsForMonth(month, year));
		return income;
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
		Order order = new Order(this.getListCustomer().get(6), this.getListRestaurant().get(2));
		order.AddMealToOrder("classic");
		order.AddSingleItemToOrder("soup");
		order.getBill();
		Order order2 = new Order(this.getListCustomer().get(1), this.getListRestaurant().get(1));
		order2.AddMealToOrder("classic");
		order2.AddSingleItemToOrder("pineapple");
		order2.AddSingleItemToOrder("quiche");
		order2.getBill();
		try {
			this.setCourierToOrder(order);
			this.setCourierToOrder(order2);
			this.closeOrder(order);
			this.closeOrder(order2);
			order2.setCompleteMonth(2);
			order.setCompleteMonth(2);
			
		} catch (NoCourierFoundToDeliver e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (OrderNotCompletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public double getIncomeLastMonth() {
		return incomeLastMonth;
	}

	public void setIncomeLastMonth(double incomeLastMonth) {
		this.incomeLastMonth = incomeLastMonth;
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

	public double getTotalPriceLastMonth() {
		return totalPriceLastMonth;
	}

	public void setTotalPriceLastMonth(double totalPriceLastMonth) {
		this.totalPriceLastMonth = totalPriceLastMonth;
	}

	public int getTotalNumberOfOrdersLastMonth() {
		return totalNumberOfOrdersLastMonth;
	}

	public void setTotalNumberOfOrdersLastMonth(int totalNumberOfOrdersLastMonth) {
		this.totalNumberOfOrdersLastMonth = totalNumberOfOrdersLastMonth;
	}
	
	

}
