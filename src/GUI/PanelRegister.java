package GUI;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class PanelRegister extends JPanel{
	private ImageIcon iconRegistration;
	private JComboBox<String> userComboBox;
	private JButton next;
	private JTextField textFieldUserName;
	private JTextField textFieldPassword;
	private JButton back;
	private JPanel userNamePanel;
	private JPanel passwordPanel;
	
	public PanelRegister(double coeffHeight, double coeffWidth){
		setLayout(null);
		setBackground(Color.white);
		
		//Display a registration message
		this.iconRegistration = new ImageIcon("Registration.png");
		JLabel labelRegistration = new JLabel("",iconRegistration,JLabel.CENTER);
		labelRegistration.setBounds((int)(700 * coeffWidth),(int)(400 * coeffHeight),(int)(1300 * coeffWidth),(int)(200 * coeffHeight));
		add(labelRegistration);
		
		//Choice of the kind of users
		String[] users = {"Customer","Courier","Manager","Restaurant"};
		this.userComboBox = new JComboBox<String>(users);
		this.userComboBox.setBounds((int)(1150 * coeffWidth),(int)(880 * coeffHeight),(int)(400 * coeffWidth),(int)(50 * coeffHeight));
		
		//Display the text field to put the username
		this.userNamePanel = new JPanel();
		this.userNamePanel.setBorder(BorderFactory.createTitledBorder("Please define your username"));
		this.textFieldUserName = new JTextField("",20);
		this.textFieldUserName.setBackground(Color.lightGray);
		this.userNamePanel.add(textFieldUserName);
		this.userNamePanel.setBounds((int)(1150 * coeffWidth),(int)(1000 * coeffHeight),(int)(400 * coeffWidth),(int)(70 * coeffHeight));
		
		//Display the text field to put the password
		this.passwordPanel = new JPanel();
		passwordPanel.setBorder(BorderFactory.createTitledBorder("Please define your password"));
		this.textFieldPassword = new JTextField("",20);
		this.textFieldPassword.setBackground(Color.lightGray);
		this.passwordPanel.add(textFieldPassword);
		this.passwordPanel.setBounds((int)(1150 * coeffWidth),(int)(1120 * coeffHeight),(int)(400 * coeffWidth),(int)(70 * coeffHeight));
		
		//Button "Back"
		this.back = new JButton("Back");
		this.back.setBounds((int)(10 * coeffWidth),(int)(10 * coeffHeight),(int)(120 * coeffWidth),(int)(40 * coeffHeight));
				
		//Button "Next"
		this.next = new JButton("Next");
		this.next.setBounds((int)(1290 * coeffWidth),(int)(1250 * coeffHeight),(int)(120 * coeffWidth),(int)(60 * coeffHeight));
		
		add(this.userComboBox);
		add(userNamePanel);
		add(userNamePanel);
		add(passwordPanel);
		add(this.back);
		add(this.next);
		setVisible(true);
	}

	/**
	 * @return the iconRegistration
	 */
	public ImageIcon getIconRegistration() {
		return iconRegistration;
	}

	/**
	 * @return the userComboBox
	 */
	public JComboBox<String> getUserComboBox() {
		return userComboBox;
	}

	/**
	 * @return the next
	 */
	public JButton getNext() {
		return next;
	}

	/**
	 * @return the textFieldUserName
	 */
	public JTextField getTextFieldUserName() {
		return textFieldUserName;
	}

	/**
	 * @return the textFieldPassword
	 */
	public JTextField getTextFieldPassword() {
		return textFieldPassword;
	}

	/**
	 * @return the back
	 */
	public JButton getBack() {
		return back;
	}

	/**
	 * @return the userNamePanel
	 */
	public JPanel getUserNamePanel() {
		return userNamePanel;
	}

	/**
	 * @return the passwordPanel
	 */
	public JPanel getPasswordPanel() {
		return passwordPanel;
	}
	
}
