package testrestaurant;

import static org.junit.Assert.*;

import org.junit.Test;

import restaurant.*;

public class FullMealTest {

	@Test
	public void constructortest() {
		FullMeal fullmealtest = new FullMeal("meal");
		assertTrue(fullmealtest.getName().equals("meal"));
	}
	
	@Test
	public void constructortest2() {
		FullMeal fullmealtest = new FullMeal("meal");
		System.out.println(fullmealtest.getPrice());
		assertTrue(fullmealtest.getPrice()==0);
	}
	
	@Test
	public void constructortest3() {
		FullMeal fullmealtest = new FullMeal("meal");
		assertTrue(fullmealtest.isGlutenFree());
	}
	
	@Test
	public void constructortest4() {
		FullMeal fullmealtest = new FullMeal("meal");
		assertTrue(fullmealtest.isVegetarian());
	}
	
	@Test
	public void testDifferentID() {
		FullMeal fullmealtest = new FullMeal("meal1");
		FullMeal fullmealtest2 = new FullMeal("meal2");
		assertTrue(fullmealtest.getId()!=fullmealtest2.getId());
	}
	
	@Test
	public void testVegetarian() throws WrongItemAdded{
		MainDish testmaindish = new MainDish("quiche");
		testmaindish.setVegetarian(false);
		
		FullMeal fullmealtest = new FullMeal("meal");
		fullmealtest.addItem(testmaindish);
		
		assertTrue(!fullmealtest.isVegetarian());
	}
	
	@Test
	public void testGlutenFree() throws WrongItemAdded{
		MainDish testmaindish = new MainDish("quiche");
		testmaindish.setGlutenFree(false);;
		
		FullMeal fullmealtest = new FullMeal("meal");
		fullmealtest.addItem(testmaindish);
		
		assertTrue(!fullmealtest.isGlutenFree());
	}
	
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
