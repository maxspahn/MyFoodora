package system;

public class TargetProfit_DeliveryCost implements TargetPolicy{

	public TargetProfit_DeliveryCost() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setTargetPolicy(MyFoodora myFoodora, double value1,	double value2) {
		double deliveryCost = -1;
		if(myFoodora.getTargetProfit() == 0){
			myFoodora.setTargetProfit(myFoodora.computeValuesLastMonth()[0]);
		}
		if(myFoodora.getTargetCommands() == 0){
			myFoodora.setTargetCommands((int) myFoodora.computeValuesLastMonth()[2]);			
		}
		if(myFoodora.getTargetProfit() == 0 || myFoodora.getTargetCommands() == 0){
			System.out.println("no Target defined and income last month is 0");
		}
		else{
			deliveryCost  = -myFoodora.getTargetProfit()/myFoodora.getTargetCommands() + myFoodora.getAveragePricePerCommand() * value1 + value2;
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
