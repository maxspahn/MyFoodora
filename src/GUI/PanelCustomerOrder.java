package GUI;

import java.awt.Color;
import restaurant.*;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import system.MyFoodora;
import user_management.Restaurant;

/**The class PanelCustomerOrder creates the panel used to make an order, it is only visible by a customer. It extends JPanel.
 * @author jeremyaugot
 * @author maxspahn
 *
 */
public class PanelCustomerOrder extends JPanel{
	private Restaurant chosenRestaurant;
	
	private JLabel textCustomerPanel;
	private JLabel textOrderPanel;
	private JLabel restaurantName;
	private JButton chooseItem;
	private JButton showItem;
	private JButton chooseMeal;
	private JButton showMeal;
	private JButton removeSomething;
	private JButton back;
	private JButton pay;
	private ImageIcon myFoodoraIcon;
	private JList<String> itemNamelist;
	private JList<String> mealNamelist;

	
	public PanelCustomerOrder(Restaurant restaurant, double coeffHeight, double coeffWidth){
		this.chosenRestaurant = restaurant;
		setLayout(null);
		
		//Display a registration message
		this.myFoodoraIcon = new ImageIcon("./ima/MyFoodora.png");
		JLabel myFoodoraLabel = new JLabel("",myFoodoraIcon,JLabel.CENTER);
		myFoodoraLabel.setBounds((int)(2100 * coeffWidth),(int)(100 * coeffHeight),(int)(700 * coeffWidth),(int)(300 * coeffHeight));
		add(myFoodoraLabel);
				
		//Creation of the area where the text is put
		JPanel textPanelCust = new JPanel();
		textPanelCust.setBorder(BorderFactory.createTitledBorder("Customer panel"));
		this.textCustomerPanel = new JLabel();
		textPanelCust.add(this.textCustomerPanel);
		textPanelCust.setBackground(Color.white);
		textPanelCust.setBounds((int)(15 * coeffWidth),(int)(15 * coeffHeight),(int)(1010 * coeffWidth),(int)(1620 * coeffHeight));
		add(textPanelCust);
		
		//Creation of the area where the summary of the order is put
		JPanel textPanelOrd = new JPanel();
		textPanelOrd.setBorder(BorderFactory.createTitledBorder("Your order"));
		this.textOrderPanel = new JLabel();
		textPanelOrd.add(this.textOrderPanel);
		textPanelOrd.setBackground(Color.white);
		textPanelOrd.setBounds((int)(1100 * coeffWidth),(int)(15 * coeffHeight),(int)(1010 * coeffWidth),(int)(1500 * coeffHeight));
		add(textPanelOrd);
		
		//Buttons
		this.chooseItem = new JButton("Choose");
		this.chooseItem.setBounds((int)(2470 * coeffWidth),(int)(710 * coeffHeight),(int)(180 * coeffWidth),(int)(50 * coeffHeight));
		add(this.chooseItem);
		
		this.showItem = new JButton("Show");
		this.showItem.setBounds((int)(2250 * coeffWidth),(int)(710 * coeffHeight),(int)(180 * coeffWidth),(int)(50 * coeffHeight));
		add(this.showItem);
		
		this.chooseMeal = new JButton("Choose");
		this.chooseMeal.setBounds((int)(2470 * coeffWidth),(int)(1310 * coeffHeight),(int)(180 * coeffWidth),(int)(50 * coeffHeight));
		add(this.chooseMeal);
		
		this.showMeal = new JButton("Show");
		this.showMeal.setBounds((int)(2250 * coeffWidth),(int)(1310 * coeffHeight),(int)(180 * coeffWidth),(int)(50 * coeffHeight));
		add(this.showMeal);
		
		this.removeSomething = new JButton("Remove something");
		this.removeSomething.setBounds((int)(1350 * coeffWidth),(int)(1550 * coeffHeight),(int)(300 * coeffWidth),(int)(50 * coeffHeight));
		add(this.removeSomething);
		
		this.back = new JButton("Back");
		this.back.setBounds((int)(2590 * coeffWidth),(int)(1600 * coeffHeight),(int)(120 * coeffWidth),(int)(50 * coeffHeight));
		add(this.back);
		
		this.pay = new JButton("Pay");
		this.pay.setBounds((int)(1750 * coeffWidth),(int)(1550 * coeffHeight),(int)(120 * coeffWidth),(int)(50 * coeffHeight));
		add(this.pay);
		
		
		//List of items
		ArrayList<SingleItem> allSingleItems = this.chosenRestaurant.getMenu().getSingleItems();
		int singleItemsNumber = allSingleItems.size();
		String[] singleItemsList = new String[singleItemsNumber];
		
		for (int i = 0; i < singleItemsNumber; i++) {
			SingleItem singleItem_i = allSingleItems.get(i);
				singleItemsList[i] = singleItem_i.getName();
		}
		
		this.itemNamelist = new JList<String>(singleItemsList);
		JScrollPane singleItemsPanel = new JScrollPane(this.itemNamelist);
		singleItemsPanel.setBorder(BorderFactory.createTitledBorder("Single Items"));
		singleItemsPanel.setBounds((int)(2200 * coeffWidth),(int)(400 * coeffHeight),(int)(500 * coeffWidth),(int)(300 * coeffHeight));
		add(singleItemsPanel);		
		
		//List of meals
		ArrayList<Meal> allMeals = this.chosenRestaurant.getMenu().getMeals();
		int mealsNumber = allMeals.size();
		String[] mealsList = new String[mealsNumber];
		
		for (int i = 0; i < mealsNumber; i++) {
			mealsList[i] = allMeals.get(i).getName();
		}
		
		this.mealNamelist = new JList<String>(mealsList);
		JScrollPane mealsPanel = new JScrollPane(this.mealNamelist);
		mealsPanel.setBorder(BorderFactory.createTitledBorder("Meals"));
		mealsPanel.setBounds((int)(2200 * coeffWidth),(int)(1000 * coeffHeight),(int)(500 * coeffWidth),(int)(300 * coeffHeight));
		add(mealsPanel);
		
		//Name of the chosen restaurant
		this.restaurantName = new JLabel(this.chosenRestaurant.getName());
		this.restaurantName.setBounds((int)(2310 * coeffWidth),(int)(360 * coeffHeight),(int)(300 * coeffWidth),(int)(20 * coeffHeight));
		add(this.restaurantName);
	}
	
	/**
	 * @return the chosenRestaurant
	 */
	public Restaurant getChosenRestaurant() {
		return chosenRestaurant;
	}



	/**
	 * @return the textCustomerPanel
	 */
	public JLabel getTextCustomerPanel() {
		return textCustomerPanel;
	}



	/**
	 * @return the textOrderPanel
	 */
	public JLabel getTextOrderPanel() {
		return textOrderPanel;
	}



	/**
	 * @return the chooseItem
	 */
	public JButton getChooseItem() {
		return chooseItem;
	}



	/**
	 * @return the showItem
	 */
	public JButton getShowItem() {
		return showItem;
	}



	/**
	 * @return the chooseMeal
	 */
	public JButton getChooseMeal() {
		return chooseMeal;
	}



	/**
	 * @return the showMeal
	 */
	public JButton getShowMeal() {
		return showMeal;
	}



	/**
	 * @return the removeSomething
	 */
	public JButton getRemoveSomething() {
		return removeSomething;
	}



	/**
	 * @return the itemNamelist
	 */
	public JList<String> getItemNamelist() {
		return itemNamelist;
	}



	/**
	 * @return the mealNamelist
	 */
	public JList<String> getMealNamelist() {
		return mealNamelist;
	}

	/**
	 * @param chosenRestaurant the chosenRestaurant to set
	 */
	public void setChosenRestaurant(Restaurant chosenRestaurant) {
		this.chosenRestaurant = chosenRestaurant;
		this.restaurantName.setText(chosenRestaurant.getName());
	}
	

	/**
	 * @return the back
	 */
	public JButton getBack() {
		return back;
	}

	
	/**
	 * @return the pay
	 */
	public JButton getPay() {
		return pay;
	}

	
}
