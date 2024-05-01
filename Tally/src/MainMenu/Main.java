package MainMenu;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.util.ArrayList;
import java.util.EventListener;

import javax.management.AttributeList;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.MenuListener;
import javax.swing.plaf.basic.BasicLookAndFeel;

import com.bulenkov.darcula.DarculaLaf;
import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;

import Leagues.BaseballLeaguesView;
import Leagues.BasketballLeaguesView;
import Leagues.FootballLeaguesView;
import Leagues.SoccerLeaguesView;
import LoginRegister.LoginUI;
import Matches.MatchesView;

public class Main extends JFrame {

    static JMenuBar menuBar;
    static JMenu basketballMenu, soccerMenu, footballMenu, baseballMenu, loginMenu, nbaMenu, wnbaMenu, ncaaMenu, mlsMenu, nflMenu, mlbMenu, eplMenu, ncaaFMenu, ncaaMBBMenu, ncaaWBBMenu, ncaaBaseballMenu;
    static JMenuItem wnbaItem, ncaaItem, loginMenuItem, registerMenuItem, nbaNewsMenuItem, nbaStandingsMenuItem, nbaScheduleMenuItem, mlsMenuItem;
    static JFrame frame;// = new JFrame();
    static JScrollPane scrollPane;
    static JPanel panel;
    static ArrayList<ArrayList<JMenuItem>> menuItems = new ArrayList<ArrayList<JMenuItem>>();
    static ArrayList<JMenu> menus = new ArrayList<JMenu>();

    public Main() {
        
        BasicLookAndFeel theme = new FlatMacDarkLaf();
        try {
            UIManager.setLookAndFeel(theme);
        } catch (UnsupportedLookAndFeelException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        //Menu Bar
        menuBar = new JMenuBar();
        menuBar.setMargin(new Insets(20, 20, 20, 20));
        //menuBar.underlineSelectionColor();
        

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
        addSubMenu(nbaMenu);

        wnbaMenu = new JMenu("WNBA");
        addSubMenu(wnbaMenu);

        ncaaMBBMenu = new JMenu("NCAA Men's Basketball");
        addSubMenu(ncaaMBBMenu);

        ncaaWBBMenu = new JMenu("NCAA Women's Basketball");
        addSubMenu(ncaaWBBMenu);

        //baseballMenu.setForeground(Color.BLUE);
        basketballMenu.add(nbaMenu);
        basketballMenu.add(wnbaMenu);
        basketballMenu.add(ncaaMBBMenu);
        basketballMenu.add(ncaaWBBMenu);

        //SoccerMenuItems
        mlsMenu = new JMenu("MLS");
        addSubMenu(mlsMenu);

        eplMenu =new JMenu("EPL");
        addSubMenu(eplMenu);

        soccerMenu.add(mlsMenu);
        soccerMenu.add(eplMenu);

        //FootballMenuItems
        nflMenu = new JMenu("NFL");
        addSubMenu(nflMenu);

        ncaaFMenu = new JMenu("NCAA Football");
        addSubMenu(ncaaFMenu);

        footballMenu.add(nflMenu);
        footballMenu.add(ncaaFMenu);

        //BaseballMenuItems
        mlbMenu =  new JMenu("MLB");
        addSubMenu(mlbMenu);
        ncaaBaseballMenu = new JMenu("NCAA Baseball");
        addSubMenu(ncaaBaseballMenu);

        baseballMenu.add(mlbMenu);
        baseballMenu.add(ncaaBaseballMenu);

        //Add Menu Items to menu Bar
        menuBar.add(basketballMenu);
        menuBar.add(soccerMenu);
        menuBar.add(footballMenu);
        menuBar.add(baseballMenu);
        menuBar.add(loginMenu);
        //menuBar.setBackground(Color.LIGHT_GRAY);

        //Starting Panel
        //ChooseSportView baseballLeaguesView = new ChooseSportView(this);
        //panel = baseballLeaguesView.getPanel();
        MatchesView matchesView = new MatchesView(this, "", "MLS");
        panel = matchesView.getPanel();
        //LoginUI loginUI = new LoginUI(this);
        //panel = loginUI.getLoginPanel();

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
        //nbaNewsMenuItem.addActionListener(lisetenerForBasketball);
        //nbaStandingsMenuItem.addActionListener(lisetenerForBasketball);
        //nbaScheduleMenuItem.addActionListener(lisetenerForBasketball);
        for(int i = 0; i < menuItems.size(); i++) {
            for(int j = 0; j < menuItems.get(i).size(); j++) {
                menuItems.get(i).get(j).addActionListener(lisetenerForBasketball);
            }
        }
        //menuItems.get(0).get(0).addActionListener(lisetenerForBasketball);
        //wnbaMenu.addActionListener(lisetenerForBasketball);
        loginMenuItem.addActionListener(lisetenerForBasketball);
        registerMenuItem.addActionListener(lisetenerForBasketball);
        //mlsMenuItem.addActionListener(lisetenerForBasketball);
	}

    void addLoginMenuListener(MenuListener listenerForLogin) {
		loginMenu.addMenuListener(listenerForLogin);
	}

    public void updatePanel(JPanel newPanel) {
        scrollPane.getViewport().remove(panel);
        scrollPane.setViewportView(newPanel);
    }

    void addSubMenu(JMenu menu) {
        menuItems.add(new ArrayList<JMenuItem>());
        int index = menuItems.size()-1;
        menuItems.get(index).add(new JMenuItem("News"));
        menuItems.get(index).add(new JMenuItem("Standings"));
        menuItems.get(index).add(new JMenuItem("Schedule"));
        menu.add(menuItems.get(index).get(0));
        menu.add(menuItems.get(index).get(1));
        menu.add(menuItems.get(index).get(2));
    }
}
