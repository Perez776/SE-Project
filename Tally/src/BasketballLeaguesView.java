import javax.swing.*;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;

public class BasketballLeaguesView implements ActionListener {
    JTextField t1, t2;

	JButton changeLeagueBN;
	JButton standingsBN;

    JLabel l1;
	JLabel wnbaLabel;
	JLabel nbaLabel;
	JLabel collegeBBLabel;

	JScrollPane j;

    JComboBox cb;
	JFrame f = new JFrame();
	JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT));

	BasketballLeaguesView(){
		//Labels
		l1 = new JLabel("NBA Recent News");
        l1.setBounds(800,30,150,30);

		BasketballLeaguesModel baskeballLeaguesModel = new BasketballLeaguesModel();
		nbaLabel = baskeballLeaguesModel.getNBANews();
		wnbaLabel = baskeballLeaguesModel.getWNBANews();
		collegeBBLabel = baskeballLeaguesModel.getCollegeBasketballNews();

		String a[] = {"NBA", "WNBA", "NCAA"};
        cb = new JComboBox<>(a);
        cb.setBounds(100,150,90,90);

		
		p.setBounds(200, 500, 1900, 800);
		p.add(nbaLabel);

		JScrollPane j = new JScrollPane(p);
		j.setBounds(300, 500, 1500, 500);

		//Buttons
		changeLeagueBN = new JButton("<html>Change<br/>League</html");
		changeLeagueBN.setBounds(30,150,70,40);
		Font font2 = new Font("serif", Font.BOLD, 12);
		changeLeagueBN.setFont(font2);
        changeLeagueBN.addActionListener(this);

		standingsBN = new JButton("<html>Views<br/>Standings</html");
		standingsBN.setBounds(400,150,70,40);
		standingsBN.setFont(font2);
        standingsBN.addActionListener(this);

		//Add Components to frame
		f.add(l1);
		f.add(changeLeagueBN);
		f.add(standingsBN);
        f.add(cb);
		f.add(j);
		
		//Set up Frame
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setExtendedState(f.getExtendedState() | JFrame.MAXIMIZED_BOTH);
		f.setLayout(null);
		f.setVisible(true);
		f.setTitle("Tally");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(cb.getSelectedIndex() == 0) {
			if(e.getSource() == changeLeagueBN) {
				p.remove(wnbaLabel);
				p.remove(collegeBBLabel);
				p.add(nbaLabel);
				l1.setText("NBA News");
				p.revalidate();
				p.repaint();
			}
			if(e.getSource() == standingsBN) {
				f.dispose();
				NBAView nbaStandings = new NBAView();
			}
		}
		if(cb.getSelectedIndex() == 1) {
			if(e.getSource() == changeLeagueBN) {
				p.remove(nbaLabel);
				p.remove(collegeBBLabel);
				p.add(wnbaLabel);
				l1.setText("WNBA News");
				p.revalidate();
				p.repaint();
			}
		}
		if(cb.getSelectedIndex() == 2) {
			if(e.getSource() == changeLeagueBN) {
				p.remove(nbaLabel);
				p.remove(wnbaLabel);
				p.add(collegeBBLabel);
				l1.setText("College BasketBall News");
				p.revalidate();
				p.repaint();
			}
		}
	}
}
