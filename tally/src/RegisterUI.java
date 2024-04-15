import javax.swing.*;

import java.awt.Color;
import java.awt.event.*;
import java.sql.*;

public class RegisterUI implements ActionListener {
    JTextField t1, t2;
	JButton b1, b2;
    JLabel l1, l2, l3, l4, l5, l6;
	JFrame f = new JFrame();

	RegisterUI(){
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

		t2 = new JTextField();
		t2.setBounds(100,150,200,30);
		
		//Buttons
		b1 = new JButton("Register");
		b1.setBounds(130,200,90,30);
        b1.addActionListener(this);

		b2 = new JButton("Cancel");
		b2.setBounds(130,250,90,30);
		b2.addActionListener(this);

		//Add Components to frame
		f.add(l1);
		f.add(l2);
		f.add(l3);
		f.add(l4);
		f.add(l5);
		f.add(l6);
		f.add(t1);
		f.add(t2);
		f.add(b1);
		f.add(b2);
		
		//Set up Frame
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setExtendedState(f.getExtendedState() | JFrame.MAXIMIZED_BOTH);
		f.setLayout(null);
		f.setVisible(true);
		f.setTitle("Tally");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
        //Get Text Field Inputs
		String username = t1.getText();
		String password = t2.getText();
		System.out.printf("The entries are: %s %s \n", username, password);

		//If b1 Register button is clicked
		if(e.getSource() == b1) {
			//Connect to DB and attempt to register user input into the database
			ConnectDB db = new ConnectDB();
			Register register = new Register(username, password, db);

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
				MainMenuView afterLoginUI = new MainMenuView(username);
			}
		}

		//If The Cancel button is clicked, go to the login page.
		if(e.getSource() == b2) {
			f.dispose();
            LoginUI login = new LoginUI();
		}
    }
}
