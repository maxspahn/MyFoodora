package scenario;

import java.util.Scanner;

import restaurant.WrongItemAdded;
import system.MyFoodora;
import system.Order;
import user_management.Courier;
import user_management.Customer;
import user_management.Restaurant;
import user_management.RestaurantNotFoundException;
import user_management.User;
import user_management.UserNotFoundException;
import user_management.WrongUserNameOrPassWordException;

public class Scenario {
	
	public User currentUser = null;

	public static void main(String[] args) {
		
		Scenario sc = new Scenario();
		MyFoodora myFoodora = new MyFoodora();
		myFoodora.load();
		//sc.registerUser(myFoodora);
		//sc.loginUser(myFoodora);
		sc.orderMeal(myFoodora);
		
	}

	/**
	 * Startup scenario
	 * TODO sends alert to all the user that have agreed to be notified.
	 */
	public void startupScenario(){
		
	}
	

	
	/**
	 * Register a user
	 */
	public void registerUser(MyFoodora myFoodora){
		// 1.
		Scanner scanner = new Scanner(System.in);
		User currentNewUser = null;
		System.out.println("Hello to myFoodora, you have choosen to register.");
		System.out.println("You want to register as Customer or as a Couirier, type 'customer' or 'courier'");
		String type = scanner.nextLine();
		// 2. - 6.
		if(type.equalsIgnoreCase("customer")){
			CustomerQuestions cq = new CustomerQuestions();
			currentNewUser = cq.askCustomerQuestions(myFoodora);
		}
		
		else if (type.equalsIgnoreCase("courier")){
			CourierQuestions coq = new CourierQuestions();
			currentNewUser = coq.askCourierQuestions(myFoodora);
		}
		else{System.out.println("This type does not exist");return;}
		
		// 7.
		System.out.println("Your want your account to be registered? (y,n)");
		String save = scanner.nextLine();
		if(save.equalsIgnoreCase("n")){
			myFoodora.getListUsers().remove(myFoodora.getListUsers().size()-1);
			if(currentNewUser instanceof Customer){ myFoodora.getListCustomer().remove(myFoodora.getListCustomer().size()-1);}
			else if(currentNewUser instanceof Courier){ myFoodora.getListCourier().remove(myFoodora.getListCourier().size()-1);}
			System.out.println("Your information have been discarded");
		}
		
		else{
			System.out.println("Your account have been registered to MyFoodora!");
		}	
		
	}
	
	/**
	 * Login a User
	 */
	public void loginUser(MyFoodora myFoodora){
		Scanner scanner = new Scanner(System.in);
		// 1.
		System.out.println("You have choosen to login to you account");
		boolean existant = false;
		String userName, password;
		while(!existant){
			existant = true;
			System.out.println("Please type your userName");
			userName = scanner.nextLine();
			System.out.println("Please type your password");
			password = scanner.nextLine();
			try {
				currentUser = myFoodora.loginUser(userName, password);
			} catch (WrongUserNameOrPassWordException e) {
				existant = false;
				System.out.println(e.getMessage());
			}
		}
		
		System.out.println("You have been logged in!");
		System.out.println(currentUser.getName());
		
		scanner.close();
		
	}
	
	/**
	 * Ordering a Meal
	 */
	public void orderMeal(MyFoodora myFoodora){
		this.loginUser(myFoodora);
		if(!(currentUser instanceof Customer)){
			System.out.println("You cannot order a meal, because you are not a customer");
			return;
		}

		Scanner scanner = new Scanner(System.in);
			
		System.out.println("Hello, " + currentUser.getName() + ", you decided to order on MyFoodora");
		System.out.println("Please choose one of the restaurant below, by typing their number, 'enter' to see the list");
		System.out.println(myFoodora.listRestaurantsToString());
		scanner.nextLine();

		int choosenRest = -1;
		System.out.println("Which item you want to order? type the number");
		choosenRest = scanner.nextInt();
		boolean choosen = false;
		String restName = "";
		Restaurant rest = null;
		Order myOrder = null;
		while(!choosen){
			choosen = true;
			try {
				rest = myFoodora.getListRestaurant().get(choosenRest);
				restName = rest.getUserName();
				myOrder = ((Customer) currentUser).newOrder(restName, "newOrder");
			} catch (RestaurantNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IndexOutOfBoundsException e){
				System.out.println("The choosen restaurant does not exist");
				choosen = false;
			}
		}
		
		System.out.println("Want do you want to add to your command");
		System.out.println("'enter' to start the process");
		System.out.println(myFoodora.getListRestaurant().get(choosenRest).getMenu());
		
		String finished = "";
		while(!finished.equalsIgnoreCase("n")){
			System.out.println("Select an item by the number");
			int itemIndex = scanner.nextInt();
			System.out.println("How many to you want to order ('0' for abort)");
			int number = scanner.nextInt();
			try {
				myOrder.AddItemToOrder(itemIndex -1, rest, number);
			} catch (WrongItemAdded e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("You want to continue? (y,n)");
			finished = scanner.nextLine();
		}
		
		System.out.println("Your order:");
		System.out.println(myOrder);
		
		
		
		scanner.close();
	}

}
