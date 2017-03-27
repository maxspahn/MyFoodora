package testSystem;

import static org.junit.Assert.*;

import org.junit.Test;

import system.*;
import user_management.*;

public class TestIncomeComputation {

	@Test
	public void testOrderProcessing() {
		MyFoodora myFoodera = new MyFoodora();
		int [] adress1 = {2,5};
		int [] adress2 = {3,6};
		int [] adress3 = {1,6};
		Customer customer = new Customer("spahn", "maxspahn3", "wer123", "02303013", "maxspahn3@yahoo.de", adress1);
		Restaurant rest = new Restaurant("mcDo", "mcdonald", "mc123", "01020304", "mcdo@donald.com", adress2);
		
		myFoodera.getListCourier().add(new Courier("peter", "peter12", "peterk", "09341", "peter@gmail.com", adress3));

		Order order1 = new Order(customer, rest);
		order1.AddMealToOrder("basic");
		order1.AddSingleItemToOrder("soup");
		order1.getBill();
		
		myFoodera.getListCourier().get(0).setAcceptProbability(1);
		
		assertTrue(order1.isComplete() == false);
		try {
			myFoodera.setCourierToOrder(order1);
		} catch (NoCourierFoundToDeliver e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
		try {
			myFoodera.addOrderToCompleteOrders(order1);
		} catch (OrderNotCompletException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
		assertTrue(order1.getPrice() == 13.45);
		
		assertTrue(order1.getProfit() == 0.34);
		
		assertTrue(order1.isComplete() == true);
	}
	
	@Test
	public void testGetIncome(){
	}

}
