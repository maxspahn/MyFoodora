package testUser_management;

import static org.junit.Assert.*;

import org.junit.Test;

import user_management.CourierFactory;
import user_management.CustomerFactory;
import user_management.ManagerFactory;
import user_management.RestaurantFactory;

public class TestActivateUser {

	@Test
	public void test() {

		ManagerFactory manFac = new ManagerFactory();
		RestaurantFactory resFac = new RestaurantFactory();
		CustomerFactory custFac = new CustomerFactory();
		CourierFactory courFac = new CourierFactory();
		
		
		
		assertTrue(manFac.getManagerList().get(0).getMyFoodora().listUsers.get(3).isActivated());
		manFac.getManagerList().get(1).disactivate("bertrandc");		
		assertFalse(manFac.getManagerList().get(0).getMyFoodora().listUsers.get(3).isActivated());
		
		manFac.getManagerList().get(1).activateUser("bertrandc");		
		assertTrue(manFac.getManagerList().get(0).getMyFoodora().listUsers.get(3).isActivated());
	}

}
