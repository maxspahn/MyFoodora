package testUser_management;

import static org.junit.Assert.*;

import org.junit.Test;

import user_management.ManagerFactory;
import user_management.RestaurantFactory;
import user_management.SameUserNameException;
import user_management.UserNotFoundException;

public class RemoveUser {

	@Test
	public void test() {
		
		int [] coord = {12,12};
		ManagerFactory manFac = new ManagerFactory();
		RestaurantFactory resFac = new RestaurantFactory();
		
		
		// Create a new manager with the factory and test if it is added to the list
		try {
			manFac.createAccount("maxspahn", "max3", "wer123", "001230123", "maxs@pmal.de", coord);
		} catch (SameUserNameException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		assertEquals(3,manFac.getManagerList().get(2).getMyFoodora().getListUsers().size());
		
		try{
		// The CEO removes the new manager that has been created.
		manFac.getManagerList().get(0).removeUser("max3");
		assertEquals(2,manFac.getManagerList().get(2).getMyFoodora().getListUsers().size());
		
		
		// Create a new manager and try to remove itself, it should not work, because a manager is not
		// allowed to remove another manager.
		try {
			manFac.createAccount("maxspahn", "max4", "wer123", "001230123", "maxs@pmal.de", coord);
		} catch (SameUserNameException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}		
		manFac.getManagerList().get(1).removeUser("max4");
		assertEquals(3,manFac.getManagerList().get(0).getMyFoodora().getListUsers().size());	
		
		// Create a new restaurant and let the CEO remove it
		try {
			resFac.createAccount("maxspahn", "McDonalds", "wer123", "001230123", "maxs@pmal.de", coord);
		} catch (SameUserNameException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}		
		assertEquals(4,manFac.getManagerList().get(0).getMyFoodora().getListUsers().size());	
		manFac.getManagerList().get(1).removeUser("McDonalds");
		assertEquals(3,manFac.getManagerList().get(0).getMyFoodora().getListUsers().size());	
		
		// CEO removes a manager.
		manFac.getManagerList().get(0).removeUser("max4");
		assertEquals(2,manFac.getManagerList().get(0).getMyFoodora().getListUsers().size());
		
		// CEO tries to remove himself.
		manFac.getManagerList().get(0).removeUser("sparrowj");
		assertEquals(2,manFac.getManagerList().get(0).getMyFoodora().getListUsers().size());
		}
		catch (UserNotFoundException e){
			System.out.println(e.getMessage());
		}
		
	}

}
