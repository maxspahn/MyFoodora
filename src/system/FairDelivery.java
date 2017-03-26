package system;

import java.util.ArrayList;

import user_management.Courier;

public class FairDelivery implements DeliveryPolicy {

	public FairDelivery() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Courier allocateCourier(Order order, ArrayList<Courier> couriers) {
		int minDelivered = 10000;
		Courier selected = null;
		int temp;
		for (Courier courier : couriers) {
			temp = courier.getCountDeliveredOrder();
			if(temp < minDelivered){
				minDelivered = temp;
				selected = courier;
			}
		}
		selected.setAvailability(false);
		order.setCourier(selected);
		return selected;
	}

}
