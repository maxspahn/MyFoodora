package testrestaurant;

import static org.junit.Assert.*;

import org.junit.Test;

import restaurant.*;

public class HalfMealTest {

	@Test
	public void constructortest() {
		HalfMeal halfmealtest = new HalfMeal("Half Meal");
		assertTrue(halfmealtest.getName().equals("Half Meal"));
	}
	
	@Test
	public void constructortest2() {
		HalfMeal halfmealtest = new HalfMeal("meal");
		assertTrue(halfmealtest.getPrice()==0);
	}
	
	@Test
	public void constructortest3() {
		HalfMeal halfmealtest = new HalfMeal("meal");
		assertTrue(halfmealtest.isGlutenFree());
	}
	
	@Test
	public void constructortest4() {
		HalfMeal halfmealtest = new HalfMeal("meal");
		assertTrue(halfmealtest.isVegetarian());
	}
	
	@Test
	public void testDifferentID() {
		HalfMeal halfmealtest = new HalfMeal("meal1");
		HalfMeal halfmealtest2 = new HalfMeal("meal2");
		assertTrue(halfmealtest.getId()!=halfmealtest2.getId());
	}
	
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
	
	@Test
	public void testVegetarian() throws WrongItemAdded{
		MainDish testmaindish = new MainDish("quiche");
		testmaindish.setVegetarian(false);
		
		HalfMeal halfmealtest = new HalfMeal("meal");
		halfmealtest.addItem(testmaindish);
		
		assertTrue(!halfmealtest.isVegetarian());
	}
	
	@Test
	public void testGlutenFree() throws WrongItemAdded{
		MainDish testmaindish = new MainDish("quiche");
		testmaindish.setGlutenFree(false);;
		
		HalfMeal halfmealtest = new HalfMeal("meal");
		halfmealtest.addItem(testmaindish);
		
		assertTrue(!halfmealtest.isGlutenFree());
	}
	
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
