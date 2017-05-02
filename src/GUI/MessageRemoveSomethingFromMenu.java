package GUI;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import user_management.Restaurant;

/**The MessageRemoveSomethingFromMenu class display a question message, with a given restaurant in which the menu has to be set, and a string representing the action to do. It extends JFrame.
 * @author jeremyaugot
 * @author maxspahn
 *
 */
public class MessageRemoveSomethingFromMenu extends JFrame{
	private String choice;
	
	/**
	 * @param rest Restaurant 
	 * @param s String containing the action to perform: "singleitem" to display the single item list, and "meal" to display the meal list.
	 */
	public MessageRemoveSomethingFromMenu(Restaurant rest,String s){
		String[] displayedList;
		if(s.equalsIgnoreCase("singleitem")){ //If the item to be removed is a single item
			int size = rest.getMenu().getSingleItems().size();
			displayedList = new String[size];
			for (int i = 0; i < size; i++) {
				displayedList[i] = rest.getMenu().getSingleItems().get(i).getName();
			}
		}
		else{  //If the item to be removed is a meal
			int size = rest.getMenu().getMeals().size();
			displayedList = new String[size];
			for (int i = 0; i < size; i++) {
				displayedList[i] = rest.getMenu().getMeals().get(i).getName();
			}
		}
		this.choice = (String) JOptionPane.showInputDialog(this,"Remove Something from the order","Edit menu",JOptionPane.QUESTION_MESSAGE,null,displayedList,displayedList[0]);
	}
	/**
	 * @return the choice
	 */
	public String getChoice() {
		return choice;
	}
	
}

