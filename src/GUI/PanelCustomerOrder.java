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

	
	public PanelCustomerOrder(Restaurant restaurant){
		this.chosenRestaurant = restaurant;
		setLayout(null);
		
		//Display a registration message
		this.myFoodoraIcon = new ImageIcon("MyFoodora.png");
		JLabel myFoodoraLabel = new JLabel("",myFoodoraIcon,JLabel.CENTER);
		myFoodoraLabel.setBounds(2200, 100, 500, 200);
		add(myFoodoraLabel);
				
		//Creation of the area where the text is put
		JPanel textPanelCust = new JPanel();
		textPanelCust.setBorder(BorderFactory.createTitledBorder("Customer panel"));
		this.textCustomerPanel = new JLabel();
		textPanelCust.add(this.textCustomerPanel);
		textPanelCust.setBackground(Color.white);
		textPanelCust.setBounds(15, 15, 1010, 1620);
		add(textPanelCust);
		
		//Creation of the area where the summary of the order is put
		JPanel textPanelOrd = new JPanel();
		textPanelOrd.setBorder(BorderFactory.createTitledBorder("Your order"));
		this.textOrderPanel = new JLabel();
		textPanelOrd.add(this.textOrderPanel);
		textPanelOrd.setBackground(Color.white);
		textPanelOrd.setBounds(1100, 15, 1010, 1500);
		add(textPanelOrd);
		
		//Buttons
		this.chooseItem = new JButton("Choose");
		this.chooseItem.setBounds(2470, 710, 100, 50);
		add(this.chooseItem);
		
		this.showItem = new JButton("Show");
		this.showItem.setBounds(2330,710, 100, 50);
		add(this.showItem);
		
		this.chooseMeal = new JButton("Choose");
		this.chooseMeal.setBounds(2470, 1310, 100, 50);
		add(this.chooseMeal);
		
		this.showMeal = new JButton("Show");
		this.showMeal.setBounds(2330,1310, 100, 50);
		add(this.showMeal);
		
		this.removeSomething = new JButton("Remove something");
		this.removeSomething.setBounds(1400,1550, 200, 50);
		add(this.removeSomething);
		
		this.back = new JButton("Back");
		this.back.setBounds(2600,1600, 100, 50);
		add(this.back);
		
		this.pay = new JButton("Pay");
		this.pay.setBounds(1700,1550, 100, 50);
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
		singleItemsPanel.setBounds(2300, 400, 300, 300);
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
		mealsPanel.setBounds(2300, 1000, 300, 300);
		add(mealsPanel);
		
		//Name of the chosen restaurant
		this.restaurantName = new JLabel(this.chosenRestaurant.getName());
		this.restaurantName.setBounds(2310, 360, 300, 20);
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

	public static void main(String[] args) {
		MyFoodora myFoodora = new MyFoodora();
		myFoodora.load();
		PanelCustomerOrder panel = new PanelCustomerOrder(myFoodora.getListRestaurant().get(0));
		JFrame frame = new JFrame();
		frame.add(panel);
		frame.pack();
		frame.setVisible(true);
	}
}
