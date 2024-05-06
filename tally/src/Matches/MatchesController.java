package Matches;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.*;
import javax.swing.border.Border;

import LoginRegister.LoginView;
import LoginRegister.RegisterView;
import MainMenu.MainView;
import News.NewsView;
import Standings.StandingsView;

public class MatchesController {
    private MatchesView view;
    private MatchesModel model;
    private MainView main;
    MatchSelectionListener matchSelectionListener;

    public MatchesController(MatchesView view, MatchesModel model, MainView main) {
        this.matchSelectionListener = new MatchSelectionListener();
        this.view = view;
        this.model = model;
        this.main = main;
        this.view.addMatchSelectionListener(this.matchSelectionListener);
        this.view.addMatchesListener(new MatchesListener());
        this.view.addChooseInfoTypeListener(new ChooseInfoTypeListener());
    }

    class MatchSelectionListener implements ListSelectionListener {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            view.stats.setText("");

            int selectedIndex = view.list.getSelectedIndex();

            view.panel.remove(view.stats);
            view.panel.revalidate();
			view.panel.repaint();

            String matchStats = view.model.getMatchStats(selectedIndex);
            view.stats.setText("Click on the Match to View Stats");
            
            view.stats.setBounds(700, 300, 500, 600);
            view.stats.setText(matchStats);
            view.panel.add(view.stats);
            main.updatePanel(view.panel);
        }
    }

    class MatchesListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String year = view.yearsCB.getSelectedItem().toString();
            String monthStr = view.monthsCB.getSelectedItem().toString();
            String monthNum = view.monthMap.get(monthStr);

            String date = year + monthNum;

            view.panel.remove(view.j);
            view.panel.revalidate();
			view.panel.repaint();

            view.model = new MatchesModel(view.sportName, view.leagueName, date);
            view.list = new JList<>(view.model.getMatchInfo());
            view.list.setBounds(100, 300, 500,11000);

            view.p = new JPanel(new FlowLayout(FlowLayout.LEFT));
            view.p.setBounds(200, 500, 1900, 50000);
            view.p.add(view.list);

            Border blueBorder = BorderFactory.createLineBorder(Color.decode("#007AFF"), 2);
            Border nameBorder = BorderFactory.createTitledBorder(blueBorder, "  Game Schedule  ");

            //ScrollPane
            view.j = new JScrollPane(view.p);
            view.j.setBounds(100, 300, 500, 600);
            view.j.getVerticalScrollBar().setUnitIncrement(16);
            view.j.getHorizontalScrollBar().setUnitIncrement(16);
            view.j.setBorder(nameBorder);
        
            view.panel.add(view.j);
            view.panel.revalidate();
			view.panel.repaint();

            main.updatePanel(view.panel);

            view.list.addListSelectionListener(matchSelectionListener);
        }
    }

    class ChooseInfoTypeListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if(view.chooseInfo.getSelectedIndex() == 0) {
                NewsView newsView = new NewsView(view.main, view.leagueName);
                JPanel panel = newsView.getPanel();
    
                view.main.updatePanel(panel);
            }
            if(view.chooseInfo.getSelectedIndex() == 1) {
                MatchesView matchesView = new MatchesView(view.main, "", view.leagueName);
                JPanel panel = matchesView.getPanel();
    
                view.main.updatePanel(panel);
            }
            if(view.chooseInfo.getSelectedIndex() == 2) {
                StandingsView standingsView = new StandingsView(view.main, view.leagueName);
                JPanel panel = standingsView.getPanel();
    
                view.main.updatePanel(panel);
            }
        }
    }
}
