package News;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

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