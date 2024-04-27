package Leagues;


import javax.swing.*;

import Standings.NBAView;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;

public class SoccerLeaguesView implements ActionListener {
    JTextField t1, t2;

	JButton changeLeagueBN;
	JButton standingsBN;
    JLabel l1;
	JLabel mlsLabel;
	JLabel epLabel;
	JScrollPane j;
	JPanel panel = new JPanel();
    JComboBox cb;
	JFrame f = new JFrame();
	JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT));

	public SoccerLeaguesView(){
		//Get API News
		SoccerLeaguesModel SoccerLeaguesModel = new SoccerLeaguesModel();
		mlsLabel = SoccerLeaguesModel.getMLSNews();
        epLabel = SoccerLeaguesModel.getEPLNews();

		//Labels
		l1 = new JLabel("MLB Recent News");
        l1.setBounds(800,30,150,30);

		//Combo Boxes
		String a[] = {"MLS", "EPL"};
        cb = new JComboBox<>(a);
        cb.setBounds(100,150,90,90);
		cb.addActionListener(this);

		//Panels
		p.setBounds(200, 500, 1900, 800);
		p.add(mlsLabel);

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

		panel.add(l1);
		panel.add(changeLeagueBN);
		panel.add(standingsBN);
        panel.add(cb);
		panel.add(j);

		/* 
		//Add Components to frame
		f.add(l1);
		f.add(changeLeagueBN);
		f.add(standingsBN);
        f.add(cb);
		f.add(j);
		//Set up Frame
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setExtendedState(f.getExtendedState() | JFrame.MAXIMIZED_BOTH);
		f.setLayout(null);
		f.setVisible(true);
		f.setTitle("Tally");
		*/
	}

	public JPanel getPanel() {
		return this.panel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(cb.getSelectedIndex() == 0) {
			p.remove(epLabel);
			p.add(mlsLabel);
			l1.setText("MLS News");
			p.revalidate();
			p.repaint();

			if(e.getSource() == standingsBN) {
				f.dispose();
				NBAView nbaStandings = new NBAView();
			}
		}
        if(cb.getSelectedIndex() == 1) {
			p.remove(mlsLabel);
			p.add(epLabel);
			l1.setText("Enlgish Premier League News");
			p.revalidate();
			p.repaint();
		}
	}
}