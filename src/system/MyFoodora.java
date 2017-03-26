package system;

import java.util.ArrayList;

import user_management.*;

public class MyFoodora {
	
	private TargetPolicy targetPolicy;
	private DeliveryPolicy deliveryPolicy;

	private double service_fee;
	private double markup_percentage;
	private double delivery_cost;
	
	private ArrayList<User> listUsers;
	private ArrayList<Courier> listCourier;
	private ArrayList<Order> completeOrders;
	
	public MyFoodora(){
		this.setListUsers(new ArrayList<User>());
		this.setListCourier(new ArrayList<Courier>());
		this.setDeliveryPolicy(new FastestDelivery());
		this.setCompleteOrders(new ArrayList<Order>());
	}	
		
	public TargetPolicy getTargetPolicy() {
		return targetPolicy;
	}

	public void setTargetPolicy(TargetPolicy targetPolicy) {
		this.targetPolicy = targetPolicy;
	}

	public double getService_fee() {
		return service_fee;
	}

	public void setService_fee(double service_fee) {
		this.service_fee = service_fee;
	}

	public double getMarkup_percentage() {
		return markup_percentage;
	}
	
	public void setMarkup_percentage(double markup_percentage) {
		this.markup_percentage = markup_percentage;
	}

	public double getDelivery_cost() {
		return delivery_cost;
	}

	public void setDelivery_cost(double delivery_cost) {
		this.delivery_cost = delivery_cost;
	}

	
	public DeliveryPolicy getDeliveryPolicy() {
		return deliveryPolicy;
	}

	public void setDeliveryPolicy(DeliveryPolicy deliveryPolicy) {
		this.deliveryPolicy = deliveryPolicy;
	}

	public ArrayList<User> getListUsers() {
		return listUsers;
	}

	public void setListUsers(ArrayList<User> listUsers) {
		this.listUsers = listUsers;
	}

	public ArrayList<Courier> getListCourier() {
		return listCourier;
	}

	public void setListCourier(ArrayList<Courier> listCourier) {
		this.listCourier = listCourier;
	}

	public void setTargetPolicy(){
		this.targetPolicy.setTargetPolicy();
		this.setService_fee(this.targetPolicy.getService_fee());
		this.setDelivery_cost(this.getTargetPolicy().getDelivery_cost());
		this.setMarkup_percentage(this.getTargetPolicy().getMarkup_percentage());
	}
	
	public void setCourierToOrder(Order order){
		this.getDeliveryPolicy().allocateCourier(order, this.getListCourier());
	}

	public ArrayList<Order> getCompleteOrders() {
		return completeOrders;
	}

	public void setCompleteOrders(ArrayList<Order> completeOrders) {
		this.completeOrders = completeOrders;
	}

}
