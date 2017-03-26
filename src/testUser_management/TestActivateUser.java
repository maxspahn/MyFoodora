package testUser_management;

import static org.junit.Assert.*;

import org.junit.Test;

import user_management.CourierFactory;
import user_management.CustomerFactory;
import user_management.ManagerFactory;
import user_management.RestaurantFactory;
import user_management.UserNotFoundException;

public class TestActivateUser {

	@Test
	public void test() {

		ManagerFactory manFac = new ManagerFactory();
		RestaurantFactory resFac = new RestaurantFactory();
		CustomerFactory custFac = new CustomerFactory();
		CourierFactory courFac = new CourierFactory();
		
		assertTrue(manFac.getManagerList().get(0).getMyFoodora().getListUsers().get(3).isActivated());
		try {
			manFac.getManagerList().get(1).disactivate("bertrandc");
		} catch (UserNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}		
		assertFalse(manFac.getManagerList().get(0).getMyFoodora().getListUsers().get(3).isActivated());
		
		try {
			manFac.getManagerList().get(1).activateUser("bertrandc");
		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		assertTrue(manFac.getManagerList().get(0).getMyFoodora().getListUsers().get(3).isActivated());
	}

}
