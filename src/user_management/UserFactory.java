package user_management;

import java.util.ArrayList;

public abstract class UserFactory {
	//Static list of all the managers of MyFoodora, static->it is the same for each instance of UserFactory
	protected static ArrayList<Manager> managerList = new ArrayList<Manager>();
	//Static list of all the customers of MyFoodora
	protected static ArrayList<Customer> customerList = new ArrayList<Customer>();
	//Static list of all the couriers of MyFoodora
	protected static ArrayList<Courier> courierList = new ArrayList<Courier>();
	//Static list of all the restaurants of MyFoodora
	protected static ArrayList<Restaurant> restaurantList = new ArrayList<Restaurant>();
	
	
	//getters
	public ArrayList<Manager> getManagerList(){
		return UserFactory.managerList;
	}
	
	
	public static ArrayList<Customer> getCustomerList() {
		return customerList;
	}

	public static ArrayList<Courier> getCourierList() {
		return courierList;
	}

	public static ArrayList<Restaurant> getRestaurantList() {
		return restaurantList;
	}

	//setters
	public static void setManagerList(ArrayList<Manager> managerList) {
		UserFactory.managerList = managerList;
	}

	public static void setCustomerList(ArrayList<Customer> customerList) {
		UserFactory.customerList = customerList;
	}


	public static void setCourierList(ArrayList<Courier> courierList) {
		UserFactory.courierList = courierList;
	}


	public static void setRestaurantList(ArrayList<Restaurant> restaurantList) {
		UserFactory.restaurantList = restaurantList;
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
		for (User loopUser : this.getManagerList().get(0).getMyFoodora().getListUsers()){
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

	
	//A method which adds the user in the user list and the list in which he/she belongs
	public void addUserToLists(User user){
		//The user is added in the list of all users
		this.getManagerList().get(0).getMyFoodora().getListUsers().add(user);
		
		//If the user is a manager, he/she is added to the manager list
		if(user instanceof Manager){
			this.getManagerList().add((Manager) user);
		}
		
		//If the user is a customer, he/she is added to the manager list
		if(user instanceof Customer){
			this.getCustomerList().add((Customer) user);
			}
		
		//If the user is a courier, he/she is added to the manager list
		if(user instanceof Courier){
			this.getCourierList().add((Courier) user);
			}
		
		//If the user is a restaurant, he/she is added to the manager list
		if(user instanceof Restaurant){
			this.getRestaurantList().add((Restaurant) user);
			}
	}
	
	//Method to check if the userName already exists
	public boolean checkExistenceUserName(String userName){
		boolean alreadyExist = false;
		for (User us : this.getManagerList().get(0).getMyFoodora().getListUsers()){
			if (us.getUserName().equals(userName)){
				alreadyExist = true;
			}	
		}
		return alreadyExist;
	}
	
}
