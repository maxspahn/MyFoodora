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

	
	public PanelCustomer(MyFoodora myFoodora, double coeffHeight, double coeffWidth){
		this.myFoodora = myFoodora;
		setLayout(null);
		
		//Display "MyFoodora"
		this.myFoodoraIcon = new ImageIcon("MyFoodora.png");
		JLabel myFoodoraLabel = new JLabel("",myFoodoraIcon,JLabel.CENTER);
		myFoodoraLabel.setBounds((int)(2200 * coeffWidth),(int)(100 * coeffHeight),(int)(500 * coeffWidth),(int)(200 * coeffHeight));
		add(myFoodoraLabel);
				
		//Creation of the area where the text is put
		JPanel textPanel = new JPanel();
		textPanel.setBorder(BorderFactory.createTitledBorder("Customer panel"));
		this.text = new JLabel();
		textPanel.add(this.text);
		textPanel.setBackground(Color.white);
		textPanel.setBounds((int)(15 * coeffWidth),(int)(15 * coeffHeight),(int)(2100 * coeffWidth),(int)(1620 * coeffHeight));
		add(textPanel);
		
		//Buttons
		this.choose = new JButton("Choose");
		this.choose.setBounds((int)(2400 * coeffWidth),(int)(780 * coeffHeight),(int)(100 * coeffWidth),(int)(50 * coeffHeight));
		add(this.choose);
		
		this.show = new JButton("Show");
		this.show.setBounds((int)(2400 * coeffWidth),(int)(710 * coeffHeight),(int)(100 * coeffWidth),(int)(50 * coeffHeight));
		add(this.show);
		
		this.logout = new JButton("Logout");
		this.logout.setBounds((int)(2350 * coeffWidth),(int)(1400 * coeffHeight),(int)(200 * coeffWidth),(int)(100 * coeffHeight));
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
		restaurantsPanel.setBounds((int)(2300 * coeffWidth),(int)(400 * coeffHeight),(int)(300 * coeffWidth),(int)(300 * coeffHeight));
		add(restaurantsPanel);		
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
