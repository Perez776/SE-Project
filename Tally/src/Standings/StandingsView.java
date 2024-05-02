
package Standings;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.text.DefaultFormatter;

import API.APIInfo;
import LoginRegister.*;
import MainMenu.MainView;

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

public class StandingsView {
	JButton b1, b2;
    JLabel titLabel;
    JComboBox cb;
	JFrame f = new JFrame();
	JTable table;
	MainView main;
	JPanel panel = new JPanel();
	//ImageIcon img;
	StandingsModel model;

	public StandingsView(MainView main, String leagueName) {
		this.main = main;
		this.model = new StandingsModel(leagueName);

		//Border
		Border bluBorder = BorderFactory.createLineBorder(Color.decode("#007AFF"), 2);

		//Labels
		String title = leagueName + " Standings";
        titLabel = new JLabel(title);
        titLabel.setBounds(100,30,90,30);

       	//APIInfo api = new APIInfo("https://basketball.sportdevs.com/standings?league_id=eq.2161&season_id=eq.7823"); 
		//Object teamWins [] = api.getAPIListItem("competitors", "wins"); 
		//Object teamLosses [] = api.getAPIListItem("competitors", "losses"); 
		//Object teamNames [] = api.getAPIListItem("competitors", "team_name");
		//Object img [] = api.getAPIListItem("competitors", "team_hash_image");
		//int pos = 0;

		/* 
		JLabel s = new JLabel();
		String k = "";
		ImageIcon imageIcon = new ImageIcon();
		Icon icon;
		

		TableModel model = new DefaultTableModel(null,columnNames){
            @Override
            public Class<?> getColumnClass(int column) {
                if (column==3) return ImageIcon.class;
                return Object.class;
            }
        };
*/
		//JTable
		table = model.getStandingsTable();
		table.setFillsViewportHeight(true); 
		table.setShowGrid(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);


		//JScrollPanes
		JScrollPane scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBounds(50, 300, 1200, 700);
		scrollPane.setBorder(bluBorder);

		//Buttons
		b1 = new JButton("Register");
		b1.setBounds(130,200,90,30);
   
		b2 = new JButton("Cancel");
		b2.setBounds(130,250,90,30);
	
		//Add Components to frame
		panel.setLayout(null);
        panel.setPreferredSize( new Dimension( 2000, 2000));
        panel.setMinimumSize( new Dimension( 2000, 2000));
		panel.add(titLabel);
		panel.add(scrollPane);
	}


	public JPanel getPanel() {
		return this.panel;
	}

	public static void sortbyColumn(int arr[][], int col)
    {
      Arrays.sort(arr, (a, b) -> Integer.compare(a[col],b[col])); // increasing order
    }
}