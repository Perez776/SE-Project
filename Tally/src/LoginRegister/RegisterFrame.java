

package LoginRegister;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.swing.*;
import javax.swing.plaf.basic.BasicLookAndFeel;

import com.formdev.flatlaf.themes.FlatMacDarkLaf;

import Database.ConnectDB;
import Database.CreateDB;
import MainMenu.MainView;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.*;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.sql.*;
import java.util.Base64;

public class RegisterFrame extends JFrame implements ActionListener {
    JTextField t1;
	JPasswordField pw;
	JButton b1, b2;
    JLabel l1, l2, l3, l4, l5, l6;
	JFrame frame;
	JPanel panel = new JPanel();
	SecretKeyFactory f;
    byte[] hash;

	public RegisterFrame(){
     
		addTheme();

		Font font = new Font(Font.MONOSPACED, Font.BOLD, 25);

		//Labels
        l1 = new JLabel("Register");
		l1.setBounds(100,10,400,100);
		l1.setFont(font);

		l2 = new JLabel("");
		l2.setBounds(770,210,600,30);
		l2.setForeground(Color.RED);
		l2.setFont(font);

		l3 = new JLabel("");
		l3.setBounds(770,210,600,30);
		l3.setForeground(Color.RED);
		l3.setFont(font);

		l4 = new JLabel("");
		l4.setBounds(770,310,600,30);
		l4.setForeground(Color.RED);
		l4.setFont(font);

		l5 = new JLabel("Username:");
        l5.setFont(font);
        l5.setBounds(100,210,400,30);

		l6 = new JLabel("Password:");
		l6.setFont(font);
        l6.setBounds(100,310,400,30);

		//Textfields
        t1 = new JTextField();
		t1.setBounds(300,200,400,70);

		pw = new JPasswordField();
		pw.setBounds(300,300,400,70);
		
		//Buttons
		b1 = new JButton("Register");
		b1.setBounds(300,400,300,70);
		b1.addActionListener(this);

		b2 = new JButton("Cancel");
		b2.setBounds(300,500,300,70);
		b2.addActionListener(this);

		//Add components to frame
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
        frame.add(l6);
        frame.add(l5);
		frame.add(t1);
		frame.add(pw);
		frame.add(b1);
		frame.add(b2);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
        //Get Text Field Inputs
		String username = t1.getText();
		String password = pw.getText();

		//Get hash token to insert into database table
		String token = getHashedToken(password);

		//If Register button is clicked
		if(e.getSource() == b1) {
			
			//Connect to DB and attempt to register user input into the database
			CreateDB s = new CreateDB();
			ConnectDB db = new ConnectDB();
			RegisterModel register = new RegisterModel(username, password, token, db);

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
				frame.dispose();
				LoginFrame login = new LoginFrame();
			}
		}

		//If The Cancel button is clicked, go to the login page.
		if(e.getSource() == b2) {
            frame.dispose();
            LoginFrame login = new LoginFrame();
		}
    }

	public void addTheme() {
		BasicLookAndFeel theme = new FlatMacDarkLaf();
        try {
            UIManager.setLookAndFeel(theme);
        } catch (UnsupportedLookAndFeelException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
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
