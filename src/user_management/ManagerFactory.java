package user_management;

import java.util.Date;
import java.util.Scanner;

public class ManagerFactory extends UserFactory{
	private MyFoodora myFoodora;
	
	public ManagerFactory(){
		this.myFoodora = new MyFoodora();
	}
	

	@Override
	public User createAccount(String name, String userName, String passWord, String phone, String email, int[] adress) throws SameUserNameException {
		Manager man = new Manager(name, userName, passWord, phone, email, adress);
		
		//Check if the username already exists in the database
		boolean alreadyExist = false;
		for (User us : this.managerList.get(0).getMyFoodora().listUsers){
			if (us.getUserName()==userName){
				alreadyExist = true;
			}
		}
		
		//If the username does not exist
		if(!alreadyExist){
			//The manager is added to the manager list
			this.managerList.add(man);
			//The manager is added to the user list of MyFoodora
			this.managerList.get(0).getMyFoodora().listUsers.add(man);
			return man;
		}
		
		else{
			throw new SameUserNameException();
		}
	}
		
	@Override
	//By default, there are a CEO and his deputy
	public void load() {
		
		//Creation of the CEO account
		int[] adress = {4,9};
		Manager CEO;
		try {
			CEO = new Manager("Jack", "sparrowj", 
			"blackPearl", "0712457126","jack.sparrow@gmail.com", adress);
			CEO.setSurname("Sparrow");
			CEO.setBirthdayDate(new Date(1970,7,19));
			CEO.setMyFoodora(this.myFoodora);
			CEO.setRole("CEO");
			
			this.managerList.add(CEO);
			CEO.getMyFoodora().listUsers.add(CEO);
			
			//Creation of the deputy account
			int[] adress2 = {4,9};
			Manager deputy = (Manager) this.createAccount("Bob", "spongeb", 
			"patrick", "0675314518","bob.sponge@gmail.com", adress);
			deputy.setSurname("Sponge");
			deputy.setBirthdayDate(new Date(1978,12,29));
			deputy.setMyFoodora(this.myFoodora);
			deputy.setRole("deputy");
			
		} catch (SameUserNameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
