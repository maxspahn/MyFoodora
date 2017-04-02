package system;

/** Interface TargetPolicy is the key element of the Strategy-Pattern to set the fees according to a specific policy.
 * @author maxspahn
 * @author jeremyaugot
 */
public interface TargetPolicy {
	
	/** Method to compute and set the fees according to the policy.
	 * The function takes as input the targetProfit and targetCommands (number of commands predicted for the targetPeriod) which are attributes of myFoodora.
	 * If they had not been set previously (default value is zero), this method takes the values of the previous month.
	 * @param myFoodora The core system.
	 * @param value1 depending on the policy : if TargetProfit_Markup : value1 = service_fee else value1 = markup_percentage.
	 * @param value2 depending on the policy : if TargetProfit_DeliveryCost : value2 = service_fee else value2 = delivery_cost.
	 */
	public void setTargetPolicy(MyFoodora myFoodora, double value1, double value2);

}