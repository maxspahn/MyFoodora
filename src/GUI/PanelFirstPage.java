package GUI;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**The class PanelFirstPage creates the first panel visible by everyone. It allows to choose between "Login" and "Register". It extends JPanel.
 * @author jeremyaugot
 * @author maxspahn
 *
 */
public class PanelFirstPage extends JPanel{
	private JButton login;
	private JButton register;
	private ImageIcon iconWelcome;
	
	public PanelFirstPage(double coeffHeight, double coeffWidth){
		setBackground(Color.white);
		setLayout(null);
		
		//Welcome message
		this.iconWelcome = new ImageIcon("./ima/WelcomeToMyFoodora.png");
		JLabel labelWelcome = new JLabel("",iconWelcome,JLabel.CENTER);
		labelWelcome.setBounds((int)(550*coeffWidth), (int)(400*coeffHeight), (int)(1600*coeffWidth), (int)(200*coeffHeight));
		add(labelWelcome);
		setVisible(true);
		
		//Buttons
		this.login = new JButton("Login");
		this.login.setBounds((int)(1200*coeffWidth), (int)(900*coeffHeight), (int)(300*coeffWidth), (int)(150*coeffHeight));
		this.register = new JButton("Register");
		this.register.setBounds((int)(1200*coeffWidth), (int)(1200*coeffHeight), (int)(300*coeffWidth), (int)(150*coeffHeight));
		
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
