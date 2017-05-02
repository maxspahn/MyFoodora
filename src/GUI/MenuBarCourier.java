package GUI;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**The class MenuBarCourier creates the menu bar displayed on the courier panel. It extends JManuBar.
 * @author jeremyaugot
 * @author maxspahn
 *
 */
public class MenuBarCourier extends JMenuBar{
	private JMenuItem getProfile;
	private JMenuItem setEmail;
	private JMenuItem setPhone;
	private JMenuItem setAdress;
	private JMenuItem getAvailabilityStatus;
	private JMenuItem onDuty;
	private JMenuItem offDuty;
	private JMenuItem unregister;
	

	public MenuBarCourier(){
		//Creation of the menus
		JMenu profileMenu = new JMenu("Profile");
		JMenu availabilityMenu = new JMenu("Availability");
		JMenu unregisterMenu = new JMenu("Unregister");
		
		add(availabilityMenu);
		add(profileMenu);
		add(unregisterMenu);
		
		//Creation of the items of the availabilityMenu
		this.getAvailabilityStatus = new JMenuItem("Get availability status");
		JMenu setAvailability = new JMenu("Set availability status");
		this.onDuty = new JMenuItem("ON Duty");
		this.offDuty = new JMenuItem("OFF Duty");
		setAvailability.add(this.onDuty);
		setAvailability.add(this.offDuty);
		availabilityMenu.add(this.getAvailabilityStatus);
		availabilityMenu.add(setAvailability);
		
		//Creation of the items of the profileMenu
		this.getProfile = new JMenuItem("Get my profile");
				
		JMenu setProfile = new JMenu("Set my profile");
		this.setEmail = new JMenuItem("Set my email");
		this.setPhone = new JMenuItem("Set my phone");
		this.setAdress = new JMenuItem("Set my position");
		setProfile.add(this.setEmail);
		setProfile.add(this.setPhone);
		setProfile.add(this.setAdress);
		profileMenu.add(this.getProfile);
		profileMenu.add(setProfile);
		
		//Creation of the items of the unregisterMenu
		this.unregister = new JMenuItem("Unregister");
		unregisterMenu.add(this.unregister);
				
	}
	
	public static void main(String[] args) {
		MenuBarCourier menuBar = new MenuBarCourier();
		JFrame frame = new JFrame();
		frame.setJMenuBar(menuBar);
		frame.pack();
		frame.setVisible(true);
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

	/**
	 * @return the getAvailabilityStatus
	 */
	public JMenuItem getGetAvailabilityStatus() {
		return getAvailabilityStatus;
	}

	/**
	 * @return the onDuty
	 */
	public JMenuItem getOnDuty() {
		return onDuty;
	}

	/**
	 * @return the offDuty
	 */
	public JMenuItem getOffDuty() {
		return offDuty;
	}

	/**
	 * @return the unregister
	 */
	public JMenuItem getUnregister() {
		return unregister;
	}
	
}
