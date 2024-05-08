package MainMenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.JPanel;
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

            //If login menu is clicked
            if(e.getSource() == view.loginMenuItem) {
                LoginView newsView = new LoginView(view);
                JPanel panel = newsView.getLoginPanel();
                view.updatePanel(panel);
            }
            
            //If register menu is clicked
            if(e.getSource() == view.registerMenuItem) {
                RegisterView newsView = new RegisterView(view);
                JPanel panel = newsView.getPanel();
                view.updatePanel(panel);
            }
            else 
            {
                //Get menu item hashcode for fast access
                String [] infoArr = view.subMenuMap.get(e.getSource().hashCode());
                String leagueName = infoArr[0];
                String infoType = infoArr[1];

                //If schedule menu is clicked
                if(infoType == "Schedule") {
                    MatchesView matchesView = new MatchesView(view, "basketball", leagueName);//,String.valueOf(thisYear), thisMonthStr);
                    JPanel panel = matchesView.getPanel();
                    view.updatePanel(panel);
                }
                
                //If News menu is clicked
                if(infoType == "News") {
                    NewsView newsView = new NewsView(view, leagueName);
                    JPanel panel = newsView.getPanel();
                    view.updatePanel(panel);
                }
                
                //If standings menu is clicked
                if(infoType == "Standings") {
                    StandingsView newsView = new StandingsView(view, leagueName);
                    JPanel panel = newsView.getPanel();
                    view.updatePanel(panel);
                }
            }
        }
    }
}
