package GUI;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuBarManager extends JMenuBar{
	private JMenuItem showAllUsers;
	private JMenuItem showCustomers;
	private JMenuItem showRestaurants;
	private JMenuItem showCouriers;
	private JMenuItem showManagers;
	private JMenuItem mostSellingRestaurant;
	private JMenuItem leastSellingRestaurant;
	private JMenuItem mostActiveCourier;
	private JMenuItem leastActiveCourier;
	private JMenuItem getServiceFeePercentage;
	private JMenuItem setServiceFeePercentage;
	private JMenuItem getMarkupPercentage;
	private JMenuItem setMarkupPercentage;
	private JMenuItem getDeliveryCost;
	private JMenuItem setDeliveryCost;
	private JMenuItem totalIncome;
	private JMenuItem totalIncomePerCustomer;
	private JMenuItem profit;
	private JMenuItem getDeliveryPolicy;
	private JMenuItem setDeliveryPolicy;
	private JMenuItem getProfile;
	private JMenuItem setEmail;
	private JMenuItem setPhone;
	private JMenuItem setAdress;
	
	public MenuBarManager(){
		//Creation of the menu bar
		//JMenuBar menuBar = new JMenuBar();
		JMenu usersMenu = new JMenu("Users");
		JMenu comparisonsMenu = new JMenu("Comparisons");
		JMenu feesMenu = new JMenu("Fees");
		JMenu earningsMenu = new JMenu("Earnings");
		JMenu deliveryPolicyMenu = new JMenu("Delivery Policy");
		JMenu profileMenu = new JMenu("Profile");
		
		add(usersMenu);
		add(comparisonsMenu);
		add(feesMenu);
		add(earningsMenu);
		add(deliveryPolicyMenu);
		add(profileMenu);
		
		//usersMenu
		this.showAllUsers = new JMenuItem("Show all users");
		this.showCustomers = new JMenuItem("Show customers");
		this.showRestaurants = new JMenuItem("Show restaurants");
		this.showCouriers = new JMenuItem("Show couriers");
		this.showManagers = new JMenuItem("Show managers");
		usersMenu.add(this.showAllUsers);
		usersMenu.add(this.showCustomers);
		usersMenu.add(this.showRestaurants);
		usersMenu.add(this.showCouriers);
		usersMenu.add(this.showManagers);
		
		//comparisonsMenu
		JMenu comparisonRestMenu = new JMenu("Restaurants");
		JMenu comparisonCourMenu = new JMenu("Couriers");
		this.mostSellingRestaurant = new JMenuItem("Get most selling restaurant");
		this.leastSellingRestaurant = new JMenuItem("Get least selling restaurant");
		this.mostActiveCourier = new JMenuItem("Get most active courier");
		this.leastActiveCourier = new JMenuItem("Get least active courier");
		comparisonRestMenu.add(this.mostSellingRestaurant);
		comparisonRestMenu.add(this.leastSellingRestaurant);
		comparisonCourMenu.add(this.mostActiveCourier);
		comparisonCourMenu.add(this.leastActiveCourier);
		comparisonsMenu.add(comparisonRestMenu);
		comparisonsMenu.add(comparisonCourMenu);
		
		//feesMenu
		JMenu serviceFeeMenu = new JMenu("Service-fee percentage");
		JMenu markupMenu = new JMenu("Markup percentage");
		JMenu deliveryMenu = new JMenu("Delivery cost");
		this.getServiceFeePercentage = new JMenuItem("Get service-fee percentage");
		this.setServiceFeePercentage = new JMenuItem("Set service-fee percentage");
		this.getMarkupPercentage = new JMenuItem("Get markup percentage");
		this.setMarkupPercentage = new JMenuItem("Set markup percentage");
		this.getDeliveryCost = new JMenuItem("Get delivery cost");
		this.setDeliveryCost = new JMenuItem("Set delivery cost");
		serviceFeeMenu.add(this.getServiceFeePercentage);
		serviceFeeMenu.add(this.setServiceFeePercentage);
		markupMenu.add(this.getMarkupPercentage);
		markupMenu.add(this.setMarkupPercentage);
		deliveryMenu.add(this.getDeliveryCost);
		deliveryMenu.add(this.setDeliveryCost);
		feesMenu.add(serviceFeeMenu);
		feesMenu.add(markupMenu);
		feesMenu.add(deliveryMenu);
		
		//earningsMenu
		this.totalIncome = new JMenuItem("Total income over a time period");
		this.totalIncomePerCustomer = new JMenuItem("Income per customer over a time period");
		this.profit = new JMenuItem("Profit");
		earningsMenu.add(this.totalIncome);
		earningsMenu.add(this.totalIncomePerCustomer);
		earningsMenu.add(this.profit);
		
		//deliveryPolicyMenu
		this.getDeliveryPolicy = new JMenuItem("Get current delivery policy");
		this.setDeliveryPolicy = new JMenuItem("Set new delivery policy");
		deliveryPolicyMenu.add(this.getDeliveryPolicy);
		deliveryPolicyMenu.add(this.setDeliveryPolicy);
		
		//profileMenu
		this.getProfile = new JMenuItem("Get my profile");
		JMenu setProfile = new JMenu("Set way to contact me");
		this.setEmail = new JMenuItem("New email");
		this.setAdress = new JMenuItem("New letter");
		this.setPhone = new JMenuItem("New phone");
		setProfile.add(this.setEmail);
		setProfile.add(this.setPhone);
		setProfile.add(this.setAdress);
		profileMenu.add(this.getProfile);
		profileMenu.add(setProfile);
	}
	
	public static void main(String[] args) {
		MenuBarManager menuBar = new MenuBarManager();
		JFrame frame = new JFrame();
		frame.setJMenuBar(menuBar);
		frame.pack();
		frame.setVisible(true);
	}

	/**
	 * @return the showAllUsers
	 */
	public JMenuItem getShowAllUsers() {
		return showAllUsers;
	}

	/**
	 * @return the showCustomers
	 */
	public JMenuItem getShowCustomers() {
		return showCustomers;
	}

	/**
	 * @return the showRestaurants
	 */
	public JMenuItem getShowRestaurants() {
		return showRestaurants;
	}

	/**
	 * @return the showCouriers
	 */
	public JMenuItem getShowCouriers() {
		return showCouriers;
	}

	/**
	 * @return the showManagers
	 */
	public JMenuItem getShowManagers() {
		return showManagers;
	}

	/**
	 * @return the mostSellingRestaurant
	 */
	public JMenuItem getMostSellingRestaurant() {
		return mostSellingRestaurant;
	}

	/**
	 * @return the leastSellingRestaurant
	 */
	public JMenuItem getLeastSellingRestaurant() {
		return leastSellingRestaurant;
	}

	/**
	 * @return the mostActiveCourier
	 */
	public JMenuItem getMostActiveCourier() {
		return mostActiveCourier;
	}

	/**
	 * @return the leastActiveCourier
	 */
	public JMenuItem getLeastActiveCourier() {
		return leastActiveCourier;
	}

	/**
	 * @return the getServiceFeePercentage
	 */
	public JMenuItem getGetServiceFeePercentage() {
		return getServiceFeePercentage;
	}

	/**
	 * @return the setServiceFeePercentage
	 */
	public JMenuItem getSetServiceFeePercentage() {
		return setServiceFeePercentage;
	}

	/**
	 * @return the getMarkupPercentage
	 */
	public JMenuItem getGetMarkupPercentage() {
		return getMarkupPercentage;
	}

	/**
	 * @return the setMarkupPercentage
	 */
	public JMenuItem getSetMarkupPercentage() {
		return setMarkupPercentage;
	}

	/**
	 * @return the getDeliveryCost
	 */
	public JMenuItem getGetDeliveryCost() {
		return getDeliveryCost;
	}

	/**
	 * @return the setDeliveryCost
	 */
	public JMenuItem getSetDeliveryCost() {
		return setDeliveryCost;
	}

	/**
	 * @return the toalIncome
	 */
	public JMenuItem getTotalIncome() {
		return totalIncome;
	}

	/**
	 * @return the toalIncomePerCustomer
	 */
	public JMenuItem getTotalIncomePerCustomer() {
		return totalIncomePerCustomer;
	}

	/**
	 * @return the profit
	 */
	public JMenuItem getProfit() {
		return profit;
	}

	/**
	 * @return the getDeliveryPolicy
	 */
	public JMenuItem getGetDeliveryPolicy() {
		return getDeliveryPolicy;
	}

	/**
	 * @return the setDeliveryPolicy
	 */
	public JMenuItem getSetDeliveryPolicy() {
		return setDeliveryPolicy;
	}

	/**
	 * @return the getProfile
	 */
	public JMenuItem getGetProfile() {
		return getProfile;
	}

	/**
	 * @return the setEmail
	 */
	public JMenuItem getSetEmail() {
		return setEmail;
	}

	/**
	 * @return the setPhone
	 */
	public JMenuItem getSetPhone() {
		return setPhone;
	}

	/**
	 * @return the setAdress
	 */
	public JMenuItem getSetAdress() {
		return setAdress;
	}
}
