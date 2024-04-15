import javax.swing.*;
import java.awt.Color;
import java.awt.event.*;
import java.sql.*;
           
public class LoginUI implements ActionListener {

	JTextField t1,t2;
	JButton b, b2;
    JLabel l1, l2, l3, l4;
	JFrame f1= new JFrame();
	
	LoginUI() {
        l1 = new JLabel("Login");
        l1.setBounds(100,30,90,30);

		l2 = new JLabel("");
        l2.setBounds(325,100,400,30);
		l2.setForeground(Color.RED);

		l3 = new JLabel("Username:");
        l3.setBounds(25,100,400,30);
		l3.setForeground(Color.BLUE);

		l4 = new JLabel("Password:");
        l4.setBounds(25,150,400,30);
		l4.setForeground(Color.BLUE);

        t1 = new JTextField();
        t1.setBounds(100,100,200,30);

		t2 = new JTextField();
		t2.setBounds(100,150,200,30);
		
		b = new JButton("Continue");
		b.setBounds(130,200,90,30);
		b.addActionListener(this);

		b2 = new JButton("Register");
		b2.setBounds(130,250,90,30);
		b2.addActionListener(this);

		f1.add(l1);
		f1.add(l2);
		f1.add(l3);
		f1.add(l4);
		f1.add(t1);
		f1.add(t2);
		f1.add(b);
		f1.add(b2);
		
		f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f1.setExtendedState(f1.getExtendedState() | JFrame.MAXIMIZED_BOTH);
		f1.setLayout(null);
		f1.setVisible(true);
		f1.setTitle("Tally");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		String username = t1.getText();
		String password = t2.getText();
		System.out.printf("The entries are: %s %s \n", username, password);
		//int a = Integer.parseInt(s1);
		//int b = Integer.parseInt(s2);

		//Connect to database and check if login is valid.
		ConnectDB db = new ConnectDB();
		Login login = new Login(username,password, db);

		//If Login Button is clicked.
		if(e.getSource() == b) {
			//Check if login is valid
			login.checkLogin();

			//If login was valid, go to next page.
			if(login.validUser() == true)
			{
				f1.dispose();
				MainMenuView afterLoginUI = new MainMenuView(username);
			}
			else
			{
				l2.setText("Login Failed");
			}
		}

		//If Register button is clicked, go to register page.
		if(e.getSource() == b2) {
			f1.dispose();
			RegisterUI register = new RegisterUI();
		}
	}
	
}
