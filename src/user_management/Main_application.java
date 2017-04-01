package user_management;

import static org.junit.Assert.assertTrue;

import restaurant.ItemDoesNotExist;
import restaurant.WrongItemAdded;
import system.*;

public class Main_application {
	public static void main(String[] args) throws SameUserNameException, WrongItemAdded, RestaurantNotFoundException, WrongUserNameOrPassWordException, FidelityCardDoesNotExistException {
		MyFoodora myFoodora = new MyFoodora();
		myFoodora.load();
		try{
		double profit = myFoodora.getProfitForMonth(3, 2017);
		double priceOrder1 = 0.95*(2+11.3)+3.1;
		double priceOrder2 = 0.9*(2.3+6.7+2.5)+3.1+3;
		double profitcomputed = priceOrder1*0.1+2-3+priceOrder2*0.1+2-3;
		System.out.println(profit);
		System.out.println(100*profitcomputed);
		double roundProfitcomputed = Math.round(100*profitcomputed);
		System.out.println(Math.round(100*profitcomputed));
		System.out.println(roundProfitcomputed/100);
		}
		catch(OrderNotCompletException e){
			e.getMessage();
		}
	}
	
}
