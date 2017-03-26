package testUser_management;

import static org.junit.Assert.*;

import org.junit.Test;

import user_management.Manager;
import user_management.ManagerFactory;
import user_management.SameUserNameException;
import user_management.User;

public class ManagerTest {
	
	private ManagerFactory manFac;
	
	public ManagerTest(){
		this.manFac = new ManagerFactory();
	}

	@Test
	public void testManagerCreation() {
		int [] coord = {12,12};
		
		try {
			this.manFac.createAccount("maxspahn", "max3", "wer123", "001230123", "maxs@pmal.de", coord);
		} catch (SameUserNameException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
		assertEquals(3, this.manFac.getManagerList().get(2).getMyFoodora().getListUsers().size());

		Manager manager = (Manager) this.manFac.getManagerList().get(0).getMyFoodora().getListUsers().get(0);
		assertTrue(manager.getRole().equals("CEO"));
		
		User theSame = null;
		try {
			theSame = this.manFac.createAccount("maxlustig", "max3", "peter", "0899000", "maxlustig", coord);
		} catch (SameUserNameException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
		assertNull(theSame);

		assertEquals(3, this.manFac.getManagerList().get(2).getMyFoodora().getListUsers().size());
		
		
		
		
		
		
	}
	

}
