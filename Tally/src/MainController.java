
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.prefs.BackingStoreException;

import javax.swing.JPanel;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import Leagues.BaseballLeaguesView;
import Leagues.BasketballLeaguesView;
import LoginRegister.LoginUI;
import LoginRegister.RegisterUI;


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
            if(e.getSource() == view.loginMenuItem) {
                LoginUI loginUI = new LoginUI();
                JPanel loginPanel = loginUI.getLoginPanel();
                view.scrollPane.getViewport().remove(view.panel);
                view.scrollPane.setViewportView(loginPanel);
                System.out.println("Changed to login");
            }
            if(e.getSource() == view.registerMenuItem) {
                RegisterUI registerUI = new RegisterUI();
                JPanel panel = registerUI.getPanel();
                view.scrollPane.getViewport().remove(view.panel);
                view.scrollPane.setViewportView(panel);
                System.out.println("Changed to Register");
            }
            
            if(e.getSource() == view.nbaNewsMenuItem) 
            {
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
