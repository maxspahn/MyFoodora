package system;

import java.util.ArrayList;
import java.util.Date;

import restaurant.*;
import user_management.*;

public class Order {
	
	private Customer customer;
	private Courier courier;
	private Restaurant restaurant;
	private ArrayList<SingleItem> singleItems;
	private ArrayList<Meal> meals;
	private boolean complete;
	private Date completeDate;
	private FidelityCard fidelityCard;
	private double price;
	private double profit;

	public Order(Customer customer, Restaurant restaurant) {
		this.setCustomer(customer);
		this.setMeals(new ArrayList<Meal>());
		this.setSingleItems(new ArrayList<SingleItem>());
		this.setComplete(false);
		this.setFidelityCard(customer.getFidelityCard());
		this.setRestaurant(restaurant);
	}
	
	public void AddSingleItemToOrder(String singleItemName){
		this.getSingleItems().add(this.getRestaurant().getMenu().getSingleItem(singleItemName));
	}
	
	public void AddMealToOrder(String mealName){
		this.getMeals().add(this.getRestaurant().getMenu().getMeal(mealName));
	}
	
	public void getBill() {
		this.setPrice(this.getFidelityCard().computePrice(this));
	}
	
	public void finishOrder(MyFoodora myfoodera) throws OrderNotCompletException{
		if(this.complete){
			//this.customer.addOrder(this);
			myfoodera.addOrderToCompleteOrders(this);
			this.courier.setCountDeliveredOrder(this.courier.getCountDeliveredOrder() + 1);
			this.courier.setAvailability(true);
			this.completeDate = new Date();
		}
		else{
			throw new OrderNotCompletException();
		}
	}
	
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Courier getCourier() {
		return courier;
	}

	public void setCourier(Courier courier) {
		this.courier = courier;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public ArrayList<SingleItem> getSingleItems() {
		return singleItems;
	}

	public void setSingleItems(ArrayList<SingleItem> singleItems) {
		this.singleItems = singleItems;
	}

	public ArrayList<Meal> getMeals() {
		return meals;
	}

	public void setMeals(ArrayList<Meal> meals) {
		this.meals = meals;
	}

	public boolean isComplete() {
		return complete;
	}

	public void setComplete(boolean complete) {
		this.complete = complete;
	}

	public Date getCompleteDate() {
		return completeDate;
	}

	public FidelityCard getFidelityCard() {
		return fidelityCard;
	}

	public void setFidelityCard(FidelityCard fidelityCard) {
		this.fidelityCard = fidelityCard;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getProfit() {
		return profit;
	}

	public void setProfit(double profit) {
		this.profit = profit;
	}

	public int[] getYearMonthOfOrder() throws OrderNotCompletException{
		if(this.isComplete()){
			int [] yearMonth = {this.getCompleteDate().getYear(), this.getCompleteDate().getMonth()};
			return yearMonth;
		}
		else{
			throw new OrderNotCompletException();
		}
	}
	
	public String toString(){
		String s = new String();
		s += "Order from Customer : " + this.getCustomer().getUserName() + " in restaurant : " + this.getRestaurant().getName();
		if(this.isComplete()){
			s += " is completed";
		}
		return s;
	}
	
	

}
