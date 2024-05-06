package MainMenu;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicScrollBarUI;

import API.APIInfo;
import MainMenu.*;
import Matches.MatchesModel;
import News.NewsController;
import News.NewsModel;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

public class HomePageView {
    JTextField t1, t2;
	JButton [] viewMoreButtons;
    JButton followTeamButton;
    JLabel l1;
	JLabel mlbLabel;
	JLabel collegeFBLabel;
	JScrollPane j;
	JPanel panel = new JPanel();
	JLabel newsLabel;

	HashMap<Integer, Integer> hashMap;
	URL url;
	BufferedImage image;
	String imgs []; 
	String leagueName;

	//Create Border
	Border blueBorder = BorderFactory.createLineBorder(Color.decode("#007AFF"), 2);
	Border nameBorder = BorderFactory.createTitledBorder(blueBorder, "  News  ");

    JComboBox chooseInfoTypeCB;

	JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT));

	MainView main;

    MatchesModel matchesModel;

    HomePageModel model;

    String [] leagues = {"NBA", "WNBA", "NCAA Men's Basketball", "NCAA Women's Basketball", 
                        "UEFA Champions League", "MLS", "EPL", "Mexican Liga BBVA MX", "Spanish La Liga", "German Bundesliga", "French Ligue 1",
                        "NFL", "NCAA Football", "MLB", "NCAA Baseball"};

	public HomePageView(MainView main, String leagueName) {
        model = new HomePageModel(leagueName);
		this.main = main;
		this.leagueName = leagueName;
		//Get API News
        System.out.println(leagueName);

		//Label
        JLabel titeLabel = new JLabel("Available Leagues : ");
        titeLabel.setBounds(60, 10, 150, 150);
        panel.add(titeLabel);

        int x = 0;
        int y = 140;
 
        for(int i = 0; i < leagues.length; i++) {
            System.out.println(leagues[i]);
            matchesModel = new MatchesModel("", leagues[i], "");
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
            JLabel label = new JLabel(new ImageIcon(image.getScaledInstance(150, 150, Image.SCALE_SMOOTH)));
            label.setBounds(10 + x * 200, y, 150, 150);
            panel.add(label);
            
            x++;
            //Position labels to next line
            if(i == 5 || i == 11) {
                x = 0;
                y = y + 200;
            }
        }

        JLabel followedTeamsLabel = new JLabel("Followed Teams");
        followedTeamsLabel.setBounds(500, 10, 150, 150);
        panel.add(followedTeamsLabel);

        followTeamButton = new JButton("<html>Follow <br> Team<html>");
        followTeamButton.setBounds(500, 100, 150, 150);
        //panel.add(followTeamButton);

        
        /* 
		Font font = new Font(null);

		JLabel imgJLabel;
		imgs = model.getImgURLS();
		viewMoreButtons = new JButton[imgs.length];
		int startingPos = 240;

		for(int i = 0; i < imgs.length; i++) {
			try {
				url = new URL(imgs[i]);
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

			imgJLabel = new JLabel(new ImageIcon(image.getScaledInstance(250, 140, Image.SCALE_SMOOTH)));
			imgJLabel.setBounds(10, startingPos+i*230, 250, 140);
			p.add(imgJLabel);
			imgJLabel.setBorder(blueBorder);

			newsLabel = new JLabel(model.getNews()[i]);
			newsLabel.setBounds(300, startingPos+i*230, 1000, 100);
			newsLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
			newsLabel.setBorder(nameBorder);
			p.add(newsLabel);

			viewMoreButtons[i]= new JButton("View More");
			viewMoreButtons[i].setBounds(300, startingPos+100+i*230, 200, 50);
			p.add(viewMoreButtons[i]);
		}
*/

/* 
		String title = leagueName + " News";
		l1 = new JLabel(title, SwingConstants.CENTER);
		l1.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
        l1.setBounds(400,20,700,30);

		//Combo Boxes
		String a[] = {"News", "Schedule", "Standings"};
		chooseInfoTypeCB = new JComboBox<>(a);
		chooseInfoTypeCB.setBounds(700,60,90,90);
		chooseInfoTypeCB.setSelectedIndex(0);

		//Panels
		p.setBounds(0, 0, 1900, 2000);
		p.setLayout(null);
        p.setPreferredSize( new Dimension( 2000, 2000));
        p.setMinimumSize( new Dimension( 2000, 2000));
	*/
		//Add Components to panel
		panel.setLayout(null);
        panel.setPreferredSize( new Dimension( 3000, 3000));
        panel.setMinimumSize( new Dimension( 3000, 3000));
		//panel.add(l1);
        //panel.add(chooseInfoTypeCB);
		panel.add(p);

		//hashMap = getHashCodeMap();
        HomePageController homePageController = new HomePageController(this, model,leagueName);
	}

	
	public void addHomePageListener(ActionListener listenerForMatches) {
		followTeamButton.addActionListener(listenerForMatches);
	}

	public JPanel getPanel() {
		return this.panel;
	}

/* 
	public HashMap<Integer, Integer> getHashCodeMap() {
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

		//Map of months and month number
		for(int i = 0; i <  viewMoreButtons.length; i++)
		{
			map.putIfAbsent(viewMoreButtons[i].hashCode(), i);
		}

		return map;
	}

    */
}