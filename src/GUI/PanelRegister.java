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
	
	public PanelRegister(){
		setLayout(null);
		setBackground(Color.white);
		
		//Display a registration message
		this.iconRegistration = new ImageIcon("Registration.png");
		JLabel labelRegistration = new JLabel("",iconRegistration,JLabel.CENTER);
		labelRegistration.setBounds(700, 400, 1300, 200);
		add(labelRegistration);
		
		//Choice of the kind of users
		String[] users = {"Customer","Courier","Manager","Restaurant"};
		this.userComboBox = new JComboBox<String>(users);
		this.userComboBox.setBounds(1200, 900, 300, 40);
		
		//Display the text field to put the username
		this.userNamePanel = new JPanel();
		this.userNamePanel.setBorder(BorderFactory.createTitledBorder("Please define your username"));
		this.textFieldUserName = new JTextField("",20);
		this.textFieldUserName.setBackground(Color.lightGray);
		this.userNamePanel.add(textFieldUserName);
		this.userNamePanel.setBounds(1200,1000, 300, 60);
		
		//Display the text field to put the password
		this.passwordPanel = new JPanel();
		passwordPanel.setBorder(BorderFactory.createTitledBorder("Please define your password"));
		this.textFieldPassword = new JTextField("",20);
		this.textFieldPassword.setBackground(Color.lightGray);
		this.passwordPanel.add(textFieldPassword);
		this.passwordPanel.setBounds(1200,1100, 300, 60);
		
		//Button "Back"
		this.back = new JButton("Back");
		this.back.setBounds(10,10, 100, 40);
				
		//Button "Next"
		this.next = new JButton("Next");
		this.next.setBounds(1300,1200, 100, 60);
		
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
