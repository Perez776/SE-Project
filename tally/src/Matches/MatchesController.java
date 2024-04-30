package Matches;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import Leagues.BasketballLeaguesView;
import Leagues.SoccerLeaguesView;
import LoginRegister.LoginUI;
import LoginRegister.RegisterUI;
import MainMenu.Main;
import Standings.NBAView;

public class MatchesController {
    private MatchesView view;
    private MatchesModel model;
    Main main;

    public MatchesController(MatchesView view, MatchesModel model, Main main) {
        this.view = view;
        this.model = model;
        this.main = main;
        this.view.addMatchSelectionListener(new MatchSelectionListener());
    }

    class MatchSelectionListener implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent e) {
            String [] matchStats = model.getMatchStats();
            view.stats.setText("");

            for(int i = 0; i < matchStats.length; i++)
            {
                if(view.list.getSelectedIndex() == i)
                {
                    view.stats = new JLabel(matchStats[i]);
                    view.stats.setBounds(1000, 300, 500, 500);
                    view.panel.add(view.stats);
                    main.updatePanel(view.panel);
                }
            }
            
            // TODO Auto-generated method stub
            //throw new UnsupportedOperationException("Unimplemented method 'valueChanged'");
        }
    }

}