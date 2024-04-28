package Standings;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.text.DefaultFormatter;

import API.APIInfo;
import LoginRegister.*;
import MainMenu.Main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

public class NBAView implements ActionListener {
    JTextField t1, t2;
	JButton b1, b2;
    JLabel l1, l2, l3, label;
    JComboBox cb;
    JList list1, list2, list3, list4;
	JFrame f = new JFrame();
	JTable table;
	JScrollPane sp;
	Main main;
	JPanel panel = new JPanel();
	//ImageIcon img;

	public NBAView(Main main) {
		this.main = main;
		//Labels
        l1 = new JLabel("NBA Standings");
        l1.setBounds(100,30,90,30);
	

        APIInfo api = new APIInfo("https://basketball.sportdevs.com/standings?league_id=eq.2161&season_id=eq.7823"); 
		Object teamWins [] = api.getAPIListItem("competitors", "wins"); 
		Object teamLosses [] = api.getAPIListItem("competitors", "losses"); 
		Object teamNames [] = api.getAPIListItem("competitors", "team_name");
		Object img [] = api.getAPIListItem("competitors", "team_hash_image");
		int pos = 0;

		String[] columnNames = {"Team Name", "Team Wins", "Team Losses", "s"};

		Object a [][] = new Object[teamNames.length][4];

		URL url;
		Image image;

		for(int i = 0; i < teamNames.length; i++)
		{
			a[i][0] = teamNames[i];//.toString();
			a[i][1] = teamWins[i];//.toString();
			a[i][2] = teamLosses[i];//.toString();
			a[i][3] = img[i];//.toString();
			String k = "https://images.sportdevs.com/" + img[i] + ".png";

			//System.out.println(k);
			//System.out.println(teamNames[i]);
		}

	
		
		sp = new JScrollPane();
		sp.setBounds(100, 37, 407, 79);
		table = new JTable(a, columnNames);
		JScrollPane scrollPane = new JScrollPane(table);
		table.setFillsViewportHeight(true); 
		scrollPane.setBounds(500, 300, 1000, 1000);

		//Buttons
		b1 = new JButton("Register");
		b1.setBounds(130,200,90,30);
        b1.addActionListener(this);

		b2 = new JButton("Cancel");
		b2.setBounds(130,250,90,30);
		b2.addActionListener(this);

		//Add Components to frame
		panel.setLayout(null);
        panel.setPreferredSize( new Dimension( 2000, 2000));
        panel.setMinimumSize( new Dimension( 2000, 2000));
		panel.add(l1);
		panel.add(l1);
		panel.add(scrollPane);
	}

	public static void sortbyColumn(int arr[][], int col)
    {
      Arrays.sort(arr, (a, b) -> Integer.compare(a[col],b[col])); // increasing order
    }

	public JPanel getPanel() {
		return this.panel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
        if(cb.getSelectedIndex() == 1) {
            f.dispose();
            LoginUI login = new LoginUI(main);
        }
    }
}

