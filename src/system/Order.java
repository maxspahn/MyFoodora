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
	private Date creationDate;
	private FidelityCard fidelityCard;

	public Order(Customer customer) {
		this.setCustomer(customer);
		this.setMeals(new ArrayList<Meal>());
		this.setSingleItems(new ArrayList<SingleItem>());
		this.setComplete(false);
		this.creationDate = new Date();
		this.setFidelityCard(customer.getFidelityCard());
	}
	
	public void AddSingleItemToOrder(String singleItemName){
		this.getSingleItems().add(this.getRestaurant().getMenu().getSingleItem(singleItemName));
	}
	
	public void AddMealToOrder(String mealName){
		this.getMeals().add(this.getRestaurant().getMenu().getMeal(mealName));
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

	public Date getCreationDate() {
		return creationDate;
	}

	public FidelityCard getFidelityCard() {
		return fidelityCard;
	}

	public void setFidelityCard(FidelityCard fidelityCard) {
		this.fidelityCard = fidelityCard;
	}

	
	

}
