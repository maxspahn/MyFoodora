package system;

public class TargetProfit_ServiceFee implements TargetPolicy{

	public TargetProfit_ServiceFee() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void setTargetPolicy(MyFoodora myFoodora, double value1,	double value2) {
		myFoodora.computeTotalIncomeLastMonth();
		double serviceFee = -1;
		if(myFoodora.getTargetProfit() == 0){
			myFoodora.setTargetProfit(myFoodora.computeTotalIncomeLastMonth());
		}
		if(myFoodora.getTargetProfit() == 0){
			System.out.println("no Target defined and income last month is 0");
		}
		else{
			serviceFee  = myFoodora.getTargetProfit() + myFoodora.getTotalNumberOfOrdersLastMonth() * value2 - myFoodora.getTotalPriceLastMonth() * value1;
			serviceFee /= myFoodora.getTotalNumberOfOrdersLastMonth();
		}
		
		if(serviceFee <= 0){
			System.out.println("The target cannot be fullfilled with the values given, the fees have not been changed");
		}
		else{
			myFoodora.setDelivery_cost(value2);
			myFoodora.setMarkup_percentage(value1);
			myFoodora.setService_fee(serviceFee);			
		}
		
	}

}
