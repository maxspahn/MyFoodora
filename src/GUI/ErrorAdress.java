package GUI;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ErrorAdress extends JFrame{
	
	public ErrorAdress(){
		JOptionPane.showMessageDialog(this,"The adress coordinates must be integers.","Adress error",JOptionPane.ERROR_MESSAGE);
	}

}
