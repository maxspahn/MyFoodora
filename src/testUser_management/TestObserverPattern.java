package testUser_management;

import static org.junit.Assert.*;

import org.junit.Test;

import restaurant.ItemDoesNotExist;
import system.MyFoodora;
import user_management.Customer;

/**Test the observer pattern.
 * @author jeremyaugot
 * @author maxspahn
 *
 */
public class TestObserverPattern {

	/**Test if a customer receives a notification when a restaurant sets a new meal of the week.
	 * 
	 */
	@Test
	public void setMealOfTheWeektest() {
		MyFoodora m = new MyFoodora();
		m.load();
		try {
			m.getListRestaurant().get(0).setMealOfTheWeek("Classic");
		} catch (ItemDoesNotExist e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Customer cust = m.getListCustomer().get(0);
		assertTrue(cust.getNotificationNumber()==1);
	}
	
	/**Test if a customer receives a notification when a restaurant sets a new special discount.
	 * 
	 */
	@Test
	public void setDiscounttest() {
		MyFoodora m = new MyFoodora();
		m.load();
	
		m.getListRestaurant().get(0).setDiscount(0.2);
		
		Customer cust = m.getListCustomer().get(0);
		assertTrue(cust.getNotificationNumber()==1);
	}
	
	/**Test if the notifications disappear once they are read.
	 * 
	 */
	@Test
	public void readNotificationtest() {
		MyFoodora m = new MyFoodora();
		m.load();
		try {
			m.getListRestaurant().get(0).setMealOfTheWeek("Classic");
		} catch (ItemDoesNotExist e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Customer cust = m.getListCustomer().get(0);
		
		cust.readNotifications();
		
		assertTrue(cust.getNotifications().size()==1);
	}
	
	/**Test if the two notifications disappear once they are read.
	 * 
	 */
	@Test
	public void readTwoNotificationtest() {
		MyFoodora m = new MyFoodora();
		m.load();
		try {
			m.getListRestaurant().get(0).setMealOfTheWeek("Classic");
			m.getListRestaurant().get(1).setDiscount(0.2);
		} catch (ItemDoesNotExist e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Customer cust = m.getListCustomer().get(0);
		
		cust.readNotifications();
		
		assertTrue(cust.getNotifications().size()==1);
	}
}
