package testSystem;

import static org.junit.Assert.*;

import org.junit.Test;

import restaurant.ItemDoesNotExist;
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
		order.AddMealToOrder("basic");		
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
			order.AddMealToOrder("basic");		
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
	public void HalfMealSortTest(){
		MyFoodora myFoodora = new MyFoodora();
		myFoodora.load();
	}
}
