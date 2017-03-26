package system;

public abstract class TargetPolicy {
	
	private double service_fee;
	private double markup_percentage;
	private double delivery_cost;
	
	public double computeProfit(double price){
		double profit = price * this.getMarkup_percentage() + this.getService_fee()- this.getDelivery_cost();
		return profit;
	}

	public TargetPolicy() {
		// TODO Auto-generated constructor stub
	}

	public double getService_fee() {
		return service_fee;
	}

	public void setService_fee(double service_fee) {
		this.service_fee = service_fee;
	}

	public double getMarkup_percentage() {
		return markup_percentage;
	}

	public void setMarkup_percentage(double markup_percentage) {
		this.markup_percentage = markup_percentage;
	}

	public double getDelivery_cost() {
		return delivery_cost;
	}

	public void setDelivery_cost(double delivery_cost) {
		this.delivery_cost = delivery_cost;
	}
	
	public abstract void setTargetPolicy();

}
