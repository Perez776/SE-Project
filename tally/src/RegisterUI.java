import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class RegisterUI implements ActionListener {
    JTextField t1,t2;
	JButton b1, b2;
    JLabel l1;
	JFrame f = new JFrame();

	RegisterUI(){
        l1 = new JLabel("Register");
        l1.setBounds(100,30,90,30);

        t1 = new JTextField();
        t1.setBounds(100,100,90,30);

		t2 = new JTextField();
		t2.setBounds(100,150,90,30);
		
		b1 = new JButton("Register");
		b1.setBounds(130,200,90,30);
        b1.addActionListener(this);

		b2 = new JButton("Cancel");
		b2.setBounds(130,250,90,30);
		b2.addActionListener(this);

		f.add(t1);
		f.add(t2);
        f.add(l1);
		f.add(b1);
		f.add(b2);
		
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setExtendedState(f.getExtendedState() | JFrame.MAXIMIZED_BOTH);
		f.setLayout(null);
		f.setVisible(true);
		f.setTitle("Tally");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
        
		String username = t1.getText();
		String password = t2.getText();
		
		//int a = Integer.parseInt(s1);
		//int b = Integer.parseInt(s2);
		
		System.out.printf("The entries are: %s %s \n", username, password);

		if(e.getSource() == b1) {
			ConnectDB db = new ConnectDB();
			Register register = new Register(username, password, db);

			Boolean registerSuccessful = true;

			if(register.usernameTaken() == true )
			{
				registerSuccessful = false;
			}
			if(register.validUsername() == false && registerSuccessful == true) 
			{
				registerSuccessful = false;
			}
			if(register.validPassword() == false && registerSuccessful == true) 
			{
				registerSuccessful = false;
			}

			if(registerSuccessful == true) {
				f.dispose();
				AfterLoginUI afterLoginUI = new AfterLoginUI(username);
			}
		}

		if(e.getSource() == b2) {
			f.dispose();
            LoginUI login = new LoginUI();
		}
    }
}
