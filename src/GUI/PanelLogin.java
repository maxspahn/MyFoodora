package GUI;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class PanelLogin extends JPanel{
	private ImageIcon iconLogin;
	private JTextField textFieldUserName;
	private JTextField textFieldPassword;
	private JButton okLogin;
	private JButton back;
	
	public PanelLogin(){
	setLayout(null);
	setBackground(Color.white);
	
	//Login message
	this.iconLogin = new ImageIcon("Login.png");
	JLabel labelLogin = new JLabel("",iconLogin,JLabel.CENTER);
	labelLogin.setBounds(700, 400, 1300, 200);
			
	//Text field to put the username
	JPanel userNamePanel = new JPanel();
	userNamePanel.setBorder(BorderFactory.createTitledBorder("Please enter your username"));;
	this.textFieldUserName = new JTextField("",20);
	this.textFieldUserName.setBackground(Color.lightGray);
	userNamePanel.add(this.textFieldUserName);
	userNamePanel.setBounds(1200,900, 300, 60);
					
	//Text field to put the password
	JPanel passwordPanel = new JPanel();
	passwordPanel.setBorder(BorderFactory.createTitledBorder("Please enter your password"));
	this.textFieldPassword = new JTextField("",20);
	this.textFieldPassword.setBackground(Color.lightGray);
	passwordPanel.add(textFieldPassword);
	passwordPanel.setBounds(1200,1000, 300, 60);
			
	//Button "ok"
	this.okLogin = new JButton("OK");
	this.okLogin.setBounds(1300,1100, 100, 60);
		
	//Button "Back"
	this.back = new JButton("Back");
	this.back.setBounds(10,10, 100, 40);
	

	add(labelLogin);
	add(userNamePanel);
	add(passwordPanel);
	add(okLogin);
	add(this.back);
	setVisible(true);
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
	 * @return the okLogin
	 */
	public JButton getOkLogin() {
		return okLogin;
	}

	/**
	 * @return the back
	 */
	public JButton getBack() {
		return back;
	}
	
	
}
