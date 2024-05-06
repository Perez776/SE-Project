package Matches;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.PopupMenu;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
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

	JList list;
	JLabel stats = new JLabel();
    JLabel l1;
	JPanel panel = new JPanel();
	JScrollPane j;
	JComboBox chooseInfo, monthsCB, yearsCB;
	JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT));

	MainView main;

	URL url;
	BufferedImage image;

	String months[] = {"Dec", "Nov", "Oct", "Sep", "Aug", "Jul", "Jun", "May", "Apr", "Mar", "Feb", "Jan"};
	String cbArr [];
	String leagueName;
	String sportName;
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
		JLabel titeLabel = new JLabel(league + " Schedule", SwingConstants.CENTER);
		titeLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
		titeLabel.setBounds(400,20,700,30);

		stats = new JLabel("Click on the Match to View Stats", SwingConstants.CENTER);
		stats.setBounds(700, 300, 500, 600);
		stats.setBorder(nameBorder);

		//League logo label
		String urlString = model.getLeagueLogo();

		try {
			url = new URL(urlString);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			image = ImageIO.read(url);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		JLabel label = new JLabel(new ImageIcon(image.getScaledInstance(150, 150, Image.SCALE_SMOOTH)));
		label.setBounds(10, 10, 150, 150);
		panel.add(label);

		//Lists
		list = new JList(model.getMatchInfo());
		list.setBounds(100, 300, 500,11000);
		DefaultListCellRenderer renderer =  (DefaultListCellRenderer)list.getCellRenderer();  
		renderer.setHorizontalAlignment(JLabel.CENTER);  

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


		//Combo Boxes
		String a[] = {"News", "Schedule", "Standings"};
		chooseInfo = new JComboBox<>(a);
		chooseInfo.setBounds(700,60,90,90);
		chooseInfo.setSelectedIndex(1);

		//years list for combo box
		Object years[] = new Object[thisYear-1999];
		for(int i = thisYear-2000; i >= 0; i = i - 1) {
			years[i] = thisYear - i;
		}

		yearsCB = new JComboBox<>(years);
		yearsCB.setBounds(100,170,90,90);

		monthsCB = new JComboBox<>(months);
		monthsCB.setBounds(200,170,90,90);

		String thisMonthStr = new SimpleDateFormat("M").format(today.getTime());
		int thisMonthNum = Integer.parseInt(thisMonthStr);
		monthsCB.setSelectedIndex(thisMonthNum+2);

		//Add Components to Main Panel
		panel.setLayout(null);
        panel.setPreferredSize( new Dimension( 2000, 12000));
		panel.add(titeLabel);
        panel.add(chooseInfo);
		panel.add(stats);
		panel.add(monthsCB);
		panel.add(yearsCB);
		panel.add(j);
		
		//Add Controller
		MatchesController MMMC = new MatchesController(this, this.model, this.main);
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

	public void addChooseInfoTypeListener(ActionListener chooseInfoTypeListener) {
		chooseInfo.addActionListener(chooseInfoTypeListener);
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