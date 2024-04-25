package MainMenu;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import LoginRegister.*;

//eslint-disable-next-line
public class MainMenuView extends JFrame implements ActionListener{
	
    JFrame f = new JFrame();
	JList list;
	JButton b;
	JLabel l1, l2;

	public MainMenuView(String username) {

		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(1000,1000);
		f.setExtendedState(f.getExtendedState() | JFrame.MAXIMIZED_BOTH);
		f.setLayout(null);
		f.setVisible(true);
		f.setTitle("Tally");

		l1 = new JLabel("Welcome " + username);
        l1.setBounds(200, 0, 200,30);

		l2 = new JLabel("Choose a sport");
        l2.setBounds(100,60,90,30);

		String l[] = {"Soccer", "Football", "Basketball", "Baseball"};

		list = new JList(l);
		list.setBackground(Color.YELLOW);
		list.setBounds(100, 100, 200,70);

		b = new JButton("Continue");
		b.setBounds(330,100,90,30);

		f.add(l1);
		f.add(l2);
		f.add(list);
		f.add(b);

		//Button Listener
		MainMenuModel m = new MainMenuModel();
		MainMenuController MMMC = new MainMenuController(this, m);
		//b.createActionListener(MMMC);
	}

	void addMainMenuListener(ActionListener listenerForSportButton) {
		b.addActionListener(listenerForSportButton);
	}
	
/* 
	@Override
	public void actionPerformed(ActionEvent e) {
		if(list.getSelectedIndex() == 0) {
			f.dispose();
            LoginUI login = new LoginUI();
		}
	}	
	*/
}
