package MainMenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePageController {
    private HomePageView view;
    private HomePageModel model;

    public HomePageController(HomePageView view, HomePageModel model, String leagueName) {
        this.view = view;
        this.model = model;
        this.view.addHomePageListener(new HomePageListener());
    } 

    class HomePageListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            //model.test();
        }
    }
}