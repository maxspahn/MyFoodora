package testUser_management;

import static org.junit.Assert.*;

import org.junit.Test;

import system.MyFoodora;
import user_management.CourierFactory;
import user_management.CustomerFactory;
import user_management.ManagerFactory;
import user_management.RestaurantFactory;
import user_management.UserNotFoundException;

/**Test to activate a user.
 * @author jeremyaugot
 * @author maxspahn
 *
 */
public class TestActivateUser {

	/**Test to activate a user: check if he/she is added to the lists he belongs to.
	 * 
	 */
	@Test
	public void test() {
		
		MyFoodora myFoodora = new MyFoodora();
		myFoodora.load();
		
		assertTrue(myFoodora.getListUsers().get(3).isActivated());
		try {
			myFoodora.getListManager().get(1).disactivate("bertrandc");
		} catch (UserNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}		
		assertFalse(myFoodora.getListUsers().get(3).isActivated());
		
		try {
			myFoodora.getListManager().get(1).activateUser("bertrandc");
		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		assertTrue(myFoodora.getListUsers().get(3).isActivated());
	}

}
