package News;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicScrollBarUI;

import API.APIInfo;
import MainMenu.*;
import Matches.MatchesModel;

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

public class NewsView {
    JTextField t1, t2;
	JButton changeLeagueBN;
	JButton standingsBN, linkButton;
	JButton [] viewMoreButtons;
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

	//Create Border
	Border blueBorder = BorderFactory.createLineBorder(Color.decode("#007AFF"), 2);
	Border nameBorder = BorderFactory.createTitledBorder(blueBorder, "  News  ");

    JComboBox chooseInfoTypeCB;

	JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT));

	MainView main;

    NewsModel model;

	public NewsView(MainView main, String leagueName) {
		this.main = main;

		//Buttons
		linkButton = new JButton("<html>Open<br/>Link</html");
		linkButton.setBounds(900,150,70,40);
		panel.add(linkButton);

		//Get API News
        model = new NewsModel(leagueName); 

		//Labels
		MatchesModel matchesModel = new MatchesModel("", leagueName, "202404");
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
		JLabel label = new JLabel(new ImageIcon(image.getScaledInstance(200, 200, Image.SCALE_SMOOTH)));
		label.setBounds(10, 10, 200, 200);
		panel.add(label);

		Font font = new Font(null);


		JLabel imgJLabel;
		imgs = model.getImgURLS();
		viewMoreButtons = new JButton[imgs.length];
		int startingPos = 270;

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
			imgJLabel.setBounds(10, startingPos+i*250, 250, 140);
			p.add(imgJLabel);
			imgJLabel.setBorder(blueBorder);

			newsLabel = new JLabel(model.getNews()[i]);
			newsLabel.setBounds(300, startingPos+i*250, 1000, 100);
			newsLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
			newsLabel.setBorder(nameBorder);
			p.add(newsLabel);

			viewMoreButtons[i]= new JButton("View More");
			viewMoreButtons[i].setBounds(300, startingPos+100+i*250, 200, 50);
			p.add(viewMoreButtons[i]);
		}


		//Label
		String title = leagueName + " News";
		l1 = new JLabel(title);
		l1.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
        l1.setBounds(700,20,700,30);

		//Combo Boxes
		String a[] = {"News", "Schedule", "Standings"};
		chooseInfoTypeCB = new JComboBox<>(a);
		chooseInfoTypeCB.setBounds(700,60,90,90);

		//Panels
		p.setBounds(0, 0, 1900, 2000);
		p.setLayout(null);
        p.setPreferredSize( new Dimension( 2000, 2000));
        p.setMinimumSize( new Dimension( 2000, 2000));
	
		//Add Components to panel
		panel.setLayout(null);
        panel.setPreferredSize( new Dimension( 3000, 3000));
        panel.setMinimumSize( new Dimension( 3000, 3000));
		panel.add(l1);
        panel.add(chooseInfoTypeCB);
		panel.add(p);

		hashMap = getHashCodeMap();

		NewsController newsController = new NewsController(this);
	}

	public void addNewsListener(ActionListener listenerForNews) {
		linkButton.addActionListener(listenerForNews);

		for(int i = 0; i < viewMoreButtons.length; i++) {
			viewMoreButtons[i].addActionListener(listenerForNews);
		}
	}

	public JPanel getPanel() {
		return this.panel;
	}


	public HashMap<Integer, Integer> getHashCodeMap() {
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

		//Map of months and month number
		for(int i = 0; i <  viewMoreButtons.length; i++)
		{
			map.putIfAbsent(viewMoreButtons[i].hashCode(), i);
		}

		return map;
	}
}