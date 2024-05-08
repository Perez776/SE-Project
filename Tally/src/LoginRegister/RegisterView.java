package LoginRegister;

import javax.swing.*;
import Database.ConnectDB;
import Database.CreateDB;
import MainMenu.MainView;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.*;

public class RegisterView implements ActionListener {
    JTextField t1;
	JPasswordField pw;
	JButton b1, b2;
    JLabel l1, l2, l3, l4, l5, l6;
	JFrame f = new JFrame();
	JPanel panel = new JPanel();
	MainView main;

	public RegisterView(MainView main){
		this.main = main;

		//Labels
        l1 = new JLabel("Register");
        l1.setBounds(100,30,90,30);

		l2 = new JLabel("");
        l2.setBounds(325,100,400,30);
		l2.setForeground(Color.RED);

		l3 = new JLabel("");
        l3.setBounds(325,100,400,30);
		l3.setForeground(Color.RED);

		l4 = new JLabel("");
        l4.setBounds(325,150,400,30);
		l4.setForeground(Color.RED);

		l5 = new JLabel("Username:");
        l5.setBounds(25,100,400,30);
		l5.setForeground(Color.BLUE);

		l6 = new JLabel("Password:");
        l6.setBounds(25,150,400,30);
		l6.setForeground(Color.BLUE);

		//Textfields
        t1 = new JTextField();
        t1.setBounds(100,100,200,30);

		pw = new JPasswordField();
		pw.setBounds(100,150,200,30);
		
		//Buttons
		b1 = new JButton("Register");
		b1.setBounds(130,200,90,30);
        b1.addActionListener(this);

		b2 = new JButton("Cancel");
		b2.setBounds(130,250,90,30);
		b2.addActionListener(this);

		panel.setLayout(null);
        panel.setPreferredSize( new Dimension( 2000, 2000));
        panel.setMinimumSize( new Dimension( 2000, 2000));
		panel.add(l1);
		panel.add(l2);
		panel.add(l3);
		panel.add(l4);
		panel.add(l5);
		panel.add(l6);
		panel.add(t1);
		panel.add(pw);
		panel.add(b1);
		panel.add(b2);
	}

	public JPanel getPanel () {
		return this.panel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
        //Get Text Field Inputs
		String username = t1.getText();
		String password = pw.getText();

		String hashedToken = getHashedToken(password);

		//If b1 Register button is clicked
		if(e.getSource() == b1) {
			//Connect to DB and attempt to register user input into the database
			CreateDB s = new CreateDB();
			ConnectDB db = new ConnectDB();

			RegisterModel register = new RegisterModel(username, password, hashedToken, db);


			//Default to true
			Boolean registerSuccessful = true;
			
			//If Username is taken
			if(register.usernameTaken() == true ) {
				l2.setText("Username Taken. Choose Another.");
				registerSuccessful = false;
			}
			else {
				l2.setText("");
			}
			//If Username is invalid
			if(register.validUsername() == false ) {
				l2.setText("");
				l3.setText("Username Must Be At Least 3 Characters.");
				registerSuccessful = false;
			}
			else {
				l3.setText("");
			}
			//If Password is invalid
			if(register.validPassword() == false ) {
				l4.setText("Password Must Be At Least 5 Characters");
				registerSuccessful = false;
			}
			else {
				l4.setText("");
			}

			//If all test cases passed and successfully registered into the DB, go to new page.
			if(registerSuccessful == true) {
				f.dispose();
				LoginView login = new LoginView(main);
				panel = login.getLoginPanel();
				main.updatePanel(panel);
			}
		}

		//If The Cancel button is clicked, go to the login page.
		if(e.getSource() == b2) {
            LoginView login = new LoginView(main);
			panel = login.getLoginPanel();
			main.updatePanel(panel);
		}
    }

	String getHashedToken(String password) {

		PasswordAuthentication authentication = new PasswordAuthentication();

		String token = authentication.hash(password);

		System.out.println(token);

		System.out.println(authentication.authenticate("7777", token));

		return token;
	}
}
