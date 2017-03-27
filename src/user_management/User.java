package user_management;

import system.MyFoodora;

public abstract class User {
	private int ID;
	private static int counter; //static counter to avoid 2 users with the same ID
	private String name;
	private String userName;
	private String passWord;
	private String phone; //Written like "0123456789"
	private String email;
	private int[] adress; //Written like {x,y}
	private boolean activated;
	
	private MyFoodora myFoodora;
	
	
	//constructor
	public User(String name, String userName, String passWord, String phone, String email, int[] adress){
		this.name = name;
		this.userName = userName;
		this.passWord = passWord;
		this.phone = phone;
		this.email = email;
		this.adress = adress;
		this.activated = true;
		this.ID = User.counter+1;
		User.counter++;
	}
	
	//getters
	public int getID() {
		return ID;
	}
	public String getName() {
		return name;
	}
	public String getUserName() {
		return userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public String getPhone() {
		return phone;
	}
	public String getEmail() {
		return email;
	}
	public int[] getAdress() {
		return adress;
	}
	public boolean isActivated() {
		return activated;
	}
	
	//setters
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setAdress(int[] adress) {
		this.adress = adress;
	}
	public void setActivated(boolean activated) {
		this.activated = activated;
	}

	public MyFoodora getMyFoodora() {
		return myFoodora;
	}

	public void setMyFoodora(MyFoodora myFoodora) {
		this.myFoodora = myFoodora;
	}	
	
}
