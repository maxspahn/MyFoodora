package GUI;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Error extends JFrame{
	public Error(String title, String message){
		JOptionPane.showMessageDialog(this,message,title,JOptionPane.ERROR_MESSAGE);
	}
}
