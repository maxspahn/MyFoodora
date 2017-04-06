package testSystem;

import static org.junit.Assert.*;

import org.junit.Test;

import system.*;

/**Test the BasicFidelityCard class.
 * @author jeremyaugot
 * @author maxspahn
 *
 */
public class TestBasicFidelityCard {

	/**Test if the price of the meal named "Classic" is well computed, taking into account the 5% discount.
	 * 
	 */
	@Test
	public void ClassicPricetest() {
		MyFoodora myFoodora = new MyFoodora();
		myFoodora.load();
		Order order = myFoodora.getCompleteOrders().get(0);
		double price = order.getPrice();
		double computedPrice = 0.95*(2+11.3)+3.1;
		assertTrue(price==computedPrice);
	}
	 
	/**Test if the price of the meal of the week is well computed, taking into account the 10% discount.
	 * 
	 */
	@Test
	public void MealOfTheWeekTest(){
		MyFoodora myFoodora = new MyFoodora();
		myFoodora.load();
		Order order = myFoodora.getCompleteOrders().get(1);
		double price = order.getPrice();
		double computedPrice = 0.9*(2.3+6.7+2.5)+3.1+3;
		assertTrue(price==computedPrice);
		
	}
	
}
