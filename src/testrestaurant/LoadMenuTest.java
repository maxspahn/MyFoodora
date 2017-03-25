package testrestaurant;

import static org.junit.Assert.*;

import org.junit.Test;

import restaurant.Menu;

public class LoadMenuTest {

	@Test
	public void LoadSingleItemstest() {
		Menu menuTest = new Menu("Menu");
		assertTrue(menuTest.getSingleItems().get(1).getName().equals("Ceasar Salad"));
	}

	@Test
	public void LoadMealstest() {
		Menu menuTest = new Menu("Menu");
		assertTrue(menuTest.getMeals().get(1).getName().equals("Classic"));
	}

}
