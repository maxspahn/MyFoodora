package system;

import java.util.ArrayList;

import user_management.*;

/**Way to choose a courier according to the position of the customer who made the order.
 * @author maxspahn
 * @author jeremyaugot
 *
 */
public interface DeliveryPolicy {
	

	// Takes an order as impute and returns the courier that is the nearest according the 
	// deliveryPolicy.
	// @todo needs access to the list of couriers.
	
	/** Allocate a courier to an order.
	 * @param order Order to deliver.
	 * @param couriers ArrayList<Courier> containing all the couriers.
	 * @param refused ArrayList<Integer> containing the answers of the couriers.
	 * @return Integer which represents the position of the courier in the list of couriers. He is the nearest according to the delivery policy.
	 */
	public int allocateCourier(Order order, ArrayList<Courier> couriers, ArrayList<Integer> refused);
	
	public String toString();

}
