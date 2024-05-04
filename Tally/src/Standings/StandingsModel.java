package Standings;

import java.net.URL;
import java.util.ArrayList;

import javax.swing.JTable;

import API.APIInfo;

public class StandingsModel {
    APIInfo apiInfo;
    ArrayList<ArrayList<Object>> apiItems = new ArrayList<>();
    String date;

    public StandingsModel(String league, String date) {

        this.date = date;
        boolean newLink = false;
        String params[] = this.getParameters(league);

        apiInfo = new APIInfo("https://site.web.api.espn.com/apis/v2/sports/" + params[0] + "/" + params[1] + "/standings?season=" + date);
    
        apiItems = apiInfo.getESPNStandings();

        if(apiItems == null) {
            apiInfo = new APIInfo("https://site.web.api.espn.com/apis/v2/sports/" + params[0] + "/" + params[1] + "/standings");
            apiItems = apiInfo.getESPNStandings();
        }
    }


    public JTable getStandingsTable() {

        if(apiItems == null || apiItems.size() == 0) {
            //JTable table = new JTable();
            Object t1[] = {"Standings Not Available"};
            Object t2[][] = {{"Standings Not Available"}};
            JTable tab = new JTable(t2, t1);
            return tab;
        }

        ArrayList<ArrayList<Object>> apiItems= this.apiItems;

		ArrayList<ArrayList<Object>> tableItems = new ArrayList<ArrayList<Object>>();

		Object [][] arr = new Object[apiItems.size()][apiItems.get(0).size()];

        //Column Names
		ArrayList<Object> columnNames = new ArrayList<Object>();
		columnNames.add("Team Name");

		for(int j = 3; j < apiItems.get(0).size(); j=j+2) {
			columnNames.add(apiItems.get(0).get(j));
        }

		Object columns [] = columnNames.toArray();
		
		for(int i = 0; i < apiItems.size(); i++) {
			tableItems.add(new ArrayList<Object>());

			for(int j = 2; j < apiItems.get(0).size(); j=j+2) {

                if(j < apiItems.get(i).size()) {
                    tableItems.get(i).add(apiItems.get(i).get(j));
                }
                else {
                    tableItems.get(i).add("");
                }
			}

		}

		for(int i = 0; i < tableItems.size(); i++) {
			arr[i] = tableItems.get(i).toArray();
		}

        JTable table = new JTable(arr, columns);

        return table;
    }


    public String [] getParameters(String league) {
        String params[] = new String[2];

        if(league == "NBA") {
            params[0] = "basketball";
            params[1] = "nba";
        }
        if(league == "WNBA") {
            params[0] = "basketball";
            params[1] = "wnba";
        }
        if(league == "NCAA Men's Basketball") {
            params[0] = "basketball";
            params[1] = "mens-college-basketball";
        }
        if(league == "NCAA Women's Basketball") {
            params[0] = "basketball";
            params[1] = "womens-college-basketball";
        }
        if(league == "NFL") {
            params[0] = "football";
            params[1] = "nfl";
        }
        if(league == "NCAA Football") {
            params[0] = "football";
            params[1] = "college-football";
        }
        if(league == "MLS") {
            params[0] = "soccer";
            params[1] = "usa.1";
        }
        if(league == "EPL") {
            params[0] = "soccer";
            params[1] = "eng.1";
        }
        if(league == "MLB") {
            params[0] = "baseball";
            params[1] = "mlb";
        }
        if(league == "NCAA Baseball") {
            params[0] = "baseball";
            params[1] = "college-baseball";
        }     
        if(league == "French Ligue 1") {
            params[0] = "soccer";
            params[1] = "fra.1";
        }
        if(league == "Mexican Liga BBVA MX") {
            params[0] = "soccer";
            params[1] = "mex.1";
        }
        if(league == "German Bundesliga") {
            params[0] = "soccer";
            params[1] = "ger.1";
        }
        if(league == "UEFA Champions League") {
            params[0] = "soccer";
            params[1] = "uefa.champions";
        }
        if(league == "Spanish La Liga") {
            params[0] = "soccer";
            params[1] = "esp.1";
        }

        return params;
    }
}
