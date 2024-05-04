package Standings;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;

import LoginRegister.LoginView;
import LoginRegister.RegisterView;
import MainMenu.MainView;
import Matches.MatchesModel;
import Matches.MatchesView;
import News.NewsView;

public class StandingsController {
    private StandingsView view;

    public StandingsController(StandingsView view) {
        this.view = view;

        this.view.addStandingsListener(new StandingsListener());
    }

    class StandingsListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String year = view.yearsCB.getSelectedItem().toString();

            view.panel.remove(view.scrollPane);
            view.panel.revalidate();
			view.panel.repaint();

            view.model = new StandingsModel(view.leagueName, year);

            view.table = view.model.getStandingsTable();
            view.table.setFillsViewportHeight(true); 
            view.table.setShowGrid(true);
            view.table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            view.table.getColumnModel().getColumn(0).setPreferredWidth(200);
            view.setUpTable(view.table);

            Border blueBorder = BorderFactory.createLineBorder(Color.decode("#007AFF"), 2);
            Border nameBorder = BorderFactory.createTitledBorder(blueBorder, "  League Standings  ");

            //ScrollPane
            view.scrollPane = new JScrollPane(view.table);
            view.scrollPane.setBounds(50, 300, 1200, 700);
            view.scrollPane.getVerticalScrollBar().setUnitIncrement(16);
            view.scrollPane.getHorizontalScrollBar().setUnitIncrement(16);
            view.scrollPane.setBorder(nameBorder);
        
            view.panel.add(view.scrollPane);
            view.panel.revalidate();
			view.panel.repaint();

            view.main.updatePanel(view.panel);
        }
    }
}
