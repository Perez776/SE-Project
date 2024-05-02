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
import News.NewsView;
import Standings.NBAView;
import Standings.StandingsView;


public class MainController {
    private MainView view;

    public MainController(MainView view) {
        this.view = view;

        this.view.addBasketballMenuListener(new BasketBallMenuListener());
        this.view.addLoginMenuListener(new LoginMenuListener());
    }

    class BasketBallMenuListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            if(e.getSource() == view.loginMenuItem) {
                LoginUI newsView = new LoginUI(view);
                JPanel panel = newsView.getLoginPanel();
                view.updatePanel(panel);
            }
            if(e.getSource() == view.registerMenuItem) {
                RegisterUI newsView = new RegisterUI(view);
                JPanel panel = newsView.getPanel();
                view.updatePanel(panel);
            }
            else 
            {
                String [] infoArr = view.subMenuMap.get(e.getSource().hashCode());
                String leagueName = infoArr[0];
                String infoType = infoArr[1];
    
                if(infoType == "Schedule") {
                    MatchesView matchesView = new MatchesView(view, "basketball", leagueName);
                    JPanel panel = matchesView.getPanel();
                    view.updatePanel(panel);
                }
                if(infoType == "News") {
                    NewsView newsView = new NewsView(view, leagueName);
                    JPanel panel = newsView.getPanel();
                    view.updatePanel(panel);
                }
                if(infoType == "Standings") {
                    StandingsView newsView = new StandingsView(view, leagueName);
                    JPanel panel = newsView.getPanel();
                    view.updatePanel(panel);
                }
               
                if(e.getSource() == view.loginMenuItem) {
                    LoginUI newsView = new LoginUI(view);
                    JPanel panel = newsView.getLoginPanel();
                    view.updatePanel(panel);
                }
            }
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
