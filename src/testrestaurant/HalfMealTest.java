package testrestaurant;

import static org.junit.Assert.*;

import org.junit.Test;

import restaurant.*;

/**Test the HalfMeal class.
 * @author jeremyaugot
 * @author maxspahn
 *
 */
public class HalfMealTest {

	/**Test the constructor and if the name of the created product is correct.
	 * 
	 */
	@Test
	public void constructortest() {
		HalfMeal halfmealtest = new HalfMeal("Half Meal");
		assertTrue(halfmealtest.getName().equals("Half Meal"));
	}
	
	/**Test if the default price of the created product is 0.
	 * 
	 */
	@Test
	public void constructortest2() {
		HalfMeal halfmealtest = new HalfMeal("meal");
		assertTrue(halfmealtest.getPrice()==0);
	}
	
	/**Test if a new half meal is gluten free by default.
	 * 
	 */
	@Test
	public void constructortest3() {
		HalfMeal halfmealtest = new HalfMeal("meal");
		assertTrue(halfmealtest.isGlutenFree());
	}
	
	/**Test if a new half meal is vegetarian by default.
	 * 
	 */
	@Test
	public void constructortest4() {
		HalfMeal halfmealtest = new HalfMeal("meal");
		assertTrue(halfmealtest.isVegetarian());
	}
	
	/**Test if 2 half meal can have the same ID.
	 * 
	 */
	@Test
	public void testDifferentID() {
		HalfMeal halfmealtest = new HalfMeal("meal1");
		HalfMeal halfmealtest2 = new HalfMeal("meal2");
		assertTrue(halfmealtest.getId()!=halfmealtest2.getId());
	}
	
	/**Test if it is possible to add a dessert and a starter to a half meal.
	 * 
	 */
	@Test
	public void testDesertAndStarter() {
		try{
		HalfMeal halfmealtest = new HalfMeal("meal");
		halfmealtest.addItem(new Starter("Tomato"));
		halfmealtest.addItem(new Desert("Cake"));
		assertTrue(false);
		}
		catch(WrongItemAdded e){assertTrue(true);}
	}
	
	/**Test if it is possible to add a dessert and a main dish to a half meal.
	 * 
	 */
	@Test
	public void testDesertAndMainDish() {
		try{
		HalfMeal halfmealtest = new HalfMeal("meal");
		halfmealtest.addItem(new MainDish("quiche"));
		halfmealtest.addItem(new Desert("Cake"));
		assertTrue(halfmealtest.getDesert().getName().equals("Cake")&&halfmealtest.getMainDish().getName().equals("quiche"));
		}
		catch(WrongItemAdded e){assertTrue(false);}
	}
	
	/**Test if it is possible to add a starter and a main dish to a half meal.
	 * 
	 */
	@Test
	public void testStarterAndMainDish() {
		try{
		HalfMeal halfmealtest = new HalfMeal("meal");
		halfmealtest.addItem(new MainDish("quiche"));
		halfmealtest.addItem(new Starter("Tomato"));
		assertTrue(halfmealtest.getStarter().getName().equals("Tomato")&&halfmealtest.getMainDish().getName().equals("quiche"));
		}
		catch(WrongItemAdded e){assertTrue(false);}
	}
	
	/**Test if it is possible to add a starter, a main dish and a dessert to a half meal.
	 * 
	 */
	@Test
	public void testStarterAndMainDishAndDesert() {
		try{
		HalfMeal halfmealtest = new HalfMeal("meal");
		halfmealtest.addItem(new MainDish("quiche"));
		halfmealtest.addItem(new Starter("Tomato"));
		halfmealtest.addItem(new Desert("cake"));
		
		assertTrue(false);
		}
		catch(WrongItemAdded e){assertTrue(true);}
	}
	
	/**Test if it is possible to add 2 starters to a half meal.
	 * 
	 */
	@Test
	public void testStarterAndStarter() {
		try{
		HalfMeal halfmealtest = new HalfMeal("meal");
		halfmealtest.addItem(new Starter("Tomato"));
		halfmealtest.addItem(new Starter("Salad"));
		
		assertTrue(false);
		}
		catch(WrongItemAdded e){assertTrue(true);}
	}
	
	/**Test if a half meal becomes non-vegetarian while adding non-vegetarian items.
	 * @throws WrongItemAdded if the item cannot be added to the half meal.
	 */
	@Test
	public void testVegetarian() throws WrongItemAdded{
		MainDish testmaindish = new MainDish("quiche");
		testmaindish.setVegetarian(false);
		
		HalfMeal halfmealtest = new HalfMeal("meal");
		halfmealtest.addItem(testmaindish);
		
		assertTrue(!halfmealtest.isVegetarian());
	}
	
	/**Test if a half meal becomes non-gluten free while adding non-gluten free items.
	 * @throws WrongItemAdded if the item cannot be added to the half meal.
	 */
	@Test
	public void testGlutenFree() throws WrongItemAdded{
		MainDish testmaindish = new MainDish("quiche");
		testmaindish.setGlutenFree(false);;
		
		HalfMeal halfmealtest = new HalfMeal("meal");
		halfmealtest.addItem(testmaindish);
		
		assertTrue(!halfmealtest.isGlutenFree());
	}
	
	/**Test if the price of a half meal is correct knowing the price of the items it contains.
	 * @throws WrongItemAdded if the item cannot be added to the half meal.
	 */
	@Test
	public void testPrice() throws WrongItemAdded{
		HalfMeal halfmealtest = new HalfMeal("meal");
		MainDish testmaindish = new MainDish("quiche");
		testmaindish.setPrice(3);
		Starter teststarter = new Starter("tomato");
		teststarter.setPrice(2);
		
		halfmealtest.addItem(teststarter);
		halfmealtest.addItem(testmaindish);
		
		assertTrue(halfmealtest.getPrice()==0.95*(teststarter.getPrice()+testmaindish.getPrice()));
		
	}

}
