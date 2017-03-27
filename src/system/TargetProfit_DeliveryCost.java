package system;

public class TargetProfit_DeliveryCost implements TargetPolicy{

	public TargetProfit_DeliveryCost() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setTargetPolicy(MyFoodora myFoodora, double value1,	double value2) {
		myFoodora.computeTotalIncomeLastMonth();
		double deliveryCost = -1;
		if(myFoodora.getTargetProfit() == 0){
			myFoodora.setTargetProfit(myFoodora.computeTotalIncomeLastMonth());
		}
		if(myFoodora.getTargetProfit() == 0){
			System.out.println("no Target defined and income last month is 0");
		}
		else{
			deliveryCost  = myFoodora.getTotalPriceLastMonth() * value1 + value2 * myFoodora.getTotalNumberOfOrdersLastMonth() - myFoodora.getTargetProfit();
			deliveryCost /= myFoodora.getTotalNumberOfOrdersLastMonth();
		}
		
		if(deliveryCost <= 0){
			System.out.println("The target cannot be fullfilled with the values given, the fees have not been changed");
		}
		else{
			myFoodora.setDelivery_cost(deliveryCost);
			myFoodora.setMarkup_percentage(value1);
			myFoodora.setService_fee(value2);			
		}
		
	}


}
