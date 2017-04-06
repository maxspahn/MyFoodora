package testUser_management;

import static org.junit.Assert.*;

import org.junit.Test;

import system.MyFoodora;
import user_management.ManagerFactory;
import user_management.RestaurantFactory;
import user_management.SameUserNameException;
import user_management.User;
import user_management.UserNotFoundException;

/**Test to remove a user from MyFoodora system.
 * @author jeremyaugot
 * @author maxspahn
 *
 */
public class RemoveUser {

	/**Test to remove a user from MyFoodora system: check if the user if well removed from the lists.
	 * 
	 */
	@Test
	public void test() {
		
		int [] coord = {12,12};
		MyFoodora myFoodora = new MyFoodora();
		myFoodora.load();
		
		
		// Create a new manager with the factory and test if it is added to the list
		try {
			myFoodora.getManagerFactory().createAccount("maxspahn", "max3", "wer123", "001230123", "maxs@pmal.de", coord);
		} catch (SameUserNameException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		assertEquals(17,myFoodora.getListUsers().size());
		
		try{
		// The CEO removes the new manager that has been created.
		myFoodora.getListManager().get(0).removeUser("max3");
		assertEquals(16,myFoodora.getListUsers().size());
		
		
		// Create a new manager and try to remove itself, it should not work, because a manager is not
		// allowed to remove another manager.
		try {
			myFoodora.getManagerFactory().createAccount("maxspahn", "max4", "wer123", "001230123", "maxs@pmal.de", coord);
		} catch (SameUserNameException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}		
		myFoodora.getListManager().get(1).removeUser("max4");
		assertEquals(17,myFoodora.getListUsers().size());	
		
		// Create a new restaurant and let the CEO remove it
		try {
			myFoodora.getRestaurantFactory().createAccount("maxspahn", "McDonalds", "wer123", "001230123", "maxs@pmal.de", coord);
		} catch (SameUserNameException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}		

		assertEquals(18,myFoodora.getListUsers().size());	
		myFoodora.getListManager().get(1).removeUser("McDonalds");
		assertEquals(17,myFoodora.getListUsers().size());	

		// CEO removes a manager.
		myFoodora.getListManager().get(0).removeUser("max4");
		assertEquals(16,myFoodora.getListUsers().size());
		
		// CEO tries to remove himself.
		myFoodora.getListManager().get(0).removeUser("sparrowj");
		assertEquals(16,myFoodora.getListManager().get(0).getMyFoodora().getListUsers().size());
		}
		catch (UserNotFoundException e){
			System.out.println(e.getMessage());
		}
		
	}

}
