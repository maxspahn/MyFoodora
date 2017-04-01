package testSystem;

import static org.junit.Assert.*;

import org.junit.Test;

import system.*;

public class TestBasicFidelityCard {

	@Test
	public void ClassicPricetest() {
		MyFoodora myFoodora = new MyFoodora();
		myFoodora.load();
		Order order = myFoodora.getCompleteOrders().get(0);
		double price = order.getPrice();
		double computedPrice = 0.95*(2+11.3)+3.1;
		assertTrue(price==computedPrice);
	}
	 
	@Test
	public void MealOfTheWeekTest(){
		MyFoodora myFoodora = new MyFoodora();
		myFoodora.load();
		Order order = myFoodora.getCompleteOrders().get(1);
		double price = order.getPrice();
		double computedPrice = 0.9*(2.3+6.7+2.5)+3.1+3;
		assertTrue(price==computedPrice);
		
	}
	
	@Test
	public void NbHalfMealTest(){
		MyFoodora myFoodora = new MyFoodora();
		myFoodora.load();
		assertTrue(myFoodora.getNbOfHalfMeals(halfMeal))
	}
}
