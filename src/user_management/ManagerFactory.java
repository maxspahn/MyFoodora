package user_management;

import system.MyFoodora;

/** ManagerFactory class enables creating a new manager thanks to a factory pattern. Extends UserFactory class. 
 * It also provides the method to load two managers in MyFoodora system.
 * @author maxspahn
 * @author jeremyaugot
 *
 */
public class ManagerFactory extends UserFactory{
	
	public ManagerFactory(MyFoodora myFoodora){
		super(myFoodora);
	}
	
	
	@Override
	public void createAccount(String name, String userName, String passWord, String phone, String email, int[] adress) throws SameUserNameException {
		Manager man = new Manager(name, userName, passWord, phone, email, adress);
		man.setMyFoodora(this.getMyFoodora());
		
		//Check if the userName already exists in the database
		boolean alreadyExist = this.checkExistenceUserName(userName);
		
		//If the userName does not exist
		if(!alreadyExist){
			//The manager is added to the lists of MyFoodora
			this.addUserToLists(man);
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

		try {
			this.createAccount("Jack", "sparrowj", 
			"blackPearl", "0712457126","jack.sparrow@gmail.com", adress);
			Manager CEO = (Manager) this.login("sparrowj", "blackPearl");
			CEO.setSurname("Sparrow");
			CEO.setBirthdayDate(12,1,1978);
			CEO.setRole("CEO");
	
			
			//Creation of the deputy account
			int[] adress2 = {4,9};
			this.createAccount("Bob", "spongeb", 
			"patrick", "0675314518","bob.sponge@gmail.com", adress2);
			Manager deputy = (Manager) this.login("spongeb", "patrick");
			deputy.setSurname("Sponge");
			deputy.setBirthdayDate(12,2,1978);
			deputy.setRole("deputy");
			
		} catch (SameUserNameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WrongUserNameOrPassWordException e){
			System.out.println(e.getMessage());
		}
		
	}

}
