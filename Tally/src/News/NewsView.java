package News;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;

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
import java.util.Vector;

public class NewsView {
    JTextField t1, t2;
	JButton changeLeagueBN;
	JButton standingsBN, linkButton;
    JLabel l1;
	JLabel mlbLabel;
	JLabel collegeFBLabel;
	JScrollPane j;
	JPanel panel = new JPanel();
	JLabel newsLabel;

	URL url;
	BufferedImage image;

    JComboBox cb;
	JFrame f = new JFrame();
	JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT));

	MainView main;

    NewsModel model;

	public NewsView(MainView main, String leagueName) {
		this.main = main;

		linkButton = new JButton("<html>Open<br/>Link</html");
		linkButton.setBounds(900,150,70,40);
		panel.add(linkButton);
		//Get API News
        model = new NewsModel(leagueName); 

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
		JLabel label = new JLabel(new ImageIcon(image.getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
		label.setBounds(0, 0, 100, 100);
		panel.add(label);


		JLabel imgJLabel;
		String imgs [] = model.getImgURLS();
		int startingPos = 300;
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

			imgJLabel = new JLabel(new ImageIcon(image.getScaledInstance(120, 70, Image.SCALE_SMOOTH)));
			imgJLabel.setBounds(0, startingPos+i*80, 120, 70);
			p.add(imgJLabel);

			newsLabel = new JLabel(model.getNews()[i]);
			newsLabel.setBounds(140, startingPos+i*80, 1000, 50);
			p.add(newsLabel);
		}


		//Labels
		String title = leagueName + " News";
		l1 = new JLabel(title);
        l1.setBounds(800,30,400,30);

		//Combo Boxes
		String a[] = {"MLB", ""};
        cb = new JComboBox<>(a);
        cb.setBounds(100,150,90,90);

		//Panels
		p.setBounds(0, 0, 1900, 2000);
		p.setLayout(null);
        p.setPreferredSize( new Dimension( 2000, 2000));
        p.setMinimumSize( new Dimension( 2000, 2000));
		//p.add(newsLabel);

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
		panel.add(p);

		NewsController newsController = new NewsController(this);
	}

	public void addNewsListener(ActionListener listenerForMatches) {
		linkButton.addActionListener(listenerForMatches);
	}

	public JPanel getPanel() {
		return this.panel;
	}
}