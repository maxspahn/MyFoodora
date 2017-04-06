package system;

import java.io.Serializable;

import restaurant.Meal;
import restaurant.SingleItem;

/** Implementation of FideltyCard. Meal of the week at a special discount. Part of the StrategyPattern to compute the price of an order.
 * The price is the sum of the prices for the items. The meal of the weak gets a special discount instead of the 5% basic for a meal.
 * 
 * @author maxspahn
 * @author jeremyaugot
 *
 */
public class BasicFidelityCard implements FidelityCard, Serializable{


	/** BasicFidelityCard provides a simple computation of the price + specialidscount for the meal of the week.
	 * 
	 * @param oder Price is calculated for the given order.
	 * @return returns the price, 0 if no item in order.
	 */
	@Override
	public double computePrice(Order order) {
		double price = 0;
		double temp;
		for (SingleItem singleItem : order.getSingleItems()) {
			price += singleItem.getPrice();
		}
		for (Meal meal : order.getMeals()) {
			temp = meal.getPrice();
			if(meal.isMealOfTheWeek()){
				temp /= 0.95;
				temp *= (1-meal.getDiscount());
			}
			price += temp;
		}
		return price;
	}

}
