package system;

import java.util.ArrayList;

import user_management.*;

public class MyFoodora {
	
	private TargetPolicy targetPolicy;
	private DeliveryPolicy deliveryPolicy;
	
	private ArrayList<User> listUsers;
	private ArrayList<Courier> listCourier;
	private ArrayList<Order> completeOrders;
	
	private double incomeLastMonth;
	private int indexLastCommandMonth;
	
	public MyFoodora(){
		this.setListUsers(new ArrayList<User>());
		this.setListCourier(new ArrayList<Courier>());
		this.setDeliveryPolicy(new FastestDelivery());
		this.setCompleteOrders(new ArrayList<Order>());
		this.setIndexLastCommandMonth(0);
	}	
		
	public TargetPolicy getTargetPolicy() {
		return targetPolicy;
	}

	public void setTargetPolicy(TargetPolicy targetPolicy) {
		this.targetPolicy = targetPolicy;
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
	
	public void addOrderToCompleteOrders(Order order){
		this.completeOrders.add(order);
	}
	
	public void computeProfitForOrder(Order order) throws OrderNotCompletException{
		if(order.isComplete()){
			double profit = this.getTargetPolicy().computeProfit(order.getPrice());
			order.setProfit(profit);
		}
		else{
			throw new OrderNotCompletException();
		}
	}
	
	public int getIndexOfFirstCommandInMonth(int month, int year) throws NoOrderInMonth, OrderNotCompletException{
		int index = -1;
		for (int i = 0; i < this.getCompleteOrders().size(); i++) {
			if(this.getCompleteOrders().get(i).getYearMonthOfOrder()[0] == year && this.getCompleteOrders().get(i).getYearMonthOfOrder()[1] == month){
				index = i;
				break;
			}
		}
		if(index ==-1){
			throw new NoOrderInMonth(month, year);
		}
		return index;
	}
	
	public int getIndexOfLastCommandInMonth(int month, int year) throws NoOrderInMonth, OrderNotCompletException{
		int index = -1;
		if(year == this.getCompleteOrders().get(this.getCompleteOrders().size() -1).getYearMonthOfOrder()[0] && month == this.getCompleteOrders().get(this.getCompleteOrders().size() -1).getYearMonthOfOrder()[1]){
			index = this.getCompleteOrders().size()-1;
		}
		else{
			if(month == 12){
				month = 0;
				year = year ++;
			}
			index = this.getIndexOfFirstCommandInMonth(month +1, year) -1;
		}
		return index;
	}
	
	public void computeTotalIncomeLastMonth(){
		double income = 0;
		for (int i = this.getIndexLastCommandMonth(); i < this.getCompleteOrders(); i++) {
			income += this.getCompleteOrders().get(i).getProfit();
		}
		this.setIncomeLastMonth(income);
	}

	public double getIncomeLastMonth() {
		return incomeLastMonth;
	}

	public void setIncomeLastMonth(double incomeLastMonth) {
		this.incomeLastMonth = incomeLastMonth;
	}

	public int getIndexLastCommandMonth() {
		return indexLastCommandMonth;
	}

	public void setIndexLastCommandMonth(int indexLastCommandMonth) {
		this.indexLastCommandMonth = indexLastCommandMonth;
	}
	
	

}
