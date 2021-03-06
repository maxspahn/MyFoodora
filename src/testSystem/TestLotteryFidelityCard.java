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

/**Test the LotteryFidelityCard class.
 * @author jeremyaugot.
 * @author maxspahn
 *
 */
public class TestLotteryFidelityCard {
	
	/**Test if the price of a meal is well computed, which means that there is no reduction on the meal of the week and the price can be 0 if the customer wins the lottery.
	 * 
	 */
	@Test
	public void priceTest(){
		MyFoodora myFoodora = new MyFoodora();
		myFoodora.load();
		Restaurant rest = myFoodora.getListRestaurant().get(0);
		try{
		Order order = new Order(myFoodora.getListCustomer().get(0), rest);
		order.AddMealToOrder("basic",1); //Basic is the meal of the week
		order.getCustomer().setFidelityCard("lotteryfidelitycard");
		order.getBill();
		myFoodora.getListCourier().get(0).setAcceptProbability(1); //To be sure that the order is always accepted in the test
		myFoodora.setCourierToOrder(order);
		myFoodora.closeOrder(order);
		assertTrue(order.getPrice()== ((double) Math.round(0.95*(2.3+6.7+2.5) * 100))/100 ||order.getPrice()==0);}
		
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
