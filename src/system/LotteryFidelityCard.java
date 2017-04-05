package system;

import java.util.Date;

import restaurant.Meal;
import restaurant.SingleItem;

/**Lottery fidelity card implements the FidelityCard class. A member who has this card will not access to any offer nor gain any point.
 * Nevertheless he will have a certain probability to win her/his meal for free each day.
 * @author jeremyaugot
 * @author maxspahn
 *
 */
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

	
	
	/**
	 * @return lastFreeCommandYear Integer.
	 */
	protected int getLastFreeCommandYear() {
		return lastFreeCommandYear;
	}

	/**
	 * @param lastFreeCommandYear Integer.
	 */
	protected void setLastFreeCommandYear(int lastFreeCommandYear) {
		this.lastFreeCommandYear = lastFreeCommandYear;
	}

	/**
	 * @return Integer lasFreeCommandMonth.
	 */
	protected int getLastFreeCommandMonth() {
		return lastFreeCommandMonth;
	}

	/**
	 * @param lastFreeCommandMonth Integer.
	 */
	protected void setLastFreeCommandMonth(int lastFreeCommandMonth) {
		this.lastFreeCommandMonth = lastFreeCommandMonth;
	}

	/**
	 * @return Integer lastFreeCommandDay.
	 */
	protected int getLastFreeCommandDay() {
		return lastFreeCommandDay;
	}

	/**
	 * @param lastFreeCommandDay Integer.
	 */
	protected void setLastFreeCommandDay(int lastFreeCommandDay) {
		this.lastFreeCommandDay = lastFreeCommandDay;
	}

	/**
	 * @return Double probability.
	 */
	public double getProbability() {
		return probability;
	}

	/**
	 * @param probability Double.
	 */
	public void setProbability(double probability) {
		this.probability = probability;
	}


}
