import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuController {
    private MainMenuView view;
    private MainMenuModel model;

    public MainMenuController(MainMenuView view, MainMenuModel model) {
        this.view = view;
        this.model = model;

        this.view.addMainMenuListener(new MainMenuListener());
    }

    class MainMenuListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            if(view.list.getSelectedIndex() == 0) {
                view.f.dispose();
                LoginUI login = new LoginUI();
            }
            if(view.list.getSelectedIndex() == 1) {
                view.f.dispose();
                LoginUI login = new LoginUI();
            }  
            if(view.list.getSelectedIndex() == 2) {
                view.f.dispose();
                LoginUI login = new LoginUI();
            }
            if(view.list.getSelectedIndex() == 3) {
                view.f.dispose();
                RegisterUI login = new RegisterUI();
            }
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
        }
        
    }
}
