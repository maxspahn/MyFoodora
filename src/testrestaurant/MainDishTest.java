package testrestaurant;

import static org.junit.Assert.*;

import restaurant.*;

import org.junit.Test;


public class MainDishTest {

	@Test
	public void constructortest() {
		MainDish dessertTest = new MainDish("quiche");
		assertTrue(dessertTest.getName().equals("quiche"));
	}
	
	@Test
	public void constructortest2() {
		MainDish dessertTest = new MainDish("quiche");
		assertTrue(dessertTest.getPrice()==0);
	}
	
	@Test
	public void constructortest3() {
		MainDish dessertTest = new MainDish("quiche");
		assertTrue(dessertTest.isGlutenFree()==false);
	}
	
	@Test
	public void constructortest4() {
		MainDish dessertTest = new MainDish("quiche");
		assertTrue(dessertTest.isVegetarian()==false);
	}
	
	@Test
	public void testDifferentID() {
		MainDish dessertTest = new MainDish("quiche");
		MainDish dessertTest2 = new MainDish("quiche");
		assertTrue(dessertTest.getId()!=dessertTest2.getId());
	}

}
