package testUser_management;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

import system.MyFoodora;
import system.Order;
import system.OrderNotCompletException;
import system.TargetCannotBeFullfilled;
import user_management.Manager;
import user_management.ManagerFactory;
import user_management.Restaurant;
import user_management.SameUserNameException;
import user_management.User;
import user_management.UserNotFoundException;
import user_management.WrongUserNameOrPassWordException;

/**Test the Manager class.
 * @author jeremyaugot
 * @author maxspahn
 *
 */
/**
 * @author miche
 *
 */
public class ManagerTest {
	

	/**Test the creation of a manager, and if he/she is added to the lists.
	 * 
	 */
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
	
	/**Test if a new user is added to the lists he bolongs to.
	 * 
	 */
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
	
	/**Test to activate a user.
	 * 
	 */
	@Test
	public void activateUser(){
		MyFoodora mf = new MyFoodora();
		mf.load();
		
		try {
			mf.getListManager().get(0).disactivate("valmontb");
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
	
	/**Test to change the fees in MyFoodora system thanks to the manager's command.
	 * 
	 */
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
	
	/**Test if the most and least selling restaurants are well determinated.
	 * 
	 */
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
	
	/**Test to set the delivery cost according to the target policy.
	 * 
	 */
	@Test
	public void setFeesTargetPolicy_DeliveryCost(){
		MyFoodora m = new MyFoodora();
		m.load();
		Manager manager = null;
		try{
			manager = (Manager) m.getUser("spongeb");
		} catch (UserNotFoundException e){
			e.printStackTrace();
		}
		
		//Testing without given targetValues, it takes the last month values
		try {
			manager.setDeliveryCostAccordingTargetPolicy(0.5, 2);
		} catch (TargetCannotBeFullfilled e) {
			System.out.println(e.getMessage());
		}
		System.out.println(m.getDelivery_cost());
		assertTrue(m.getDelivery_cost() == 9.44);

		
		//Testing with given targetValues
		manager.setTargetCommands(10);
		manager.setTargetProfit(100);
		try {
			manager.setDeliveryCostAccordingTargetPolicy(0.5, 2);
		} catch (TargetCannotBeFullfilled e) {
			System.out.println(e.getMessage());
		}
		assertTrue(m.getDelivery_cost() == 0.05);	
		
	}
	
	/**Test to set the markup percentage according to the target policy.
	 * 
	 */
	@Test
	public void setFeesTargetPolicy_MarkUp(){
		MyFoodora m = new MyFoodora();
		m.load();
		Manager manager = null;
		try{
			manager = (Manager) m.getUser("spongeb");
		} catch (UserNotFoundException e){
			e.printStackTrace();
		}
		
		//Testing without given targetValues, it takes the last month values
		try {
			manager.setMarkupAccordingTargetPolicy(2, 3);
		} catch (TargetCannotBeFullfilled e) {
			System.out.println(e.getMessage());
		}
		assertTrue(m.getMarkup_percentage() == 0.1);

		
		//Testing with given targetValues
		manager.setTargetCommands(10);
		manager.setTargetProfit(100);
		try {
			manager.setMarkupAccordingTargetPolicy(2, 3);
		} catch (TargetCannotBeFullfilled e) {
			System.out.println(e.getMessage());
		}
		assertTrue(m.getMarkup_percentage() == 0.68);	
		
	}
	
	/**Test to set the service fee according to the target policy.
	 * 
	 */
	@Test
	public void setFeesTargetPolicy_ServiceFee(){
		MyFoodora m = new MyFoodora();
		m.load();
		Manager manager = null;
		try{
			manager = (Manager) m.getUser("spongeb");
		} catch (UserNotFoundException e){
			e.printStackTrace();
		}
		
		//Testing without given targetValues, it takes the last month values
		// In this case no positive could be computed, so the fees stay unchanged
		try {
			manager.setServiceFeeAccordingTargetPolicy(0.4, 3);
		} catch (TargetCannotBeFullfilled e) {
			System.out.println(e.getMessage());
		}
		assertTrue(m.getService_fee() == 2);

		
		//Testing with given targetValues
		manager.setTargetCommands(10);
		manager.setTargetProfit(100);
		try {
			manager.setServiceFeeAccordingTargetPolicy(0.4, 3);
		} catch (TargetCannotBeFullfilled e) {
			System.out.println(e.getMessage());
		}		
		assertTrue(m.getService_fee() == 6.56);
		
		
		
	}
	
	
	/**Test to set the delivery policy.
	 * 
	 */
	@Test
	public void TestSetdeliveryPolicy(){
		MyFoodora m = new MyFoodora();
		m.load();
		
		Manager manager = null;
		manager = m.getListManager().get(1);
		
		manager.setDeliveryPolicy("dynamicDelivery");
		// should return fastestDelivery because the given policy does not exist.
		assertTrue(m.getDeliveryPolicy().toString().equalsIgnoreCase("fastestDelivery"));
		
		manager.setDeliveryPolicy("fairDelivery");
		// should return fairDelivery
		assertTrue(m.getDeliveryPolicy().toString().equalsIgnoreCase("FairDelivery"));
		
		manager.setDeliveryPolicy("fastest");
		// should return fairDelivery
		assertTrue(m.getDeliveryPolicy().toString().equalsIgnoreCase("fastestDelivery"));
	}
	
	
	/**Test if the incomes are well computed: total income over a period, per customer, profit.
	 * 
	 */
	@Test
	public void testComputationsIncome(){
		MyFoodora m = new MyFoodora();
		m.load();
		
		Manager manager = null;
		manager = m.getListManager().get(1);
		
		try {
			assertTrue(manager.computeIncomePerCustomerOverPeriod(1, 3, 2017, 31, 3, 2017) == m.getIncomeForMonth(3, 2017)/2);
		} catch (OrderNotCompletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		//add a new order done by one of the customers who has already did a command.
		Order order = new Order(m.getListCustomer().get(1),m.getListRestaurant().get(1));
		try {
			order.AddMealToOrder("basic",1);
			order.AddSingleItemToOrder("apple_pie",1);
			order.getBill();
			m.setCourierToOrder(order);
			m.closeOrder(order);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		try {
			//test if the new order was added
			assertTrue(m.getIncomeForMonth(5, 2017)== 12.85);
			//test if the computation takes into account if there was twice the same customer
			assertTrue(manager.computeIncomePerCustomerOverPeriod(1, 3, 2017, 31, 4, 2017) == (m.getIncomeForMonth(3, 2017) + m.getIncomeForMonth(4, 2017))/2);
			//test value in the future should be 0
			assertTrue(manager.computeIncomePerCustomerOverPeriod(01, 06, 2017, 31, 6, 2017)==0);
		} catch (OrderNotCompletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			// tests the income calculation
			assertTrue(manager.computeTotalIncomeAndProfitOverPeriod(01, 3, 2017, 31, 5, 2017)[0] == 45.04);
		} catch (OrderNotCompletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
