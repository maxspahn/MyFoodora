package testrestaurant;

import static org.junit.Assert.*;

import restaurant.*;

import org.junit.Test;

/**Test the Starter class.
 * @author jeremyaugot
 * @author maxspahn
 *
 */
public class StarterTest {

	/**Test the constructor and if the name of the created starter is correct.
	 * 
	 */
	@Test
	public void constructortest() {
		Starter startertest = new Starter("Tomato");
		assertTrue(startertest.getName().equals("Tomato"));
	}
	
	
	/**Test if the default price of a new starter is equals to 0.
	 * 
	 */
	@Test
	public void constructortest2() {
		Starter startertest = new Starter("Tomato");
		assertTrue(startertest.getPrice()==0);
	}
	
	/**Test if a new starter is non-gulten free by default.
	 * 
	 */
	@Test
	public void constructortest3() {
		Starter startertest = new Starter("Tomato");
		assertTrue(startertest.isGlutenFree()==false);
	}
	
	/**Test if a new starter if non-vegetarian by default.
	 * 
	 */
	@Test
	public void constructortest4() {
		Starter startertest = new Starter("Tomato");
		assertTrue(startertest.isVegetarian()==false);
	}
	
	/**Test if 2 starters can have the same ID.
	 * 
	 */
	@Test
	public void testDifferentID() {
		Starter startertest = new Starter("Tomato");
		Starter startertest2 = new Starter("Salad");
		assertTrue(startertest.getId()!=startertest2.getId());
	}

	/**Test if the starter list is well created.
	 * 
	 */
	@Test
	public void testListMainDish() {
		Menu menu = new Menu("Menu");
		assertTrue(menu.getStarters().get(2).getName().equals("Quiche"));
	}
}
