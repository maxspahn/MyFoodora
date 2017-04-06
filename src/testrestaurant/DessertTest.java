package testrestaurant;

import static org.junit.Assert.*;

import restaurant.*;

import org.junit.Test;

/**Test of the methods of Dessert class.
 * @author jeremyaugot
 * @author maxspahn
 *
 */
public class DessertTest {

	
	/**Test the constructor and the command getName().
	 * 
	 */
	@Test
	public void constructortest() {
		Desert dessertTest = new Desert("mousse_au_chocolat");
		assertTrue(dessertTest.getName().equals("mousse_au_chocolat"));
	}
	
	/**Test the constructor and if the default price is 0.
	 * 
	 */
	@Test
	public void constructortest2() {
		Desert dessertTest = new Desert("mousse_au_chocolat");
		assertTrue(dessertTest.getPrice()==0);
	}
	
	/**Test the constructor and if the dessert is not glu.
	 * 
	 */
	@Test
	public void constructortest3() {
		Desert dessertTest = new Desert("mousse_au_chocolat");
		assertTrue(dessertTest.isGlutenFree()==false);
	}
	
	/**Test the constructor and if the dessert is not vegetarian by default.
	 * 
	 */
	@Test
	public void constructortest4() {
		Desert dessertTest = new Desert("mousse_au_chocolat");
		assertTrue(dessertTest.isVegetarian()==false);
	}
	
	
	/**Test if 2 desserts can have the same ID.
	 * 
	 */
	@Test
	public void testDifferentID() {
		Desert dessertTest = new Desert("mousse_au_chocolat");
		Desert dessertTest2 = new Desert("cake");
		assertTrue(dessertTest.getId()!=dessertTest2.getId());
	}
	
}
