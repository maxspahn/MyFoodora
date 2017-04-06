package testrestaurant;

import static org.junit.Assert.*;

import restaurant.*;

import org.junit.Test;


/**Test the MainDish class.
 * @author jeremyaugot
 * @author maxspahn
 *
 */
public class MainDishTest {

	/**Test the constructor and if the name of the created main dish is correct.
	 * 
	 */
	@Test
	public void constructortest() {
		MainDish dessertTest = new MainDish("quiche");
		assertTrue(dessertTest.getName().equals("quiche"));
	}
	
	/**Test if the default price of a main dish is 0.
	 * 
	 */
	@Test
	public void constructortest2() {
		MainDish dessertTest = new MainDish("quiche");
		assertTrue(dessertTest.getPrice()==0);
	}
	
	/**Test if a main dish is gluten free by default.
	 * 
	 */
	@Test
	public void constructortest3() {
		MainDish dessertTest = new MainDish("quiche");
		assertTrue(dessertTest.isGlutenFree()==false);
	}
	
	/**Test if a main dish is vegetarian by default.
	 * 
	 */
	@Test
	public void constructortest4() {
		MainDish dessertTest = new MainDish("quiche");
		assertTrue(dessertTest.isVegetarian()==false);
	}
	
	/**Test if 2 main dishes can have the same ID.
	 * 
	 */
	@Test
	public void testDifferentID() {
		MainDish dessertTest = new MainDish("quiche");
		MainDish dessertTest2 = new MainDish("quiche");
		assertTrue(dessertTest.getId()!=dessertTest2.getId());
	}

}
