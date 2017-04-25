package commandLineTool;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeSet;

import restaurant.ItemDoesNotExist;
import restaurant.WrongItemAdded;
import system.*;
import user_management.*;

public class Launch {
	
	private User currentUser;
	private MyFoodora myFoodora;
	private ArrayList<Order> myOrders;
	private ArrayList<String> commands;
	
	public Launch(){
		myFoodora = new MyFoodora();
		myFoodora.load();
		currentUser = null;
		myOrders = new ArrayList<Order>();
		this.fillCommandList();

	}
	
	public void fillCommandList(){
		commands = new ArrayList<String>();
		commands.add("login \t\t\t<username> <password>");
		commands.add("logout \t\t\t<>");
		commands.add("registerRestaurant \t<name> <adress> <username> <password>");
		commands.add("registerCustomer \t<firstName> <lastName> <username> <adress> <password>");
		commands.add("registerCourier \t<firstName> <lastName> <username> <position> <password>");
		commands.add("addDishRestaurantMenu \t<dishName> <dishCategory> <foodCategory> <unitPrice>");
		commands.add("createMeal \t\t<mealName>");
		commands.add("addDish2Meal \t\t<dishName> <mealName>");
		commands.add("showMeal \t\t<mealName>");
		commands.add("saveMeal \t\t<mealName>");
		commands.add("setSpecialOffer \t<mealName>");
		commands.add("removeFromSpecialOffer \t<mealName>");
		commands.add("createOrder \t\t<restaurantName> <orderName>");
		commands.add("addItem2Order \t\t<orderName> <itemName>");
		commands.add("endOrder \t\t<orderName>");
		commands.add("onDuty \t\t\t<>");
		commands.add("offDuty \t\t<>");
		commands.add("findDeliverer \t\t<orderName>");
		commands.add("setDeliveryPolicy \t<delPolicyName>");
		commands.add("setProfitPolicy \t<ProfitPolicyName>");
		commands.add("associateCard \t\t<userName> <cardType>");
		commands.add("showCouriers \t\t<>");
		commands.add("showCourierDeliveries \t<>");
		commands.add("showRestaurantTop \t<>");
		commands.add("showCustomers \t\t<>");
		commands.add("showMenuItem \t\t<restaurant-name>");
		commands.add("showTotalProfit \t<>");
		commands.add("showTotalProfit \t<startDate> <endDate>");
		commands.add("runTest \t\t<testScenario-file>");
		commands.add("help \t\t\t<>");
		commands.add("showRestaurants \t<>");
		commands.add("registerManager \t<name> <userName> <adress> <password>");

		Collections.sort(commands, String.CASE_INSENSITIVE_ORDER);
	}
	
	public void serializeDatas(){
		 FileOutputStream datafile = null;
	     ObjectOutputStream out = null;
		
		try{
			//Creation of a new file
			datafile = new FileOutputStream("MyFoodoraDatas.txt");
			//Creation of an ObjectOutputStream to serialize
			out = new ObjectOutputStream(datafile);
			//Serialize all the attributes of myFoodora
			out.writeObject(this.myFoodora.getListCustomer());
			out.writeObject(this.myFoodora.getListCourier());
			out.writeObject(this.myFoodora.getListRestaurant());
			out.writeObject(this.myFoodora.getListManager());
			out.writeObject(this.myFoodora.getListUsers());
			out.writeObject(this.myFoodora.getCompleteOrders());
			out.writeObject(this.myFoodora.getService_fee());
			out.writeObject(this.myFoodora.getDelivery_cost());
			out.writeObject(this.myFoodora.getMarkup_percentage());
			out.writeObject(this.myFoodora.getTargetProfit());
			out.writeObject(this.myFoodora.getTargetCommands());
			out.writeObject(this.myFoodora.getDeliveryPolicy());
			out.writeObject(this.myFoodora.getTargetPolicy());
			out.writeObject(this.myFoodora.getDeliverdSingleItems());
			out.writeObject(this.myFoodora.getDeliveredFullMeals());
			out.writeObject(this.myFoodora.getDeliveredHalfMeals());
			System.out.println("All the databases have been serialized and saved in 'MyFoodoraDatas.txt'.");
			out.close();
			datafile.close();
			
			}
			catch(FileNotFoundException e){
				e.getMessage();
			}catch(IOException e){
				e.printStackTrace();
			}
	}
	
	public String commandsToString(){
		String s = "";
		for (String string : this.commands) {
			s += string + "\n";
		}
		return s;
	}

	public static void main(String[] args) {
		Launch launch = new Launch();
		String exit = "";
		System.out.println("Welcome to MyFoodora, type 'help' to get a list of commands, 'q' to exit");
		while(!exit.equals("q")){
			if(launch.getCurrentUser() != null){
				System.out.println("Logged in as " + launch.getCurrentUser().getUserName() + "[" + launch.getUserType() + "]");
			}
			Scanner scanner = new Scanner(System.in);
			exit = scanner.nextLine();
			launch.executeCommand(exit);
		}
		//Before shutting down MyFoodora, all the data need to be serialized
		launch.serializeDatas();
		System.out.println("MyFoodora shut down");
	}
	
	
	
	public void executeCommand(String arg){
		String [] args = arg.split(" ");
		
		
		switch (args[0].toLowerCase()) {
		case "login":
			this.login(args);
			break;
		case "logout":
			this.logout(args);
			break;
		case "registerrestaurant":
			this.registerRestaurant(args);
			break;
		case "registercustomer":
			this.registerCustomer(args);
			break;
		case "registercourier":
			this.registerCourier(args);
			break;
		case "adddishrestaurantmenu":
			this.addDishRestaurantMenu(args);
			break;
		case "createmeal":
			this.createMeal(args);
			break;
		case "adddish2meal":
			this.addDish2Meal(args);
			break;
		case "showmeal":
			this.showMeal(args);
			break;
		case "setspecialoffer":
			this.setSpecialOffer(args);
			break;
		case "removefromspecialoffer":
			this.removeFromSpecialOffer(args);
			break;
		case "createorder":
			this.createOrder(args);
			break;
		case "additem2order":
			this.addItem2Order(args);
			break;
		case "endorder":
			this.endOrder(args);
			break;
		case "onduty":
			this.onDuty(args);
			break;
		case "offduty":
			this.offDuty(args);
			break;
		case "finddeliverer":
			this.findDeliverer(args);
			break;
		case "setdeliverypolicy":
			this.setDeliveryPolicy(args);
			break;
		case "setprofitpolicy":
			this.setProfitPolicy(args);
			break;
		case "associatecard":
			this.associateCard(args);
			break;
		case "showcourierdeliveries":
			this.showCourierDeliveries(args);
			break;
		case "showrestauranttop":
			this.showRestaurantTop(args);
			break;
		case "showcustomers":
			this.showCustomers(args);
			break;
		case "showmenuitem":
			this.showMenuItem(args);
			break;
		case "showtotalprofit":
			this.showTotalProfit(args);
			break;
		case "help":
			this.help(args);
			break;
		case "showrestaurants":
			this.showRestaurants(args);
			break;
		case "q":
			System.out.println("MyFoodora shut down");
		case "showcouriers":
			this.showCouriers(args);
			break;
		case "runtest" :
			this.runTest(args);
			break;
		case "registermanager" :
			this.registerManager(args);
			break;
		default:
			System.out.println("This command does not exist, 'help' for information");;
		}
		
		}
	
	/** Logs in a user, the current user is then set to the user given.
	 * @param args Name and password.
	 */
	public void login(String [] args){
		if(rightNumberofArguments(args, 3)){
			try {
				this.setCurrentUser(this.getMyFoodora().loginUser(args[1], args[2]));
				System.out.println("You are now loged in!");
			} catch (WrongUserNameOrPassWordException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	/** Logs out the currently logged user.
	 * @param args no argument.
	 */
	public void logout(String [] args){
		if(rightNumberofArguments(args, 1)){
			if(this.getCurrentUser() == null){
				System.out.println("You are not logged in, you cannot get logged out");
				return;
			}
			this.setCurrentUser(null);
			System.out.println("See you next time, you're are logged out");
		}
	}
	
	/** Register a new restaurant.
	 * @param args name, address, username, password.
	 */
	public void registerRestaurant(String [] args ){
		if(rightNumberofArguments(args, 5)){
			String [] ad = args[2].split(",");
			int a = Integer.parseInt(ad[0]);
			int b = Integer.parseInt(ad[1]);
			int [] adress = {a,b};
			try {
				this.myFoodora.getRestaurantFactory().createAccount(args[1], args[3], args[4], "", "", adress);
			} catch (SameUserNameException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	/** A manager can register other managers to the system.
	 * @param args name, username, password, address.
	 */
	public void registerManager(String [] args){
		if(rightNumberofArguments(args, 5) && this.isManager()){
			String [] ad = args[3].split(",");
			int [] adress = {Integer.parseInt(ad[0]), Integer.parseInt(ad[1])};
			try {
				this.getMyFoodora().getManagerFactory().createAccount(args[1], args[2], args[4], "", "", adress);
			} catch (SameUserNameException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	/** Register a new customer.
	 * @param args first name, last name, username, adress, password.
	 */
	public void registerCustomer(String [] args){
		if(rightNumberofArguments(args, 6)){
			String [] ad = args[4].split(",");
			int [] adress = {Integer.parseInt(ad[0]), Integer.parseInt(ad[1])};
			try {
				this.myFoodora.getCustomerFactory().createAccount(args[2], args[3], args[5], "", "", adress);
				((Customer) myFoodora.getUser(args[3])).setSurname(args[1]);
			} catch (SameUserNameException e) {
				System.out.println(e.getMessage());
			} catch (UserNotFoundException e) {}
			
		}
	}
	
	/** Register a new Courier.
	 * @param args first name, last name, username, position, password.
	 */
	public void registerCourier(String [] args){
		if(rightNumberofArguments(args, 6)){
			String [] ad = args[4].split(",");
			int [] adress = {Integer.parseInt(ad[0]), Integer.parseInt(ad[1])};
			try {
				this.myFoodora.getCourierFactory().createAccount(args[2], args[3], args[5], "", "", adress);
				((Courier) myFoodora.getUser(args[3])).setSurname(args[1]);
				((Courier) myFoodora.getUser(args[3])).setAvailability(true);
			} catch (SameUserNameException e) {
				System.out.println(e.getMessage());
			} catch (UserNotFoundException e) {}
			
		}
	}
	
	/** A restaurant can add a new dish to its menu.
	 * @param args dish name, dish category, food category, unit price.
	 */
	public void addDishRestaurantMenu(String [] args){
		if(rightNumberofArguments(args, 5) && isRestaurant()){
			try {
				((Restaurant) this.getCurrentUser()).getMenu().addItem(args[2], args[1]);
				((Restaurant) this.getCurrentUser()).getMenu().getSingleItem(args[1]).setPrice(Double.parseDouble(args[4]));
				if(args[3].equalsIgnoreCase("gluten-free") || args[3].equalsIgnoreCase("glutenfree")){
					((Restaurant) this.getCurrentUser()).getMenu().getSingleItem(args[1]).setGlutenFree(true);
				}
				else if(args[3].equalsIgnoreCase("vegitarian") || args[3].equalsIgnoreCase("vegie")){
					((Restaurant) this.getCurrentUser()).getMenu().getSingleItem(args[1]).setVegetarian(true);
				}
			} catch (WrongItemAdded e) {
				System.out.println(e.getMessage());
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (ItemDoesNotExist e) {}
			
		}
	}
	
	// modified you need to decide if you want a halfMeal or a fullMeal
	/** A restaurant can create a new meal.
	 * @param args menu name, menu type
	 */
	public void createMeal (String [] args){
		if(rightNumberofArguments(args, 3) && isRestaurant()){
			try {
				((Restaurant) this.getCurrentUser()).createNewItem(args[2], args[1]);
			} catch (WrongItemAdded e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	/** A restaurant can add a dish to meal.
	 * @param args dish name, meal name.
	 */
	public void addDish2Meal(String [] args){
		if(rightNumberofArguments(args, 3) && isRestaurant()){
			try {
				System.out.println(((Restaurant) this.getCurrentUser()).getMenu());
				((Restaurant) this.getCurrentUser()).getMenu().addItemToMeal(args[1], args[2]);
			} catch (WrongItemAdded | ItemDoesNotExist e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	/** A restaurant can show a specified meal.
	 * @param args meal name.
	 */
	public void showMeal(String [] args){
		if(rightNumberofArguments(args, 2) && isRestaurant()){
			try {
				System.out.println(((Restaurant) this.getCurrentUser()).getMenu().getMeal(args[1]));
			} catch (ItemDoesNotExist e) {
				System.out.println(e.getMessage());;
			}
		}
	}
	
	/** A restaurant can set the special offer.
	 * @param args meal name.
	 */
	public void setSpecialOffer(String [] args){
		if(rightNumberofArguments(args, 2) && isRestaurant()){
			try {
				((Restaurant) this.getCurrentUser()).setMealOfTheWeek(args[1]);
			} catch (ItemDoesNotExist e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	/** A restaurant can remove the status special offer from a meal.
	 * @param args meal name.
	 */
	public void removeFromSpecialOffer(String [] args){
		if(rightNumberofArguments(args, 2) && isRestaurant()){
			try {
				((Restaurant) this.getCurrentUser()).getMenu().getMeal(args[1]).setMealOfTheWeek(false);
			} catch (ItemDoesNotExist e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	/** A customer can create a new command.
	 * @param args restaurant name, order name.
	 */
	public void createOrder(String [] args){
		if(rightNumberofArguments(args, 3) && isCustomer()){
			try {
				myOrders.add(((Customer) this.getCurrentUser()).newOrder(args[1], args[2]));
			} catch (RestaurantNotFoundException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	/** A customer can add an item to an order.
	 * @param args order name, item name.
	 */
	public void addItem2Order(String [] args){
		if(rightNumberofArguments(args, 3) && isCustomer()){
			try {
				this.getOrder(args[1]).AddItemToOrder(args[2], 1);
			} catch (ItemDoesNotExist e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	//modified no date as argument, it is always the actual date
	/** A customer can end an order.
	 * @param args
	 */
	public void endOrder(String [] args){
		if(rightNumberofArguments(args, 2) && isCustomer()){
			this.getOrder(args[1]).getBill();
			System.out.println(this.getOrder(args[1]));
			try {
				myFoodora.setCourierToOrder(this.getOrder(args[1]));
				myFoodora.closeOrder(this.getOrder(args[1]));
				
				System.out.println("Your courier is :" + this.getOrder(args[1]).getCourier());
				myOrders.remove(this.getOrder(args[1]));
			} catch (NoCourierFoundToDeliver | OrderNotCompletException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	/** A courier can change his status to 'on duty'.
	 * @param args empty
	 */
	public void onDuty(String [] args){
		if(rightNumberofArguments(args, 1) && isCourier()){
			((Courier) this.getCurrentUser()).setAvailability(true);
		}
	}
	
	/** A courier can change his status to 'off duty'.
	 * @param args empty
	 */
	public void offDuty(String [] args){
		if(rightNumberofArguments(args, 1) && isCourier()){
			((Courier) this.getCurrentUser()).setAvailability(false);
		}
	}
	
	// Set the courier to an order is done by the system not by the restaurant
	/**
	 * Find a deliverer for a given order.
	 * @param args 
	 */
	public void findDeliverer(String [] args){
		if(rightNumberofArguments(args, 2) && isManager()){
			try {
				myFoodora.setCourierToOrder(this.getOrder(args[1]));
			} catch (NoCourierFoundToDeliver e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	/** Set the delivery policy for the system.
	 * @param args Name of the policy.
	 */
	public void setDeliveryPolicy(String [] args){
		if(rightNumberofArguments(args, 2) && isManager()){
			((Manager) this.getCurrentUser()).setDeliveryPolicy(args[1]);
		}
	}
	
	/** Set the profit policy for the system.
	 * @param args Name of the profit policy.
	 */
	public void setProfitPolicy(String [] args){
		if(rightNumberofArguments(args, 2) && isManager()){
			((Manager) this.getCurrentUser()).setTargetPolicy(args[1]);
		}
	}
	
	/** A manager can associate a new card to a user.
	 * @param args Username, cardname.
	 */
	public void associateCard (String [] args){
		if(rightNumberofArguments(args, 3) && isManager()){
			try {
				((Customer) myFoodora.getUser(args[1])).setFidelityCard(args[2]);
			} catch (FidelityCardDoesNotExistException | UserNotFoundException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	
	/** Prints a sorted list of all couriers according to their number of delivered orders.
	 * Only possible as manager.
	 * @param args empty
	 */
	public void showCourierDeliveries(String [] args){
		if(rightNumberofArguments(args, 1) && isManager()){
			this.getMyFoodora().sortCourier();
			System.out.println(this.getMyFoodora().listCourierToString(this.getCurrentUser()));
		}
	}
	
	/** Prints a sorted list of all the restaurants according to their total selling. 
	 * If the user is a manager he can see all the selling.
	 * @param args empty
	 */
	public void showRestaurantTop(String [] args){
		if(rightNumberofArguments(args, 1) && isManager()){
			this.getMyFoodora().sortRestaurant();
			System.out.println(this.getMyFoodora().listRestaurantsToString(this.getCurrentUser()));
		}
	}
	
	
	
	/** Show the menu of a given restaurant.
	 * @param args Name of the restaurant.
	 */
	public void showMenuItem(String [] args){
		if(rightNumberofArguments(args, 2)){
			try {
				System.out.println(((Restaurant) this.getMyFoodora().getUser(args[1])).getMenu());
			} catch (UserNotFoundException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	/** Show the total profit of the system for a given time period if dates are given.
	 * If no dates are given the all time profit is printed.
	 * @param args empty or the dates limiting the time period.
	 */
	public void showTotalProfit(String [] args){
		if(rightNumberofArguments(args, 1, 3) && isManager()){
			if(args.length == 1){
				System.out.println("Total income : " + ((Manager) this.getCurrentUser()).computeTotalProfit());
			}
			else{
				int day1 = Integer.parseInt(args[1].split("/")[0]);
				int month1 = Integer.parseInt(args[1].split("/")[1]);
				int year1 = Integer.parseInt(args[1].split("/")[2]);
				int day2 = Integer.parseInt(args[2].split("/")[0]);
				int month2 = Integer.parseInt(args[2].split("/")[1]);
				int year2 = Integer.parseInt(args[2].split("/")[2]);
				double profit = 0;
				try {
					profit = ((Manager) this.getCurrentUser()).computeTotalIncomeAndProfitOverPeriod(day1, month1, year1, day2, month2, year2)[1];
				} catch (OrderNotCompletException e) {} // impossible case
				System.out.println("Total income between " + args[1] + " and " + args[2] + " : " + profit);
				
			}
		}
	}
	
	/** Show all available restaurants.
	 * @param args
	 */
	public void showRestaurants(String [] args){
		if(rightNumberofArguments(args, 1)){
			System.out.println(this.getMyFoodora().listRestaurantsToString(this.getCurrentUser()));
		}
	}
	
	/** A manager can see all registered couriers.
	 * @param args
	 */
	public void showCouriers(String [] args){
		if(rightNumberofArguments(args, 1) && isManager()){
			System.out.println(this.getMyFoodora().listCourierToString(this.getCurrentUser()));
		}
	}
	
	/** A manager can see all registered clients.
	 * @param args
	 */
	public void showCustomers(String [] args){
		if(rightNumberofArguments(args, 1) && isManager()){
			System.out.println(this.getMyFoodora().listCustomerToString());
		}
	}
	
	/** Prints all available commands.
	 * @param args
	 */
	public void help(String [] args){
		if(rightNumberofArguments(args, 1)){
			System.out.println(this.commandsToString());
		}
	}
	
	/** Running a test with a certain number of commands that are executed from the given file.
	 * @param fileName Name of the file to be executed.
	 */
	public void runTest(String[] args){
		String fileName = "";
		if(args.length == 1){
			fileName = "testScenario1.txt";
		}
		if(rightNumberofArguments(args, 2, 1)){
			if(args.length == 2){
				fileName = args[1];
			}
			try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
			    String line;
			    while ((line = br.readLine()) != null) {
			    	System.out.println(line);
			    	if(!line.equals("")){
			    		this.executeCommand(line);
			    	}
			    }
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(args.length ==1){
			
		}
	}
	
	
	
	
	/** Checks if the right number of arguments was given to a command.
	 * @param args String array of arguments.
	 * @param length Cerrect length for the specific command.
	 * @return True if array length is correct.
	 */
	public boolean rightNumberofArguments(String [] args, int length){
		if(args.length != length){
			System.out.println("Wrong number of inputs");
			return false;
		}
		return true;
	}
	
	/** Overload of the previous functions to check when two number of arguments are possible.
	 * @param args String array of arguments.
	 * @param length Correct length for the specific command.
	 * @param length2 Second correct length for the specific command.
	 * @return True if array length is correct.
	 */
	public boolean rightNumberofArguments(String [] args, int length, int length2){
		if(args.length == length || args.length == length2){
			return true;
		}
		System.out.println("Wrong number of inputs");
		return false;
	}
	
	/** Checks if the current user is a manager.
	 * @return
	 */
	public boolean isManager(){
		if(this.getCurrentUser() instanceof Manager){
			return true;
		}
		System.out.println("Permission denied, only Manager can do this action");
		return false;
	}
	
	/** Checks if the current user is a restaurant.
	 * @return
	 */
	public boolean isRestaurant(){
		if(this.getCurrentUser() instanceof Restaurant){
			return true;
		}
		System.out.println("Permission denied, only Restaurants can do this action");
		return false;
	}
	
	/** Checks if the current user is a customer.
	 * @return
	 */
	public boolean isCustomer(){
		if(this.getCurrentUser() instanceof Customer){
			return true;
		}
		System.out.println("Permission denied, only Customers can do this action");
		return false;
	}
	
	/** Checks if the current user is a courier.
	 * @return
	 */
	public boolean isCourier(){
		if(this.getCurrentUser() instanceof Courier){
			return true;
		}
		System.out.println("Permission denied, only Courier can do this action");
		return false;
	}
	
	/** Searching an order by name in the list of orders.
	 * @param orderName
	 * @return Order object corresponding to the order name.
	 */
	public Order getOrder(String orderName){
		for (Order order : myOrders) {
			if(order.getName().equalsIgnoreCase(orderName)){
				return order;
			}
		}
		System.out.println("There is no order with that name");
		return null;
	}
	
	/** Get the user type to display it in the clui.
	 * @return
	 */
	public String getUserType(){
		if(this.getCurrentUser() instanceof Manager){return "Manager";}
		else if(this.getCurrentUser() instanceof Restaurant) {return "Restaurant";}
		else if(this.getCurrentUser() instanceof Courier) {return "Courier";}
		else if(this.getCurrentUser() instanceof Customer){return "Customer";}
		else{return "not logged in";}
	}

	/**
	 * @return the currentUser
	 */
	public User getCurrentUser() {
		return currentUser;
	}

	/**
	 * @param currentUser the currentUser to set
	 */
	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}

	/**
	 * @return the myFoodora
	 */
	public MyFoodora getMyFoodora() {
		return myFoodora;
	}

	/**
	 * @param myFoodora the myFoodora to set
	 */
	public void setMyFoodora(MyFoodora myFoodora) {
		this.myFoodora = myFoodora;
	}
	
	

}
