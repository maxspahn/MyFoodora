package user_management;

import system.MyFoodora;

/** A user has an ID, a name, a username, a password, a phone, an email, an adress. He can be activated or deactivated. 
 * A user has access to MyFoodora.
 * @author maxspahn
 * @author jeremyaugot
 */
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
	
	
	/**Constructor.
	 * By default, a user is activated.
	 * 
	 */
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
	
	/**
	 * @return the iD
	 */
	public int getID() {
		return ID;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @return the passWord
	 */
	public String getPassWord() {
		return passWord;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @return the adress
	 */
	public int[] getAdress() {
		return adress;
	}

	/**
	 * @return the activated
	 */
	public boolean isActivated() {
		return activated;
	}

	/**
	 * @param passWord the passWord to set
	 */
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @param adress the adress to set
	 */
	public void setAdress(int[] adress) {
		this.adress = adress;
	}

	/**
	 * @param activated the activated to set
	 */
	public void setActivated(boolean activated) {
		this.activated = activated;
	}

	/**
	 * @return the myFoodora
	 */
	public MyFoodora getMyFoodora() {
		return myFoodora;
	}

	/**
	 * @param myFoodora the myFoodora to set
	 */
	public void setMyFoodora(MyFoodora myFoodora) {
		this.myFoodora = myFoodora;
	}

}
