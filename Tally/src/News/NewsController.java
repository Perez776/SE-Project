package News;

import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

import Matches.MatchesView;
import Standings.StandingsView;

public class NewsController {
    NewsView view;
    NewsModel model;

    public NewsController(NewsView view) {
        this.view = view;
        this.model = view.model;

        this.view.addNewsListener(new NewsListener());
        this.view.addChooseInfoTypeListener(new ChooseInfoTypeListener());
    }

    
    class NewsListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

              //Get hash code of the selected button
              int code = e.getSource().hashCode();
              int index = view.hashMap.get(code);
              //Get more news Api
              String [][] news = model.getMoreNews();

            //If the more news is a video, open link
            if(news[index][1] == "video") {
                try {
                    Desktop.getDesktop().browse(new URL(news[index][0]).toURI());
                } catch (MalformedURLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                } catch (URISyntaxException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
            //Else, open a new frame consisting of the news article
            else {
                JFrame f = new JFrame("Tally");

                //Labels for more news frame
                JLabel newsLabel = new JLabel( "<html>"  + news[index][0]  + "<html>"  );
                newsLabel.setBounds(10,200,1400,5000);
                newsLabel.setVerticalAlignment(JLabel.TOP);
                newsLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));

                //get News Image Label
                JLabel imageLabel = getNewsImageLabel(index);
              
                //Add Components to panel
                JPanel panel = new JPanel();
                panel.setLayout(null);
                panel.setPreferredSize( new Dimension( 2000, 5000));
                panel.setMinimumSize( new Dimension( 2000, 5000));
                panel.add(imageLabel);
                panel.add(newsLabel);

                //add panel to the scroll pane fro scrollbar
                JScrollPane scrollPane = new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                scrollPane.getVerticalScrollBar().setUnitIncrement(16);
                scrollPane.getHorizontalScrollBar().setUnitIncrement(16);

                //Add the scrollpane to the new frame
                f.setContentPane(scrollPane);
                f.setBounds(0, 0, 1500, 1000);
		        f.setExtendedState(f.getExtendedState());

                //run the frame
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        f.setVisible(true);
                    }
                });
            }
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

    public JLabel getNewsImageLabel(int index) {
        //Get img url
        URL imgURL;
        try {
            imgURL = new URL(view.imgs[index]);
            view.image = ImageIO.read(imgURL);
        } catch (MalformedURLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        //
        JLabel imageLabel = new JLabel(new ImageIcon(view.image.getScaledInstance(300, 180, Image.SCALE_SMOOTH)));
        imageLabel.setBounds(500, 10, 300, 180);
        imageLabel.setBorder(view.blueBorder);

        return imageLabel;
    }

}