package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**The class PanelRegisterManager creates the panel used to register as a new manager, it is visible by those who want to be manager. It extends JPanel.
 * @author jeremyaugot
 * @author maxspahn
 *
 */
public class PanelRegisterManager extends PanelRegister{
	private JTextField textFieldFirstName;
	private JTextField textFieldLastName;
	private JTextField textFieldAdressX;
	private JTextField textFieldAdressY;
	private JTextField textFieldEmail;
	private JTextField textFieldPhone;
	private JTextField textFieldRole;
	private JButton register;

	public PanelRegisterManager(double coeffHeight, double coeffWidth){
		super(coeffHeight,coeffWidth);
		setLayout(null);
		setBackground(Color.white);
		
		//Display the text field to put the first name
		JPanel firstNamePanel = new JPanel();
		firstNamePanel.setBorder(BorderFactory.createTitledBorder("Please enter your first name"));
		this.textFieldFirstName = new JTextField("",20);
		this.textFieldFirstName.setBackground(Color.lightGray);
		firstNamePanel.add(textFieldFirstName);
		firstNamePanel.setBounds((int)(1150 * coeffWidth),(int)(700 * coeffHeight),(int)(400 * coeffWidth),(int)(80 * coeffHeight));
				
		//Display the text field to put the last name
		JPanel lastNamePanel = new JPanel();
		lastNamePanel.setBorder(BorderFactory.createTitledBorder("Please enter your last name"));
		this.textFieldLastName = new JTextField("",20);
		this.textFieldLastName.setBackground(Color.lightGray);
		lastNamePanel.add(textFieldLastName);
		lastNamePanel.setBounds((int)(1150 * coeffWidth),(int)(800 * coeffHeight),(int)(400 * coeffWidth),(int)(80 * coeffHeight));
		
		//Display the text field to put the adress
		JPanel adressPanel = new JPanel();
		adressPanel.setBorder(BorderFactory.createTitledBorder("Please enter your adress"));
		JLabel xLabel = new JLabel("X coordinate:");
		this.textFieldAdressX = new JTextField("",4);
		this.textFieldAdressX.setBackground(Color.lightGray);
		adressPanel.add(xLabel);
		adressPanel.add(textFieldAdressX);
		JLabel yLabel = new JLabel("Y coordinate:");
		this.textFieldAdressY = new JTextField("",4);
		this.textFieldAdressY.setBackground(Color.lightGray);
		adressPanel.add(yLabel);
		adressPanel.add(textFieldAdressY);
		adressPanel.setBounds((int)(1100 * coeffWidth),(int)(900 * coeffHeight),(int)(500 * coeffWidth),(int)(80 * coeffHeight));
		
		//Display the text field to put the email
		JPanel emailPanel = new JPanel();
		emailPanel.setBorder(BorderFactory.createTitledBorder("Please enter your email"));
		this.textFieldEmail = new JTextField("",40);
		this.textFieldEmail.setBackground(Color.lightGray);
		emailPanel.add(textFieldEmail);
		emailPanel.setBounds((int)(1050 * coeffWidth),(int)(1000 * coeffHeight),(int)(600 * coeffWidth),(int)(80 * coeffHeight));
				
		//Display the text field to put the phone number
		JPanel phonePanel = new JPanel();
		phonePanel.setBorder(BorderFactory.createTitledBorder("Please enter your phone number"));
		this.textFieldPhone = new JTextField("",20);
		this.textFieldPhone.setBackground(Color.lightGray);
		phonePanel.add(textFieldPhone);
		phonePanel.setBounds((int)(1150 * coeffWidth),(int)(1100 * coeffHeight),(int)(400 * coeffWidth),(int)(80 * coeffHeight));
		
		//Display the text field to put the role
		JPanel rolePanel = new JPanel();
		rolePanel.setBorder(BorderFactory.createTitledBorder("Please enter your role"));
		this.textFieldRole = new JTextField("",20);
		this.textFieldRole.setBackground(Color.lightGray);
		rolePanel.add(textFieldRole);
		rolePanel.setBounds((int)(1150 * coeffWidth),(int)(1200 * coeffHeight),(int)(400 * coeffWidth),(int)(80 * coeffHeight));
		
		//Button "Register"
		this.register = new JButton("Register");
		this.register.setBounds((int)(1290 * coeffWidth),(int)(1400 * coeffHeight),(int)(120 * coeffWidth),(int)(60 * coeffHeight));
	
		this.getUserNamePanel().setVisible(false);
		this.getPasswordPanel().setVisible(false);
		this.getNext().setVisible(false);
		this.getUserComboBox().setVisible(false);
		add(firstNamePanel);
		add(lastNamePanel);
		add(adressPanel);
		add(emailPanel);
		add(phonePanel);
		add(rolePanel);
		add(this.register);
		setVisible(true);
	}
	
	/**
	 * @return the textFieldFirstName
	 */
	public JTextField getTextFieldFirstName() {
		return textFieldFirstName;
	}

	/**
	 * @return the textFieldLastName
	 */
	public JTextField getTextFieldLastName() {
		return textFieldLastName;
	}

	/**
	 * @return the textFieldAdressX
	 */
	public JTextField getTextFieldAdressX() {
		return textFieldAdressX;
	}

	/**
	 * @return the textFieldAdressY
	 */
	public JTextField getTextFieldAdressY() {
		return textFieldAdressY;
	}

	/**
	 * @return the textFieldEmail
	 */
	public JTextField getTextFieldEmail() {
		return textFieldEmail;
	}

	/**
	 * @return the textFieldPhone
	 */
	public JTextField getTextFieldPhone() {
		return textFieldPhone;
	}

	/**
	 * @return the textFieldRole
	 */
	public JTextField getTextFieldRole() {
		return textFieldRole;
	}

	/**
	 * @return the register
	 */
	public JButton getRegister() {
		return register;
	}
}
