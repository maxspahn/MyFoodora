package testSystem;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import restaurant.ItemDoesNotExist;
import system.MyFoodora;
import system.NoCourierFoundToDeliver;
import system.Order;
import system.OrderNotCompletException;
import user_management.FidelityCardDoesNotExistException;
import user_management.Restaurant;

public class TestLotteryFidelityCard {
	
	@Test
	public void priceTest(){
		MyFoodora myFoodora = new MyFoodora();
		myFoodora.load();
		Restaurant rest = myFoodora.getListRestaurant().get(0);
		try{
		Order order = new Order(myFoodora.getListCustomer().get(0), rest);
		order.AddMealToOrder("basic",1);
		order.getCustomer().setFidelityCard("lotteryfidelitycard");
		order.getBill();
		myFoodora.getListCourier().get(0).setAcceptProbability(1); //To be sure that the order is always accepted in the test
		myFoodora.setCourierToOrder(order);
		myFoodora.closeOrder(order);
		assertTrue(order.getPrice()== 0.95*(2.3+6.7+2.5)||order.getPrice()==0);}
		
		catch(NoCourierFoundToDeliver e){
			e.getMessage();
		}
		catch(OrderNotCompletException e){
			e.getMessage();
		}
		catch(ItemDoesNotExist e){
			e.getMessage();
		}
		catch(FidelityCardDoesNotExistException e){
			e.getMessage();
		}
	}

}
