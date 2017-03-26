package user_management;

import java.util.ArrayList;

import restaurant.*;

public class Restaurant extends User implements Observable{
	//list of all the products (single items and meals) of the restaurant
	private Menu menu;  
	private double discount; //Discount for the meal of the week
	private ArrayList<Customer> allCustomers;
	
	//Attributes for the observer pattern
	private boolean changed;
	private String message;
	
	/*
	* constructor: by default the list of sold products is empty
	*/
	public Restaurant(String name, String userName, String passWord, String phone, String email, int[] adress){
		super(name, userName, passWord, phone, email, adress);
		this.menu = new Menu();
		this.discount = 0.1;
		this.allCustomers = new ArrayList<Customer>();
		this.changed = false;
		this.message = "";
	}

	//getters
	public Menu getMenu() {
		return this.menu;
	}

	public double getDiscount(){
		return this.discount;
	}
	
	public ArrayList<Customer> getAllCustomers() {
		return allCustomers;
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
	
	public void setAllCustomers(ArrayList<Customer> allCustomers) {
		this.allCustomers = allCustomers;
	}

	public String toString(){	
		int x = this.getAdress()[0];
		int y = this.getAdress()[1];
		return "ID:"+this.getID()+"/Name:"+this.getName()+"/Username:"+this.getUserName()
			+"/Password:"+this.getPassWord()+"/Email:"+this.getEmail()+"/Phone:"+this.getPhone()
			+"/Adress:{"+x+";"+y+"}";
	}
	
	//Add an item
	public void addItem(String type, String name) throws WrongItemAdded{
		this.menu.addItem(type, name);
	}
	
	//Add an item to a meal
	public void addItemToMeal(String mealName, String itemName) throws WrongItemAdded{
		this.menu.addItemToMeal(mealName, itemName);
	}
	
	public void setMealOfTheWeek(String mealName){
		int indexMeal = 0;
		for (int i = 0; i < this.menu.getMeals().size(); i++) {
			if(this.menu.getMeals().get(i).getName().equalsIgnoreCase(mealName)){
				indexMeal = i;
				break;
			}
		}
		this.menu.getMeals().get(indexMeal).setMealOfTheWeek(true);
		this.menu.getMeals().get(indexMeal).setDiscount(this.discount);
		
		//Send a notification to the customers to tell them that the meal of the week has changed
		this.changed = true;
		this.notifyObservers("The restaurant "+this.getName()+" has a new meal of the week: "+mealName);
		
	}

	@Override
	public void notifyObservers(String message) {
		if (this.changed){
			for (Customer cust : this.allCustomers){
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
	
	
}
