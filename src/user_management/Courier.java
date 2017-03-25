package user_management;

public class Courier extends PhysicalUser{
	private boolean availability; //False if he/she is already delivering a product
	private int countDeliveredOrder;	
	
	/*
	* constructor: if the courier gives his/her availability
	* At the beginning, the courier have not delivered any order yet: countDeliveredOrder=0
	*/
	public Courier(String name, String userName, String passWord, String phone,
			String email, int[] adress) {
		super(name, userName, passWord, phone, email, adress);
		this.availability = true;
		this.countDeliveredOrder = 0;
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
		
	public String toString(){	
		int x = this.getAdress()[0];
		int y = this.getAdress()[1];
		return "ID:"+this.getID()+"/Name:"+this.getName()+"/Surname:"+this.getSurname()+"/Username:"+this.getUserName()
			+"/Password:"+this.getPassWord()+"/Email:"+this.getEmail()+"/Phone:"+this.getPhone()
			+"/Adress:{"+x+";"+y+"}"+"/Availability"+this.availability+"/DeliveredOrder:"+this.countDeliveredOrder;
	}
	
}
