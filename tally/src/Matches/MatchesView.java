package Matches;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Leagues.FootballLeaguesModel;
import MainMenu.ChooseSportController;
import MainMenu.ChooseSportModel;
import MainMenu.Main;
import Standings.NBAView;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.datatransfer.SystemFlavorMap;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

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
        l1.setBounds(800,30,300,20);

		stats = new JLabel("Click on the Match to View Stats", SwingConstants.CENTER);
		stats.setBounds(800, 300, 500, 900);
		stats.setBackground(Color.lightGray);
		stats.setOpaque(true);

		//Lists
		list = new JList(model.getMatchInfo());
		list.setBounds(100, 300, 500,11000);
		list.setBackground(Color.lightGray);

		String a[] = {"NFL", "NCAA Football"};
        cb = new JComboBox<>(a);
        cb.setBounds(100,150,90,90);

		//Panels
		p.setBounds(200, 500, 1900, 50000);
		p.setBackground(Color.lightGray);
		p.add(list);

		
		//ScrollPane
		JScrollPane j = new JScrollPane(p);
		j.setBounds(100, 300, 500, 900);
		j.getVerticalScrollBar().setUnitIncrement(16);
		j.getHorizontalScrollBar().setUnitIncrement(16);


		//Buttons
		changeLeagueBN = new JButton("<html>Change<br/>League</html");
		changeLeagueBN.setBounds(30,150,70,40);
		Font font2 = new Font("serif", Font.BOLD, 12);
		changeLeagueBN.setFont(font2);
        //changeLeagueBN.addActionListener(this);

		standingsBN = new JButton("<html>Views<br/>Standings</html");
		standingsBN.setBounds(400,150,70,40);
		standingsBN.setFont(font2);
        //standingsBN.addActionListener(this);

		//Add Components to frame
		panel.setLayout(null);
        panel.setPreferredSize( new Dimension( 2000, 12000));
        //panel.setMinimumSize( new Dimension( 4000, 4000));
		panel.add(l1);
		panel.add(changeLeagueBN);
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