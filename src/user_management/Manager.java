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
		this.myFoodora.listUsers.add(user);
	}
	
	//A manager can remove any user, only the CEO can remove other managers
	public void removeUser(String userName){
		
		int userIndex = -1;
		for (int i = 0; i < this.myFoodora.listUsers.size(); i++) {
			if(this.myFoodora.listUsers.get(i).getUserName().equalsIgnoreCase(userName)){
				userIndex = i;
			}
		}
		try{
			//If the user to remove is a manager
			if (this.myFoodora.listUsers.get(userIndex) instanceof Manager){
				
				//If the manager who is acting is the CEO and the manager to remove is not himself
				if((this.getRole().equals("CEO"))&&(!this.myFoodora.listUsers.get(userIndex).equals(this))){
					this.myFoodora.listUsers.remove(this.myFoodora.listUsers.get(userIndex));
				}
				
				
				//If the manager wants to remove himself
				else if(this.myFoodora.listUsers.get(userIndex).equals(this)){
				System.out.println("You cannot remove yourself.");
				}
				
				//If the manager who is acting is not the CEO
				else{
					System.out.println("You are not allowed to remove another manager.");
				}
				}
			
			//If the user to remove is not a manager, then any manager can do it
			else{
				this.myFoodora.listUsers.remove(this.myFoodora.listUsers.get(userIndex));
			}
		}
		catch(ArrayIndexOutOfBoundsException e){
			System.out.println("User not found" + e.getMessage());
		}
	}
	
	//A manager can activate any user
	public void activateUser(String userName){
		int userIndex = -1;
		for (int i = 0; i < this.myFoodora.listUsers.size(); i++) {
			if(this.myFoodora.listUsers.get(i).getUserName().equalsIgnoreCase(userName)){
				userIndex = i;
			}
		}
		try{
			this.myFoodora.listUsers.get(userIndex).setActivated(true);
		}
		catch(ArrayIndexOutOfBoundsException e){
			System.out.println("User not found" + e.getMessage());
		}
	}
	
	//A manager can disactivate any user, excepted other managers
	public void disactivate(String userName){
		int userIndex = -1;
		for (int i = 0; i < this.myFoodora.listUsers.size(); i++) {
			if(this.myFoodora.listUsers.get(i).getUserName().equalsIgnoreCase(userName)){
				userIndex = i;
			}
		}
		
		try{
			//If the user to disactivate is a manager
			if (this.myFoodora.listUsers.get(userIndex) instanceof Manager){
				System.out.println("You cannot disactivate a manager");
			}
			
			//If the user to disactivate is not a manager
			else{
				this.myFoodora.listUsers.get(userIndex).setActivated(false);
			}
		}
		catch(ArrayIndexOutOfBoundsException e){
			System.out.println("User not found" + e.getMessage());
		}
	}
	
	public String toString(){	
		int x = this.getAdress()[0];
		int y = this.getAdress()[1];
		return "ID:"+this.getID()+"/Name:"+this.getName()+"/Surname:"+this.getSurname()+"/Username:"+this.getUserName()
			+"/Password:"+this.getPassWord()+"/Email:"+this.getEmail()+"/Phone:"+this.getPhone()
			+"/Adress:{"+x+";"+y+"}"+"/Role:"+this.getRole();
	}

}
