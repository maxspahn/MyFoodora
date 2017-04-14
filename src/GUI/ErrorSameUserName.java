package GUI;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ErrorSameUserName extends JFrame {
	
	public ErrorSameUserName(){
		JOptionPane.showMessageDialog(this,"This username already exists.","Same username error",JOptionPane.ERROR_MESSAGE);
	}
	
	public static void main(String[] args) {
		ErrorSameUserName errorSameUserName = new ErrorSameUserName();
	}

}
