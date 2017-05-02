package testrestaurant;

import static org.junit.Assert.*;

import org.junit.Test;

import restaurant.ItemDoesNotExist;
import restaurant.Menu;
import restaurant.WrongItemAdded;

/**Test the Meal class.
 * @author jeremyaugot
 * @author maxspahn
 *
 */
public class MealTest {

	/**Test if the factory pattern works by creating new meals.
	 * 
	 */
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
	
	/**Test to add an item which does not exist in a meal.
	 * 
	 */
	@Test
	public void testAddItemDoesNotExistToMeal(){
		Menu menuTest = new Menu("Menu");
		
		try {
			menuTest.addItem("fullmeal", "LocalAdvise");
			menuTest.addItemToMeal("LocalAdvise", "tomato"); //The item "tomato" does not exist
			assertTrue(false); 
		} catch (WrongItemAdded e) {
			e.getMessage();
		}
		catch (ArrayIndexOutOfBoundsException e){
			e.getMessage();
		} catch (ItemDoesNotExist e) {
			//The exception expected to be thrown
			assertTrue(true);
		}
	}
	
	/**Test to add an item to a meal which does not exist.
	 * 
	 */
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
		catch (ArrayIndexOutOfBoundsException e){
			e.printStackTrace();
		}
	}
}
