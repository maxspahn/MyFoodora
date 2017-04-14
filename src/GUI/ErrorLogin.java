package GUI;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ErrorLogin extends JFrame {
	public ErrorLogin(){
		JOptionPane.showMessageDialog(this,"Unfortunately your username or your password is wrong.","Login error",JOptionPane.ERROR_MESSAGE);
	}
	
	public static void main(String[] args) {
		ErrorLogin errorLogin = new ErrorLogin();
	}

}
