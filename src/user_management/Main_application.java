package user_management;

public class Main_application {
	public static void main(String[] args) throws SameUserNameException {
		ManagerFactory managerFactory = new ManagerFactory();
		CourierFactory courierFactory = new CourierFactory();
		CustomerFactory customerFactory = new CustomerFactory();
		RestaurantFactory restaurantFactory = new RestaurantFactory();
		managerFactory.load();
		int[] adress = {1,2};
		//customerFactory.createAccount("hjn", "a", "kjn", "kjn","jn", adress);
		courierFactory.load();
		Customer cust = (Customer) customerFactory.createAccount("jeremy", "jerem", "2E", "0665216811", "jeremy.augot@student.ecp.fr", adress);
		System.out.println(cust.toString());
		

		System.out.println(courierFactory.getManagerList());
		System.out.println(courierFactory.getManagerList().get(0).getMyFoodora().listUsers);
		System.out.println(courierFactory.getManagerList().get(1).toString());

		
	}
}
