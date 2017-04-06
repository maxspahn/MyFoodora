package system;

import java.io.Serializable;

/** Implementation of TargetPolicy to compute and set the deliveryCost according to a targetProfit and given values for markupPercentage and serviceFee.
 * @author maxspahn
 * @author jeremyaugot
 */
public class TargetProfit_DeliveryCost implements TargetPolicy, Serializable{

	/** default Constructor.
	 * 
	 */
	public TargetProfit_DeliveryCost() {
		super();
	}
	
	
	/**
	 * @param value1 markup_percentage.
	 * @param value2 service_fee.
	 */
	@Override
	public void setTargetPolicy(MyFoodora myFoodora, double value1,	double value2) {
		double deliveryCost = -1;
		if(myFoodora.getTargetProfit() == 0){
			myFoodora.setTargetProfit(myFoodora.computeValuesLastMonth()[1]);
		}
		if(myFoodora.getTargetCommands() == 0){
			myFoodora.setTargetCommands((int) myFoodora.computeValuesLastMonth()[2]);			
		}
		if(myFoodora.getTargetProfit() == 0 || myFoodora.getTargetCommands() == 0){
			System.out.println("no Target defined and income last month is 0");
		}
		else{
			deliveryCost  = -myFoodora.getTargetProfit()/myFoodora.getTargetCommands() + myFoodora.getAveragePricePerCommand() * value1 + value2;
			deliveryCost = ((double)Math.round(deliveryCost*100))/100;
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
