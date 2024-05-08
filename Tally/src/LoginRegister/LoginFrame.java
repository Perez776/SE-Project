package LoginRegister;

import MainMenu.*;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.swing.*;
import javax.swing.plaf.basic.BasicLookAndFeel;

import com.formdev.flatlaf.themes.FlatMacDarkLaf;

import Database.ConnectDB;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import java.nio.charset.StandardCharsets;
import java.security.DigestException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;
      
public class LoginFrame extends JFrame implements ActionListener {

	JTextField t1;
	JPasswordField pw;
	JButton b, b2;
    JLabel l1, l2, l3, l4;
	JFrame frame;
	SecretKeyFactory f;
    byte[] hash;

	public LoginFrame() {

		addTheme();

		Font font = new Font(Font.MONOSPACED, Font.BOLD, 25);

        l1 = new JLabel("Login");
        l1.setBounds(100,10,400,100);
		l1.setFont(font);

		l2 = new JLabel("");
        l2.setBounds(770,210,400,30);
		l2.setForeground(Color.RED);
		l2.setFont(font);

		l3 = new JLabel("Username:");
		l3.setFont(font);
        l3.setBounds(100,210,400,30);
		//l3.setForeground(Color.BLUE);

		l4 = new JLabel("Password:");
		l4.setFont(font);
        l4.setBounds(100,310,400,30);
		//l4.setForeground(Color.BLUE);

        t1 = new JTextField();
        t1.setBounds(300,200,400,70);

		pw = new JPasswordField();
		pw.setBounds(300,300,400,70);
		
		b = new JButton("Continue");
		b.setBounds(300,400,300,70);
		b.addActionListener(this);

		b2 = new JButton("Register");
		b2.setBounds(300,500,300,70);
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

		PasswordAuthentication authentication = new PasswordAuthentication();

		String token = authentication.hash(password);

		//System.out.println(token);

		//System.out.println(authentication.authenticate("7777", token));
	
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

	public void addTheme() {
        BasicLookAndFeel theme = new FlatMacDarkLaf();
        try {
            UIManager.setLookAndFeel(theme);
        } catch (UnsupportedLookAndFeelException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	}	

	String getHashedPassword(String pass) {
		SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        KeySpec spec = new PBEKeySpec(pass.toCharArray(), salt, 65536, 128);
        try {
            f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            hash = f.generateSecret(spec).getEncoded();
        } catch (InvalidKeySpecException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Base64.Encoder enc = Base64.getEncoder();

		System.out.printf("salt: %s%n", enc.encodeToString(salt));	
		System.out.printf("hash: %s%n", enc.encodeToString(hash));

        String hashedPassword = enc.encodeToString(hash);

		return hashedPassword;
	}
}
