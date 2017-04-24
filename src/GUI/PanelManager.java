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

public class PanelManager extends JPanel{
	
	private JLabel text;
	private JButton logout;
	private JButton choosePolicy;
	private JButton chooseUser;
	private JList<String> actionsPolicyList;
	private JList<String> actionsUsersList;

	public PanelManager(){
		setLayout(null);
		
		//Display "MyFoodora"
		ImageIcon myFoodoraIcon = new ImageIcon("MyFoodora.png");
		JLabel myFoodoraLabel = new JLabel("",myFoodoraIcon,JLabel.CENTER);
		myFoodoraLabel.setBounds(2200, 100, 500, 200);
		add(myFoodoraLabel);
				
		//Creation of the area where the text is put
		JPanel textPanel = new JPanel();
		textPanel.setBorder(BorderFactory.createTitledBorder("Manager panel"));
		this.text = new JLabel();
		textPanel.add(this.text);
		textPanel.setBackground(Color.white);
		textPanel.setBounds(15, 15, 2100, 1620);
		add(textPanel);
		
		//Buttons
		this.logout = new JButton("Logout");
		this.logout.setBounds(2350, 1400, 200, 100);
		add(this.logout);
		
		this.choosePolicy = new JButton("Choose");
		this.choosePolicy.setBounds(2400, 720, 100, 50);
		add(this.choosePolicy);
		
		this.chooseUser = new JButton("Choose");
		this.chooseUser.setBounds(2400, 1220, 100, 50);
		add(this.chooseUser);
		
		//Target policy
		String[] actionsPolicy = {"Get current target policy","Set new target policy","Set service-fee-% according to policy","Set markup-% according to policy","Set delivery-cost according to policy"};
		this.actionsPolicyList = new JList<String>(actionsPolicy);
		JScrollPane targetPolicyPanel = new JScrollPane(actionsPolicyList);
		targetPolicyPanel.setBorder(BorderFactory.createTitledBorder("TARGET POLICY"));
		targetPolicyPanel.setBounds(2300, 400, 300, 300);
		add(targetPolicyPanel);		
		
		//Users
		String[] actionsUsers = {"Add a user to the system","Remove a user from the system","Activate a user","Deactivate a user"};
		this.actionsUsersList = new JList<String>(actionsUsers);
		JScrollPane UsersPanel = new JScrollPane(actionsUsersList);
		UsersPanel.setBorder(BorderFactory.createTitledBorder("USERS"));
		UsersPanel.setBounds(2300, 900, 300, 300);
		add(UsersPanel);
	}

	public static void main(String[] args) {
		PanelManager panel = new PanelManager();
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
	 * @return the choosePolicy
	 */
	public JButton getChoosePolicy() {
		return choosePolicy;
	}

	/**
	 * @return the chooseUser
	 */
	public JButton getChooseUser() {
		return chooseUser;
	}

	/**
	 * @return the actionsPolicyList
	 */
	public JList<String> getActionsPolicyList() {
		return actionsPolicyList;
	}

	/**
	 * @return the actionsUsersList
	 */
	public JList<String> getActionsUsersList() {
		return actionsUsersList;
	}
}
