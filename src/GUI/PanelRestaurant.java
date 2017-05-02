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

/**The class PanelRestaurant creates the panel visible by the restaurants. It extends JPanel.
 * @author jeremyaugot
 * @author maxspahn
 *
 */
public class PanelRestaurant extends JPanel{
	
	private ImageIcon myFoodoraIcon;
	private JLabel text;
	private JList<String> actionsToEditMenu;
	private JButton okActionSetMenu;
	private JButton logout;
	
	public PanelRestaurant(double coeffHeight, double coeffWidth){
		setLayout(null);
		
		//Display "MyFoodora"
		this.myFoodoraIcon = new ImageIcon("MyFoodora.png");
		JLabel myFoodoraLabel = new JLabel("",myFoodoraIcon,JLabel.CENTER);
		myFoodoraLabel.setBounds((int)(2100 * coeffWidth),(int)(100 * coeffHeight),(int)(700 * coeffWidth),(int)(300 * coeffHeight));
		add(myFoodoraLabel);
				
		//Creation of the area where the text is put
		JPanel textPanel = new JPanel();
		textPanel.setBorder(BorderFactory.createTitledBorder("Restaurant panel"));
		this.text = new JLabel();
		textPanel.add(this.text);
		textPanel.setBackground(Color.white);
		textPanel.setBounds((int)(15 * coeffWidth),(int)(15 * coeffHeight),(int)(2100 * coeffWidth),(int)(1620 * coeffHeight));
		add(textPanel);
		
		//Edit Menu		
		String[] actions = {"Add a single item","Create a meal","Remove a single item", "Remove a meal","Set the meal of the week"};
		this.actionsToEditMenu = new JList<String>(actions);
		
		JScrollPane editMenuPanel = new JScrollPane(this.actionsToEditMenu);
		editMenuPanel.setBorder(BorderFactory.createTitledBorder("SET MY MENU"));
		editMenuPanel.setBackground(Color.white);
		editMenuPanel.setBounds((int)(2200 * coeffWidth),(int)(400 * coeffHeight),(int)(500 * coeffWidth),(int)(300 * coeffHeight));
		add(editMenuPanel);
		
		//Buttons
		this.okActionSetMenu = new JButton("OK");
		this.okActionSetMenu.setBounds((int)(2390 * coeffWidth),(int)(780 * coeffHeight),(int)(120 * coeffWidth),(int)(50 * coeffHeight));
		add(this.okActionSetMenu);
		
		this.logout = new JButton("Logout");
		this.logout.setBounds((int)(2350 * coeffWidth),(int)(1400 * coeffHeight),(int)(200 * coeffWidth),(int)(100 * coeffHeight));
		add(this.logout);
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
