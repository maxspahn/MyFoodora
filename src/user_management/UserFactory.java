package user_management;

import system.MyFoodora;

/** Abstract UserFactory, part of the Factory-Pattern to create users.
 * @author maxspahn
 * @author jeremyaugot
 *
 */
public abstract class UserFactory {
	
	private MyFoodora myFoodora;
	
	/** Constructor. Makes sure that every user has access to the same system. 
	 * @param myFoodora The core part of the system.
	 */
	public UserFactory(MyFoodora myFoodora){
		this.myFoodora = myFoodora;
	}
	
	/**
	* Method to create a new account, uses createAccountNextStep() method.
	* Asks for all the attributes in common between all the users.
	*/
	abstract public void createAccount(String name, String userName, String passWord, String phone, String email, int[] adress) throws SameUserNameException;
		
	/** Method to login to an existing account.
	 * @param userName Name of user to login.
	 * @param password User's password.
	 * @return The user object.
	 * @throws WrongUserNameOrPassWordException
	 */
	public User login(String userName, String password) throws WrongUserNameOrPassWordException{
		User loggedUser = null;
		boolean found = false;  //Boolean: to know if the user has been found in the user list
		for (User loopUser : this.getMyFoodora().getListUsers()){
			if (loopUser.getUserName()==userName){
				if (loopUser.getPassWord()==password){
					loggedUser = loopUser;
					found = true;
				}
			}
		}
		if (!found){  //If the user has not been found
			throw new WrongUserNameOrPassWordException();
		}
		else{
			return loggedUser;
		}
	}
	
	
	/** Abstract method to load the users who are already inserted in the system.
	 * The function is overriden by every specific user-type.
	 */
	public abstract void load();

	
	/**A method which adds the user in the user list and the list in which he/she belongs.
	 * 
	 * @param user User to be added to the system.
	 */
	public void addUserToLists(User user){
		//The user is added in the list of all users
		this.getMyFoodora().getListUsers().add(user);
		
		//If the user is a manager, he/she is added to the manager list
		if(user instanceof Manager){
			
			this.getMyFoodora().getListManager().add((Manager) user);
		}
		
		//If the user is a customer, he/she is added to the manager list
		if(user instanceof Customer){
			this.getMyFoodora().getListCustomer().add((Customer) user);
			}
		
		//If the user is a courier, he/she is added to the manager list
		if(user instanceof Courier){
			this.getMyFoodora().getListCourier().add((Courier) user);
			}
		
		//If the user is a restaurant, he/she is added to the manager list
		if(user instanceof Restaurant){
			this.getMyFoodora().getListRestaurant().add((Restaurant) user);
			}
	}
	
	/**Method to check if the userName already exists.
	 * 
	 * @param userName User to be checked.
	 * @return True if the user has been registered in the system.
	 */
	public boolean checkExistenceUserName(String userName){
		boolean alreadyExist = false;
		for (User us : this.getMyFoodora().getListUsers()){
			if (us.getUserName().equals(userName)){
				alreadyExist = true;
			}	
		}
		return alreadyExist;
	}

	/**
	 * @return the myFoodora object
	 */
	public MyFoodora getMyFoodora() {
		return myFoodora;
	}
	
}
