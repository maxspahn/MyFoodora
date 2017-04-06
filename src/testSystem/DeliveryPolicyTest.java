package testSystem;

import static org.junit.Assert.*;

import org.junit.Test;

import restaurant.ItemDoesNotExist;
import system.FairDelivery;
import system.MyFoodora;
import system.NoCourierFoundToDeliver;
import system.Order;
import system.OrderNotCompletException;
import user_management.Restaurant;
import user_management.SameUserNameException;
import user_management.UserNotFoundException;

/**Test the DeliveryPolicy class.
 * @author jeremyaugot
 * @author maxspahn
 *
 */
public class DeliveryPolicyTest {

	/**Test the fastest delivery policy: if the courier the closer from the restaurant is contacted first.
	 * 
	 */
	@Test
	public void testFastestDelivery() {
		MyFoodora m = new MyFoodora();
		m.load();
		

		m.getListCourier().get(0).setAcceptProbability(1); //To be sure that the order is always accepted in the test
		m.getListCourier().get(1).setAcceptProbability(1);
		
		//locations: valmontb -> (12,0)   courier
		//			 varam    -> (3,27)   courier
		//           rest     -> (12,-3)  restaurant
		//			 batona   -> (0,13)   customer
		
		
		//Order should be accepted by valmontb because he is the closest to the restaurant 
		Restaurant rest = m.getListRestaurant().get(0);
		Order order = new Order(m.getListCustomer().get(0),rest);
		try {
			order.AddMealToOrder("basic",1);
			order.AddSingleItemToOrder("pineapple",1);
			order.getBill();
			m.setCourierToOrder(order);
			m.closeOrder(order);
		} catch (ItemDoesNotExist e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoCourierFoundToDeliver e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (OrderNotCompletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertTrue(order.getCourier().getUserName() == "valmontb");
		
		//Check if location has been changed to the adress of the customer.
		assertTrue(order.getCourier().getLocation()[1] == 13);
		
		// new locations :	valmontb -> (0,13)   courier
		//			 		varam    -> (3,27)   courier
		//           		rest     -> (12,-3)  restaurant
		//			 		batona   -> (0,13)   customer
		
		
		//This order should be accepted by varam because she is the closest to the restaurant that is created.
		int[] adress = {4,22};
		Order order2 = null;
		try {	
			
			m.getRestaurantFactory().createAccount("testResto", "testing", "123", "0101001", "haosuteh", adress);
			Restaurant restTest = (Restaurant) m.getUser("testing");
			order2 = new Order(m.getListCustomer().get(1), restTest);
			order2.AddSingleItemToOrder("quiche",1);
			order2.AddMealToOrder("exotic",1);
			order2.getBill();
			m.setCourierToOrder(order2);
			m.closeOrder(order2);
		} catch (SameUserNameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ItemDoesNotExist e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoCourierFoundToDeliver e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (OrderNotCompletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertTrue(order2.getCourier().getUserName()== "varam");
		assertTrue(order2.getCourier().getLocation() == order2.getCustomer().getAdress());
		
	}
	
	/**Test the fair delivery policy: if the courier with the least number of delivered orders is contacted first.
	 * 
	 */
	@Test
	public void testFairDelivery(){
		MyFoodora m = new MyFoodora();
		m.load();
		
		//Acceptance probability must be 1 to make sure the tests work
		m.getListCourier().get(0).setAcceptProbability(1);
		m.getListCourier().get(1).setAcceptProbability(1);
		
		m.setDeliveryPolicy(new FairDelivery());
		
		// tests the setter and the getter and the constructor of the FairDelivery
		assertTrue(m.getDeliveryPolicy() instanceof FairDelivery);
		
		// Creation of a new order, it should be given to courier varam
		Order order= null;
		try {
			order = new Order(m.getListCustomer().get(1), m.getListRestaurant().get(2));
			order.AddMealToOrder("basic",1);
			order.AddSingleItemToOrder("pineapple",1);
			order.getBill();
			m.setCourierToOrder(order);
			m.closeOrder(order);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		assertTrue(order.getCourier().getUserName().equals("varam"));
		
		// The next Order should be given to the newly created courier newOne
		int [] adress = {0,0};
		Order order1= null;
		try {
			m.getCourierFactory().createAccount("newCo", "newOne", "1234", "9234", "maksp@tmaobli.de", adress);
			m.getListCourier().get(2).setAcceptProbability(1);
			m.getListCourier().get(2).setAvailability(true);
			order1 = new Order(m.getListCustomer().get(1), m.getListRestaurant().get(2));
			order1.AddMealToOrder("basic",1);
			order1.AddSingleItemToOrder("pineapple",1);
			order1.getBill();
			m.setCourierToOrder(order1);
			m.closeOrder(order1);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertTrue(order1.getCourier().getEmail().equals("maksp@tmaobli.de"));
		
		
		// The next order should be given to valmontb, because the acceptanceProbabilities of the two other are set to 0
		// even though he has already two orders delivered
		Order order2= null;
		try {
			m.getListCourier().get(1).setAcceptProbability(0);
			m.getListCourier().get(2).setAcceptProbability(0);
			order2 = new Order(m.getListCustomer().get(1), m.getListRestaurant().get(2));
			order2.AddMealToOrder("basic",1);
			order2.AddSingleItemToOrder("pineapple",1);
			order2.getBill();
			m.setCourierToOrder(order2);
			m.closeOrder(order2);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertTrue(order2.getCourier().getUserName().equals("valmontb"));
		
		
	}

}
