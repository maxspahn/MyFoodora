package GUI;

import java.awt.Color;
import java.awt.MenuBar;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class MenuBarCustomer extends JMenuBar{
	private JMenuItem basicFidelityCardItem;
	private JMenuItem pointFidelityCardItem;
	private JMenuItem lotteryFidelityCardItem;
	private JMenuItem showFeatures;
	private JMenuItem readNotifications;
	private JMenuItem yesOffers;
	private JMenuItem noOffers;
	private JMenuItem contactEmail;
	private JMenuItem contactPhone;
	private JMenuItem contactLetter;
	private JMenuItem historyOfOrders;
	private JMenuItem getProfile;
	private JMenuItem setEmail;
	private JMenuItem setPhone;
	private JMenuItem setAdress;
	private JLabel text;
	
	public MenuBarCustomer(){
		//setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
		
		//Creation of the menu bar
		//JMenuBar menuBar = new JMenuBar();
		JMenu fidelityCardMenu = new JMenu("Fidelity Card");
		JMenu offersMenu = new JMenu("Offers");
		JMenu historyOfOrdersMenu = new JMenu("History of orders");
		JMenu profileMenu = new JMenu("Profile");
		
		add(fidelityCardMenu);
		add(offersMenu);
		add(historyOfOrdersMenu);
		add(profileMenu);
		
		//Creation of the items of the fidelityCardMenu
		JMenu setFidelityCard = new JMenu("Set Fidelity Card");
		this.basicFidelityCardItem = new JMenuItem("Change to basic fidelity card");
		this.pointFidelityCardItem = new JMenuItem("Change to point fidelity card");
		this.lotteryFidelityCardItem = new JMenuItem("Change to lottery fidelity card");
		setFidelityCard.add(this.basicFidelityCardItem);
		setFidelityCard.add(this.pointFidelityCardItem);
		setFidelityCard.add(this.lotteryFidelityCardItem);
		
		this.showFeatures = new JMenuItem("Show features");
		fidelityCardMenu.add(setFidelityCard);
		fidelityCardMenu.add(showFeatures);
		
		//Creation of the items of the offersMenu
		this.readNotifications = new JMenuItem("Read notifications");
		
		JMenu receiveOffers = new JMenu("Receive offers");
		this.yesOffers = new JMenuItem("Yes");
		this.noOffers = new JMenuItem("No");
		receiveOffers.add(this.yesOffers);
		receiveOffers.add(this.noOffers);
		
		JMenu wayToContactMe = new JMenu("Set way to contact me");
		this.contactEmail = new JMenuItem("Change to email");
		this.contactLetter = new JMenuItem("Change to letter");
		this.contactPhone = new JMenuItem("Change to phone");
		wayToContactMe.add(this.contactEmail);
		wayToContactMe.add(this.contactPhone);
		wayToContactMe.add(this.contactLetter);
		
		offersMenu.add(this.readNotifications);
		offersMenu.add(receiveOffers);
		offersMenu.add(wayToContactMe);
		
		//Creation of the items of the historyOfOrdersMenu
		this.historyOfOrders = new JMenuItem("Get history of orders");
		historyOfOrdersMenu.add(this.historyOfOrders);
		
		//Creation of the items of the profileMenu
		this.getProfile = new JMenuItem("Get my profile");
		
		JMenu setProfile = new JMenu("Set my profile");
		this.setEmail = new JMenuItem("Set my email");
		this.setPhone = new JMenuItem("Set my phone");
		this.setAdress = new JMenuItem("Set my adress");
		setProfile.add(this.setEmail);
		setProfile.add(this.setPhone);
		setProfile.add(this.setAdress);
		profileMenu.add(this.getProfile);
		profileMenu.add(setProfile);
		
		//frame.setJMenuBar(menuBar);
		setVisible(true);
	}
	
	
	public static void main(String[] args) {
		MenuBarCustomer menuBar = new MenuBarCustomer();
		JFrame frame = new JFrame();
		frame.setJMenuBar(menuBar);
		frame.pack();
		frame.setVisible(true);
		
	}


	/**
	 * @return the basicFidelityCardItem
	 */
	public JMenuItem getBasicFidelityCardItem() {
		return basicFidelityCardItem;
	}


	/**
	 * @return the pointFidelityCardItem
	 */
	public JMenuItem getPointFidelityCardItem() {
		return pointFidelityCardItem;
	}


	/**
	 * @return the lotteryFidelityCardItem
	 */
	public JMenuItem getLotteryFidelityCardItem() {
		return lotteryFidelityCardItem;
	}


	/**
	 * @return the showFeatures
	 */
	public JMenuItem getShowFeatures() {
		return showFeatures;
	}


	/**
	 * @return the readNotifications
	 */
	public JMenuItem getReadNotifications() {
		return readNotifications;
	}


	/**
	 * @return the yesOffers
	 */
	public JMenuItem getYesOffers() {
		return yesOffers;
	}


	/**
	 * @return the noOffers
	 */
	public JMenuItem getNoOffers() {
		return noOffers;
	}


	/**
	 * @return the contactEmail
	 */
	public JMenuItem getContactEmail() {
		return contactEmail;
	}


	/**
	 * @return the contactPhone
	 */
	public JMenuItem getContactPhone() {
		return contactPhone;
	}


	/**
	 * @return the contactLetter
	 */
	public JMenuItem getContactLetter() {
		return contactLetter;
	}


	/**
	 * @return the historyOfOrders
	 */
	public JMenuItem getHistoryOfOrders() {
		return historyOfOrders;
	}


	/**
	 * @return the profile
	 */
	public JMenuItem getGetProfile() {
		return getProfile;
	}


	/**
	 * @return the text
	 */
	public JLabel getText() {
		return text;
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
