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

	public PanelCourier(){
		setLayout(null);
		
		//Display "MyFoodora"
		ImageIcon myFoodoraIcon = new ImageIcon("MyFoodora.png");
		JLabel myFoodoraLabel = new JLabel("",myFoodoraIcon,JLabel.CENTER);
		myFoodoraLabel.setBounds(2200, 100, 500, 200);
		add(myFoodoraLabel);
				
		//Creation of the area where the text is put
		JPanel textPanel = new JPanel();
		textPanel.setBorder(BorderFactory.createTitledBorder("Courier panel"));
		this.text = new JLabel();
		textPanel.add(this.text);
		textPanel.setBackground(Color.white);
		textPanel.setBounds(15, 15, 2100, 1620);
		add(textPanel);
		
		//Buttons
		this.logout = new JButton("Logout");
		this.logout.setBounds(2350, 1400, 200, 100);
		add(this.logout);
		
		this.deliverOrder = new JButton("Deliver an order");
		this.deliverOrder.setBounds(2350, 800, 200, 100);
		add(this.deliverOrder);

	}
	public static void main(String[] args) {
		PanelCourier panel = new PanelCourier();
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
