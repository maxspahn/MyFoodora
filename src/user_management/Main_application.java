package user_management;

import static org.junit.Assert.assertTrue;

import restaurant.Menu;

import restaurant.ItemDoesNotExist;
import restaurant.WrongItemAdded;
import system.*;

public class Main_application {
	public static void main(String[] args) throws SameUserNameException, WrongItemAdded, RestaurantNotFoundException, WrongUserNameOrPassWordException, FidelityCardDoesNotExistException, ItemDoesNotExist, ItemNotInOrderException {
		MyFoodora myFoodora = new MyFoodora();
		myFoodora.load();
		Restaurant rest = myFoodora.getListRestaurant().get(1);
		rest.setMealOfTheWeek("classic");
		rest.setDiscount(0.2);
		
		Customer cust = myFoodora.getListCustomer().get(0);
		
		System.out.println(cust.readNotifications());
		System.out.println(cust.getNotifications());
		
	}
	
}
