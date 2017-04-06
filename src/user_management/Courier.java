package user_management;

import java.io.Serializable;

import system.Order;

/** Implementation of Courier, extends the PhysicalUser class.
 * 
 * @author maxspahn
 * @author jeremyaugot
 *
 */

public class Courier extends PhysicalUser implements Serializable{
	private boolean availability; //False if he/she is already delivering a product
	private int countDeliveredOrder;	
	private double acceptProbability;
	private int[] location;
	
	/**Constructor.
	 * By default, the courier is available, and the counter of delivered order is equal to 0.
	 * 
	 */
	public Courier(String name, String userName, String passWord, String phone,
			String email, int[] adress) {
		super(name, userName, passWord, phone, email, adress);
		this.availability = false;
		this.countDeliveredOrder = 0;
		this.setAcceptProbability(0.9);
		this.setLocation(this.getAdress());
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
		String s = "ID:"+this.getID()+"\t : "+this.getName() + " : "+this.getEmail()+" "+this.getPhone()
			+" : { "+x+" ; "+y+" }"+" DeliveredOrders : "+this.countDeliveredOrder;
		if(this.isAvailability()){
			s += " on Duty";
		}
		else{ s += " off Duty";}
		return s;
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
