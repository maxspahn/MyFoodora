package user_management;

import system.Order;

public class Courier extends PhysicalUser{
	private boolean availability; //False if he/she is already delivering a product
	private int countDeliveredOrder;	
	private double acceptProbability;
	private int[] location;
	
	/*
	* constructor: if the courier gives his/her availability
	* At the beginning, the courier have not delivered any order yet: countDeliveredOrder=0
	*/
	public Courier(String name, String userName, String passWord, String phone,
			String email, int[] adress) {
		super(name, userName, passWord, phone, email, adress);
		this.availability = true;
		this.countDeliveredOrder = 0;
		this.setAcceptProbability(0.9);
		this.setLocation(this.getAdress());
	}

	//getters
	public boolean isAvailability() {
		return availability;
	}

	public int getCountDeliveredOrder() {
		return countDeliveredOrder;
	}

	//setters
	public void setAvailability(boolean availability) {
		this.availability = availability;
	}

	public void setCountDeliveredOrder(int countDeliveredOrder) {
		this.countDeliveredOrder = countDeliveredOrder;
	}
	
	public boolean acceptOrder(Order order){
		if(Math.random() < this.getAcceptProbability()){
			this.setAvailability(false);
			return true;
		}
		return false;
	}
		
	public String toString(){	
		int x = this.getAdress()[0];
		int y = this.getAdress()[1];
		return "ID:"+this.getID()+"/Name:"+this.getName()+"/Surname:"+this.getSurname()+"/Username:"+this.getUserName()
			+"/Password:"+this.getPassWord()+"/Email:"+this.getEmail()+"/Phone:"+this.getPhone()
			+"/Adress:{"+x+";"+y+"}"+"/Availability"+this.availability+"/DeliveredOrder:"+this.countDeliveredOrder;
	}

	public double getAcceptProbability() {
		return acceptProbability;
	}

	public void setAcceptProbability(double acceptProbability) {
		this.acceptProbability = acceptProbability;
	}
	
	
	//A courier can unregister himself form MyFoodora
	public void unRegister(){
		this.getMyFoodora().getListCourier().remove(this);
		System.out.println("Your account has been deleted.");
	}

	/**
	 * @return the location
	 */
	public int[] getLocation() {
		return location;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(int[] location) {
		this.location = location;
	}
}
