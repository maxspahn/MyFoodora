package user_management;

import system.MyFoodora;

public class RestaurantFactory extends UserFactory{
	
	public RestaurantFactory(MyFoodora myFoodora){
		super(myFoodora);
	}
	
	@Override
	public void createAccount(String name, String userName, String passWord, String phone, String email, int[] adress) throws SameUserNameException {
		Restaurant res = new Restaurant(name, userName, passWord, phone, email, adress);
		//Check if the username already exists in the database
		boolean alreadyExist = this.checkExistenceUserName(userName);
		res.setMyFoodora(this.getMyFoodora());
							
		//If the username does not exist
		if(!alreadyExist){
						
			//The customer is added to the user list of MyFoodora
			this.addUserToLists(res);
		}
							
		else{
			throw new SameUserNameException();
		}
	}
		


	@Override
	public void load() {
		
		try{
			//Creation of the first restaurant
			int[] adress = {12,-3};
			this.createAccount("The Five Fields", "fiveFields", 
			"j/890", "0178560531","thefivefields@gmail.com", adress);
			Restaurant restaurant1 = (Restaurant) this.login("fiveFields", "j/890");
			
			//Creation of the second restaurant
			int[] adress2 = {-67,-9};
			this.createAccount("The Ledbury", "ledBury", 
			"ichlib", "0470986511","theledbury@gmail.com", adress2);
			Restaurant restaurant2 = (Restaurant) this.login("ledBury", "ichlib");
			
			//Creation of the third restaurant
			int[] adress3 = {0,-9};
			this.createAccount("Typing Room", "typingRoom", 
			"je-9ii", "031000244","theledbury@gmail.com", adress3);
			Restaurant restaurant3 = (Restaurant) this.login("typingRoom", "je-9ii");
			
			//Creation of the fourth restaurant
			int[] adress4 = {12,1};
			this.createAccount("Marianne Restaurant", "marianneRestaurant", 
			"eng2015", "0176550012","mariannerestaurant@gmail.com", adress4);
			Restaurant restaurant4 = (Restaurant) this.login("marianneRestaurant","eng2015");
			
			//Creation of the fifth restaurant
			int[] adress5 = {-70,-6};
			this.createAccount("Seven Park Place", "7parkplace", 
			"7res2017", "0476558010","7parkplace@gmail.com", adress5);		
			Restaurant restaurant5 = (Restaurant) this.login("7parkplace", "7res2017");
		
			} 
			
			catch (SameUserNameException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (WrongUserNameOrPassWordException e){
				System.out.println(e.getMessage());
			}
		
	}



}
