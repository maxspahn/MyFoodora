package user_management;

import system.Order;

/** Implementation of Courier, extends the PhysicalUser class.
 * 
 * @author maxspahn
 * @author jeremyaugot
 *
 */

public class Courier extends PhysicalUser{
	private boolean availability; //False if he/she is already delivering a product
	private int countDeliveredOrder;	
	private double acceptProbability;
	
	/**Constructor.
	 * By default, the courier is available, and the counter of delivered order is equal to 0.
	 * 
	 */
	public Courier(String name, String userName, String passWord, String phone,
			String email, int[] adress) {
		super(name, userName, passWord, phone, email, adress);
		this.availability = true;
		this.countDeliveredOrder = 0;
		this.setAcceptProbability(0.9);
	}
	
	
	/**Accept or refuse to deliver an order according to the value of the attribute acceptProbability.
	 * @param order The order to deliver.
	 * @return boolean true of false whether it has been accepted or not.
	 */
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
	
	/**Unregister from MyFoodora system.
	 * 
	 */
	public void unRegister(){
		this.getMyFoodora().getListCourier().remove(this);
		System.out.println("Your account has been deleted.");
	}

	/**
	 * @return the availability
	 */
	public boolean isAvailability() {
		return availability;
	}

	/**
	 * @param availability the availability to set
	 */
	public void setAvailability(boolean availability) {
		this.availability = availability;
	}

	/**
	 * @return the countDeliveredOrder
	 */
	public int getCountDeliveredOrder() {
		return countDeliveredOrder;
	}

	/**
	 * @param countDeliveredOrder the countDeliveredOrder to set
	 */
	public void setCountDeliveredOrder(int countDeliveredOrder) {
		this.countDeliveredOrder = countDeliveredOrder;
	}


	/**
	 * @return the acceptProbability
	 */
	public double getAcceptProbability() {
		return acceptProbability;
	}


	/**
	 * @param acceptProbability the acceptProbability to set
	 */
	public void setAcceptProbability(double acceptProbability) {
		this.acceptProbability = acceptProbability;
	}
		
}
