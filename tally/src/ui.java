import javax.swing.*;
import java.awt.event.*;

public class ui implements ActionListener{
	
	JTextField t1,t2;
	JButton b, b2;
    JLabel a1;
    JFrame f2;
	
	ui(){
		JFrame f= new JFrame();

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

		f.add(t1);
		f.add(t2);
        f.add(a1);
		f.add(b);
		f.add(b2);
		
		f.setSize(400,400);
		f.setLayout(null);
		f.setVisible(true);
		f.setTitle("Tally");


        f2.setSize(400,400);
		f2.setLayout(null);
		f2.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		String s1 = t1.getText();
		String s2 = t2.getText();
		
		//int a = Integer.parseInt(s1);
		//int b = Integer.parseInt(s2);
		
		System.out.printf("The numbers are: %s %s", s1, s2);
		 
	}
	
	public static void main(String args[]) {
		new ui();
	}
}
