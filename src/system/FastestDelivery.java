package system;

import java.util.ArrayList;

import user_management.Courier;

public class FastestDelivery implements DeliveryPolicy {

	public FastestDelivery() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Courier allocateCourier(Order order, ArrayList<Courier> couriers) {
		double minDistance = 10000;
		double temp;
		Courier selected = null;
		for (Courier courier : couriers) {
			temp = 0;
			temp += Math.pow(order.getRestaurant().getAdress()[0] - courier.getAdress()[0], 2);
			temp += Math.pow(order.getRestaurant().getAdress()[1] - courier.getAdress()[1], 2);
			if(temp < minDistance){
				minDistance = temp;
				selected = courier;
			}
		}
		selected.setAvailability(false);
		order.setCourier(selected);
		return selected;
	}

	

	

}
