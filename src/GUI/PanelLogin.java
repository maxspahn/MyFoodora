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
	
	public PanelLogin(double coeffHeight, double coeffWidth){
	setLayout(null);
	setBackground(Color.white);
	
	//Login message
	this.iconLogin = new ImageIcon("Login.png");
	JLabel labelLogin = new JLabel("",iconLogin,JLabel.CENTER);
	labelLogin.setBounds((int)(550 * coeffWidth),(int)(400 * coeffHeight),(int)(1600 * coeffWidth),(int)(200 * coeffHeight));
			
	//Text field to put the username
	JPanel userNamePanel = new JPanel();
	userNamePanel.setBorder(BorderFactory.createTitledBorder("Please enter your username"));;
	this.textFieldUserName = new JTextField("",20);
	this.textFieldUserName.setBackground(Color.lightGray);
	userNamePanel.add(this.textFieldUserName);
	userNamePanel.setBounds((int)(1150 * coeffWidth),(int)(900 * coeffHeight),(int)(400 * coeffWidth),(int)(70 * coeffHeight));
					
	//Text field to put the password
	JPanel passwordPanel = new JPanel();
	passwordPanel.setBorder(BorderFactory.createTitledBorder("Please enter your password"));
	this.textFieldPassword = new JTextField("",20);
	this.textFieldPassword.setBackground(Color.lightGray);
	passwordPanel.add(textFieldPassword);
	passwordPanel.setBounds((int)(1150 * coeffWidth),(int)(1020 * coeffHeight),(int)(400 * coeffWidth),(int)(70 * coeffHeight));
			
	//Button "ok"
	this.okLogin = new JButton("OK");
	this.okLogin.setBounds((int)(1290 * coeffWidth),(int)(1150 * coeffHeight),(int)(120 * coeffWidth),(int)(60 * coeffHeight));
		
	//Button "Back"
	this.back = new JButton("Back");
	this.back.setBounds((int)(10 * coeffWidth),(int)(10 * coeffHeight),(int)(120 * coeffWidth),(int)(40 * coeffHeight));
	

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
