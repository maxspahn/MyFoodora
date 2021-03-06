package system;

import java.io.Serializable;
import java.util.ArrayList;

import user_management.Courier;

/**Way of allocating a courier to an order, implements the DeliveryPolicy class.
 * It is a fair-occupation delivery: the courier with the least number of delivered orders is chosen.
 * @author jeremyaugot
 * @author maxspahn
 *
 */
public class FairDelivery implements DeliveryPolicy, Serializable {

	public FairDelivery() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int allocateCourier(Order order, ArrayList<Courier> couriers, ArrayList<Integer> refused) {
		int minDelivered = 10000;
		int temp;
		int indexCourier = -1;
		for (int i = 0; i < couriers.size(); i++) {
			if(!refused.contains(i) && couriers.get(i).isAvailability() && couriers.get(i).isActivated()){
				temp = couriers.get(i).getCountDeliveredOrder();
				if(temp < minDelivered){
					minDelivered = temp;
					indexCourier = i;
				}
			}
		}
		return indexCourier;
	}

	@Override
	public String toString() {
		return "FairDelivery";
	}

}
