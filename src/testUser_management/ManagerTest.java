package testUser_management;

import static org.junit.Assert.*;

import org.junit.Test;

import system.MyFoodora;
import user_management.Manager;
import user_management.ManagerFactory;
import user_management.SameUserNameException;
import user_management.User;
import user_management.WrongUserNameOrPassWordException;

public class ManagerTest {
	

	@Test
	public void testManagerCreation() {
		int [] coord = {12,12};
		MyFoodora myFoodora = new MyFoodora();
		myFoodora.load();
		
		try {
			myFoodora.getManagerFactory().createAccount("maxspahn", "max3", "wer123", "001230123", "maxs@pmal.de", coord);
		} catch (SameUserNameException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
		assertEquals(17, myFoodora.getListUsers().size());

		Manager manager = (Manager) myFoodora.getListManager().get(0);
		assertTrue(manager.getRole().equals("CEO"));
		
		User theSame = null;
		try {
			myFoodora.getManagerFactory().createAccount("maxlustig", "max3", "peter", "0899000", "maxlustig", coord);
			theSame = myFoodora.getManagerFactory().login("max3", "peter");
		} catch (SameUserNameException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		catch (WrongUserNameOrPassWordException e){
			System.out.println(e.getMessage());
		}
		
		assertNull(theSame);

		assertEquals(17, myFoodora.getListUsers().size());
		
		
		
		
		
		
	}
	

}
