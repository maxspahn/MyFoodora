package user_management;

import java.util.ArrayList;

public class Restaurant extends User{
	//list of all the products (single items and meals) of the restaurant
	private Menu menu;  
	private double discount;
	
	/*
	* constructor: by default the list of sold products is empty
	*/
	public Restaurant(String name, String userName, String passWord, String phone, String email, int[] adress){
		super(name, userName, passWord, phone, email, adress);
		this.menu = new Menu();
		this.discount = 0.1;
	}

	//getters
	public Menu getListSoldProducts() {
		return this.menu;
	}

	public double getDiscount(){
		return this.discount;
	}
	
	//setters
	public void setListMeals(Menu menu) {
		this.menu = menu;
	}
	
	public void setDiscount(double discount){
		this.discount = discount;
	}
	
	public String toString(){	
		int x = this.getAdress()[0];
		int y = this.getAdress()[1];
		return "ID:"+this.getID()+"/Name:"+this.getName()+"/Username:"+this.getUserName()
			+"/Password:"+this.getPassWord()+"/Email:"+this.getEmail()+"/Phone:"+this.getPhone()
			+"/Adress:{"+x+";"+y+"}";
	}
	
}
