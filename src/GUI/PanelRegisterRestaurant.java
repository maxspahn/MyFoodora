package GUI;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PanelRegisterRestaurant extends PanelRegister{
	private JTextField textFieldName;
	private JTextField textFieldAdressX;
	private JTextField textFieldAdressY;
	private JTextField textFieldEmail;
	private JTextField textFieldPhone;
	private JButton register;
	
	public PanelRegisterRestaurant(double coeffHeight, double coeffWidth){
		super(coeffHeight,coeffWidth);
		setLayout(null);
		setBackground(Color.white);
		
		//Display the text field to put the first name
		JPanel namePanel = new JPanel();
		namePanel.setBorder(BorderFactory.createTitledBorder("Please enter the name of your restaurant"));
		this.textFieldName = new JTextField("",20);
		this.textFieldName.setBackground(Color.lightGray);
		namePanel.add(textFieldName);
		namePanel.setBounds((int)(1200 * coeffWidth),(int)(800 * coeffHeight),(int)(300 * coeffWidth),(int)(60 * coeffHeight));
		
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
		adressPanel.setBounds((int)(1200 * coeffWidth),(int)(900 * coeffHeight),(int)(300 * coeffWidth),(int)(60 * coeffHeight));
		
		//Display the text field to put the email
		JPanel emailPanel = new JPanel();
		emailPanel.setBorder(BorderFactory.createTitledBorder("Please enter your email"));
		this.textFieldEmail = new JTextField("",40);
		this.textFieldEmail.setBackground(Color.lightGray);
		emailPanel.add(textFieldEmail);
		emailPanel.setBounds((int)(1100 * coeffWidth),(int)(1000 * coeffHeight),(int)(500 * coeffWidth),(int)(60 * coeffHeight));
				
		//Display the text field to put the phone number
		JPanel phonePanel = new JPanel();
		phonePanel.setBorder(BorderFactory.createTitledBorder("Please enter your phone number"));
		this.textFieldPhone = new JTextField("",20);
		this.textFieldPhone.setBackground(Color.lightGray);
		phonePanel.add(textFieldPhone);
		phonePanel.setBounds((int)(1200 * coeffWidth),(int)(1100 * coeffHeight),(int)(300 * coeffWidth),(int)(60 * coeffHeight));
		
		//Button "Register"
		this.register = new JButton("Register");
		this.register.setBounds((int)(1300 * coeffWidth),(int)(1300 * coeffHeight),(int)(100 * coeffWidth),(int)(60 * coeffHeight));
	
		this.getUserNamePanel().setVisible(false);
		this.getPasswordPanel().setVisible(false);
		this.getNext().setVisible(false);
		this.getUserComboBox().setVisible(false);
		add(namePanel);
		add(adressPanel);
		add(emailPanel);
		add(phonePanel);
		add(this.register);
		setVisible(true);
	
	}
	
	

	/**
	 * @return the textFieldName
	 */
	public JTextField getTextFieldName() {
		return textFieldName;
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
	 * @return the register
	 */
	public JButton getRegister() {
		return register;
	}
	
}
