package system;

import restaurant.Meal;
import restaurant.SingleItem;

public class PointFidelityCard implements FidelityCard{
	
	private int points;

	public PointFidelityCard() {
		this.setPoints(0);
	}

	@Override
	public double computePrice(Order order) {
		double price = 0;
		for (SingleItem singleItem : order.getSingleItems()) {
			price += singleItem.getPrice();
		}
		for (Meal meal : order.getMeals()) {
			price += meal.getPrice() * 0.95;
		}
		if(this.getPoints() >= 100){
			price *= 0.9;
			this.setPoints(this.getPoints()-100);
		}
		else{
			this.addPoints(price);			
		}
		return price;
	}
	
	private void addPoints(double price){
		int count = (int) points/10;
		this.setPoints(this.getPoints() + count );
	}

	public int getPoints() {
		return points;
	}

	private void setPoints(int points) {
		this.points = points;
	}

}
