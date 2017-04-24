package GUI;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import user_management.Restaurant;

public class MessageRemoveSomethingFromMenu extends JFrame{
	private String choice;
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

