package GUI;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelCourier extends JPanel{

	private JLabel text;
	private JButton logout;
	private JButton deliverOrder;

	public PanelCourier(double coeffHeight, double coeffWidth){
		setLayout(null);
		
		//Display "MyFoodora"
		ImageIcon myFoodoraIcon = new ImageIcon("MyFoodora.png");
		JLabel myFoodoraLabel = new JLabel("",myFoodoraIcon,JLabel.CENTER);
		myFoodoraLabel.setBounds((int)(2200 * coeffWidth),(int)(100 * coeffHeight),(int)(500 * coeffWidth),(int)(200 * coeffHeight));
		add(myFoodoraLabel);
				
		//Creation of the area where the text is put
		JPanel textPanel = new JPanel();
		textPanel.setBorder(BorderFactory.createTitledBorder("Courier panel"));
		this.text = new JLabel();
		textPanel.add(this.text);
		textPanel.setBackground(Color.white);
		textPanel.setBounds((int)(15 * coeffWidth),(int)(15 * coeffHeight),(int)(2100 * coeffWidth),(int)(1620 * coeffHeight));
		add(textPanel);
		
		//Buttons
		this.logout = new JButton("Logout");
		this.logout.setBounds((int)(2350 * coeffWidth),(int)(1400 * coeffHeight),(int)(200 * coeffWidth),(int)(100 * coeffHeight));
		add(this.logout);
		
		this.deliverOrder = new JButton("Deliver an order");
		this.deliverOrder.setBounds((int)(2350 * coeffWidth),(int)(800 * coeffHeight),(int)(200 * coeffWidth),(int)(100 * coeffHeight));
		add(this.deliverOrder);

	}
	
	/**
	 * @return the text
	 */
	public JLabel getText() {
		return text;
	}
	/**
	 * @return the logout
	 */
	public JButton getLogout() {
		return logout;
	}
	/**
	 * @return the deliverOrder
	 */
	public JButton getDeliverOrder() {
		return deliverOrder;
	}
	
}
