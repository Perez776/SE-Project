package MainMenu;
import Leagues.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class ChooseSportController {
    private ChooseSportView view;
    private ChooseSportModel model;
    Main main;

    public ChooseSportController(ChooseSportView view, ChooseSportModel model, Main main) {
        this.view = view;
        this.model = model;
        this.main = main;

        this.view.addMainMenuListener(new MainMenuListener());
    }

    class MainMenuListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            if(view.list.getSelectedIndex() == 0) {
                //view.f.dispose();
                SoccerLeaguesView soccerLeaguesView = new SoccerLeaguesView(main);
                JPanel panel = soccerLeaguesView.getPanel();
                main.updatePanel(panel);
            }
            if(view.list.getSelectedIndex() == 1) {
                //view.f.dispose();
                FootballLeaguesView fbView = new FootballLeaguesView(main);
                JPanel panel = fbView.getPanel();
                main.updatePanel(panel);
            }  
            if(view.list.getSelectedIndex() == 2) {
                //view.f.dispose();
                BasketballLeaguesView bb = new BasketballLeaguesView(main);
                JPanel panel = bb.getPanel();
                main.updatePanel(panel);
            }
            if(view.list.getSelectedIndex() == 3) {
                //view.f.dispose();
                BaseballLeaguesView baseballLeaguesView = new BaseballLeaguesView(main);
                JPanel panel = baseballLeaguesView.getPanel();
                main.updatePanel(panel);
            }
            // TODO Auto-generated method stub
            //throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
        }
        
    }
}
