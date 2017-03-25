package testrestaurant;

import static org.junit.Assert.*;

import restaurant.*;

import org.junit.Test;

public class StarterTest {

	@Test
	public void constructortest() {
		Starter startertest = new Starter("Tomato");
		assertTrue(startertest.getName().equals("Tomato"));
	}
	
	
	@Test
	public void constructortest2() {
		Starter startertest = new Starter("Tomato");
		assertTrue(startertest.getPrice()==0);
	}
	
	@Test
	public void constructortest3() {
		Starter startertest = new Starter("Tomato");
		assertTrue(startertest.isGlutenFree()==false);
	}
	
	@Test
	public void constructortest4() {
		Starter startertest = new Starter("Tomato");
		assertTrue(startertest.isVegetarian()==false);
	}
	
	@Test
	public void testDifferentID() {
		Starter startertest = new Starter("Tomato");
		Starter startertest2 = new Starter("Salad");
		assertTrue(startertest.getId()!=startertest2.getId());
	}

}
