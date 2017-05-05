package system;

import java.io.Serializable;

/** Implementation of TargetPolicy to compute and set the serviceFee according to a targetProfit and given values for markupPercentage and deliveryCost.
 * @author maxspahn
 * @author jeremyaugot
 */
public class TargetProfit_ServiceFee implements TargetPolicy, Serializable{

	public TargetProfit_ServiceFee() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param value1 markup_percentage.
	 * @param value2 delivery_cost.
	 * @throws TargetCannotBeFullfilled 
	 */
	@Override
	public void setTargetPolicy(MyFoodora myFoodora, double value1,	double value2) throws TargetCannotBeFullfilled {
		System.out.println("in TargetProfit_ServiceFee");
		double serviceFee = -1;
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
			serviceFee  = myFoodora.getTargetProfit()/myFoodora.getTargetCommands() +  value2 - myFoodora.getAveragePricePerCommand() * value1;
			serviceFee = ((double) Math.round(serviceFee * 100))/100;
		}
		
		if(serviceFee <= 0){
			throw new TargetCannotBeFullfilled();
		}
		else{
			myFoodora.setDelivery_cost(value2);
			myFoodora.setMarkup_percentage(value1);
			myFoodora.setService_fee(serviceFee);			
		}
		
		
	}
	
	public String toString(){
		String s = "service fee";
		return s;
	}
	

}
