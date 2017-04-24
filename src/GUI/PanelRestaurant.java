package GUI;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import system.MyFoodora;

public class PanelRestaurant extends JPanel{
	
	private ImageIcon myFoodoraIcon;
	private JLabel text;
	private JList<String> actionsToEditMenu;
	private JButton okActionSetMenu;
	private JButton logout;
	
	public PanelRestaurant(){
		setLayout(null);
		
		//Display "MyFoodora"
		this.myFoodoraIcon = new ImageIcon("MyFoodora.png");
		JLabel myFoodoraLabel = new JLabel("",myFoodoraIcon,JLabel.CENTER);
		myFoodoraLabel.setBounds(2200, 100, 500, 200);
		add(myFoodoraLabel);
				
		//Creation of the area where the text is put
		JPanel textPanel = new JPanel();
		textPanel.setBorder(BorderFactory.createTitledBorder("Restaurant panel"));
		this.text = new JLabel();
		textPanel.add(this.text);
		textPanel.setBackground(Color.white);
		textPanel.setBounds(15, 15, 2100, 1620);
		add(textPanel);
		
		//Edit Menu		
		String[] actions = {"Add a single item","Create a meal","Remove a single item", "Remove a meal","Set the meal of the week"};
		this.actionsToEditMenu = new JList<String>(actions);
		
		JScrollPane editMenuPanel = new JScrollPane(this.actionsToEditMenu);
		editMenuPanel.setBorder(BorderFactory.createTitledBorder("SET MY MENU"));
		editMenuPanel.setBackground(Color.white);
		editMenuPanel.setBounds(2300, 400, 300, 300);
		add(editMenuPanel);
		
		//Buttons
		this.okActionSetMenu = new JButton("OK");
		this.okActionSetMenu.setBounds(2400, 780, 100, 50);
		add(this.okActionSetMenu);
		
		this.logout = new JButton("Logout");
		this.logout.setBounds(2350, 1400, 200, 100);
		add(this.logout);
	}
	
	public static void main(String[] args) {
		PanelRestaurant panel = new PanelRestaurant();
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
	 * @return the actionsToEditMenu
	 */
	public JList<String> getActionsToEditMenu() {
		return actionsToEditMenu;    
	}

	/**
	 * @return the okActionSetMenu
	 */
	public JButton getOkActionSetMenu() {
		return okActionSetMenu;
	}

	/**
	 * @return the logout
	 */
	public JButton getLogout() {
		return logout;
	}

}
