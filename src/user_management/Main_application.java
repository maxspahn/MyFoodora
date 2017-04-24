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
		Restaurant rest = myFoodora.getListRestaurant().get(0);
		Menu menu = new Menu("Menu");
		System.out.println(myFoodora.getListUsers());
	}
	
}
