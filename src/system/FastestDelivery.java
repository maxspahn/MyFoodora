package system;

import java.io.Serializable;
import java.util.ArrayList;

import user_management.Courier;

/**Way of allocating a courier to an order, implements the DeliveryPolicy class.
 * It is a fastest delivery: the courier who is the closer to the restaurant is chosen.
 * @author jeremyaugot
 * @author maxspahn
 *
 */
public class FastestDelivery implements DeliveryPolicy, Serializable {

	public FastestDelivery() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int allocateCourier(Order order, ArrayList<Courier> couriers, ArrayList<Integer> refused) {
		double minDistance = 10000;
		double temp;
		int indexCourier = -1;
		for (int i = 0; i < couriers.size(); i++) {
			if(!refused.contains(i) && couriers.get(i).isAvailability() && couriers.get(i).isActivated()){
				temp = 0;
				temp += Math.pow(order.getRestaurant().getAdress()[0] - couriers.get(i).getLocation()[0], 2);
				temp += Math.pow(order.getRestaurant().getAdress()[1] - couriers.get(i).getLocation()[1], 2);
				if(temp < minDistance){
					minDistance = temp;
					indexCourier = i;
				}
			}
		}
		return indexCourier;
	}
	
	@Override
	public String toString() {
		return "FastestDelivery";
	}

	

	

}
