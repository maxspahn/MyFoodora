package system;

import java.io.Serializable;
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
public class Order implements Serializable{
	
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
	private String name;

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
		this.setName("Order " + this.ID);
	}
	
	public Order(Customer customer, Restaurant restaurant, String name){
		this.setCustomer(customer);
		this.setMeals(new ArrayList<Meal>());
		this.setSingleItems(new ArrayList<SingleItem>());
		this.setComplete(false);
		this.setRestaurant(restaurant);
		this.ID = Order.counter+1;
		Order.counter++;
		this.setName(name);
	}
	
	/** Adds singleItem to the order.
	 * @param singleItemName The name of the item.
	 * @throws ItemDoesNotExist 
	 */
	public void AddSingleItemToOrder(String singleItemName, int number) throws ItemDoesNotExist{
		for (int i = 0; i < number; i++) {
			this.getSingleItems().add(this.getRestaurant().getMenu().getSingleItem(singleItemName));
		}
	}
	
	/**Remove an item from the order.
	 * @param itemName String which contains the name of the item to remove.
	 * @throws ItemDoesNotExist if the item does not exist.
	 * @throws ItemNotInOrderException if the item is not in the order.
	 */
	public void RemoveItemFromOrder(String itemName) throws ItemDoesNotExist, ItemNotInOrderException{
		boolean singleItemFound = false;
		boolean mealFound = false;
		for (int i = 0; i < this.singleItems.size(); i++) {
			if(this.singleItems.get(i).getName().equalsIgnoreCase(itemName)){
				singleItemFound = true;
			}
		}
		for (int j = 0; j < this.meals.size(); j++) {
			if(meals.get(j).getName().equalsIgnoreCase(itemName)){
				mealFound = true;

			}
		}
		if(singleItemFound){this.singleItems.remove(this.restaurant.getMenu().getSingleItem(itemName));}
		else if(mealFound){this.meals.remove(this.restaurant.getMenu().getMeal(itemName));}
		else{throw new ItemNotInOrderException();}
	}
	/**Adds meal to the order.
	 * @param mealName Name of the meal.
	 * @throws ItemDoesNotExist
	 */
	public void AddMealToOrder(String mealName, int number) throws ItemDoesNotExist{
		for (int i = 0; i < number; i++) {
			this.getMeals().add(this.getRestaurant().getMenu().getMeal(mealName));
		}
	}
	
	/** Adds an item to the order, no matter if singleItem or Meal.
	 * @param itemName
	 * @param number
	 * @throws ItemDoesNotExist
	 */
	public void AddItemToOrder(String itemName, int number) throws ItemDoesNotExist {
		boolean found = false;
		try {
			if(this.getRestaurant().getMenu().getMeal(itemName) != null){
				this.AddMealToOrder(itemName, number);
				found = true;
			}
			else if(this.getRestaurant().getMenu().getSingleItem(itemName) != null){
				this.AddSingleItemToOrder(itemName, number);
				found = true;
			}
		} catch (ItemDoesNotExist e) {
			//no message
		}
		if(!found){
			throw new ItemDoesNotExist(itemName);
		}
	}
	
	/** Adds a number of items to the order, depending of the index given. It is used in user interaction.
	 * @param index
	 * @param restaurant
	 * @param number
	 * @throws WrongItemAdded
	 */
	public void AddItemToOrder(int index, Restaurant restaurant, int number) throws WrongItemAdded{
		if(index  >=  restaurant.getMenu().getMeals().size() + restaurant.getMenu().getSingleItems().size()){
			throw new WrongItemAdded();
		}
		else if(index  >= restaurant.getMenu().getSingleItems().size()){
			try {
				this.AddSingleItemToOrder(restaurant.getMenu().getSingleItems().get(index).getName(), number);
			} catch (ItemDoesNotExist e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			try {
				this.AddMealToOrder(restaurant.getMenu().getMeals().get(index).getName(), number);
			} catch (ItemDoesNotExist e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
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

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

}
