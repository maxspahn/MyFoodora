package user_management;

import static org.junit.Assert.assertTrue;

public class Main_application {
	public static void main(String[] args) throws SameUserNameException {
		ManagerFactory managerFactory = new ManagerFactory();
		//CourierFactory courierFactory = new CourierFactory();
		CustomerFactory customerFactory = new CustomerFactory();
		RestaurantFactory restaurantFactory = new RestaurantFactory();
		
		int[] adress = {1,2};
		Restaurant rest = (Restaurant) restaurantFactory.getRestaurantList().get(0);
		rest.setDiscount(0.5);
		
		Customer cust = (Customer) customerFactory.createAccount("jeje","je", "iijijij", "098765432", "jere@gmail.com",adress);
		System.out.println(cust.getNotifications());
		

		cust.setSpamAgreement(true);

		
		rest.setDiscount(0.3);
		rest.setMealOfTheWeek("FullMeal");
		
		System.out.println(cust.getNotifications());
		
		System.out.println(cust.getNotifications());
		
	}
}
