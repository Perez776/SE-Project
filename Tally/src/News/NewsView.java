package News;
import javax.swing.*;

import MainMenu.*;
import Standings.NBAView;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Label;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;

public class NewsView {
    JTextField t1, t2;
	JButton changeLeagueBN;
	JButton standingsBN;
    JLabel l1;
	JLabel mlbLabel;
	JLabel collegeFBLabel;
	JScrollPane j;
	JPanel panel = new JPanel();
	JLabel newsLabel;

    JComboBox cb;
	JFrame f = new JFrame();
	JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT));

	MainView main;

    NewsModel model;

	public NewsView(MainView main, String leagueName) {
		this.main = main;

		//Get API News
        model = new NewsModel(leagueName);
		newsLabel = model.getNewsLabel();


		//Labels
		String title = leagueName + " News";
		l1 = new JLabel(title);
        l1.setBounds(800,30,400,30);

		//Combo Boxes
		String a[] = {"MLB", ""};
        cb = new JComboBox<>(a);
        cb.setBounds(100,150,90,90);

		//Panels
		p.setBounds(200, 500, 1900, 800);
		panel.setLayout(null);
        panel.setPreferredSize( new Dimension( 2000, 2000));
        panel.setMinimumSize( new Dimension( 2000, 2000));
		p.add(newsLabel);

		//Scroll Panes
		JScrollPane j = new JScrollPane(p);
		j.setBounds(100, 300, 1300, 500);
		j.getVerticalScrollBar().setUnitIncrement(16);
		j.getHorizontalScrollBar().setUnitIncrement(16);

		//Buttons
		changeLeagueBN = new JButton("<html>Change<br/>League</html");
		changeLeagueBN.setBounds(30,150,70,40);
		Font font2 = new Font("serif", Font.BOLD, 12);
		changeLeagueBN.setFont(font2);

		standingsBN = new JButton("<html>Views<br/>Standings</html");
		standingsBN.setBounds(400,150,70,40);
		standingsBN.setFont(font2);

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
}