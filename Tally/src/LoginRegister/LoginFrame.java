package LoginRegister;

import MainMenu.*;
import javax.swing.*;
import javax.swing.plaf.basic.BasicLookAndFeel;

import com.formdev.flatlaf.themes.FlatMacDarkLaf;

import Database.ConnectDB;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.*;
import java.sql.*;
      
public class LoginFrame extends JFrame implements ActionListener {

	JTextField t1;
	JPasswordField pw;
	JButton b, b2;
    JLabel l1, l2, l3, l4;
	JFrame frame;

	public LoginFrame() {

        BasicLookAndFeel theme = new FlatMacDarkLaf();
        try {
            UIManager.setLookAndFeel(theme);
        } catch (UnsupportedLookAndFeelException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

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

		pw = new JPasswordField();
		pw.setBounds(100,150,200,30);
		
		b = new JButton("Continue");
		b.setBounds(130,200,90,30);
		b.addActionListener(this);

		b2 = new JButton("Register");
		b2.setBounds(130,250,90,30);
		b2.addActionListener(this);

        frame = new JFrame("Tally");
        frame.setLayout(null);
        frame.setBounds(0, 0, 700, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
       
        frame.add(l1);
		frame.add(l2);
		frame.add(l3);
		frame.add(l4);
		frame.add(t1);
		frame.add(pw);
		frame.add(b);
		frame.add(b2);
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
				frame.dispose();
				l2.setText("Login Successful");
				l2.setForeground(Color.GREEN);
                MainView mainView = new MainView();
			}
			else
			{
				l2.setText("Login Failed");
			}
		}

		//If Register button is clicked, go to register page.
		if(e.getSource() == b2) {
			frame.dispose();
			RegisterFrame register = new RegisterFrame();

		}
	}
	
}