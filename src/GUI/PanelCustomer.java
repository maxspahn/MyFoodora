package GUI;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelCustomer extends JPanel{
	
	private JLabel text;
	private JButton order;
	private JButton logout;
	private ImageIcon myFoodoraIcon;

	public PanelCustomer(){
		setLayout(null);

		//Display a registration message
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
		this.order = new JButton("Order");
		this.order.setBounds(2300, 600, 300, 150);
		add(this.order);
		
		this.logout = new JButton("Logout");
		this.logout.setBounds(2300, 1100, 300, 150);
		add(this.logout);
		
	}
	
	public static void main(String[] args) {
		PanelCustomer panel = new PanelCustomer();
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
	public JButton getOrder() {
		return order;
	}

	/**
	 * @return the logout
	 */
	public JButton getLogout() {
		return logout;
	}
	

}
