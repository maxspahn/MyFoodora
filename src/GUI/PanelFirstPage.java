package GUI;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class PanelFirstPage extends JPanel{
	private JButton login;
	private JButton register;
	private ImageIcon iconWelcome;
	
	public PanelFirstPage(){
		setBackground(Color.white);
		setLayout(null);
		
		//Welcome message
		this.iconWelcome = new ImageIcon("WelcomeToMyFoodora.png");
		JLabel labelWelcome = new JLabel("",iconWelcome,JLabel.CENTER);
		labelWelcome.setBounds(700, 400, 1300, 200);
		add(labelWelcome);
		setVisible(true);
		
		//Buttons
		this.login = new JButton("Login");
		this.login.setBounds(1200, 900, 300, 150);
		this.register = new JButton("Register");
		this.register.setBounds(1200, 1200, 300, 150);
		
		add(this.login);
		add(this.register);
	}

	/**
	 * @return the login
	 */
	public JButton getLogin() {
		return login;
	}

	/**
	 * @return the register
	 */
	public JButton getRegister() {
		return register;
	}

}
