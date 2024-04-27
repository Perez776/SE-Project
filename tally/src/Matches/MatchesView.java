package Matches;

import javax.swing.*;

import Leagues.FootballLeaguesModel;
import MainMenu.Main;
import Standings.NBAView;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;

public class MatchesView implements ActionListener {
    JTextField t1, t2;

	JButton changeLeagueBN;
	JButton standingsBN;

    JLabel l1;
	JLabel nbaMatchesLabel;
	JLabel collegeFBLabel;
	JPanel panel = new JPanel();
	JScrollPane j;
	Main main;
    JComboBox cb;
	JFrame f = new JFrame();
	JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT));

	public MatchesView(Main main) {
		this.main = main;

		//Labels
		l1 = new JLabel("NBA Schedule");
        l1.setBounds(800,30,300,20);

		MatchesModel matchesView = new MatchesModel();
		nbaMatchesLabel = matchesView.getNBAMatchesLabel();
	
		JList list = new JList(matchesView.getAPIarray());
		list.setBackground(Color.YELLOW);
		list.setBounds(0, 0, 500,2000);

		for(int i = 0; i < matchesView.getAPIarray().length; i++) {
			System.out.println(matchesView.getAPIarray()[i]);
		}

		String a[] = {"NFL", "NCAA Football"};
        cb = new JComboBox<>(a);
        cb.setBounds(100,150,90,90);

		p.setBounds(200, 500, 1900, 50000);
		//p.add(nbaMatchesLabel);
		p.add(list);

		//ScrollPane
		JScrollPane j = new JScrollPane(p);
		j.setBounds(300, 500, 1500, 1000);
		j.getVerticalScrollBar().setUnitIncrement(16);
		j.getHorizontalScrollBar().setUnitIncrement(16);

		//Buttons
		changeLeagueBN = new JButton("<html>Change<br/>League</html");
		changeLeagueBN.setBounds(30,150,70,40);
		Font font2 = new Font("serif", Font.BOLD, 12);
		changeLeagueBN.setFont(font2);
        changeLeagueBN.addActionListener(this);

		standingsBN = new JButton("<html>Views<br/>Standings</html");
		standingsBN.setBounds(400,150,70,40);
		standingsBN.setFont(font2);
        standingsBN.addActionListener(this);

		//Add Components to frame
		panel.setLayout(null);
        panel.setPreferredSize( new Dimension( 2000, 2000));
        panel.setMinimumSize( new Dimension( 2000, 2000));
		panel.add(l1);
		panel.add(changeLeagueBN);
		panel.add(standingsBN);
        panel.add(cb);
		panel.add(j);
		//panel.add(list);
	}

	public JPanel getPanel() {
		return this.panel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(cb.getSelectedIndex() == 0) {
			if(e.getSource() == changeLeagueBN) {
				p.remove(collegeFBLabel);
				p.add(nbaMatchesLabel);
				l1.setText("NFL News");
				p.revalidate();
				p.repaint();
			}
			if(e.getSource() == standingsBN) {
				f.dispose();
				NBAView nbaStandings = new NBAView(main);
			}
		}
		if(cb.getSelectedIndex() == 1) {
			if(e.getSource() == changeLeagueBN) {
				p.remove(nbaMatchesLabel);
				p.add(collegeFBLabel);
				l1.setText("NCAA Football News");
				p.revalidate();
				p.repaint();
			}
		}
	}
}