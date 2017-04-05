package testSystem;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

import restaurant.HalfMeal;
import restaurant.ItemDoesNotExist;
import system.HalfMealSort;
import system.MyFoodora;
import system.Order;
import system.OrderNotCompletException;
import user_management.FidelityCardDoesNotExistException;

public class TestMyFoodora {

	@Test
	public void TotalIncometest() {
		MyFoodora myFoodora = new MyFoodora();
		myFoodora.load();
		double totalIncome = myFoodora.computeTotalIncome();
		
		assertTrue(totalIncome==0.95*(2+11.3)+3.1+0.9*(2.3+6.7+2.5)+3.1+3);
	}
	
	@Test
	public void AveragePricePerOrderTest(){
		MyFoodora myFoodora = new MyFoodora();
		myFoodora.load();
		double averagePrice = myFoodora.getAveragePricePerCommand();
		assertTrue(averagePrice==(0.95*(2+11.3)+3.1+0.9*(2.3+6.7+2.5)+3.1+3)/2);
		
	}
	
	@Test
	public void ProfitMonthTest(){
		MyFoodora myFoodora = new MyFoodora();
		myFoodora.load();
		
		try{
		Order order = new Order(myFoodora.getListCustomer().get(0), myFoodora.getListRestaurant().get(0));
		order.AddMealToOrder("basic",1);		
		order.getBill();
		myFoodora.getListCourier().get(0).setAcceptProbability(1); //To be sure that the order is always accepted in the test			myFoodora.setCourierToOrder(order);
		myFoodora.closeOrder(order);
		double profit = myFoodora.getProfitForMonth(3, 2017);
		double priceOrder1 = 0.95*(2+11.3)+3.1;
		double priceOrder2 = 0.9*(2.3+6.7+2.5)+3.1+3;
		double mp = myFoodora.getMarkup_percentage();
		double sf = myFoodora.getService_fee();
		double dc = myFoodora.getDelivery_cost();
		double profitcomputed = priceOrder1*mp+sf-dc+priceOrder2*mp+sf-dc;
		double profitcomputedround = Math.round(100*profitcomputed);
		assertTrue(profit==profitcomputedround/100);
		}
		catch(OrderNotCompletException e){
			e.getMessage();
		}
		catch(ItemDoesNotExist e){
			e.getMessage();
		}
		
	}
	
	@Test
	public void addOrderToCompleteOrderTest(){
		MyFoodora myFoodora = new MyFoodora();
		myFoodora.load();
		try{
			Order order = new Order(myFoodora.getListCustomer().get(0), myFoodora.getListRestaurant().get(0));
			order.AddMealToOrder("basic",1);		
			order.getBill();
			myFoodora.getListCourier().get(0).setAcceptProbability(1); //To be sure that the order is always accepted in the test			myFoodora.setCourierToOrder(order);
			myFoodora.closeOrder(order);

			assertTrue(myFoodora.getCompleteOrders().size()==3);
			}
			catch(OrderNotCompletException e){
				e.getMessage();
			}
			catch(ItemDoesNotExist e){
				e.getMessage();
			}
	}
	
	@Test
	public void HalfMealCountTest(){
		MyFoodora myFoodora = new MyFoodora();
		myFoodora.load();
		int count = myFoodora.getDeliveredHalfMeals().first().getCount();
		assertTrue(count==1);
	}
	
	@Test
	public void SortedHalfMealTest(){
		MyFoodora myFoodora = new MyFoodora();
		myFoodora.load();
		try{
			//Order1
			Order order = new Order(myFoodora.getListCustomer().get(0), myFoodora.getListRestaurant().get(0));
			order.AddMealToOrder("classic",1);		
			order.getBill();
			myFoodora.getListCourier().get(0).setAcceptProbability(1); //To be sure that the order is always accepted in the test			myFoodora.setCourierToOrder(order);
			myFoodora.closeOrder(order);

			//Order2
			Order order2 = new Order(myFoodora.getListCustomer().get(0), myFoodora.getListRestaurant().get(0));
			order2.AddMealToOrder("exotic",1);		
			order2.getBill();
			myFoodora.getListCourier().get(0).setAcceptProbability(1); //To be sure that the order is always accepted in the test			myFoodora.setCourierToOrder(order);
			myFoodora.closeOrder(order2);
			
			//Order3
			Order order3 = new Order(myFoodora.getListCustomer().get(0), myFoodora.getListRestaurant().get(0));
			order3.AddMealToOrder("chef's",1);		
			order3.getBill();
			myFoodora.getListCourier().get(0).setAcceptProbability(1); //To be sure that the order is always accepted in the test			myFoodora.setCourierToOrder(order);
			myFoodora.closeOrder(order3);
			
			//Order4
			Order order4 = new Order(myFoodora.getListCustomer().get(0), myFoodora.getListRestaurant().get(0));
			order.AddMealToOrder("classic",1);		
			order.getBill();
			myFoodora.getListCourier().get(0).setAcceptProbability(1); //To be sure that the order is always accepted in the test			myFoodora.setCourierToOrder(order);
			myFoodora.closeOrder(order4);
			
			//Order5
			Order order5 = new Order(myFoodora.getListCustomer().get(0), myFoodora.getListRestaurant().get(0));
			order.AddMealToOrder("exotic",1);		
			order.getBill();
			myFoodora.getListCourier().get(0).setAcceptProbability(1); //To be sure that the order is always accepted in the test			myFoodora.setCourierToOrder(order);
			myFoodora.closeOrder(order5);
			
			ArrayList<HalfMealSort> halfmeallist = new ArrayList<HalfMealSort>(myFoodora.getDeliveredHalfMeals());
			HalfMeal meal1 = halfmeallist.get(0).getHalfMeal();
			HalfMeal meal2 = halfmeallist.get(1).getHalfMeal();
			HalfMeal meal3 = halfmeallist.get(2).getHalfMeal();
			
			assertTrue(meal1.getName().equalsIgnoreCase("Classic")&&meal2.getName().equalsIgnoreCase("exotic")
					&&meal3.getName().equalsIgnoreCase("chef's"));
			
			}
			catch(OrderNotCompletException e){
				e.getMessage();
			}
			catch(ItemDoesNotExist e){
				e.getMessage();
			}
	}
}
