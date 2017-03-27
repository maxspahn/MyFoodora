package system;

import java.util.ArrayList;

import user_management.*;

/**
 * @author maxspahn
 *
 */
public interface DeliveryPolicy {
	

	// Takes an order as impute and returns the courier that is the nearest according the 
	// deliveryPolicy.
	// @todo needs access to the list of couriers.
	public int allocateCourier(Order order, ArrayList<Courier> couriers, ArrayList<Integer> refused);

}
