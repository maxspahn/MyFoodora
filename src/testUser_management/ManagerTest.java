package testUser_management;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

import system.MyFoodora;
import user_management.Manager;
import user_management.ManagerFactory;
import user_management.Restaurant;
import user_management.SameUserNameException;
import user_management.User;
import user_management.UserNotFoundException;
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
	
	@Test
	public void testAddUser(){
		MyFoodora myFoodora = new MyFoodora();
		myFoodora.load();
		
		int [] adress = {1,9};
		try {
			myFoodora.getManagerFactory().createAccount("patrick", "hasAGirlfriend", "peter", "0101012", "peter", adress);
		} catch (SameUserNameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		myFoodora.getListManager().get(2).addUser(new Restaurant("chez moi", "herE", "wer123", "023", "ma", adress));
		
		assertTrue(myFoodora.getListManager().get(2).getName().equals("patrick"));
		
		assertTrue(myFoodora.getListRestaurant().get(5).getName().equals("chez moi"));
 	}
	
	@Test
	public void activateUser(){
		MyFoodora mf = new MyFoodora();
		mf.load();
		
		try {
			mf.getListManager().get(0).deactivate("valmontb");
		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			assertTrue(!mf.getUser("valmontb").isActivated());
		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			mf.getListManager().get(1).activateUser("valmontb");
			assertTrue(mf.getUser("valmontb").isActivated());
		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}
	
	@Test
	public void setFees(){
		MyFoodora mf = new MyFoodora();
		mf.load();
		
		Manager manager = mf.getListManager().get(1);
		
		manager.setServiceFee(0.5);
		manager.setDeliveryCost(0.1);
		manager.setMarkup(0.9);
		
		assertTrue(mf.getService_fee()== 0.5);
		assertTrue(mf.getDelivery_cost()== 0.1);
		assertTrue(mf.getMarkup_percentage()== 0.9);
		
		
	}
	
	@Test
	public void bestAndWorst(){
		MyFoodora m = new MyFoodora();
		m.load();
		Manager manager = null;
		
		try {
			manager = (Manager) m.getUser("sparrowj");
			
		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertTrue(manager.mostSellingRestaurant().getUserName().equals("ledBury"));
		assertTrue(manager.leastSellingRestaurant().getUserName().equals("fiveFields"));
				
		assertTrue(manager.mostActiveCourier().getUserName().equals("valmontb"));
		assertTrue(manager.leastActiveCourier().getUserName().equals("varam"));
	}
	
	@Test
	public void setFeesTargetPolicy(){
		MyFoodora m = new MyFoodora();
		m.load();
		Manager manager = null;
		try{
			manager = (Manager) m.getUser("spongeb");
		} catch (UserNotFoundException e){
			e.printStackTrace();
		}
		
		manager.setTargetCommands(10);
		manager.setTargetProfit(100);
		
		
		
	}
	

}
