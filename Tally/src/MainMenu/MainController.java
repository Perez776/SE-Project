package MainMenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.prefs.BackingStoreException;

import javax.swing.JPanel;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import Leagues.BaseballLeaguesView;
import Leagues.BasketballLeaguesView;
import Leagues.SoccerLeaguesView;
import LoginRegister.LoginUI;
import LoginRegister.RegisterUI;
import Matches.MatchesView;
import Standings.NBAView;


public class MainController {
    private Main view;

    public MainController(Main view) {
        this.view = view;

        this.view.addBasketballMenuListener(new BasketBallMenuListener());
        this.view.addLoginMenuListener(new LoginMenuListener());
    }

    class BasketBallMenuListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == view.menuItems.get(0).get(0)) 
            {
                BasketballLeaguesView bbLeaguesTest = new BasketballLeaguesView(view);
                JPanel panel = bbLeaguesTest.getPanel();
                view.updatePanel(panel);
            }
            if(e.getSource() == view.menuItems.get(0).get(1)) 
            {
                NBAView nbaView = new NBAView(view);
                JPanel panel = nbaView.getPanel();
                view.updatePanel(panel);
            }
            if(e.getSource() == view.menuItems.get(0).get(2)) 
            {
                MatchesView matchesView = new MatchesView(view, "basketball", "NBA");
                JPanel panel = matchesView.getPanel();
                view.updatePanel(panel);
            }

            if(e.getSource() == view.loginMenuItem) {
                LoginUI loginUI = new LoginUI(view);
                JPanel loginPanel = loginUI.getLoginPanel();
                view.updatePanel(loginPanel);
                System.out.println("Changed to login");
            }
            if(e.getSource() == view.registerMenuItem) {
                RegisterUI registerUI = new RegisterUI(view);
                JPanel panel = registerUI.getPanel();
                view.updatePanel(panel);
                System.out.println("Changed to Register");
            }
            if(e.getSource() == view.mlsMenuItem) 
            {
                SoccerLeaguesView bbLeaguesTest = new SoccerLeaguesView(view);
                JPanel panel = bbLeaguesTest.getPanel();
                view.updatePanel(panel);
            }
            if(e.getSource() == view.nbaScheduleMenuItem) 
            {
                MatchesView matchesView = new MatchesView(view, "", "");
                JPanel panel = matchesView.getPanel();
                view.updatePanel(panel);
            }
            if(e.getSource() == view.nbaStandingsMenuItem) 
            {
                NBAView nbaView = new NBAView(view);
                JPanel panel = nbaView.getPanel();
                view.updatePanel(panel);
            }
            

        
            // TODO Auto-generated method stub
           // throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
        }
    }

    class LoginMenuListener implements MenuListener {

        @Override
        public void menuSelected(MenuEvent e) {
            System.out.println("opened login menu");
            // TODO Auto-generated method stub
            //throw new UnsupportedOperationException("Unimplemented method 'menuSelected'");
        }

        @Override
        public void menuDeselected(MenuEvent e) {
            // TODO Auto-generated method stub
           // throw new UnsupportedOperationException("Unimplemented method 'menuDeselected'");
        }

        @Override
        public void menuCanceled(MenuEvent e) {
            // TODO Auto-generated method stub
            //throw new UnsupportedOperationException("Unimplemented method 'menuCanceled'");
        }
    }
   // class LoginMenuListener implements ActionListener {

}
