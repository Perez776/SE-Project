package Leagues;

import javax.swing.*;

import MainMenu.Main;
import Standings.NBAView;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;

public class BasketballLeaguesView implements ActionListener {
    JTextField t1, t2;

	JButton changeLeagueBN;
	JButton standingsBN;
	JButton link;
    JLabel l1;
	JLabel wnbaLabel;
	JLabel nbaLabel;
	JLabel collegeBBLabel;
	JPanel panel = new JPanel();
	JScrollPane j;
    JComboBox cb;
	JFrame f = new JFrame();
	JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT));
	Main main;

	public BasketballLeaguesView(Main main){
		this.main = main;
		//GetAPI News
		BasketballLeaguesModel baskeballLeaguesModel = new BasketballLeaguesModel();
		nbaLabel = baskeballLeaguesModel.getNBANews();
		wnbaLabel = baskeballLeaguesModel.getWNBANews();
		collegeBBLabel = baskeballLeaguesModel.getCollegeBasketballNews();

		//Labels
		l1 = new JLabel("NBA Recent News");
        l1.setBounds(800,30,150,30);

		//Combo Box
		String a[] = {"NBA", "WNBA", "NCAA"};
        cb = new JComboBox<>(a);
        cb.setBounds(100,150,90,90);
		cb.addActionListener(this);

		//Panels
		p.setBounds(200, 500, 1300, 500);
		p.add(nbaLabel);
		panel.setLayout(null);
        panel.setPreferredSize( new Dimension( 2000, 2000));
        panel.setMinimumSize( new Dimension( 2000, 2000));

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
        changeLeagueBN.addActionListener(this);

		standingsBN = new JButton("<html>Views<br/>Standings</html");
		standingsBN.setBounds(400,150,70,40);
		standingsBN.setFont(font2);
        standingsBN.addActionListener(this);

		link = new JButton("<html>Open<br/>Link</html");
		link.setBounds(900,150,70,40);
		link.setFont(font2);
        link.addActionListener(this);

		//Add Components to panel
		panel.add(l1);
		panel.add(changeLeagueBN);
		panel.add(standingsBN);
		panel.add(link);
        panel.add(cb);
		panel.add(j);
/* 
		//Add Components to frame
		f.add(l1);
		f.add(changeLeagueBN);
		f.add(standingsBN);
		f.add(link);
        f.add(cb);
		f.add(j);
		//Set up Frame
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//f.setExtendedState(f.getExtendedState() | JFrame.MAXIMIZED_BOTH);
		f.setExtendedState(JFrame.MAXIMIZED_BOTH);
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
		if(e.getSource() == link)
		{
			try {
				Desktop.getDesktop().browse(new URL("https://www.google.com/").toURI());
			} catch (MalformedURLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (URISyntaxException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}




		if(cb.getSelectedIndex() == 0) {
				p.remove(wnbaLabel);
				p.remove(collegeBBLabel);
				p.add(nbaLabel);
				l1.setText("NBA News");
				p.revalidate();
				p.repaint();
			if(e.getSource() == standingsBN) {
				f.dispose();
				NBAView nbaStandings = new NBAView(main);
			}
		}
		if(cb.getSelectedIndex() == 1) {
			p.remove(nbaLabel);
			p.remove(collegeBBLabel);
			p.add(wnbaLabel);
			l1.setText("WNBA News");
			p.revalidate();
			p.repaint();
		}
		if(cb.getSelectedIndex() == 2) {
			p.remove(nbaLabel);
			p.remove(wnbaLabel);
			p.add(collegeBBLabel);
			l1.setText("College BasketBall News");
			p.revalidate();
			p.repaint();
		}
	}
}
