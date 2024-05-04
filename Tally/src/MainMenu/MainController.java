package MainMenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.prefs.BackingStoreException;

import javax.swing.JPanel;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import LoginRegister.LoginView;
import LoginRegister.RegisterView;
import Matches.MatchesView;
import News.NewsView;
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
            
            Calendar today = Calendar.getInstance();
            today.set(Calendar.HOUR_OF_DAY, 0);
            //get current year
            int thisYear = today.getWeekYear();
            //get month
            String thisMonthStr = new SimpleDateFormat("MMM").format(today.getTime());

            if(e.getSource() == view.loginMenuItem) {
                LoginView newsView = new LoginView(view);
                JPanel panel = newsView.getLoginPanel();
                view.updatePanel(panel);
            }
            if(e.getSource() == view.registerMenuItem) {
                RegisterView newsView = new RegisterView(view);
                JPanel panel = newsView.getPanel();
                view.updatePanel(panel);
            }
            else 
            {
                String [] infoArr = view.subMenuMap.get(e.getSource().hashCode());
                String leagueName = infoArr[0];
                String infoType = infoArr[1];
    
                if(infoType == "Schedule") {
                    MatchesView matchesView = new MatchesView(view, "basketball", leagueName);//,String.valueOf(thisYear), thisMonthStr);
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
                    LoginView newsView = new LoginView(view);
                    JPanel panel = newsView.getLoginPanel();
                    view.updatePanel(panel);
                }
            }
        }
    }

    class LoginMenuListener implements MenuListener {

        @Override
        public void menuSelected(MenuEvent e) {
            //System.out.println("opened login menu");
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
