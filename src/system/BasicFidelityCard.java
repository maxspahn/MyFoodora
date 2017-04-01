package system;

import restaurant.Meal;
import restaurant.SingleItem;

public class BasicFidelityCard implements FidelityCard{

	public BasicFidelityCard() {
		// TODO Auto-generated constructor stub
	}

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
