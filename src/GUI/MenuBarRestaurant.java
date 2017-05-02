package GUI;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**The class MenuBarRestaurant creates the menu bar displayed on the restaurant panel. It extends JManuBar.
 * @author jeremyaugot
 * @author maxspahn
 *
 */
public class MenuBarRestaurant extends JMenuBar{
	private JMenuItem getMyMenu;
	private JMenuItem getCurrentGenericDiscount;
	private JMenuItem getCurrentSpecialDiscount;
	private JMenuItem setNewSpecialDiscount;
	private JMenuItem historySoldSingleItems;
	private JMenuItem historySoldHalfMeals;
	private JMenuItem historySoldFullMeals;
	private JMenuItem getMyProfile;
	private JMenuItem setEmail;
	private JMenuItem setPhone;
	private JMenuItem setAdress;
	
	public MenuBarRestaurant(){
		
		//Creation of the menus
		JMenu myMenu = new JMenu("My Menu");
		JMenu discountsMenu = new JMenu("Discounts");
		JMenu ordersMenu = new JMenu("Orders");
		JMenu myProfileMenu = new JMenu("Profile");
		
		add(myMenu);
		add(discountsMenu);
		add(ordersMenu);
		add(myProfileMenu);
		
		//JMenu my Menu
		this.getMyMenu = new JMenuItem("Get my menu");
		myMenu.add(this.getMyMenu);
		
		//JMenu discountsMenu
		JMenu genericDiscount = new JMenu("Generic discount");
		JMenu specialDiscount = new JMenu("Special discount");
		this.getCurrentGenericDiscount = new JMenuItem("Get current generic discount");
		this.getCurrentSpecialDiscount = new JMenuItem("Get current special discount");
		this.setNewSpecialDiscount = new JMenuItem("Set new special discount");
		genericDiscount.add(this.getCurrentGenericDiscount);
		specialDiscount.add(this.getCurrentSpecialDiscount);
		specialDiscount.add(this.setNewSpecialDiscount);
		discountsMenu.add(genericDiscount);
		discountsMenu.add(specialDiscount);
		
		//JMenu ordersMenu
		JMenu historyOfOrders = new JMenu("History of orders");
		this.historySoldSingleItems = new JMenuItem("History of sold single items");
		this.historySoldHalfMeals = new JMenuItem("History of sold half meals");
		this.historySoldFullMeals = new JMenuItem("History of sold full meals");
		historyOfOrders.add(this.historySoldSingleItems);
		historyOfOrders.add(this.historySoldHalfMeals);
		historyOfOrders.add(this.historySoldFullMeals);
		ordersMenu.add(historyOfOrders);
		
		//JMenu myProfileMenu
		this.getMyProfile = new JMenuItem("Get my profile");
		JMenu setProfile = new JMenu("Set my profile");
		this.setEmail = new JMenuItem("Set my email");
		this.setPhone = new JMenuItem("Set my phone");
		this.setAdress = new JMenuItem("Set my adress");
		setProfile.add(this.setEmail);
		setProfile.add(this.setPhone);
		setProfile.add(this.setAdress);
		myProfileMenu.add(this.getMyProfile);
		myProfileMenu.add(setProfile);
		
	}

	/**
	 * @return the getMyMenu
	 */
	public JMenuItem getGetMyMenu() {
		return getMyMenu;
	}

	/**
	 * @return the getCurrentGenericDiscount
	 */
	public JMenuItem getGetCurrentGenericDiscount() {
		return getCurrentGenericDiscount;
	}

	/**
	 * @return the getCurrentSpecialDiscount
	 */
	public JMenuItem getGetCurrentSpecialDiscount() {
		return getCurrentSpecialDiscount;
	}

	/**
	 * @return the setNewSpecialDiscount
	 */
	public JMenuItem getSetNewSpecialDiscount() {
		return setNewSpecialDiscount;
	}

	/**
	 * @return the historySoldSingleItems
	 */
	public JMenuItem getHistorySoldSingleItems() {
		return historySoldSingleItems;
	}

	/**
	 * @return the historySoldHalfMeals
	 */
	public JMenuItem getHistorySoldHalfMeals() {
		return historySoldHalfMeals;
	}

	/**
	 * @return the historySoldFullMeals
	 */
	public JMenuItem getHistorySoldFullMeals() {
		return historySoldFullMeals;
	}

	/**
	 * @return the getMyProfile
	 */
	public JMenuItem getGetMyProfile() {
		return getMyProfile;
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
