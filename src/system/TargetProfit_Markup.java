package system;

import java.io.Serializable;

/** Implementation of TargetPolicy to compute and set the markupPercentage according to a targetProfit and given values for serviceFee and deliveryCost.
 * @author maxspahn
 * @author jeremyaugot
 */
public class TargetProfit_Markup implements TargetPolicy, Serializable  {

	public TargetProfit_Markup() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param value1 service_fee.
	 * @param value2 delivery_cost.
	 * @throws TargetCannotBeFullfilled 
	 */
	@Override
	public void setTargetPolicy(MyFoodora myFoodora, double value1,	double value2) throws TargetCannotBeFullfilled {
		double markup = -1;
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
			markup  = myFoodora.getTargetProfit()/myFoodora.getTargetCommands() + value2 - value1;
			markup /= myFoodora.getAveragePricePerCommand();
			markup = ((double) Math.round(markup * 100))/100;
		}
		
		if(markup <= 0){
			throw new TargetCannotBeFullfilled();
		}
		else{
			myFoodora.setDelivery_cost(value2);
			myFoodora.setMarkup_percentage(markup);
			myFoodora.setService_fee(value1);			
		}
		
	}
	
	public String toString(){
		String s = "markup Percentage";
		return s;
	}

}
