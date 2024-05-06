package MainMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class ChooseSportController {
    private ChooseSportView view;
    private ChooseSportModel model;
    MainView main;

    public ChooseSportController(ChooseSportView view, ChooseSportModel model, MainView main) {
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
            }
            if(view.list.getSelectedIndex() == 1) {
                //view.f.dispose();
            }  
            if(view.list.getSelectedIndex() == 2) {
                //view.f.dispose();
            }
            if(view.list.getSelectedIndex() == 3) {
                //view.f.dispose();
            }
            // TODO Auto-generated method stub
            //throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
        }
        
    }
}
