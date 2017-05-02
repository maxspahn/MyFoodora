package GUI;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**The MessageShowText class display an information message, with a given message and title to display. It extends JFrame.
 * @author jeremyaugot
 * @author maxspahn
 *
 */
public class MessageShowText extends JFrame{
	public MessageShowText(String message, String title){
		JOptionPane.showMessageDialog(this,message,title,JOptionPane.INFORMATION_MESSAGE);
	}

}
