package Standings;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;
import Matches.MatchesView;
import News.NewsView;

public class StandingsController {
    private StandingsView view;

    public StandingsController(StandingsView view) {
        this.view = view;

        this.view.addStandingsListener(new StandingsListener());
        this.view.addChooseInfoTypeListener(new ChooseInfoTypeListener());
    }

    class StandingsListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            //Get selected year from combo box
            String year = view.yearsCB.getSelectedItem().toString();

            //remove the panel
            view.panel.remove(view.scrollPane);
            view.panel.revalidate();
			view.panel.repaint();

            //Get new info from updated model
            view.model = new StandingsModel(view.leagueName, year);

            //Create new table with new info
            view.table = view.model.getStandingsTable();
            view.table.setFillsViewportHeight(true); 
            view.table.setShowGrid(true);
            view.table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            view.table.getColumnModel().getColumn(0).setPreferredWidth(200);
            view.setUpTable(view.table);

            //Borders
            Border blueBorder = BorderFactory.createLineBorder(Color.decode("#007AFF"), 2);
            Border nameBorder = BorderFactory.createTitledBorder(blueBorder, "  League Standings  ");

            //ScrollPane
            view.scrollPane = new JScrollPane(view.table);
            view.scrollPane.setBounds(50, 270, 1400, 600);
            view.scrollPane.getVerticalScrollBar().setUnitIncrement(16);
            view.scrollPane.getHorizontalScrollBar().setUnitIncrement(16);
            view.scrollPane.setBorder(nameBorder);
        
            //add scrollbar via scrollpane
            view.panel.add(view.scrollPane);
            view.panel.revalidate();
			view.panel.repaint();

            //Update the main panel
            view.main.updatePanel(view.panel);
        }
    }

    class ChooseInfoTypeListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if(view.chooseInfoTypeCB.getSelectedIndex() == 0) {
                NewsView newsView = new NewsView(view.main, view.leagueName);
                JPanel panel = newsView.getPanel();
    
                view.main.updatePanel(panel);
            }
            if(view.chooseInfoTypeCB.getSelectedIndex() == 1) {
                MatchesView matchesView = new MatchesView(view.main, "", view.leagueName);
                JPanel panel = matchesView.getPanel();
    
                view.main.updatePanel(panel);
            }
            if(view.chooseInfoTypeCB.getSelectedIndex() == 2) {
                StandingsView standingsView = new StandingsView(view.main, view.leagueName);
                JPanel panel = standingsView.getPanel();
    
                view.main.updatePanel(panel);
            }
        }
        
    }
}
