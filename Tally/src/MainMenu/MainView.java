package MainMenu;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Paint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.HashMap;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.event.MenuListener;
import javax.swing.plaf.basic.BasicLookAndFeel;

import com.formdev.flatlaf.themes.FlatMacDarkLaf;

import LoginRegister.LoginView;
import Matches.MatchesView;
import News.NewsView;
import Standings.StandingsView;

public class MainView extends JFrame {

    static JMenuBar menuBar = new JMenuBar();
    static JMenu basketballMenu, soccerMenu, footballMenu, baseballMenu, loginMenu, nbaMenu, wnbaMenu, ncaaMenu, nflMenu, mlbMenu, ncaaFMenu, ncaaMBBMenu, ncaaWBBMenu, ncaaBaseballMenu; 
    static JMenu uefaMenu, mlsMenu, eplMenu, ligaMXMenu, laLigaMenu, bundesligaMenu, ligue1Menu;
    static JMenuItem loginMenuItem, registerMenuItem;
    static JFrame frame;// = new JFrame();
    static JScrollPane scrollPane;
    static JPanel panel;
    static ArrayList<ArrayList<JMenuItem>> menuItems = new ArrayList<ArrayList<JMenuItem>>();
    ArrayList<String> followedTeams = new ArrayList<String>();
    HashMap<Integer, String[]> subMenuMap = new HashMap<Integer, String[]>();
    String uusername;

    public MainView() {
        
        BasicLookAndFeel theme = new FlatMacDarkLaf();
        try {
            UIManager.setLookAndFeel(theme);
        } catch (UnsupportedLookAndFeelException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        //Border blueBorder = BorderFactory.createLineBorder(Color.decode("#007AFF"), 2);
        Border blueBorder = BorderFactory.createBevelBorder(BevelBorder.RAISED);
        menuBar.setBorder(blueBorder);
        menuBar.setPreferredSize(new Dimension(400,60));

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
        addSubMenu(nbaMenu, "NBA");

        wnbaMenu = new JMenu("WNBA");
        addSubMenu(wnbaMenu, "WNBA");

        ncaaMBBMenu = new JMenu("NCAA Men's Basketball");
        addSubMenu(ncaaMBBMenu, "NCAA Men's Basketball");

        ncaaWBBMenu = new JMenu("NCAA Women's Basketball");
        addSubMenu(ncaaWBBMenu,"NCAA Women's Basketball");

        //baseballMenu.setForeground(Color.BLUE);
        basketballMenu.add(nbaMenu);
        basketballMenu.add(wnbaMenu);
        basketballMenu.add(ncaaMBBMenu);
        basketballMenu.add(ncaaWBBMenu);

        //SoccerMenuItemsEUFA Champions League
        uefaMenu =new JMenu("UEFA Champions League");
        addSubMenu(uefaMenu, "UEFA Champions League");

        mlsMenu = new JMenu("MLS");
        addSubMenu(mlsMenu, "MLS");

        eplMenu =new JMenu("EPL");
        addSubMenu(eplMenu, "EPL");
        
        ligaMXMenu =new JMenu("Mexican Liga BBVA MX");
        addSubMenu(ligaMXMenu, "Mexican Liga BBVA MX");

        laLigaMenu =new JMenu("Spanish La Liga");
        addSubMenu(laLigaMenu, "Spanish La Liga");

        bundesligaMenu =new JMenu("German Bundesliga");
        addSubMenu(bundesligaMenu, "German Bundesliga");

        ligue1Menu =new JMenu("French Ligue 1");
        addSubMenu(ligue1Menu, "French Ligue 1");

        soccerMenu.add(uefaMenu);
        soccerMenu.add(mlsMenu);
        soccerMenu.add(eplMenu);
        soccerMenu.add(ligaMXMenu);
        soccerMenu.add(laLigaMenu);
        soccerMenu.add(bundesligaMenu);
        soccerMenu.add(ligue1Menu);

        //FootballMenuItems
        nflMenu = new JMenu("NFL");
        addSubMenu(nflMenu, "NFL");

        ncaaFMenu = new JMenu("NCAA Football");
        addSubMenu(ncaaFMenu,"NCAA Football");

        footballMenu.add(nflMenu);
        footballMenu.add(ncaaFMenu);

        //BaseballMenuItems
        mlbMenu =  new JMenu("MLB");
        addSubMenu(mlbMenu,"MLB");
        ncaaBaseballMenu = new JMenu("NCAA Baseball");
        addSubMenu(ncaaBaseballMenu, "NCAA Baseball");

        baseballMenu.add(mlbMenu);
        baseballMenu.add(ncaaBaseballMenu);

        //Add Menu Items to menu Bar
        menuBar.add(basketballMenu);
        menuBar.add(soccerMenu);
        menuBar.add(footballMenu);
        menuBar.add(baseballMenu);
        menuBar.add(loginMenu);
        //menuBar.setBackground(Color.LIGHT_GRAY);

        HomePageView newsView = new HomePageView(this, "UEFA Champions League");
        //LoginView loginView = new LoginView(this);
        panel = newsView.getPanel();

        //Set up Frame
        frame = new JFrame("Tally");
        frame.setSize(2000, 2000);
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

        for(int i = 0; i < menuItems.size(); i++) {
            for(int j = 0; j < menuItems.get(i).size(); j++) {
                menuItems.get(i).get(j).addActionListener(lisetenerForBasketball);
            }
        }

        loginMenuItem.addActionListener(lisetenerForBasketball);
        registerMenuItem.addActionListener(lisetenerForBasketball);
	}

    void addLoginMenuListener(MenuListener listenerForLogin) {
		loginMenu.addMenuListener(listenerForLogin);
	}

    public void updatePanel(JPanel newPanel) {
        scrollPane.getViewport().remove(panel);
        scrollPane.setViewportView(newPanel);
    }

    void addSubMenu(JMenu menu, String league) {
        menuItems.add(new ArrayList<JMenuItem>());
        int index = menuItems.size()-1;
        menuItems.get(index).add(new JMenuItem("News"));
        menuItems.get(index).add(new JMenuItem("Schedule"));
        menuItems.get(index).add(new JMenuItem("Standings"));
        menu.add(menuItems.get(index).get(0));
        menu.add(menuItems.get(index).get(1));
        menu.add(menuItems.get(index).get(2));
        subMenuMap.get(index);
        String news [] = {league, "News"};
        String schedule [] = {league,"Schedule"};
        String standings [] = {league, "Standings"};
        subMenuMap.putIfAbsent(menuItems.get(index).get(0).hashCode(), news);
        subMenuMap.putIfAbsent(menuItems.get(index).get(1).hashCode(), schedule);
        subMenuMap.putIfAbsent(menuItems.get(index).get(2).hashCode(), standings);
    }

    public ArrayList<String> getFollowedTeams (){
        return followedTeams;
    }
}
