package GUI;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import user_management.*;

import system.*;

public class PanelCustomer extends JPanel{
	private MyFoodora myFoodora;
	
	private JLabel text;
	private JButton choose;
	private JButton logout;
	private JButton show;
	private ImageIcon myFoodoraIcon;
	private JList<String> activatedRestaurantNamelist;
	private Restaurant[] activatedRestaurantList;

	
	public PanelCustomer(MyFoodora myFoodora){
		this.myFoodora = myFoodora;
		setLayout(null);
		
		//Display "MyFoodora"
		this.myFoodoraIcon = new ImageIcon("MyFoodora.png");
		JLabel myFoodoraLabel = new JLabel("",myFoodoraIcon,JLabel.CENTER);
		myFoodoraLabel.setBounds(2200, 100, 500, 200);
		add(myFoodoraLabel);
				
		//Creation of the area where the text is put
		JPanel textPanel = new JPanel();
		textPanel.setBorder(BorderFactory.createTitledBorder("Customer panel"));
		this.text = new JLabel();
		textPanel.add(this.text);
		textPanel.setBackground(Color.white);
		textPanel.setBounds(15, 15, 2100, 1620);
		add(textPanel);
		
		//Buttons
		this.choose = new JButton("Choose");
		this.choose.setBounds(2400, 780, 100, 50);
		add(this.choose);
		
		this.show = new JButton("Show");
		this.show.setBounds(2400,710, 100, 50);
		add(this.show);
		
		this.logout = new JButton("Logout");
		this.logout.setBounds(2350, 1400, 200, 100);
		add(this.logout);
		
		//List of the restaurants
		ArrayList<Restaurant> allRestaurants = this.myFoodora.getListRestaurant();
		int restNumber = allRestaurants.size();
		String[] rest = new String[restNumber];
		this.activatedRestaurantList = new Restaurant[restNumber];
		
		//Put in the list to display only the restaurants which are activated
		for (int i = 0; i < restNumber; i++) {
			Restaurant rest_i = allRestaurants.get(i);
			if(rest_i.isActivated()){
				this.activatedRestaurantList[i] = rest_i;
				rest[i] = rest_i.getName();
			}
		}
		
		this.activatedRestaurantNamelist = new JList<String>(rest);
		JScrollPane restaurantsPanel = new JScrollPane(this.activatedRestaurantNamelist);
		restaurantsPanel.setBorder(BorderFactory.createTitledBorder("ORDER"));
		restaurantsPanel.setBounds(2300, 400, 300, 300);
		add(restaurantsPanel);		
	}
	
	public static void main(String[] args) {
		MyFoodora myFoodora = new MyFoodora();
		myFoodora.load();
		PanelCustomer panel = new PanelCustomer(myFoodora);
		JFrame frame = new JFrame();
		frame.add(panel);
		frame.pack();
		frame.setVisible(true);
	}

	/**
	 * @return the text
	 */
	public JLabel getText() {
		return text;
	}

	/**
	 * @return the order
	 */
	public JButton getChoose() {
		return choose;
	}

	/**
	 * @return the logout
	 */
	public JButton getLogout() {
		return logout;
	}

	/**
	 * @return the show
	 */
	public JButton getShow() {
		return show;
	}

	/**
	 * @return the activatedRestaurantNamelist
	 */
	public JList<String> getActivatedRestaurantNamelist() {
		return activatedRestaurantNamelist;
	}

	/**
	 * @return the activatedRestaurantList
	 */
	public Restaurant[] getActivatedRestaurantList() {
		return activatedRestaurantList;
	}
	
}
