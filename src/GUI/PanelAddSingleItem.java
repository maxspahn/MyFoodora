package GUI;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PanelAddSingleItem extends JPanel{
	private JComboBox<String> typeComboBox;
	private JTextField textFieldName;
	private JTextField textFieldPrice;
	private JCheckBox checkBoxVegetarian;
	private JCheckBox checkBoxGluten;
	private JButton back;
	private JButton OkSingleItem;

	public PanelAddSingleItem(double coeffHeight, double coeffWidth){
		setLayout(null);
		
		//Display a item creation message
		ImageIcon iconRegistration = new ImageIcon("SingleItemCreation.png");
		JLabel label = new JLabel("",iconRegistration,JLabel.CENTER);
		label.setBounds((int)(550 * coeffWidth),(int)(400 * coeffHeight),(int)(1600 * coeffWidth),(int)(200 * coeffHeight));
		add(label);
		
		//Choice of the kind of single item
		String[] types = {"Starter","Main Dish","Dessert"};
		this.typeComboBox = new JComboBox<String>(types);
		this.typeComboBox.setBounds((int)(1150 * coeffWidth),(int)(900 * coeffHeight),(int)(400 * coeffWidth),(int)(50 * coeffHeight));
		this.typeComboBox.setBackground(Color.white);
		
		//Display the text field to put the name
		JPanel namePanel = new JPanel();
		namePanel.setBorder(BorderFactory.createTitledBorder("Please define the name"));
		this.textFieldName = new JTextField("",20);
		this.textFieldName.setBackground(Color.lightGray);
		namePanel.add(textFieldName);
		namePanel.setBounds((int)(1150 * coeffWidth),(int)(1000 * coeffHeight),(int)(400 * coeffWidth),(int)(80 * coeffHeight));
		namePanel.setBackground(Color.white);
		
		//Display the text field to put the price
		JPanel pricePanel = new JPanel();
		pricePanel.setBorder(BorderFactory.createTitledBorder("Please define the price"));
		this.textFieldPrice = new JTextField("",20);
		this.textFieldPrice.setBackground(Color.lightGray);
		pricePanel.add(textFieldPrice);
		pricePanel.setBounds((int)(1150 * coeffWidth),(int)(1100 * coeffHeight),(int)(400 * coeffWidth),(int)(80 * coeffHeight));
		pricePanel.setBackground(Color.white);
		
		//Display the choice of the vegetarian
		this.checkBoxVegetarian = new JCheckBox("This single item is vegetarian");
		this.checkBoxVegetarian.setBounds((int)(1150 * coeffWidth),(int)(1250 * coeffHeight),(int)(400 * coeffWidth),(int)(40 * coeffHeight));
		
		//Display the choice of the vegetarian
		this.checkBoxGluten = new JCheckBox("This single item is gluten-free");
		this.checkBoxGluten.setBounds((int)(1150 * coeffWidth),(int)(1300 * coeffHeight),(int)(400 * coeffWidth),(int)(40 * coeffHeight));
		
		//Button "Back"
		this.back = new JButton("Back");
		this.back.setBounds((int)(10 * coeffWidth),(int)(10 * coeffHeight),(int)(120 * coeffWidth),(int)(50 * coeffHeight));
		
		//Button "Ok"
		this.OkSingleItem = new JButton("OK");
		this.OkSingleItem.setBounds((int)(1290 * coeffWidth),(int)(1400 * coeffHeight),(int)(120 * coeffWidth),(int)(60 * coeffHeight));
		
		
		add(typeComboBox);
		add(namePanel);
		add(pricePanel);
		add(checkBoxVegetarian);
		add(checkBoxGluten);
		add(this.back);
		add(this.OkSingleItem);
		
	}
	

	/**
	 * @return the typeComboBox
	 */
	public JComboBox<String> getTypeComboBox() {
		return typeComboBox;
	}

	/**
	 * @return the textFieldName
	 */
	public JTextField getTextFieldName() {
		return textFieldName;
	}

	/**
	 * @return the textFieldPrice
	 */
	public JTextField getTextFieldPrice() {
		return textFieldPrice;
	}

	/**
	 * @return the checkBoxVegetarian
	 */
	public JCheckBox getCheckBoxVegetarian() {
		return checkBoxVegetarian;
	}

	/**
	 * @return the checkBoxGluten
	 */
	public JCheckBox getCheckBoxGluten() {
		return checkBoxGluten;
	}

	/**
	 * @return the back
	 */
	public JButton getBack() {
		return back;
	}

	/**
	 * @return the okSingleItem
	 */
	public JButton getOkSingleItem() {
		return OkSingleItem;
	}
	
}
