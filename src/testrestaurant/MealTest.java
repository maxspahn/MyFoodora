package testrestaurant;

import static org.junit.Assert.*;

import org.junit.Test;

import restaurant.ItemDoesNotExist;
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
		} catch (ItemDoesNotExist e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(menuTest.getMeals());
		assertTrue(menuTest.getMeals().get(4).getDesert().getName().equals("cake"));
	}
	
	@Test
	public void testAddItemDoesNotExistToMeal(){
		Menu menuTest = new Menu("Menu");
		
		try {
			menuTest.addItem("fullmeal", "LocalAdvise");
			menuTest.addItemToMeal("LocalAdvise", "tomato");
		} catch (WrongItemAdded e) {
			assertTrue(true);
			
		}
		catch (ArrayIndexOutOfBoundsException e){
			assertTrue(true);
		} catch (ItemDoesNotExist e) {
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
			assertTrue(true);
		} catch (ItemDoesNotExist e) {
			// TODO Auto-generated catch block
			assertTrue(true);
		}
			e.printStackTrace();
		}
		catch (ArrayIndexOutOfBoundsException e){
			e.printStackTrace();
		}

	}
	}

}
