package user_management;

import static org.junit.Assert.assertTrue;

import restaurant.WrongItemAdded;
import system.MyFoodora;
import system.*;

public class Main_application {
	public static void main(String[] args) throws SameUserNameException, WrongItemAdded, RestaurantNotFoundException, WrongUserNameOrPassWordException {
		MyFoodora myFoodora = new MyFoodora();
		myFoodora.load();
		
		
		int[] adress = {1,2};
		Restaurant rest =  myFoodora.getListRestaurant().get(0);
		myFoodora.getListRestaurant().get(0).setDiscount(0.5);
		//rest.setDiscount(0.5);

		
		myFoodora.getCustomerFactory().createAccount("jeje","je", "iijijij", "098765432", "jere@gmail.com",adress);
		Order order;

		try {
			Customer cust= (Customer) myFoodora.getCustomerFactory().login("je", "iijijij");
			order = cust.newOrder("The five fields");
			order.AddSingleItemToOrder("Soup");
			
			System.out.println(order.getSingleItems());
		

		}catch (WrongUserNameOrPassWordException e){
			System.out.println(e.getMessage());
	}
	}
}
