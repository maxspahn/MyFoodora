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

/**The class PanelManager creates the panel visible by the managers. It extends JPanel.
 * @author jeremyaugot
 * @author maxspahn
 *
 */
public class PanelManager extends JPanel{
	
	private JLabel text;
	private JButton logout;
	private JButton choosePolicy;
	private JButton chooseUser;
	private JList<String> actionsPolicyList;
	private JList<String> actionsUsersList;

	public PanelManager(double coeffHeight, double coeffWidth){
		setLayout(null);
		
		//Display "MyFoodora"
		ImageIcon myFoodoraIcon = new ImageIcon("MyFoodora.png");
		JLabel myFoodoraLabel = new JLabel("",myFoodoraIcon,JLabel.CENTER);
		myFoodoraLabel.setBounds((int)(2100 * coeffWidth),(int)(100 * coeffHeight),(int)(700 * coeffWidth),(int)(300 * coeffHeight));
		add(myFoodoraLabel);
				
		//Creation of the area where the text is put
		JPanel textPanel = new JPanel();
		textPanel.setBorder(BorderFactory.createTitledBorder("Manager panel"));
		this.text = new JLabel();
		textPanel.add(this.text);
		textPanel.setBackground(Color.white);
		textPanel.setBounds((int)(15 * coeffWidth),(int)(15 * coeffHeight),(int)(2100 * coeffWidth),(int)(1620 * coeffHeight));
		add(textPanel);
		
		//Buttons
		this.logout = new JButton("Logout");
		this.logout.setBounds((int)(2350 * coeffWidth),(int)(1400 * coeffHeight),(int)(200 * coeffWidth),(int)(100 * coeffHeight));
		add(this.logout);
		
		this.choosePolicy = new JButton("Choose");
		this.choosePolicy.setBounds((int)(2360 * coeffWidth),(int)(720 * coeffHeight),(int)(180 * coeffWidth),(int)(50 * coeffHeight));
		add(this.choosePolicy);
		
		this.chooseUser = new JButton("Choose");
		this.chooseUser.setBounds((int)(2360 * coeffWidth),(int)(1220 * coeffHeight),(int)(180 * coeffWidth),(int)(50 * coeffHeight));
		add(this.chooseUser);
		
		//Target policy
		String[] actionsPolicy = {"Get current target policy","Set new target policy","Set service-fee-% according to policy","Set markup-% according to policy","Set delivery-cost according to policy"};
		this.actionsPolicyList = new JList<String>(actionsPolicy);
		JScrollPane targetPolicyPanel = new JScrollPane(actionsPolicyList);
		targetPolicyPanel.setBorder(BorderFactory.createTitledBorder("TARGET POLICY"));
		targetPolicyPanel.setBounds((int)(2200 * coeffWidth),(int)(400 * coeffHeight),(int)(500 * coeffWidth),(int)(300 * coeffHeight));
		add(targetPolicyPanel);		
		
		//Users
		String[] actionsUsers = {"Add a user to the system","Remove a user from the system","Activate a user","Deactivate a user"};
		this.actionsUsersList = new JList<String>(actionsUsers);
		JScrollPane UsersPanel = new JScrollPane(actionsUsersList);
		UsersPanel.setBorder(BorderFactory.createTitledBorder("USERS"));
		UsersPanel.setBounds((int)(2200 * coeffWidth),(int)(900 * coeffHeight),(int)(500 * coeffWidth),(int)(300 * coeffHeight));
		add(UsersPanel);
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
