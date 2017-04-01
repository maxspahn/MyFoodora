package testrestaurant;

import static org.junit.Assert.*;

import org.junit.Test;

import restaurant.Menu;
import restaurant.WrongItemAdded;

public class MealTest {

	@Test
	public void testFactoryMeal() {
		Menu menuTest = new Menu("Menu");
		try {
			menuTest.addItem("fullmeal", "fullmealtest");
			menuTest.addItem("starter", "tomato");
			menuTest.addItemToMeal("fullmealtest", "tomato");
			menuTest.addItem("dessert", "cake");
			menuTest.addItemToMeal("fullmealtest", "cake");
			
		} catch (WrongItemAdded e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(menuTest.getMeals().get(2).getDesert().getName().equals("cake"));
	}
	
	@Test
	public void testAddItemDoesNotExistToMeal(){
		Menu menuTest = new Menu("Menu");
		
		try {
			menuTest.addItem("fullmeal", "fullmealtest");
			menuTest.addItemToMeal("fullmealtest", "tomato");
		} catch (WrongItemAdded e) {
			assertTrue(true);
			
		}
		catch (ArrayIndexOutOfBoundsException e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAddItemToMealDoesNotExist(){
		Menu menuTest = new Menu("Menu");
		
		try {
			menuTest.addItem("dessert", "cake");
			menuTest.addItemToMeal("fullmealtest", "cake");
		} catch (WrongItemAdded e) {
			// TODO Auto-generated catch block
			assertTrue(true);
		}
		catch (ArrayIndexOutOfBoundsException e){
			e.printStackTrace();
		}
	}
	
	

}
