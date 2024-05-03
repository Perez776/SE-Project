
package Standings;

import javax.imageio.ImageIO;
import javax.print.attribute.standard.Media;
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
import Matches.MatchesModel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

import javax.media.CannotRealizeException;
import javax.media.Manager;
import javax.media.NoPlayerException;
import javax.media.Player;

public class StandingsView extends JPanel {
	JButton b1, b2;
    JLabel titLabel;
    JComboBox cb;
	JFrame f = new JFrame();
	JTable table;
	MainView main;
	JPanel panel = new JPanel();
	//ImageIcon img;
	StandingsModel model;
	BufferedImage myPicture, image;
	URL url;
	URL mediaURL;
	Player mediaPlayer;

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
		try {
			myPicture = ImageIO.read(new File("Tally/imgs/Screenshot 2024-05-02 225701.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JLabel picLabel = new JLabel(new ImageIcon(myPicture.getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
		picLabel.setBounds(0, 0, 100, 100);
		panel.add(picLabel);
*/


		MatchesModel matchesModel = new MatchesModel(title, leagueName, "202404");
		String urlString = matchesModel.getLeagueLogo();
		try {
			url = new URL(urlString);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			image = ImageIO.read(url);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JLabel label = new JLabel(new ImageIcon(image.getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
		label.setBounds(0, 0, 100, 100);
		panel.add(label);


		
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

	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(myPicture, 0, 0, this); // see javadoc for more info on the parameters            
    }
}