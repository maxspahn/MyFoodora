package user_management;

import static org.junit.Assert.assertTrue;
import system.MyFoodora;

public class Main_application {
	public static void main(String[] args) throws SameUserNameException {
		MyFoodora myFoodora = new MyFoodora();
		myFoodora.load();
		
		
		int[] adress = {1,2};
		Restaurant rest =  myFoodora.getListRestaurant().get(0);
		myFoodora.getListRestaurant().get(0).setDiscount(0.5);
		//rest.setDiscount(0.5);
		
		
		Customer cust = (Customer) myFoodora.getCustomerFactory().createAccount("jeje","je", "iijijij", "098765432", "jere@gmail.com",adress);
		System.out.println(cust.getNotifications());
		

		cust.setSpamAgreement(true);

		
		rest.setDiscount(0.3);
		rest.setMealOfTheWeek("FullMeal");
		
		System.out.println(cust.getNotifications());
		
		System.out.println(cust.getNotifications());
		
	}
}
