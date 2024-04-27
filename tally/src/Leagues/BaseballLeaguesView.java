package Leagues;


import javax.swing.*;

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

public class BaseballLeaguesView implements ActionListener {
    JTextField t1, t2;
	JButton changeLeagueBN;
	JButton standingsBN;
    JLabel l1;
	JLabel mlbLabel;
	JLabel collegeFBLabel;
	JScrollPane j;
	JPanel panel = new JPanel();

    JComboBox cb;
	JFrame f = new JFrame();
	JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT));

	Main main;

	public BaseballLeaguesView(Main main) {
		this.main = main;

		//Get API News
		BaseballLeaguesModel footballLeaguesModel = new BaseballLeaguesModel();
		mlbLabel = footballLeaguesModel.getMLBNews();

		//Labels
		l1 = new JLabel("MLB Recent News");
        l1.setBounds(800,30,150,30);

		//Combo Boxes
		String a[] = {"MLB", ""};
        cb = new JComboBox<>(a);
        cb.setBounds(100,150,90,90);
		cb.addActionListener(this);

		//Panels
		p.setBounds(200, 500, 1900, 800);
		p.add(mlbLabel);

		//Scroll Panes
		JScrollPane j = new JScrollPane(p);
		j.setBounds(300, 500, 1500, 500);

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

		//Add Components to panel
		panel.setLayout(null);
        panel.setPreferredSize( new Dimension( 2000, 2000));
        panel.setMinimumSize( new Dimension( 2000, 2000));
		panel.add(l1);
		panel.add(changeLeagueBN);
		panel.add(standingsBN);
        panel.add(cb);
		panel.add(j);

	}

	public JPanel getPanel() {
		return this.panel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(cb.getSelectedIndex() == 0) {
			p.remove(collegeFBLabel);
			p.add(mlbLabel);
			l1.setText("MLB News");
			p.revalidate();
			p.repaint();
	
			if(e.getSource() == standingsBN) {
				f.dispose();
				NBAView nbaStandings = new NBAView(main);
			}
		}
	}
}