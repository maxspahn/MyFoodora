package user_management;

import system.MyFoodora;

public class RestaurantFactory extends UserFactory{
	
	public RestaurantFactory(MyFoodora myFoodora){
		super(myFoodora);
	}
	
	@Override
	public User createAccount(String name, String userName, String passWord, String phone, String email, int[] adress) throws SameUserNameException {
		Restaurant res = new Restaurant(name, userName, passWord, phone, email, adress);
		//Check if the username already exists in the database
		boolean alreadyExist = this.checkExistenceUserName(userName);
		res.setMyFoodora(this.getMyFoodora());
							
		//If the username does not exist
		if(!alreadyExist){
						
			//The customer is added to the user list of MyFoodora
			this.addUserToLists(res);
			return res;
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
			Restaurant restaurant1 = (Restaurant) this.createAccount("The Five Fields", "fiveFields", 
			"j/890", "0178560531","thefivefields@gmail.com", adress);
			
			//Creation of the second restaurant
			int[] adress2 = {-67,-9};
			Restaurant restaurant2 = (Restaurant) this.createAccount("The Ledbury", "ledBury", 
			"ichlib", "0470986511","theledbury@gmail.com", adress2);
			
			//Creation of the third restaurant
			int[] adress3 = {0,-9};
			Restaurant restaurant3 = (Restaurant) this.createAccount("Typing Room", "typingRoom", 
			"je-9ii", "031000244","theledbury@gmail.com", adress3);
			
			//Creation of the fourth restaurant
			int[] adress4 = {12,1};
			Restaurant restaurant4 = (Restaurant) this.createAccount("Marianne Restaurant", "marianneRestaurant", 
			"eng2015", "0176550012","mariannerestaurant@gmail.com", adress4);
			
			//Creation of the fifth restaurant
			int[] adress5 = {-70,-6};
			Restaurant restaurant5 = (Restaurant) this.createAccount("Seven Park Place", "7parkplace", 
			"7res2017", "0476558010","7parkplace@gmail.com", adress5);			
		
			} 
			
			catch (SameUserNameException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}



}
