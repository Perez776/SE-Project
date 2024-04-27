package MainMenu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import LoginRegister.*;


public class ChooseSportView extends JFrame {
	
	JList list;
	JButton b;
	JLabel l2;
	Main main;
	JPanel panel = new JPanel();

	public ChooseSportView(Main main) {
		this.main = main;

		l2 = new JLabel("Choose a sport");
        l2.setBounds(100,60,90,30);

		String l[] = {"Soccer", "Football", "Basketball", "Baseball"};

		list = new JList(l);
		list.setBackground(Color.YELLOW);
		list.setBounds(100, 100, 200,70);

		b = new JButton("Continue");
		b.setBounds(330,100,90,30);
		
		panel.setLayout(null);
        panel.setPreferredSize( new Dimension( 2000, 2000));
        panel.setMinimumSize( new Dimension( 2000, 2000));
		panel.add(l2);
		panel.add(list);
		panel.add(b);

		//Button Listener
		ChooseSportModel m = new ChooseSportModel();
		ChooseSportController MMMC = new ChooseSportController(this, m, main);
		//b.createActionListener(MMMC);
	}

	public JPanel getPanel() {
		return panel;
	}

	void addMainMenuListener(ActionListener listenerForSportButton) {
		b.addActionListener(listenerForSportButton);
	}
}
