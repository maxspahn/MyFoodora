package user_management;

import static org.junit.Assert.assertTrue;

public class Main_application {
	public static void main(String[] args) throws SameUserNameException {
		ManagerFactory managerFactory = new ManagerFactory();
		CourierFactory courierFactory = new CourierFactory();
		CustomerFactory customerFactory = new CustomerFactory();
		RestaurantFactory restaurantFactory = new RestaurantFactory();
		int[] adress = {1,2};
		//customerFactory.createAccount("hjn", "a", "kjn", "kjn","jn", adress);
		Customer cust = (Customer) customerFactory.createAccount("jeremy", "jerem", "2E", "0665216811", "jeremy.augot@student.ecp.fr", adress);
		Customer cust1 = (Customer) customerFactory.createAccount("max", "maxs", "wer", "022033", "max@peter.cem", adress);
		System.out.println(cust.toString());
		

		Manager manager = (Manager) managerFactory.getManagerList().get(0).getMyFoodora().listUsers.get(0);
		System.out.println(manager);
		System.out.println(manager.getRole());
		System.out.println(manager.getName());

		System.out.println(courierFactory.getManagerList());
		System.out.println("userlist");
		System.out.println(courierFactory.getManagerList().get(0).getMyFoodora().listUsers);
		System.out.println(courierFactory.getManagerList().get(1).toString());

		
		System.out.println(Math.random());
	}
}
