package user_management;

import java.util.ArrayList;

public class MyFoodora {
	private ArrayList<User> listUsers;
	private ArrayList<Courier> listCouriers;
	
	public MyFoodora(){
		this.listUsers = new ArrayList<User>();
	}

	//getters
	public ArrayList<User> getListUsers() {
		return listUsers;
	}
	
	public ArrayList<Courier> getListCouriers() {
		return listCouriers;
	}
	
	//setters
	public void setListUsers(ArrayList<User> listUsers) {
		this.listUsers = listUsers;
	}
	
	public void setListCouriers(ArrayList<Courier> listCouriers) {
		this.listCouriers = listCouriers;
	}
	
	
	
}
