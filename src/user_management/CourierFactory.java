package user_management;

import java.util.*;

public class CourierFactory extends UserFactory{

	@Override
	public User createAccount(String name, String userName, String passWord, String phone, String email, int[] adress) throws SameUserNameException {
		Courier cour = new Courier(name, userName, passWord, phone, email, adress);
		
		//Check if the username already exists in the database
		boolean alreadyExist = false;
		for (User us : this.managerList.get(0).getMyFoodora().listUsers){
			if (us.getUserName()==userName){
				alreadyExist = true;
			}	
		}
					
		//If the username does not exist
		if(!alreadyExist){
				
			//The customer is added to the user list of MyFoodora
			this.managerList.get(0).getMyFoodora().listUsers.add(cour);
			return cour;
		}
					
		else{
			throw new SameUserNameException();
		}
		
	}


	@Override
	//By default, there are 2 couriers
	public void load() {
		
		try{
		//Creation of the first courier
		int[] adress1 = {12,0};
		Courier courier1 = (Courier) this.createAccount("Bob", "valmontb",
				"1234", "0678901223", "bob.valmont@gmail.com", adress1);
		courier1.setSurname("Valmont");
		courier1.setBirthdayDate(new Date(1990,10,1));
		courier1.setAvailability(true);
		
		//Creation of the second courier
		int[] adress2 = {3,27};
		Courier courier2 = (Courier) this.createAccount("Marie", "varam",
				"var78", "0665000021", "marie.vara@gmail.com", adress2);
		courier2.setSurname("Vara");
		courier2.setBirthdayDate(new Date(1992,1,26));
		courier2.setAvailability(true);
		
		}
		
		catch (SameUserNameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
