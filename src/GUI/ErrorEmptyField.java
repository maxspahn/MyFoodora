package GUI;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ErrorEmptyField extends JFrame{
	public ErrorEmptyField(String field){
		JOptionPane.showMessageDialog(this,"The field "+field+" must be completed.","Empty field error",JOptionPane.ERROR_MESSAGE);
	}
}
