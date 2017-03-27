package system;

import java.util.Date;

import restaurant.Meal;
import restaurant.SingleItem;

public class LotteryFidelityCard implements FidelityCard{
	
	private Date lastFreeCommandDate;
	private double probability;

	public LotteryFidelityCard() {
		// TODO Auto-generated constructor stub
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
		// TODO each day
		double decider = Math.random();
		if(decider < this.getProbability()){
			price = 0;
			this.setLastFreeCommandDate(order.getCompleteDate());
		}
		return price;
		
	}

	public Date getLastFreeCommandDate() {
		return this.lastFreeCommandDate;
	}
	
	public void setLastFreeCommandDate(Date lastFreeCommandDate) {
		this.lastFreeCommandDate = lastFreeCommandDate;
	}
	
	public double getProbability() {
		return probability;
	}

	public void setProbability(double probability) {
		this.probability = probability;
	}


}
