package user_management;

import java.io.Serializable;

import system.MyFoodora;

/** CourierFactory class enables creating a new courier thanks to a factory pattern. Extends UserFactory class. 
 * It also provides the method to load two couriers in MyFoodora system.
 * @author maxspahn
 * @author jeremyaugot
 *
 */
public class CourierFactory extends UserFactory implements Serializable{
	
	public CourierFactory(MyFoodora myFoodora) {
		super(myFoodora);
	}


	@Override
	public void createAccount(String name, String userName, String passWord, String phone, String email, int[] adress) throws SameUserNameException {
		Courier cour = new Courier(name, userName, passWord, phone, email, adress);
		
		boolean alreadyExist = this.checkExistenceUserName(userName);			
		//If the userName does not exist
		if(!alreadyExist){
				
			//The customer is added to the lists of MyFoodora
			this.addUserToLists(cour);
		}
					
		else{
			throw new SameUserNameException();
		}
		
	}


	@Override
	public void load() {
		
		try{
		//Creation of the first courier
		int[] adress1 = {12,0};
		this.createAccount("Bob", "valmontb",
				"1234", "0678901223", "bob.valmont@gmail.com", adress1);
		Courier courier1 = (Courier) this.login("valmontb", "1234");
		courier1.setSurname("Valmont");
		courier1.setBirthdayDate(10,1,1990);
		courier1.setAvailability(true);
		
		
		//Creation of the second courier
		int[] adress2 = {3,27};
		this.createAccount("Marie", "varam",
				"var78", "0665000021", "marie.vara@gmail.com", adress2);
		Courier courier2 = (Courier) this.login("varam", "var78");
		courier2.setSurname("Vara");
		courier2.setBirthdayDate(1,26,1992);
		courier2.setAvailability(true);
		
		}
		
		catch (SameUserNameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WrongUserNameOrPassWordException e){
			System.out.println(e.getMessage());
		}
		
	}

}
