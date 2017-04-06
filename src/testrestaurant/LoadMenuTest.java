package testrestaurant;

import static org.junit.Assert.*;

import org.junit.Test;

import restaurant.Menu;

/**Test the load of the items/meals in a menu.
 * @author jeremyaugot
 * @author maxspahn
 *
 */
public class LoadMenuTest {

	/**Test if the load of items in the menu works.
	 * 
	 */
	@Test
	public void LoadSingleItemstest() {
		Menu menuTest = new Menu("Menu");
		assertTrue(menuTest.getSingleItems().get(1).getName().equals("Ceasar Salad"));
	}

	/**Test if the load of meals in the menu works.
	 * 
	 */
	@Test
	public void LoadMealstest() {
		Menu menuTest = new Menu("Menu");
		assertTrue(menuTest.getMeals().get(1).getName().equals("Classic"));
	}

}
