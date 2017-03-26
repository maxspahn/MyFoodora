package testrestaurant;

import static org.junit.Assert.*;

import org.junit.Test;

import restaurant.Menu;
import restaurant.WrongItemAdded;
import restaurant.WrongItemRemoved;

public class RemoveItemMealTest {

	@Test
	public void removeItemTest() {
		Menu menuTest = new Menu("Menu");
		try {
			menuTest.addItem("fullmeal", "fullmealtest");
			menuTest.addItem("starter", "tomato");
			menuTest.addItemToMeal("fullmealtest", "tomato");
			menuTest.addItem("dessert", "cake");
			menuTest.addItemToMeal("fullmealtest", "cake");
			menuTest.addItemToMeal("fullmealtest", "tartar");
			
			
		} catch (WrongItemAdded e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertTrue(menuTest.getMeal("fullmealTest").isComplete());
		
		try {
			menuTest.removeItemFromMeal("fullmealTest", "tomato");
		} catch (WrongItemRemoved e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertTrue(!menuTest.getMeal("fullMealTest").isComplete());
	}

}
