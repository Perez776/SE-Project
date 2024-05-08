package LoginRegister;
import MainMenu.*;
import javax.swing.*;

import Database.ConnectDB;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.*;
import java.sql.*;
      
public class LoginView implements ActionListener {

	JTextField t1;
	JPasswordField pw;
	JButton b, b2;
    JLabel l1, l2, l3, l4;
	//JFrame f1= new JFrame();
	JPanel panel = new JPanel();

	MainView main;
	
	public LoginView(MainView main) {
		this.main = main;

		//Labels
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

		//Text Fields
        t1 = new JTextField();
        t1.setBounds(100,100,200,30);

		pw = new JPasswordField();
		pw.setBounds(100,150,200,30);
		
		//Buttons
		b = new JButton("Continue");
		b.setBounds(130,200,90,30);
		b.addActionListener(this);

		b2 = new JButton("Register");
		b2.setBounds(130,250,90,30);
		b2.addActionListener(this);

		panel.setLayout(null);
        panel.setPreferredSize( new Dimension( 2000, 2000));
        panel.setMinimumSize( new Dimension( 2000, 2000));
		panel.add(l1);
		panel.add(l2);
		panel.add(l3);
		panel.add(l4);
		panel.add(t1);
		panel.add(pw);
		panel.add(b);
		panel.add(b2);
	}

	public JPanel getLoginPanel () {
		return this.panel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		String username = t1.getText();
		String password = pw.getText();

		System.out.printf("The entries are: %s %s \n", username, password);
	
		//Connect to database and check if login is valid.
		ConnectDB db = new ConnectDB();
		LoginModel login = new LoginModel(username,password, db);

		//If Login Button is clicked.
		if(e.getSource() == b) {
			//Check if login is valid
			login.checkLogin();

			//If login was valid, go to next page.
			if(login.validUser() == true)
			{
				//f1.dispose();
				l2.setText("Login Successful");
				//l2.setForeground(Color.GREEN);
			}
			else
			{
				l2.setText("Login Failed");
			}
		}

		//If Register button is clicked, go to register page.
		if(e.getSource() == b2) {
			//f1.dispose();
			RegisterView register = new RegisterView(main);
			panel = register.getPanel();
			main.updatePanel(panel);
		}
	}
	
}
