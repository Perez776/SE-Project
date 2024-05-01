package Matches;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionListener;

import MainMenu.Main;

public class MatchesView {

	MatchesModel model;
    JTextField t1, t2;

	JButton changeLeagueBN;
	JButton standingsBN;

	JList list;
	JLabel stats = new JLabel();
    JLabel l1;
	JLabel nbaMatchesLabel;
	JLabel collegeFBLabel;
	JPanel panel = new JPanel();
	JScrollPane j;
	Main main;
    JComboBox cb;
	JFrame f = new JFrame();
	JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT));

	public MatchesView(Main main, String sport, String league) {
		this.main = main;
		this.model = new MatchesModel(sport, league, "202404");

		//Labels
		l1 = new JLabel(league + " Matches");
        l1.setBounds(730,30,300,20);

		Border blueBorder = BorderFactory.createLineBorder(Color.decode("#007AFF"), 2);
		Border nameBorder = BorderFactory.createTitledBorder(blueBorder, "  Game Statistics  ");

		stats = new JLabel("Click on the Match to View Stats", SwingConstants.CENTER);
		stats.setBounds(700, 300, 500, 600);
		//stats.setBackground(Color.GRAY);
		//stats.setOpaque(true);
		stats.setBorder(nameBorder);

		//Lists
		list = new JList(model.getMatchInfo());
		list.setBounds(100, 300, 500,11000);
		//list.setBackground(Color.lightGray);

		String a[] = {"News", "Schedule", "Standings", "Rosters"};
        cb = new JComboBox<>(a);
        cb.setBounds(100,150,90,90);

		//Panels
		p.setBounds(200, 500, 1900, 50000);
		//p.setBackground(Color.lightGray);
		p.add(list);

		
		//ScrollPane
		nameBorder = BorderFactory.createTitledBorder(blueBorder, "  Game Schedule  ");
		JScrollPane j = new JScrollPane(p);
		j.setBounds(100, 300, 500, 600);
		j.getVerticalScrollBar().setUnitIncrement(16);
		j.getHorizontalScrollBar().setUnitIncrement(16);
		j.setBorder(nameBorder);


		//Buttons
		standingsBN = new JButton("<html>Views<br/>Standings</html");
		standingsBN.setBounds(400,150,70,40);
	
		//Add Components to frame
		panel.setLayout(null);
        panel.setPreferredSize( new Dimension( 2000, 12000));
        //panel.setMinimumSize( new Dimension( 4000, 4000));
		panel.add(l1);
		panel.add(standingsBN);
        panel.add(cb);
		panel.add(stats);
		//panel.add(list);
		panel.add(j);

		//Add Controller
		MatchesController MMMC = new MatchesController(this, model, main);
	}

	public JPanel getPanel() {
		return this.panel;
	}

	public void addMatchSelectionListener(ListSelectionListener listenerForMatches) {
		list.addListSelectionListener(listenerForMatches);
	}

}