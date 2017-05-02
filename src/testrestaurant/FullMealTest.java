package testrestaurant;

import static org.junit.Assert.*;

import org.junit.Test;

import restaurant.*;

/**Test the FullMeal class.
 * @author jeremyaugot
 * @author maxspahn
 *
 */
public class FullMealTest {

	/**Test the constructor.
	 * 
	 */
	@Test
	public void constructortest() {
		FullMeal fullmealtest = new FullMeal("meal");
		assertTrue(fullmealtest.getName().equals("meal"));
	}
	
	/**Test if the default price is 0.
	 * 
	 */
	@Test
	public void constructortest2() {
		FullMeal fullmealtest = new FullMeal("meal");
		System.out.println(fullmealtest.getPrice());
		assertTrue(fullmealtest.getPrice()==0);
	}
	
	/**Test if by default a full meal is not gluten free.
	 * 
	 */
	@Test
	public void constructortest3() {
		FullMeal fullmealtest = new FullMeal("meal");
		assertTrue(fullmealtest.isGlutenFree());
	}
	
	/**Test by default if a full meal is not vegetarian.
	 * 
	 */
	@Test
	public void constructortest4() {
		FullMeal fullmealtest = new FullMeal("meal");
		assertTrue(fullmealtest.isVegetarian());
	}
	
	/**Test if 2 full meals can have the same ID.
	 * 
	 */
	@Test
	public void testDifferentID() {
		FullMeal fullmealtest = new FullMeal("meal1");
		FullMeal fullmealtest2 = new FullMeal("meal2");
		assertTrue(fullmealtest.getId()!=fullmealtest2.getId());
	}
	
	/**Test if a meal becomes non-vegetarian when a non-vegetarian item is added.
	 * @throws WrongItemAdded if the item cannot be added to the meal.
	 */
	@Test
	public void testVegetarian() throws WrongItemAdded{
		MainDish testmaindish = new MainDish("quiche");
		testmaindish.setVegetarian(false);
		
		FullMeal fullmealtest = new FullMeal("meal");
		fullmealtest.addItem(testmaindish);
		
		assertTrue(!fullmealtest.isVegetarian());
	}
	
	/**Test if a meal becomes non-gluten free when a non-gluten free item is added.
	 * @throws WrongItemAdded if the item cannot be added to the meal.
	 */
	@Test
	public void testGlutenFree() throws WrongItemAdded{
		MainDish testmaindish = new MainDish("quiche");
		testmaindish.setGlutenFree(false);;
		
		FullMeal fullmealtest = new FullMeal("meal");
		fullmealtest.addItem(testmaindish);
		
		assertTrue(!fullmealtest.isGlutenFree());
	}
	
	/**Test if the price of meal is correct, knowing the price of the items it contains.
	 * @throws WrongItemAdded if the item cannot be added to the meal.
	 */
	@Test
	public void testPrice() throws WrongItemAdded{
		FullMeal fullmealtest = new FullMeal("meal");
		MainDish testmaindish = new MainDish("quiche");
		testmaindish.setPrice(3);
		Starter teststarter = new Starter("tomato");
		teststarter.setPrice(2);
		Desert testdesert = new Desert("cake");
		testdesert.setPrice(4);
		fullmealtest.addItem(testdesert);
		
		fullmealtest.addItem(teststarter);
		fullmealtest.addItem(testmaindish);
		
		
		assertTrue(fullmealtest.getPrice()==0.95*(teststarter.getPrice()+testmaindish.getPrice()+testdesert.getPrice()));
		
	}
	
	/**Test to add 2 desserts to the same meal.
	 * 
	 */
	@Test
	public void test2deserts(){
		FullMeal fullmealtest = new FullMeal("meal");
		MainDish testmaindish = new MainDish("quiche");
		Starter teststarter = new Starter("tomato");;
		Desert testdesert = new Desert("cake");
		Desert testdesert2 = new Desert("cake2");
		
		try {
			fullmealtest.addItem(testdesert);
			fullmealtest.addItem(teststarter);
			fullmealtest.addItem(testmaindish);
			fullmealtest.addItem(testdesert2);
			assertTrue(false);
		} catch (WrongItemAdded e) {
			assertTrue(true);
		}		
	}
}
