package GUI;

import java.awt.Color;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**The class PanelChooseDate creates the panel used to choose the dates for computing the earnings,  it is only visible by a restaurant. It extends JPanel.
 * @author jeremyaugot
 * @author maxspahn
 *
 */
public class PanelChooseDate extends JPanel{
	
	private JComboBox<String> day1ComboBox;
	private JComboBox<String> month1ComboBox;
	private JComboBox<String> year1ComboBox;
	private JComboBox<String> day2ComboBox;
	private JComboBox<String> month2ComboBox;
	private JComboBox<String> year2ComboBox;
	private JButton OkDate;
	private JButton back;
	private String action;

	public PanelChooseDate(String action,double coeffHeight, double coeffWidth){
		this.action = action;
		setLayout(null);
		
		//Fees message
		ImageIcon icon = new ImageIcon("Fees.png");
		JLabel label = new JLabel("",icon,JLabel.CENTER);
		label.setBounds((int)(550 * coeffWidth),(int)(400 * coeffHeight),(int)(1600 * coeffWidth),(int)(200 * coeffHeight));
		add(label);
		
		//Panel day1
		JPanel day1Panel = new JPanel();
		day1Panel.setBorder(BorderFactory.createTitledBorder("Day 1"));
		day1Panel.setBounds((int)(1070 * coeffWidth),(int)(900 * coeffHeight),(int)(160 * coeffWidth),(int)(80 * coeffHeight));
		day1Panel.setBackground(Color.white);	
		
		//Choice of the day of first date
		String[] day1 = new String[30];
		for (int i = 0; i < day1.length; i++) {
			int val = i+1;
			day1[i] = ""+val;
		}
		this.day1ComboBox = new JComboBox<String>(day1);
		day1Panel.add(this.day1ComboBox);
		
		//Panel month1
		JPanel month1Panel = new JPanel();
		month1Panel.setBorder(BorderFactory.createTitledBorder("Month 1"));
		month1Panel.setBounds((int)(1070 * coeffWidth),(int)(1000 * coeffHeight),(int)(160 * coeffWidth),(int)(80 * coeffHeight));
		month1Panel.setBackground(Color.white);	
		
		//Choice of the month of first date
		String[] month1 = new String[12];
		for (int i = 0; i < month1.length; i++) {
			int val = i+1;
			month1[i] = ""+val;
		}	
		this.month1ComboBox = new JComboBox<String>(month1);
		month1Panel.add(this.month1ComboBox);
		
		//Panel year1
		JPanel year1Panel = new JPanel();
		year1Panel.setBorder(BorderFactory.createTitledBorder("Year 1"));
		year1Panel.setBounds((int)(1070 * coeffWidth),(int)(1100 * coeffHeight),(int)(160 * coeffWidth),(int)(80 * coeffHeight));
		year1Panel.setBackground(Color.white);	
		
		//Choice of the year of first date
		String[] year1 = {""+2014,""+2015,""+2016,""+2017};
		this.year1ComboBox = new JComboBox<String>(year1);
		year1Panel.add(this.year1ComboBox);
		
		//Panel day2
		JPanel day2Panel = new JPanel();
		day2Panel.setBorder(BorderFactory.createTitledBorder("Day 2"));
		day2Panel.setBounds((int)(1430 * coeffWidth),(int)(900 * coeffHeight),(int)(160 * coeffWidth),(int)(80 * coeffHeight));
		day2Panel.setBackground(Color.white);	
				
		//Choice of the day of second date
		String[] day2 = new String[30];
		for (int i = 0; i < day1.length; i++) {
			int val = i+1;
			day2[i] = ""+val;
		}
		this.day2ComboBox = new JComboBox<String>(day2);
		day2Panel.add(this.day2ComboBox);
				
		//Panel month1
		JPanel month2Panel = new JPanel();
		month2Panel.setBorder(BorderFactory.createTitledBorder("Month 2"));
		month2Panel.setBounds((int)(1430 * coeffWidth),(int)(1000 * coeffHeight),(int)(160 * coeffWidth),(int)(80 * coeffHeight));
		month2Panel.setBackground(Color.white);	
		
		//Choice of the month of second date
		String[] month2 = new String[12];
		for (int i = 0; i < month1.length; i++) {
			int val = i+1;
			month2[i] = ""+val;
		}	
		this.month2ComboBox = new JComboBox<String>(month2);
		month2Panel.add(this.month2ComboBox);
				
		//Panel year1
		JPanel year2Panel = new JPanel();
		year2Panel.setBorder(BorderFactory.createTitledBorder("Year 2"));
		year2Panel.setBounds((int)(1430 * coeffWidth),(int)(1100 * coeffHeight),(int)(160 * coeffWidth),(int)(80 * coeffHeight));
		year2Panel.setBackground(Color.white);	
		
		//Choice of the year of first date
		String[] year2 = {""+2014,""+2015,""+2016,""+2017};
		this.year2ComboBox = new JComboBox<String>(year2);
		year2Panel.add(this.year2ComboBox);
		
		
		add(day1Panel);
		add(month1Panel);
		add(year1Panel);
		add(day2Panel);
		add(month2Panel);
		add(year2Panel);
		
		//Button "OK"
		this.OkDate = new JButton("OK");
		this.OkDate.setBounds((int)(1240 * coeffWidth),(int)(1300 * coeffHeight),(int)(120 * coeffWidth),(int)(60 * coeffHeight));
		add(this.OkDate);
		
		this.back = new JButton("Back");
		this.back.setBounds((int)(10 * coeffWidth),(int)(10 * coeffHeight),(int)(120 * coeffWidth),(int)(50 * coeffHeight));
		add(this.back);
	}
	

	/**
	 * @return the day1ComboBox
	 */
	public JComboBox<String> getDay1ComboBox() {
		return day1ComboBox;
	}

	/**
	 * @return the month1ComboBox
	 */
	public JComboBox<String> getMonth1ComboBox() {
		return month1ComboBox;
	}

	/**
	 * @return the year1ComboBox
	 */
	public JComboBox<String> getYear1ComboBox() {
		return year1ComboBox;
	}

	/**
	 * @return the day2ComboBox
	 */
	public JComboBox<String> getDay2ComboBox() {
		return day2ComboBox;
	}

	/**
	 * @return the month2ComboBox
	 */
	public JComboBox<String> getMonth2ComboBox() {
		return month2ComboBox;
	}

	/**
	 * @return the year2ComboBox
	 */
	public JComboBox<String> getYear2ComboBox() {
		return year2ComboBox;
	}

	/**
	 * @return the okDate
	 */
	public JButton getOkDate() {
		return OkDate;
	}

	/**
	 * @return the back
	 */
	public JButton getBack() {
		return back;
	}

	/**
	 * @return the action
	 */
	public String getAction() {
		return action;
	}

	/**
	 * @param action the action to set
	 */
	public void setAction(String action) {
		this.action = action;
	}
}
