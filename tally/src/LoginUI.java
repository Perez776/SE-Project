import javax.swing.*;
import java.awt.event.*;


public class LoginUI implements ActionListener {
	
	JTextField t1,t2;
	JButton b, b2;
    JLabel a1;
	JFrame f1= new JFrame();
	
	LoginUI(){
        a1 = new JLabel("Login");
        a1.setBounds(100,30,90,30);

        t1 = new JTextField();
        t1.setBounds(100,100,90,30);

		t2 = new JTextField();
		t2.setBounds(100,150,90,30);
		
		b = new JButton("Continue");
		b.setBounds(130,200,90,30);
		b.addActionListener(this);

		b2 = new JButton("Register");
		b2.setBounds(130,250,90,30);
		b2.addActionListener(this);

		f1.add(t1);
		f1.add(t2);
        f1.add(a1);
		f1.add(b);
		f1.add(b2);
		
		f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f1.setSize(1000,1000);
		f1.setLayout(null);
		f1.setVisible(true);
		f1.setTitle("Tally");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		String s1 = t1.getText();
		String s2 = t2.getText();
		
		//int a = Integer.parseInt(s1);
		//int b = Integer.parseInt(s2);
		
		System.out.printf("The entries are: %s %s \n", s1, s2);

		Authenticate authenticate = new Authenticate(s1,s2);

		if(e.getSource() == b) {

			authenticate.checkLogin();

			if(authenticate.validUser() == true)
			{
				f1.dispose();
				AfterLoginUI w = new AfterLoginUI();
			}
		}
	}
	
}
