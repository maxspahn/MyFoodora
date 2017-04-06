package testrestaurant;

import static org.junit.Assert.*;

import restaurant.*;

import org.junit.Test;

public class DessertTest {

	@Test
	public void constructortest() {
		Desert dessertTest = new Desert("mousse_au_chocolat");
		assertTrue(dessertTest.getName().equals("mousse_au_chocolat"));
	}
	
	@Test
	public void constructortest2() {
		Desert dessertTest = new Desert("mousse_au_chocolat");
		assertTrue(dessertTest.getPrice()==0);
	}
	
	@Test
	public void constructortest3() {
		Desert dessertTest = new Desert("mousse_au_chocolat");
		assertTrue(dessertTest.isGlutenFree()==false);
	}
	
	@Test
	public void constructortest4() {
		Desert dessertTest = new Desert("mousse_au_chocolat");
		assertTrue(dessertTest.isVegetarian()==false);
	}
	
	@Test
	public void testDifferentID() {
		Desert dessertTest = new Desert("mousse_au_chocolat");
		Desert dessertTest2 = new Desert("cake");
		assertTrue(dessertTest.getId()!=dessertTest2.getId());
	}
	
}
