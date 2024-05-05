
package Standings;

import javax.imageio.ImageIO;
import javax.print.attribute.standard.Media;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.DefaultFormatter;

import com.mysql.cj.xdevapi.Table;

import API.APIInfo;
import LoginRegister.*;
import MainMenu.MainView;
import Matches.MatchesModel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import javax.media.CannotRealizeException;
import javax.media.Manager;
import javax.media.NoPlayerException;
import javax.media.Player;

public class StandingsView extends JPanel {
	JButton b1, b2;
    JLabel titeLabel;
	JTable table;
	JPanel panel = new JPanel();
	JComboBox yearsCB, chooseInfoTypeCB;
	JScrollPane scrollPane;

	String months[] = {"Dec", "Nov", "Oct", "Sep", "Aug", "Jul", "Jun", "May", "Apr", "Mar", "Feb", "Jan"};
	String cbArr [];
	String leagueName;
	String sportName;
	HashMap<String, String> monthMap = new HashMap<String, String>();

	MainView main;
	StandingsModel model;

	BufferedImage myPicture, image;
	URL url;
	URL mediaURL;

	public StandingsView(MainView main, String leagueName) {
		this.monthMap = getMontHashMap();
		this.leagueName = leagueName;
		this.main = main;

		Calendar today = Calendar.getInstance();
		today.set(Calendar.HOUR_OF_DAY, 0);
		int thisYear = today.getWeekYear();
		this.model = new StandingsModel(leagueName, String.valueOf(thisYear));

		//Labels
		String title = leagueName + " Standings";
        titeLabel = new JLabel(title, SwingConstants.CENTER);
		titeLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
        titeLabel.setBounds(400,20,700,30);

		String selectYear = "Select Year";
        JLabel selectYearLabel = new JLabel(selectYear);
		selectYearLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
        selectYearLabel.setBounds(20,210,200,30);

		MatchesModel matchesModel = new MatchesModel(title, leagueName, "202404");
		String urlString = matchesModel.getLeagueLogo();
		try {
			url = new URL(urlString);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			image = ImageIO.read(url);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JLabel label = new JLabel(new ImageIcon(image.getScaledInstance(150, 150, Image.SCALE_SMOOTH)));
		label.setBounds(10, 10, 150, 150);
		panel.add(label);
		
		//JTable
		table = model.getStandingsTable();
		setUpTable(table);

		Border blueBorder = BorderFactory.createLineBorder(Color.decode("#007AFF"), 2);
		Border nameBorder = BorderFactory.createTitledBorder(blueBorder, "  League Standings  ");

		//JScrollPanes
		scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBounds(50, 270, 1400, 600);
		scrollPane.setBorder(nameBorder);

		//Combo Boxes
			//years list for combo box
		Object years[] = new Object[thisYear-1999];
		for(int i = thisYear-2000; i >= 0; i = i - 1) {
			years[i] = thisYear - i;
		}
		yearsCB = new JComboBox<>(years);
		yearsCB.setBounds(200,180,90,90);

			//Choose Info type combo box
		String a[] = {"News", "Schedule", "Standings"};
		chooseInfoTypeCB = new JComboBox<>(a);
		chooseInfoTypeCB.setBounds(700,60,90,90);
	
		//Add Components to frame
		panel.setLayout(null);
        panel.setPreferredSize( new Dimension( 2000, 2000));
        panel.setMinimumSize( new Dimension( 2000, 2000));
		panel.add(titeLabel);
		panel.add(scrollPane);
		panel.add(yearsCB);
		panel.add(chooseInfoTypeCB);
		panel.add(selectYearLabel);

		StandingsController standingsController = new StandingsController(this);
	}


	public void addStandingsListener(ActionListener listenerForMatches) {
		yearsCB.addActionListener(listenerForMatches);
	}

	public JPanel getPanel() {
		return this.panel;
	}

	public static void sortbyColumn(int arr[][], int col)
    {
      Arrays.sort(arr, (a, b) -> Integer.compare(a[col],b[col])); // increasing order
    }

	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(myPicture, 0, 0, this); // see javadoc for more info on the parameters            
    }

	public HashMap<String, String> getMontHashMap() {
		HashMap<String, String> monthMap = new HashMap<String, String>();

		int monthNum = 1;
		//Map of months and month number
		for(int i = months.length-1; i >= 0; i--)
		{
			String monthStr = String.valueOf(monthNum);
			if(monthNum < 10) {
				monthStr = "0" + monthStr;
			}
			monthMap.putIfAbsent(months[i], monthStr);
			monthNum++;
		}

		return monthMap;
	}

	public void setUpTable(JTable table) {
		table.setFillsViewportHeight(true); 
		table.setShowGrid(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.getColumnModel().getColumn(0).setPreferredWidth(200);
		table.setRowHeight(40);
		table.setGridColor(Color.CYAN);

		TableColumn statColumn = table.getColumn("Wins");
		TableColumn lossColumn = table.getColumn("Losses");
		table.moveColumn(statColumn.getModelIndex(), 1);
		table.moveColumn(lossColumn.getModelIndex()+1, 2);

		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(table.getModel());
		table.setRowSorter(sorter);

		List<RowSorter.SortKey> sortKeys = new ArrayList<>();
		sortKeys.add(new RowSorter.SortKey(statColumn.getModelIndex(), SortOrder.DESCENDING));
		sorter.setSortKeys(sortKeys);
	}
}


	//APIInfo api = new APIInfo("https://basketball.sportdevs.com/standings?league_id=eq.2161&season_id=eq.7823"); 
	//Object teamWins [] = api.getAPIListItem("competitors", "wins"); 
	//Object teamLosses [] = api.getAPIListItem("competitors", "losses"); 
	//Object teamNames [] = api.getAPIListItem("competitors", "team_name");
	//Object img [] = api.getAPIListItem("competitors", "team_hash_image");
	//int pos = 0;