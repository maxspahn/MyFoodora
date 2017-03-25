package user_management;

import java.util.ArrayList;

public abstract class UserFactory {
	//Static list of all the managers of MyFoodora, static->it is the same for each subclass of UserFactory
	protected static ArrayList<Manager> managerList = new ArrayList<Manager>();
	
	//getters
	public ArrayList<Manager> getManagerList(){
		return UserFactory.managerList;
	}
	

	/*
	* method to create a new account, uses createAccountNextStep() method
	* Asks for all the attributes in common between all the users
	*/
	abstract public User createAccount(String name, String userName, String passWord, String phone, String email, int[] adress) throws SameUserNameException;
		
	
	
	//method to login to an existing account
	public void login(String userName, String password){
		User loggedUser = null;
		boolean found = false;  //Boolean: to know if the user has been found in the user list
		for (User loopUser : this.getManagerList().get(0).getMyFoodora().listUsers){
			if (loopUser.getUserName()==userName){
				if (loopUser.getPassWord()==password){
					loggedUser = loopUser;
					found = true;
				}
			}
		}
		if (!found){  //If the user has not been found
			System.out.println("Unfortunately your user name or your password is false.");
		}
	}
	
	//abstract method to load the users who are already inserted in the system, to be overriden
	public abstract void load();


	public static void setManagerList(ArrayList<Manager> managerList) {
		UserFactory.managerList = managerList;
	}
	
	
}
