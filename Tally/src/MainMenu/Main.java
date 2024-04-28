package MainMenu;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.util.EventListener;

import javax.swing.*;
import javax.swing.event.MenuListener;
import Leagues.BaseballLeaguesView;
import Leagues.BasketballLeaguesView;
import Leagues.FootballLeaguesView;
import Leagues.SoccerLeaguesView;
import LoginRegister.LoginUI;
import Matches.MatchesView;

public class Main extends JFrame {

    static JMenuBar menuBar = new JMenuBar();
    static JMenu basketballMenu, soccerMenu, footballMenu, baseballMenu, loginMenu, nbaMenu; 
    static JMenuItem wnbaItem, ncaaItem, loginMenuItem, registerMenuItem, nbaNewsMenuItem, nbaStandingsMenuItem, nbaScheduleMenuItem, mlsMenuItem;
    static JFrame frame;// = new JFrame();
    static JScrollPane scrollPane;
    static JPanel panel;

    //Button Listener
	//MainC m = new MainMenuModel();

    public Main() {
        //Labels
        
        //Main Panel
        JPanel p = new JPanel(new GridLayout());
        p.setLayout(null);
        p.setPreferredSize( new Dimension( 2000, 2000));
        p.setMinimumSize( new Dimension( 2000, 2000));
       
        //Menu Bar
        menuBar = new JMenuBar();

        //Menu Headers
        basketballMenu = new JMenu("Basketball");
        soccerMenu = new JMenu("Soccer");
        footballMenu = new JMenu("Football");
        baseballMenu = new JMenu("Baseball");
        loginMenu = new JMenu("Login");
        
        //MenuItems
        //Login Menu Items
        loginMenuItem = new JMenuItem("Login");
        registerMenuItem = new JMenuItem("Register");
        loginMenu.add(loginMenuItem);
        loginMenu.add(registerMenuItem);
        //BasketballMenuItems
        nbaMenu = new JMenu("NBA");
        nbaNewsMenuItem = new JMenuItem("News");
        nbaStandingsMenuItem = new JMenuItem("Standings");
        nbaScheduleMenuItem = new JMenuItem("Schedule");
        nbaMenu.add(nbaNewsMenuItem);
        nbaMenu.add(nbaStandingsMenuItem);
        nbaMenu.add(nbaScheduleMenuItem);

        wnbaItem = new JMenuItem("WNBA");
        ncaaItem = new JMenuItem("NCAA");
        basketballMenu.add(nbaMenu);
        basketballMenu.add(wnbaItem);
        basketballMenu.add(ncaaItem);
        //SoccerMenuItems
        mlsMenuItem = new JMenuItem("MLS");
        soccerMenu.add(mlsMenuItem);
    
        //Add Menu Items to menu Bar
        menuBar.add(basketballMenu);
        menuBar.add(soccerMenu);
        menuBar.add(footballMenu);
        menuBar.add(baseballMenu);
        menuBar.add(loginMenu);
        menuBar.setBackground(Color.LIGHT_GRAY);

        //Starting Panel
        //ChooseSportView baseballLeaguesView = new ChooseSportView(this);
        //panel = baseballLeaguesView.getPanel();
        MatchesView matchesView = new MatchesView(this);
        panel = matchesView.getPanel();

        //Set up Frame
        frame = new JFrame("Tally");
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);

        //ScrollPane
        scrollPane = new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
		scrollPane.getHorizontalScrollBar().setUnitIncrement(16);

        frame.setContentPane(scrollPane);
        frame.setJMenuBar(menuBar);

        //Button Listener
		//MainMenuModel m = new MainMenuModel();
		MainController MMMC = new MainController(this);

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                frame.setVisible(true);
            }
        });
    }

    //Menu Listeners
    void addBasketballMenuListener(ActionListener lisetenerForBasketball) {
        nbaNewsMenuItem.addActionListener(lisetenerForBasketball);
        nbaStandingsMenuItem.addActionListener(lisetenerForBasketball);
        nbaScheduleMenuItem.addActionListener(lisetenerForBasketball);
        ncaaItem.addActionListener(lisetenerForBasketball);
        wnbaItem.addActionListener(lisetenerForBasketball);
        loginMenuItem.addActionListener(lisetenerForBasketball);
        registerMenuItem.addActionListener(lisetenerForBasketball);
        mlsMenuItem.addActionListener(lisetenerForBasketball);
	}

    void addLoginMenuListener(MenuListener listenerForLogin) {
		loginMenu.addMenuListener(listenerForLogin);
	}

    public void updatePanel(JPanel newPanel) {
        scrollPane.getViewport().remove(panel);
        scrollPane.setViewportView(newPanel);
    }

/* 
    public Main () {

        JLabel l = new JLabel("Wwwe");
        l.setBounds(100, 1800, 100, 100);

        JPanel p = new JPanel(new GridLayout());
        p.add(l);
        p.setLayout(null);
        p.setPreferredSize( new Dimension( 2000, 2000));
        p.setMinimumSize( new Dimension( 2000, 2000));
       
        //Menu Bar
        JMenuBar mb = new JMenuBar();

        //Menu Headers
        JMenu x = new JMenu("Menu");
        JMenu x1 = new JMenu("submenu");
        JMenu login = new JMenu("Login");
        
        //MenuItems
        JMenuItem logMenuItem = new JMenuItem("Login");
        JMenuItem logMenuItem2 = new JMenuItem("Register");
        login.add(logMenuItem);
        login.add(logMenuItem2);

        JMenuItem  m1 = new JMenuItem("MenuItem1");
        JMenuItem m2 = new JMenuItem("MenuItem2");
        JMenuItem m3 = new JMenuItem("MenuItem3");
        JMenuItem s1 = new JMenuItem("SubMenuItem1");
        JMenuItem s2 = new JMenuItem("SubMenuItem2");
        x.add(m1);
        x.add(m2);
        x.add(m3);
        x1.add(s1);
        x1.add(s2);
        // add submenu
        x.add(x1);
        // add menu to menu bar
        mb.add(x);
        mb.add(login);
        // add menubar to frame
        mb.setBackground(Color.LIGHT_GRAY);


        final JFrame frame = new JFrame("Tally");
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        BBLeaguesTest bbLeaguesTest = new BBLeaguesTest();
        JPanel panel =   bbLeaguesTest.getPanel();

        JScrollPane scrollPane = new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
		scrollPane.getHorizontalScrollBar().setUnitIncrement(16);


        frame.setContentPane(scrollPane);
        frame.setJMenuBar(mb);

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                frame.setVisible(true);
            }
        });

    }
    */
}
