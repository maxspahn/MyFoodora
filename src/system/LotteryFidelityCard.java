package system;

import java.util.Date;

import restaurant.Meal;
import restaurant.SingleItem;

public class LotteryFidelityCard implements FidelityCard{
	
	private int lastFreeCommandYear;
	private int lastFreeCommandMonth;
	private int lastFreeCommandDay;
	private double probability;

	public LotteryFidelityCard() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public double computePrice(Order order) {
		double price = 0;
		Date date = new Date();
		int year = date.getYear() + 1900;
		int month = date.getMonth() + 1;
		int day = date.getDay() + 1;
		for (SingleItem singleItem : order.getSingleItems()) {
			price += singleItem.getPrice();
		}
		for (Meal meal : order.getMeals()) {
			price += meal.getPrice();
		}
		// TODO each day
		double decider = Math.random();
		if(decider < this.getProbability() && (year != this.getLastFreeCommandYear() || month != this.getLastFreeCommandMonth() || day != this.getLastFreeCommandDay())){
			price = 0;
			try{
				this.setLastFreeCommandDay(order.getCompleteDay());
				this.setLastFreeCommandMonth(order.getCompleteMonth());
				this.setLastFreeCommandYear(order.getCompleteYear());
			}
			catch( OrderNotCompletException e){
				System.out.println(e.getMessage());
			}
		}
		return price;
		
	}

	
	
	protected int getLastFreeCommandYear() {
		return lastFreeCommandYear;
	}

	protected void setLastFreeCommandYear(int lastFreeCommandYear) {
		this.lastFreeCommandYear = lastFreeCommandYear;
	}

	protected int getLastFreeCommandMonth() {
		return lastFreeCommandMonth;
	}

	protected void setLastFreeCommandMonth(int lastFreeCommandMonth) {
		this.lastFreeCommandMonth = lastFreeCommandMonth;
	}

	protected int getLastFreeCommandDay() {
		return lastFreeCommandDay;
	}

	protected void setLastFreeCommandDay(int lastFreeCommandDay) {
		this.lastFreeCommandDay = lastFreeCommandDay;
	}

	public double getProbability() {
		return probability;
	}

	public void setProbability(double probability) {
		this.probability = probability;
	}


}
