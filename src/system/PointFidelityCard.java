package system;

import java.io.Serializable;

import restaurant.Meal;
import restaurant.SingleItem;

/**Implements the FidelityCard class. Instead of having the special offer, she/he will gain a point for each 10 euros spent in the restaurant.
 * Once she/he will reach 100 points she/he will receive a 10% discount on the next order. 
 * @author jeremyaugot
 * @author maxspahn
 *
 */
public class PointFidelityCard implements FidelityCard, Serializable{
	
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
			price += meal.getPrice();
		}
		if(this.getPoints() >= 100){
			price *= 0.9;
			this.setPoints(this.getPoints()-100);
		}
		else{
			this.addPoints(price);			
		}
		price = ((double) Math.round(price * 100))/100;
		return price;
	}
	
	private void addPoints(double price){
		int count = (int) price/10;
		this.setPoints(this.getPoints() + count );
	}

	public int getPoints() {
		return points;
	}

	private void setPoints(int points) {
		this.points = points;
	}

	@Override
	public String toString(){
		return "Point fidility card";
	}
}
