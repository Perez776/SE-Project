package MainMenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.prefs.BackingStoreException;

import javax.swing.JMenu;
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

            System.out.println(e.getSource().hashCode());

            for(int i = 0; i < view.menuItems.size(); i++) {
                if(view.menuItems.get(i).get(2).hashCode() == e.getSource().hashCode())
                {
                    int hashCode = e.getSource().hashCode();
                    System.out.println(hashCode);
                    String sport = view.sportSubMenus.get(e.getSource().hashCode());
                    System.out.println(sport);
                }
            }

            /* 
            if(e.getSource() == view.menuItems.get(0).get(2)) 
            {
                MatchesView matchesView = new MatchesView(view, "", "NBA");
                JPanel panel = matchesView.getPanel();
                view.updatePanel(panel);
            }

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

            if(e.getSource() == view.menuItems.get(1).get(2)) 
            {
                MatchesView matchesView = new MatchesView(view, "basketball", "WNBA");
                JPanel panel = matchesView.getPanel();
                view.updatePanel(panel);
            }

            
            if(e.getSource() == view.menuItems.get(2).get(2)) 
            {
                MatchesView matchesView = new MatchesView(view, "basketball", "NCAA Men's Basketball");
                JPanel panel = matchesView.getPanel();
                view.updatePanel(panel);
            }

            if(e.getSource() == view.menuItems.get(3).get(2)) 
            {
                MatchesView matchesView = new MatchesView(view, "basketball", "NCAA Women's Basketball");
                JPanel panel = matchesView.getPanel();
                view.updatePanel(panel);
            }
            
            if(e.getSource() == view.menuItems.get(4).get(2)) 
            {
                MatchesView matchesView = new MatchesView(view, "soccer", "EUFA Champions League");
                JPanel panel = matchesView.getPanel();
                view.updatePanel(panel);
            }

            if(e.getSource() == view.menuItems.get(5).get(2)) 
            {
                MatchesView matchesView = new MatchesView(view, "soccer", "MLS");
                JPanel panel = matchesView.getPanel();
                view.updatePanel(panel);
            }

            if(e.getSource() == view.menuItems.get(6).get(2)) 
            {
                MatchesView matchesView = new MatchesView(view, "football", "English Premier League");
                JPanel panel = matchesView.getPanel();
                view.updatePanel(panel);
            }

            if(e.getSource() == view.menuItems.get(7).get(2)) 
            {
                MatchesView matchesView = new MatchesView(view, "football", "NCAA Football");
                JPanel panel = matchesView.getPanel();
                view.updatePanel(panel);
            }

            if(e.getSource() == view.menuItems.get(8).get(2)) 
            {
                MatchesView matchesView = new MatchesView(view, "baseball", "MLB");
                JPanel panel = matchesView.getPanel();
                view.updatePanel(panel);
            }
            if(e.getSource() == view.menuItems.get(9).get(2)) 
            {
                MatchesView matchesView = new MatchesView(view, "baseball", "NCAA Baseball");
                JPanel panel = matchesView.getPanel();
                view.updatePanel(panel);
            }

            if(e.getSource() == view.loginMenuItem) {
                LoginUI loginUI = new LoginUI(view);
                JPanel loginPanel = loginUI.getLoginPanel();
                view.updatePanel(loginPanel);
            }
            if(e.getSource() == view.registerMenuItem) {
                RegisterUI registerUI = new RegisterUI(view);
                JPanel panel = registerUI.getPanel();
                view.updatePanel(panel);
            }
            /* 
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
*/
             
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
