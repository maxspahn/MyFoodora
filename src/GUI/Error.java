package GUI;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**The Error class display an error message, with a given title and message. It extends JFrame.
 * @author jeremyaugot
 * @author maxspahn
 *
 */
public class Error extends JFrame{
	public Error(String title, String message){
		JOptionPane.showMessageDialog(this,message,title,JOptionPane.ERROR_MESSAGE);
	}
}
