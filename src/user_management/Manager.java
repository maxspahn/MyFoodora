package user_management;

import java.util.*;

public class Manager extends PhysicalUser{
	private MyFoodora myFoodora;
	private String role;
	
	public Manager(String name, String userName, String passWord, String phone,
			String email, int[] adress) {
		super(name, userName, passWord, phone, email, adress);
		this.role = "";
	}

	//getters
	public MyFoodora getMyFoodora() {
		return myFoodora;
	}	
	
	public String getRole() {
		return role;
	}
	
	//setters
	public void setMyFoodora(MyFoodora myFoodora){
		this.myFoodora = myFoodora;
	}

	public void setRole(String role){
		this.role = role;
	}
	
	//A manager can add any user
	public void addUser(User user){
		this.myFoodora.getListUsers().add(user);
	}
	
	//A manager can remove any user, only the CEO can remove other managers
	public void removeUser(String userName) throws UserNotFoundException{
		
		int userIndex = this.findIDUser(userName);
		
		//If the user to remove is a manager
		if (this.myFoodora.getListUsers().get(userIndex) instanceof Manager){
				
			//If the manager who is acting is the CEO and the manager to remove is not himself
			if((this.getRole().equals("CEO"))&&(!this.myFoodora.getListUsers().get(userIndex).equals(this))){
				this.myFoodora.getListUsers().remove(this.myFoodora.getListUsers().get(userIndex));
			}
				
				
			//If the manager wants to remove himself
			else if(this.myFoodora.getListUsers().get(userIndex).equals(this)){
			System.out.println("You cannot remove yourself.");
			}
				
			//If the manager who is acting is not the CEO
			else{
				System.out.println("You are not allowed to remove another manager.");
			}
			}
			
		//If the user to remove is not a manager, then any manager can do it
		else{
			this.myFoodora.getListUsers().remove(this.myFoodora.getListUsers().get(userIndex));			
			}
		}
	
	
	//A manager can activate any user
	public void activateUser(String userName) throws UserNotFoundException{
		int userIndex = this.findIDUser(userName);
		this.myFoodora.getListUsers().get(userIndex).setActivated(true);
		
	}
	
	//A manager can disactivate any user, excepted other managers
	public void disactivate(String userName) throws UserNotFoundException{
		int userIndex = this.findIDUser(userName);
				
		//If the user to disactivate is a manager
		if (this.myFoodora.getListUsers().get(userIndex) instanceof Manager){
			System.out.println("You cannot disactivate a manager");
			}
			
		//If the user to disactivate is not a manager
		else{
			this.myFoodora.getListUsers().get(userIndex).setActivated(false);
			}
	}
	
	public String toString(){	
		int x = this.getAdress()[0];
		int y = this.getAdress()[1];
		return "ID:"+this.getID()+"/Name:"+this.getName()+"/Surname:"+this.getSurname()+"/Username:"+this.getUserName()
			+"/Password:"+this.getPassWord()+"/Email:"+this.getEmail()+"/Phone:"+this.getPhone()
			+"/Adress:{"+x+";"+y+"}"+"/Role:"+this.getRole();
	}
	
	
	//Method to find a user by searching his userName
	private int findIDUser(String userName) throws UserNotFoundException{
		int index = -1;
		ArrayList<User> listUser = this.getMyFoodora().getListUsers();
		for (int i = 0; i < listUser.size(); i++) {
			if (listUser.get(i).getUserName().equals(userName)){
				index = i;
			}
		}
		if (index == -1){ //The user has not been found
			throw new UserNotFoundException(userName);
		}			
		return index;  //The user has been found and the index is returned
	}

}
