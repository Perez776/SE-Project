package Leagues;

import javax.swing.*;

import Standings.NBAView;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;

public class FootballLeaguesView implements ActionListener {
    private static final int VERTICAL_SCROLLBAR_ALWAYS = 0;

	JTextField t1, t2;

	JButton changeLeagueBN;
	JButton standingsBN;

    JLabel l1;
	JLabel nflLabel;
	JLabel collegeFBLabel;
	JScrollPane j;
	JPanel panel = new JPanel();

    JComboBox cb;
	JFrame f = new JFrame();
	JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT));

	public FootballLeaguesView(){
		//Get API News
		FootballLeaguesModel footballLeaguesModel = new FootballLeaguesModel();
		nflLabel = footballLeaguesModel.getNFLNews();
		collegeFBLabel = footballLeaguesModel.getCollegeFBNews();

		//Labels
		l1 = new JLabel("NFL Recent News");
       	l1.setBounds(500,10,150,70);

		//Combo Boxes
		String a[] = {"NFL", "NCAA Football"};
        cb = new JComboBox<>(a);
        cb.setBounds(100,150,90,90);
		cb.addActionListener(this);

		//Panels
		p.setBounds(0, 0, 3000, 3000);
		l1.setBounds(800,3000,150,70);
		p.add(nflLabel);

		//Scroll Panes
		JScrollPane j = new JScrollPane(p);
		j.setBounds(200, 400, 1500, 3000);
	
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

		panel.add(changeLeagueBN);
		panel.add(standingsBN);
		panel.add(cb);
		panel.add(j);

		/* 
		//Add Components to frame
		//f.add(l1);
		f.add(changeLeagueBN);
		f.add(standingsBN);
        f.add(cb);
		f.add(j);
		//Set up Frame
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setExtendedState(f.getExtendedState() | JFrame.MAXIMIZED_BOTH);
		//f.setExtendedState(JFrame.MAXIMIZED_BOTH);
		f.setLayout(null);
		f.setVisible(true);
		f.setTitle("Tally");
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		System.out.println(screenSize.getWidth());
		System.out.println(screenSize.getHeight());
		//f.setSize(screenSize.width, screenSize.height);
		*/
	}

	public JPanel panel() {
		return this.panel;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(cb.getSelectedIndex() == 0) {
			p.remove(collegeFBLabel);
			p.add(nflLabel);
			l1.setText("NFL News");
			p.revalidate();
			p.repaint();

			if(e.getSource() == standingsBN) {
				f.dispose();
				NBAView nbaStandings = new NBAView();
			}
		}
		if(cb.getSelectedIndex() == 1) {
			p.remove(nflLabel);
			p.add(collegeFBLabel);
			l1.setText("NCAA Football News");
			p.revalidate();
			p.repaint();
		}
	}
}
