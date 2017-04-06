package testSystem;

import static org.junit.Assert.*;

import org.junit.Test;

import restaurant.FullMeal;
import restaurant.ItemDoesNotExist;
import restaurant.MainDish;
import restaurant.SingleItem;
import restaurant.WrongItemAdded;
import system.MyFoodora;
import system.NoCourierFoundToDeliver;
import system.Order;
import system.OrderNotCompletException;
import system.PointFidelityCard;
import user_management.FidelityCardDoesNotExistException;
import user_management.Restaurant;

/**Test the PointFidelityCard class.
 * @author jeremyaugot
 * @author maxspahn
 *
 */
public class TestPointFidelityCard {

	/**Test if the number of points is well computed, knowing that a customer can win 1 point each 10€ spent.
	 * 
	 */
	@Test
	public void PointTest() {
		
		MyFoodora myFoodora = new MyFoodora();
		myFoodora.load();
		Restaurant rest = myFoodora.getListRestaurant().get(0);
		try{
		rest.getMenu().addItem("Starter", "camemberg");
		rest.getMenu().addPriceTag("camemberg", 145);
		Order order = new Order(myFoodora.getListCustomer().get(0), rest);
		order.AddSingleItemToOrder("camemberg",1);
		order.getCustomer().setFidelityCard("PointFidelityCard");
		order.getBill();
		myFoodora.getListCourier().get(0).setAcceptProbability(1); //To be sure that the order is always accepted in the test
		myFoodora.setCourierToOrder(order);
		myFoodora.closeOrder(order);
		
		PointFidelityCard card = (PointFidelityCard) order.getCustomer().getFidelityCard();
		assertTrue(card.getPoints()==14);}
		
		catch(WrongItemAdded e){
			e.getMessage();
		}
		catch(NoCourierFoundToDeliver e){
			e.getMessage();
		}
		catch(OrderNotCompletException e){
			e.getMessage();
		}
		catch(FidelityCardDoesNotExistException e){
			e.getMessage();
		} catch (ItemDoesNotExist e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**Test if the price of an order is well computed, knowing that the customer can have a discount when his/her number of point reaches 100.
	 * 
	 */
	@Test 
	public void PriceTest(){
		MyFoodora myFoodora = new MyFoodora();
		myFoodora.load();
		Restaurant rest = myFoodora.getListRestaurant().get(0);
		myFoodora.getListCourier().get(0).setAcceptProbability(1); 
		try{
		//first order to have more than 100 points

		rest.getMenu().addItem("Starter", "camemberg");
		rest.getMenu().addPriceTag("camemberg", 1452);
		Order order = new Order(myFoodora.getListCustomer().get(0), rest);
		order.AddSingleItemToOrder("camemberg",1);
		order.getCustomer().setFidelityCard("PointFidelityCard");
		order.getBill();
		myFoodora.getListCourier().get(0).setAcceptProbability(1); //To be sure that the order is always accepted in the test
		myFoodora.setCourierToOrder(order);
		myFoodora.closeOrder(order);
		
		//second order to check is the price is well-computed
		Restaurant rest2 = myFoodora.getListRestaurant().get(1);
		Order order2 = new Order(myFoodora.getListCustomer().get(0), rest2);
		order2.AddSingleItemToOrder("entrecote",1);
		PointFidelityCard card = (PointFidelityCard) myFoodora.getListCustomer().get(0).getFidelityCard();

		order2.getBill();
		myFoodora.setCourierToOrder(order2);
		myFoodora.closeOrder(order2);
		assertTrue((order2.getPrice()==0.9*11.3)&&(card.getPoints()==45));

		}
		
		catch(WrongItemAdded e){
			e.getMessage();
		}
		catch(NoCourierFoundToDeliver e){
			e.getMessage();
		}
		catch(OrderNotCompletException e){
			e.getMessage();
		}
		catch(FidelityCardDoesNotExistException e){
			e.getMessage();
		} catch (ItemDoesNotExist e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**Test if having more than 100 points does not change the price of former orders which are already completed. 
	 * 
	 */
	@Test
	public void DoesNotChangeFormerOrderPriceTest(){
		try{
			MyFoodora myFoodora = new MyFoodora();
		
		myFoodora.load();
		myFoodora.getListCourier().get(0).setAcceptProbability(1); 
		Order order = myFoodora.getCompleteOrders().get(1);
		order.getCustomer().setFidelityCard("pointfidelitycard");
		double price = order.getPrice();
		double computedPrice = 0.90*(2.3+6.7+2.5)+3.1+3;
		assertTrue(price==computedPrice);}
	catch(FidelityCardDoesNotExistException e){
		e.getMessage();
	}
	}
	
	/**Test if the user of a point fidelity card does not have the discount of 10% on the meal of the week.
	 * 
	 */
	@Test
	public void MealOfTheWeekTest(){
		MyFoodora myFoodora = new MyFoodora();
		myFoodora.load();
		myFoodora.getListCourier().get(0).setAcceptProbability(1); 
		Restaurant rest = myFoodora.getListRestaurant().get(0);
		try{
		Order order = new Order(myFoodora.getListCustomer().get(0), rest);
		order.AddMealToOrder("basic",1);
		order.getCustomer().setFidelityCard("PointFidelityCard");
		order.getBill();
		myFoodora.getListCourier().get(0).setAcceptProbability(1); //To be sure that the order is always accepted in the test
		myFoodora.setCourierToOrder(order);
		myFoodora.closeOrder(order);
		assertTrue(order.getPrice()== 0.95*(2.3+6.7+2.5));}
		
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
