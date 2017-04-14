package GUI;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ErrorUserName extends JFrame{
	public ErrorUserName(){
		JOptionPane.showMessageDialog(this,"Spaces in the username are not allowed.","Username error",JOptionPane.ERROR_MESSAGE);
	}

}
