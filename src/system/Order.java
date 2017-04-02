package system;

import java.util.ArrayList;
import java.util.Date;

import restaurant.*;
import user_management.*;

/** All information of an order are stored here. 
 * 
 * @author maxspahn
 * @author jeremyaugot
 *
 */
public class Order {
	
	private Customer customer;
	private Courier courier;
	private Restaurant restaurant;
	private ArrayList<SingleItem> singleItems;
	private ArrayList<Meal> meals;
	private boolean complete;
	private int completeMonth;
	private int completeYear;
	private int completeDay;
	private double price;
	private double profit;
	private int ID;
	private static int counter;

	/**Constructor. When creating an order, it is associated to a customer and a restaurant.
	 * @param customer
	 * @param restaurant
	 */
	public Order(Customer customer, Restaurant restaurant) {
		this.setCustomer(customer);
		this.setMeals(new ArrayList<Meal>());
		this.setSingleItems(new ArrayList<SingleItem>());
		this.setComplete(false);
		this.setRestaurant(restaurant);
		this.ID = Order.counter+1;
		Order.counter++;
	}
	
	/** Adds an singleItem to the order.
	 * @param singleItemName The name of the item.
	 */
	public void AddSingleItemToOrder(String singleItemName){
		this.getSingleItems().add(this.getRestaurant().getMenu().getSingleItem(singleItemName));
	}
	
	/**Adds an meal to the order.
	 * @param mealName Name of the meal.
	 * @throws ItemDoesNotExist
	 */
	public void AddMealToOrder(String mealName) throws ItemDoesNotExist{
		this.getMeals().add(this.getRestaurant().getMenu().getMeal(mealName));
	}
	
	/** Gets the price of the order.
	 * @return The price.
	 */
	public double getBill() {
		this.setPrice(this.getCustomer().getFidelityCard().computePrice(this));
		return this.getPrice();
	}
	
	/** Finishing the order. If the order is not completed, no courier allocated, it throws an exception.
	 * The completion date is set as the current date. The courier is set available. And the price of the order is added
	 * to the sum of selling of the restaurant.
	 * @throws OrderNotCompletException
	 */
	public void finishOrder() throws OrderNotCompletException{
		if(this.complete){
			this.customer.addOrder(this);
			//this.restaurant.addOrder(this);
			this.courier.setCountDeliveredOrder(this.courier.getCountDeliveredOrder() + 1);
			this.courier.setAvailability(true);
			this.courier.setLocation(this.customer.getAdress());
			Date date = new Date();
			this.setCompleteMonth(date.getMonth() + 1);
			this.setCompleteYear(date.getYear() + 1900);
			this.setCompleteDay(date.getDate());
			this.getRestaurant().setTotalSelling(this.getRestaurant().getTotalSelling() + this.getPrice());
		}
		else{
			throw new OrderNotCompletException();
		}
	}

	
	/** Overriding of the function to print the order. A second line is added if it is completed.
	 * 
	 **/
	public String toString(){
		String s = new String();
		s += "Order from Customer : " + this.getCustomer().getUserName() + " in restaurant : " + this.getRestaurant().getName();
		if(this.isComplete()){
			s += "\nCompleted on : " + this.completeDay + "/" + this.completeMonth + "/" + this.completeYear + " done by : " + this.getCourier().getUserName();
		}
		return s;
	}

	/** Get the month when the order was completed.
	 * @return
	 * @throws OrderNotCompletException
	 */
	public int getCompleteMonth() throws OrderNotCompletException {
		if(this.isComplete()){
			return completeMonth;
		}
		else{
			throw new OrderNotCompletException();
		}
	}


	/** Get the year when the order was completed.
	 * @return
	 * @throws OrderNotCompletException
	 */
	public int getCompleteYear() throws OrderNotCompletException {
		if(this.isComplete()){
			return completeYear;
		}
		else{
			throw new OrderNotCompletException();
		}
	}

	/** Get the day when the order was completed.
	 * @return
	 * @throws OrderNotCompletException
	 */
	public int getCompleteDay() throws OrderNotCompletException {
		if(this.isComplete()){
			return completeDay;
		}
		else{
			throw new OrderNotCompletException();
		}
	}

	/**
	 * @return the customer
	 */
	public Customer getCustomer() {
		return customer;
	}

	/**
	 * @param customer the customer to set
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	/**
	 * @return the courier
	 */
	public Courier getCourier() {
		return courier;
	}

	/**
	 * @param courier the courier to set
	 */
	public void setCourier(Courier courier) {
		this.courier = courier;
	}

	/**
	 * @return the restaurant
	 */
	public Restaurant getRestaurant() {
		return restaurant;
	}

	/**
	 * @param restaurant the restaurant to set
	 */
	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	/**
	 * @return the singleItems
	 */
	public ArrayList<SingleItem> getSingleItems() {
		return singleItems;
	}

	/**
	 * @param singleItems the singleItems to set
	 */
	public void setSingleItems(ArrayList<SingleItem> singleItems) {
		this.singleItems = singleItems;
	}

	/**
	 * @return the meals
	 */
	public ArrayList<Meal> getMeals() {
		return meals;
	}

	/**
	 * @param meals the meals to set
	 */
	public void setMeals(ArrayList<Meal> meals) {
		this.meals = meals;
	}

	/**
	 * @return the complete
	 */
	public boolean isComplete() {
		return complete;
	}

	/**
	 * @param complete the complete to set
	 */
	public void setComplete(boolean complete) {
		this.complete = complete;
	}

	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * @return the profit
	 */
	public double getProfit() {
		return profit;
	}

	/**
	 * @param profit the profit to set
	 */
	public void setProfit(double profit) {
		this.profit = profit;
	}

	/**
	 * @param completeMonth the completeMonth to set
	 */
	public void setCompleteMonth(int completeMonth) {
		this.completeMonth = completeMonth;
	}

	/**
	 * @param completeYear the completeYear to set
	 */
	public void setCompleteYear(int completeYear) {
		this.completeYear = completeYear;
	}

	/**
	 * @param completeDay the completeDay to set
	 */
	public void setCompleteDay(int completeDay) {
		this.completeDay = completeDay;
	}

	public int getID() {
		return ID;
	}

}
