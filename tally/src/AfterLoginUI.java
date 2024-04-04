import javax.swing.JFrame;
import javax.swing.JLabel;

public class AfterLoginUI {
    JFrame f = new JFrame();
	JLabel label = new JLabel("Welcome");

	AfterLoginUI() {
		label.setBounds(0,0,100,50);
		
		f.add(label);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(400,400);
		f.setLayout(null);
		f.setVisible(true);
		f.setTitle("Tally");
	}
}
