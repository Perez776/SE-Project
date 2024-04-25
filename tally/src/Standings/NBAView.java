package Standings;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.text.DefaultFormatter;

import API.APIInfo;
import LoginRegister.*;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ArrayList;
import java.util.Vector;

public class NBAView implements ActionListener {
    JTextField t1, t2;
	JButton b1, b2;
    JLabel l1, l2, l3, label;
    JComboBox cb;
    JList list1, list2, list3, list4;
	JFrame f = new JFrame();
	JTable table;
	JScrollPane sp;
	//ImageIcon img;

	public NBAView(){
		//Labels
        l1 = new JLabel("NBA Standings");
        l1.setBounds(100,30,90,30);
	

        APIInfo api = new APIInfo("https://basketball.sportdevs.com/standings?league_id=eq.2161&season_id=eq.7823"); 
		Object teamNames [] = api.getAPIListItem("competitors", "team_name");
		Object teamWins [] = api.getAPIListItem("competitors", "wins"); 
		Object teamLosses [] = api.getAPIListItem("competitors", "losses"); 

		String[] columnNames = {"Team Name", "Team Wins", "Team Losses"};

		Object a [][] = new Object[teamNames.length][3];

		for(int i = 0; i < teamNames.length; i++)
		{
			a[i][0] = teamNames[i];
			a[i][1] = teamWins[i];
			a[i][2] = teamLosses[i];
			//System.out.println(teamNames[i]);
		}
		
		sp = new JScrollPane();
		sp.setBounds(100, 37, 407, 79);
		table = new JTable(a, columnNames);
		JScrollPane scrollPane = new JScrollPane(table);
		table.setFillsViewportHeight(true); 
		scrollPane.setBounds(0, 100, 500, 500);

		//Buttons
		b1 = new JButton("Register");
		b1.setBounds(130,200,90,30);
        b1.addActionListener(this);

		b2 = new JButton("Cancel");
		b2.setBounds(130,250,90,30);
		b2.addActionListener(this);

		//Add Components to frame
		f.add(l1);
		f.add(scrollPane);
	
		//Set up Frame
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setExtendedState(f.getExtendedState() | JFrame.MAXIMIZED_BOTH);
		f.setLayout(null);
		f.setVisible(true);
		f.setTitle("Tally");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
        if(cb.getSelectedIndex() == 1) {
            f.dispose();
            LoginUI login = new LoginUI();
        }
    }
}

