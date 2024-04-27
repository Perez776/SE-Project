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
import MainMenu.MainMenuController;
import MainMenu.MainMenuModel;

public class Main extends JFrame {

    static JMenuBar mb = new JMenuBar();
    static JMenu basketballMenu, loginMenu, nbaMenu; 
    static JMenuItem wnbaItem, ncaaItem, loginMenuItem, registerMenuItem, nbaNewsMenuItem, nbaStandingsMenuItem;
    static JFrame frame;// = new JFrame();
    static JScrollPane scrollPane;
    static JPanel panel;

    //Button Listener
	//MainC m = new MainMenuModel();

    public Main() {
        //Labels
        JLabel l = new JLabel("Wwwe");
        l.setBounds(100, 1800, 100, 100);
        
        //Main Panel
        JPanel p = new JPanel(new GridLayout());
        p.add(l);
        p.setLayout(null);
        p.setPreferredSize( new Dimension( 2000, 2000));
        p.setMinimumSize( new Dimension( 2000, 2000));
       
        //Menu Bar
        mb = new JMenuBar();

        //Menu Headers
        basketballMenu = new JMenu("Basketball");
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
        nbaMenu.add(nbaNewsMenuItem);
        nbaMenu.add(nbaStandingsMenuItem);
        wnbaItem = new JMenuItem("WNBA");
        ncaaItem = new JMenuItem("NCAA");
        basketballMenu.add(nbaMenu);
        basketballMenu.add(wnbaItem);
        basketballMenu.add(ncaaItem);
    
        //Add Menu Items to menu Bar
        mb.add(basketballMenu);
        mb.add(loginMenu);
        mb.setBackground(Color.LIGHT_GRAY);

        frame = new JFrame("Tally");
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        BBLeaguesTest bbLeaguesTest = new BBLeaguesTest();
        panel = bbLeaguesTest.getPanel();
        scrollPane = new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
		scrollPane.getHorizontalScrollBar().setUnitIncrement(16);

        frame.setContentPane(scrollPane);
        frame.setJMenuBar(mb);

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
        ncaaItem.addActionListener(lisetenerForBasketball);
        wnbaItem.addActionListener(lisetenerForBasketball);
        loginMenuItem.addActionListener(lisetenerForBasketball);
        registerMenuItem.addActionListener(lisetenerForBasketball);
	}

    void addLoginMenuListener(MenuListener listenerForLogin) {
		loginMenu.addMenuListener(listenerForLogin);
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
