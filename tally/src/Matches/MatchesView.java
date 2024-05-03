package Matches;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.PopupMenu;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionListener;

import MainMenu.MainView;

public class MatchesView {

	MatchesModel model;
    JTextField t1, t2;

	JButton changeLeagueBN;
	JButton changeMonthBN;
	JList list;
	JLabel stats = new JLabel();
    JLabel l1;
	JPanel panel = new JPanel();
	JScrollPane j;
	JComboBox cb, monthsCB, yearsCB;
	JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT));

	MainView main;

	String months[] = {"Dec", "Nov", "Oct", "Sep", "Aug", "Jul", "Jun", "May", "Apr", "Mar", "Feb", "Jan"};
	String cbArr [];
	String leagueName;
	String sportName;
	//String year;
	//String month;
	HashMap<String, String> monthMap = new HashMap<String, String>();


	public MatchesView(MainView main, String sport, String league) {//, String year, String month) {
		this.monthMap = getMontHashMap();
		this.main = main;
		this.leagueName = league;;
		this.sportName = sport;
		//get today's date
		Calendar today = Calendar.getInstance();
		today.set(Calendar.HOUR_OF_DAY, 0);
		//get current year
		int thisYear = today.getWeekYear();
		//get current month
		//String thisMonth = new SimpleDateFormat("MMM").format(today.getTime());
		String thisMonthFull = new SimpleDateFormat("MMM").format(today.getTime());
		//combine year+month and get data from model
		String date = String.valueOf(thisYear) + monthMap.get(thisMonthFull);
		this.model = new MatchesModel(sport, league, date); //, year + this.month);
	
		//Create Border
		Border blueBorder = BorderFactory.createLineBorder(Color.decode("#007AFF"), 2);
		Border nameBorder = BorderFactory.createTitledBorder(blueBorder, "  Game Statistics  ");

		//Labels
		l1 = new JLabel(league + " Matches");
        l1.setBounds(730,30,300,20);

		stats = new JLabel("Click on the Match to View Stats", SwingConstants.CENTER);
		stats.setBounds(700, 300, 500, 600);
		stats.setBorder(nameBorder);

		//Lists
		list = new JList(model.getMatchInfo());
		list.setBounds(100, 300, 500,11000);
		

		//Panels
		p.setBounds(200, 500, 1900, 50000);
		p.add(list);

		
		//ScrollPane
		nameBorder = BorderFactory.createTitledBorder(blueBorder, "  Game Schedule  ");
		j = new JScrollPane(p);
		j.setBounds(100, 300, 500, 600);
		j.getVerticalScrollBar().setUnitIncrement(16);
		j.getHorizontalScrollBar().setUnitIncrement(16);
		j.setBorder(nameBorder);

		//Buttons
		//changeMonthBN = new JButton("<html>Change<br/>Month</html");
		//changeMonthBN.setBounds(300,150,70,40);

		//Combo Boxes
		String a[] = {"News", "Schedule", "Standings", "Rosters"};
		cb = new JComboBox<>(a);
		cb.setBounds(100,150,90,90);

		//years list for combo box
		Object years[] = new Object[thisYear-1999];
		for(int i = thisYear-2000; i >= 0; i = i - 1) {
			years[i] = thisYear - i;
		}

		yearsCB = new JComboBox<>(years);
		yearsCB.setBounds(200,150,90,90);

		monthsCB = new JComboBox<>(months);
		monthsCB.setBounds(300,150,90,90);

		String thisMonthStr = new SimpleDateFormat("M").format(today.getTime());
		int thisMonthNum = Integer.parseInt(thisMonthStr);
		monthsCB.setSelectedIndex(thisMonthNum+2);

		/* 
		//System.out.println(monthMap);
		if(this.month == thisMonthNum && this.year == thisYear) {
			cbArr = new String[thisMonthNum]; 

			int monthsSize  = months.length - thisMonthNum;
			monthNum = 0;
			for(int i = monthsSize; i < months.length; i++)
			{
				cbArr[monthNum] = months[i];
				monthNum++;
			}
		}
*/
		//Add Components to Main Panel
		panel.setLayout(null);
        panel.setPreferredSize( new Dimension( 2000, 12000));
		panel.add(l1);
        panel.add(cb);
		panel.add(stats);
		panel.add(j);
		panel.add(monthsCB);
		panel.add(yearsCB);

		//Add Controller
		MatchesController MMMC = new MatchesController(this, this.model, main);
	}

	public JPanel getPanel() {
		return this.panel;
	}

	public void addMatchSelectionListener(ListSelectionListener listenerForMatches) {
		list.addListSelectionListener(listenerForMatches);
	}

	public void addMatchesListener(ActionListener listenerForMatches) {
		yearsCB.addActionListener(listenerForMatches);
		monthsCB.addActionListener(listenerForMatches);
	}

	public HashMap<String, String> getMontHashMap() {
		HashMap<String, String> monthMap = new HashMap<String, String>();

		int monthNum = 1;
		//Map of months and month number
		for(int i = months.length-1; i >= 0; i--)
		{
			String monthStr = String.valueOf(monthNum);
			if(monthNum < 10) {
				monthStr = "0" + monthStr;
			}
			monthMap.putIfAbsent(months[i], monthStr);
			monthNum++;
		}

		return monthMap;
	}
}