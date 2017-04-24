package GUI;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import restaurant.*;
import system.MyFoodora;
import user_management.Restaurant;

public class PanelCreateMeal extends JPanel{
	private JLabel textCreateMealPanel;
	private JComboBox<String> starterComboBox;
	private JComboBox<String> mainDishComboBox;
	private JComboBox<String> dessertComboBox;
	private JButton showStarter;
	private JButton showMainDish;
	private JButton showDessert;
	private JButton create;
	private JButton back;
	private JLabel menuPanel;

	public PanelCreateMeal(Restaurant rest){
		setLayout(null);
		
		//Display a meal creation message
		ImageIcon iconRegistration = new ImageIcon("MealCreation.png");
		JLabel label = new JLabel("",iconRegistration,JLabel.CENTER);
		label.setBounds(700, 200, 1300, 200);
		
		//Creation of the area where the text is put
		JPanel textPanelRest = new JPanel();
		textPanelRest.setBorder(BorderFactory.createTitledBorder("Item's features"));
		this.textCreateMealPanel = new JLabel();
		textPanelRest.add(this.textCreateMealPanel);
		textPanelRest.setBackground(Color.white);
		textPanelRest.setBounds(100, 600, 700, 1000);
		
		//Choice of a starter
		ArrayList<Starter> starters = rest.getMenu().getStarters();
		String[] startersList = new String[starters.size()+1];
		startersList[0] = "";
		for (int j = 0; j < starters.size(); j++) {
			startersList[j+1] = starters.get(j).getName();
		}
		this.starterComboBox = new JComboBox<String>(startersList);
		
		JPanel starterPanel = new JPanel();
		starterPanel.add(this.starterComboBox);
		starterPanel.setBounds(1200, 900, 200, 60);
		starterPanel.setBackground(Color.white);
		starterPanel.setBorder(BorderFactory.createTitledBorder("List of starters"));
		
		//Choice of a main dish
		ArrayList<MainDish> mainDishes = rest.getMenu().getMainDishes();
		String[] mainDishesList = new String[mainDishes.size()+1];
		mainDishesList[0] = "";
		for (int i = 0; i < mainDishes.size(); i++) {
			mainDishesList[i+1] = mainDishes.get(i).getName();
		}
		this.mainDishComboBox = new JComboBox<String>(mainDishesList);
		
		JPanel mainDishPanel = new JPanel();
		mainDishPanel.add(this.mainDishComboBox);
		mainDishPanel.setBounds(1550, 900, 200, 60);
		mainDishPanel.setBackground(Color.white);
		mainDishPanel.setBorder(BorderFactory.createTitledBorder("List of main dishes"));
		
		//Choice of a dessert
		ArrayList<Desert> desserts = rest.getMenu().getDesserts();
		String[] dessertsList = new String[desserts.size()+1];
		dessertsList[0] = "";
		for (int i = 0; i < desserts.size(); i++) {
			dessertsList[i+1] = desserts.get(i).getName();
		}
		this.dessertComboBox = new JComboBox<String>(dessertsList);
		
		JPanel dessertPanel = new JPanel();
		dessertPanel.add(this.dessertComboBox);
		dessertPanel.setBounds(1900, 900, 200, 60);
		dessertPanel.setBackground(Color.white);
		dessertPanel.setBorder(BorderFactory.createTitledBorder("List of desserts"));
		
		//Buttons
		this.showStarter = new JButton("Show");
		this.showStarter.setBounds(1250,1000, 100, 50);
		
		this.showMainDish = new JButton("Show");
		this.showMainDish.setBounds(1600,1000, 100, 50);
		
		this.showDessert = new JButton("Show");
		this.showDessert.setBounds(1950,1000, 100, 50);
		
		this.create = new JButton("Create");
		this.create.setBounds(1600,1200, 100, 50);
		
		//Button "Back"
		this.back = new JButton("Back");
		this.back.setBounds(10,10, 100, 40);
		
		add(label);
		add(textPanelRest);
		add(starterPanel);
		add(mainDishPanel);
		add(dessertPanel);
		add(this.showStarter);
		add(this.showMainDish);
		add(this.showDessert);
		add(this.create);
		add(this.back);
	
	}
	
	public static void main(String[] args) {
		MyFoodora myFoodora = new MyFoodora();
		myFoodora.load();
		PanelCreateMeal panel = new PanelCreateMeal(myFoodora.getListRestaurant().get(0));
		JFrame frame = new JFrame();
		frame.add(panel);
		frame.pack();
		frame.setVisible(true);
	}

	/**
	 * @return the textCreateMealPanel
	 */
	public JLabel getTextCreateMealPanel() {
		return textCreateMealPanel;
	}

	/**
	 * @return the starterComboBox
	 */
	public JComboBox<String> getStarterComboBox() {
		return starterComboBox;
	}

	/**
	 * @return the mainDishComboBox
	 */
	public JComboBox<String> getMainDishComboBox() {
		return mainDishComboBox;
	}

	/**
	 * @return the dessertComboBox
	 */
	public JComboBox<String> getDessertComboBox() {
		return dessertComboBox;
	}

	/**
	 * @return the showStarter
	 */
	public JButton getShowStarter() {
		return showStarter;
	}

	/**
	 * @return the showMainDish
	 */
	public JButton getShowMainDish() {
		return showMainDish;
	}

	/**
	 * @return the showDessert
	 */
	public JButton getShowDessert() {
		return showDessert;
	}

	/**
	 * @return the create
	 */
	public JButton getCreate() {
		return create;
	}

	/**
	 * @return the back
	 */
	public JButton getBack() {
		return back;
	}

	/**
	 * @return the menuPanel
	 */
	public JLabel getMenuPanel() {
		return menuPanel;
	}

}
