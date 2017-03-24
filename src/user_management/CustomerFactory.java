package user_management;

import java.util.Date;
import java.util.Scanner;

public class CustomerFactory extends UserFactory{

	@Override
	public User createAccount(String name, String userName, String passWord, String phone, String email, int[] adress) throws SameUserNameException {
		Customer cust = new Customer(name, userName, passWord, phone, email, adress);
		
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
			this.managerList.get(0).getMyFoodora().listUsers.add(cust);
			return cust;
		}
			
		else{
			throw new SameUserNameException();
		}
	}
	
	@Override
	public void load() {
		
		try{
		//Creation of the first customer
		int[] adress = {0,13};
		Customer customer1 = (Customer) this.createAccount("Alan", "batona", 
		"jkYt8", "0699887766","alan.baton@gmail.com", adress);
		customer1.setSurname("Baton");
		customer1.setBirthdayDate(new Date(1989,1,1));
		customer1.setSpamAgreement(true);
		
		//Creation of the second customer
		int[] adress2 = {-3,8};
		Customer customer2 = (Customer) this.createAccount("Cecile","bertrandc", 
		"Hxxx8", "0712674560","cecile.bertrand@gmail.com", adress);
		customer2.setSurname("Bertrand");
		customer2.setBirthdayDate(new Date(1950,9,18));
		customer2.setSpamAgreement(true);
		
		//Creation of the third customer
		int[] adress3 = {-7,8};
		Customer customer3 = (Customer) this.createAccount("Alice", "cetina", 
		"ytR907", "0683343536","alice.cetin@gmail.com", adress);
		customer3.setSurname("Cetin");
		customer3.setBirthdayDate(new Date(1976,9,10));
		customer3.setSpamAgreement(false);
		
		//Creation of the fourth customer
		int[] adress4 = {13,13};
		Customer customer4 = (Customer) this.createAccount("Marie", "cetinm", 
		"alma79", "0751342141","marie.cetin@gmail.com", adress);
		customer4.setSurname("Cetin");
		customer4.setBirthdayDate(new Date(1979,4,6));
		customer4.setSpamAgreement(true);
		
		//Creation of the fifth customer
		int[] adress5 = {1,10};
		Customer customer5 = (Customer) this.createAccount("Monica", "cruzm", 
		"bailar", "0767458900","monica.cruz@gmail.com", adress);
		customer5.setSurname("Cruz");
		customer5.setBirthdayDate(new Date(1982,9,12));
		customer5.setSpamAgreement(true);
		
		//Creation of the sixth customer
		int[] adress6 = {20,-8};
		Customer customer6 = (Customer) this.createAccount("Vin", "dieselv", 
		"xxx", "0606060606","vin.diesel@gmail.com", adress);
		customer6.setSurname("Diesel");
		customer6.setBirthdayDate(new Date(1965,7,12));
		customer6.setSpamAgreement(false);
		
		//Creation of the seventh customer
		int[] adress7 = {18,-7};
		Customer customer7 = (Customer) this.createAccount("Martin", "hulom", 
		"ush89", "0633786511","martin.hulo@gmail.com", adress);
		customer7.setSurname("Hulo");
		customer7.setBirthdayDate(new Date(1956,2,29));
		customer7.setSpamAgreement(true);
		
		} 
		
		catch (SameUserNameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
