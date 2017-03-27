package system;

public abstract class TargetPolicy {
	
	
	
	public double computeProfit(double price, MyFoodora myFoodora){
		double profit = price * myFoodora.getMarkup_percentage() + myFoodora.getService_fee()- myFoodora.getDelivery_cost();
		return profit;
	}

	public TargetPolicy() {
		// TODO Auto-generated constructor stub
	}

	
	public abstract void setTargetPolicy(MyFoodora myFoodora);

}
