import javax.swing.JFrame;
import javax.swing.JLabel;

public class AfterLoginUI {
    JFrame f = new JFrame();
	JLabel l1, l2;

	AfterLoginUI(String username) {
		l1 = new JLabel("Welcome " + username);
        l1.setBounds(200, 0, 200,30);

		l2 = new JLabel("Choose a sport");
        l2.setBounds(100,60,90,30);
		
		f.add(l1);
		f.add(l2);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		f.setSize(1000,1000);
		f.setExtendedState(f.getExtendedState() | JFrame.MAXIMIZED_BOTH);

		f.setLayout(null);
		f.setVisible(true);
		f.setTitle("Tally");
	}
}
