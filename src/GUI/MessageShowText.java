package GUI;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class MessageShowText extends JFrame{
	public MessageShowText(String message, String title){
		JOptionPane.showMessageDialog(this,message,title,JOptionPane.INFORMATION_MESSAGE);
	}

}
