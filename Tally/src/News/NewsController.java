package News;

import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class NewsController {
    NewsView view;
    NewsModel model;

    public NewsController(NewsView view) {
        this.view = view;
        this.model = view.model;

        this.view.addNewsListener(new NewsListener());
    }

    
    class NewsListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

            int code = e.getSource().hashCode();
            int index = view.hashMap.get(code);

            System.out.println(index);

            String [][] news = model.getMoreNews();
		    System.out.println();

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

                //System.out.println(news[index][0]);
            }
            else {
               // System.out.println( news[index][0] );

                JFrame f;

                JLabel l = new JLabel( "<html>"  + news[index][0]  + "<html>"  ,  SwingConstants.CENTER);

                l.setBounds(10,10,1000,1200);
                l.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
            

                f = new JFrame("Tally");
                f.add(l);

                f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                f.setBounds(0, 0, 1500, 1200);
		        f.setExtendedState(f.getExtendedState());
                f.setLayout(null);
                f.setVisible(true);
            }
            
            if(e.getSource() == view.linkButton)
            {
                try {
                    Desktop.getDesktop().browse(new URL("https://www.google.com/").toURI());
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
        }
    }

}