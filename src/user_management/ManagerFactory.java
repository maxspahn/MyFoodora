package user_management;

public class ManagerFactory extends UserFactory{
	private MyFoodora myFoodora;
	
	public ManagerFactory(){
		this.myFoodora = new MyFoodora();
		this.load();
	}
	

	@Override
	public User createAccount(String name, String userName, String passWord, String phone, String email, int[] adress) throws SameUserNameException {
		Manager man = new Manager(name, userName, passWord, phone, email, adress);
		man.setMyFoodora(this.myFoodora);
		
		//Check if the userName already exists in the database
		boolean alreadyExist = false;
		for (User us : this.getManagerList().get(0).getMyFoodora().listUsers){
			if (us.getUserName().equalsIgnoreCase(userName)){
				alreadyExist = true;
			}
		}
		
		//If the userName does not exist
		if(!alreadyExist){
			//The manager is added to the manager list
			this.getManagerList().add(man);
			//The manager is added to the user list of MyFoodora
			this.getManagerList().get(0).getMyFoodora().listUsers.add(man);
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
			CEO.setBirthdayDate(12,1,1978);
			CEO.setMyFoodora(this.myFoodora);
			CEO.setRole("CEO");
			
			this.getManagerList().add(CEO);
			CEO.getMyFoodora().listUsers.add(CEO);
			
			//Creation of the deputy account
			int[] adress2 = {4,9};
			Manager deputy = (Manager) this.createAccount("Bob", "spongeb", 
			"patrick", "0675314518","bob.sponge@gmail.com", adress2);
			deputy.setSurname("Sponge");
			deputy.setBirthdayDate(12,2,1978);
			deputy.setMyFoodora(this.myFoodora);
			deputy.setRole("deputy");
			
		} catch (SameUserNameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
