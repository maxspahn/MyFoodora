package user_management;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeSet;

import restaurant.*;
import system.FullMealSort;
import system.HalfMealSort;
import system.SingleItemSort;

public class Restaurant extends User implements Observable{
	//list of all the products (single items and meals) of the restaurant
	private Menu menu;  
	private double discount; //Discount for the meal of the week
	
	//Attributes for the observer pattern
	private boolean changed;
	private String message;
	private double totalSelling;


	private TreeSet<FullMealSort> deliveredFullMeals;
	private TreeSet<HalfMealSort> deliveredHalfMeals;
	private TreeSet<SingleItemSort> deliverdSingleItems;
	
	/*
	* constructor: by default the list of sold products is empty
	*/
	public Restaurant(String name, String userName, String passWord, String phone, String email, int[] adress){
		super(name, userName, passWord, phone, email, adress);
		this.menu = new Menu();
		this.discount = 0.1;
		//this.allCustomers = new ArrayList<Customer>();
		this.changed = false;
		this.message = "";
		this.totalSelling = 0;
		this.setDeliverdSingleItems(new TreeSet<SingleItemSort>());
		this.setDeliveredFullMeals(new TreeSet<FullMealSort>());
		this.setDeliveredHalfMeals(new TreeSet<HalfMealSort>());
	}

	//getters
	public Menu getMenu() {
		return this.menu;
	}

	public double getDiscount(){
		return this.discount;
	}

	//setters
	public void setMenu(Menu menu) {
		this.menu = menu;
	}
	
	public void setDiscount(double discount){
		this.discount = discount;
		
		//Send a notification to the customers to tell them that there is a new discount
		this.changed = true;
		this.notifyObservers("The restaurant "+this.getName()+" offers a discount of "+this.discount+" on all the items of the menu.");
	}

	public String toString(){	
		int x = this.getAdress()[0];
		int y = this.getAdress()[1];
		return "ID:"+this.getID()+"/Name:"+this.getName()+"/Username:"+this.getUserName()
			+"/Password:"+this.getPassWord()+"/Email:"+this.getEmail()+"/Phone:"+this.getPhone()
			+"/Adress:{"+x+";"+y+"}";
	}
	
	//Set the meal of the week
	public void setMealOfTheWeek(String mealName){
		for (int i = 0; i < this.menu.getMeals().size(); i++) {
			this.menu.getMeals().get(i).setMealOfTheWeek(false);
		}
		
		this.menu.getMeal(mealName).setMealOfTheWeek(true);
		this.menu.getMeal(mealName).setDiscount(this.discount);
		
		//Send a notification to the customers to tell them that the meal of the week has changed
		this.changed = true;
		this.notifyObservers("The restaurant "+this.getName()+" has a new meal of the week: "+mealName);
		
	}

	@Override
	public void notifyObservers(String message) {
		if (this.changed){
			for (Customer cust : this.getMyFoodora().getListCustomer()){
				if (cust.getSpamAgreement()){
					cust.update(message);
				}
			}
			this.changed = false;
		}
		
	}
	
	public void setMessage(String message){
		this.message = message;
		this.changed = true;
	}
	
	//Editing the menu

	public void createNewFullMeal(String fullMealName, String starterName, String mainDishName, String dessertName) throws WrongItemAdded, ItemDoesNotExist{
		this.menu.addItem("FullMeal", fullMealName);
		this.menu.addItemToMeal(fullMealName, starterName);
		this.menu.addItemToMeal(fullMealName, mainDishName);
		this.menu.addItemToMeal(fullMealName, dessertName);
		System.out.println("The full meal "+fullMealName+" has been created and added to the menu. It contains:"+"\r\n"
		+"Starter: "+starterName+"\r\n"
		+"Main dish: "+mainDishName+"\r\n"
		+"Dessert: "+dessertName);
		
	}
	
	public void createNewHalfMeal(String halfMealName, String mainDishName, String secondItemName) throws WrongItemAdded, ItemDoesNotExist{
		this.menu.addItem("HalfMeal", halfMealName);
		this.menu.addItemToMeal(halfMealName, mainDishName);
		this.menu.addItemToMeal(halfMealName, secondItemName);
		System.out.println("The full meal "+halfMealName+" has been created and added to the menu. It contains:"+"\r\n"
		+"Main dish: "+mainDishName+"\r\n"
		+"and: "+secondItemName);
		
	}
	
	public void createNewItem(String itemType, String name) throws WrongItemAdded{
		this.menu.addItem(itemType, name);
	}


	public double getTotalSelling() {
		return totalSelling;
	}

	public void setTotalSelling(double totalSelling) {
		this.totalSelling = totalSelling;
	}

	public TreeSet<FullMealSort> getDeliveredFullMeals() {
		return deliveredFullMeals;
	}

	public void setDeliveredFullMeals(TreeSet<FullMealSort> deliveredFullMeals) {
		this.deliveredFullMeals = deliveredFullMeals;
	}

	public TreeSet<HalfMealSort> getDeliveredHalfMeals() {
		return deliveredHalfMeals;
	}

	public void setDeliveredHalfMeals(TreeSet<HalfMealSort> deliveredHalfMeals) {
		this.deliveredHalfMeals = deliveredHalfMeals;
	}

	public TreeSet<SingleItemSort> getDeliverdSingleItems() {
		return deliverdSingleItems;
	}

	public void setDeliverdSingleItems(TreeSet<SingleItemSort> deliverdSingleItems) {
		this.deliverdSingleItems = deliverdSingleItems;
	}
	
	
	
}
