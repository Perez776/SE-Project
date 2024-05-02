package Standings;

import java.net.URL;
import java.util.ArrayList;

import javax.swing.JTable;

import API.APIInfo;

public class StandingsModel {
    APIInfo apiInfo;
    ArrayList<ArrayList<String>> apiItems = new ArrayList<>();

    public StandingsModel(String league) {

        String date = "2023";

        if(league == "NBA") {
            apiInfo = new APIInfo("https://site.web.api.espn.com/apis/v2/sports/basketball/nba/standings?season=" + date);
        }
        if(league == "WNBA") {
            apiInfo = new APIInfo("https://site.web.api.espn.com/apis/v2/sports/basketball/wnba/standings?season=" + date);
        }
        if(league == "NCAA Men's Basketball") {
            apiInfo = new APIInfo("https://site.web.api.espn.com/apis/v2/sports/basketball/mens-college-basketball/standings?season=" + date);
        }
        if(league == "NCAA Women's Basketball") {
            apiInfo = new APIInfo("https://site.web.api.espn.com/apis/v2/sports/basketball/womens-college-basketball/standings?season=" + date);
        }
        if(league == "NFL") {
            apiInfo = new APIInfo("https://site.web.api.espn.com/apis/v2/sports/football/nfl/standings?season=" + date);
        }
        if(league == "NCAA Football") {
            apiInfo = new APIInfo("https://site.web.api.espn.com/apis/v2/sports/football/college-football/standings?season=" + date);
        }
        if(league == "MLS") {
            apiInfo = new APIInfo("https://site.web.api.espn.com/apis/v2/sports/soccer/usa.1/standings?season=" + date);
        }
        if(league == "EPL") {
            apiInfo = new APIInfo("https://site.web.api.espn.com/apis/v2/sports/soccer/eng.1/standings?season=" + date);
        }
        if(league == "MLB") {
            apiInfo = new APIInfo("https://site.web.api.espn.com/apis/v2/sports/baseball/mlb/standings?season=" + date);
        }
        if(league == "NCAA Baseball") {
            apiInfo = new APIInfo("https://site.web.api.espn.com/apis/v2/sports/baseball/college-baseball/standings?season=" + date);
        }     
        if(league == "French Ligue 1") {
            apiInfo = new APIInfo("https://site.web.api.espn.com/apis/v2/sports/soccer/fra.1/standings?season=" + date);
        }
        if(league == "Mexican Liga BBVA MX") {
            apiInfo = new APIInfo("https://site.web.api.espn.com/apis/v2/sports/soccer/mex.1/standings?season=" + date);
        }
        if(league == "German Bundesliga") {
            apiInfo = new APIInfo("https://site.web.api.espn.com/apis/v2/sports/soccer/ger.1/standings?season=" + date);
        }
        if(league == "EUFA Champions League") {
            apiInfo = new APIInfo("https://site.web.api.espn.com/apis/v2/sports/soccer/uefa.champions/standings?season=" + date);
        }
        if(league == "Spanish La Liga") {
            apiInfo = new APIInfo("https://site.web.api.espn.com/apis/v2/sports/soccer/esp.1/standings?season=" + date);
        }

        apiItems = apiInfo.getESPNStandings();
    }


    public ArrayList<ArrayList<String>> getApiItems() {
        return apiItems;
    }



    public JTable getStandingsTable() {
        ArrayList<ArrayList<String>> apiItems= this.apiItems;

		ArrayList<ArrayList<Object>> tableItems = new ArrayList<ArrayList<Object>>();

		Object [][] arr = new Object[apiItems.size()][apiItems.get(0).size()];
	
		ArrayList<String> columnNames = new ArrayList<String>();
		columnNames.add("Team Name");
		for(int j = 3; j < apiItems.get(0).size(); j=j+2) {
			columnNames.add(apiItems.get(0).get(j));
		}

		Object columns [] = columnNames.toArray();
	
		System.out.println(columns.length);
		
		for(int i = 0; i < apiItems.size(); i++) {
			tableItems.add(new ArrayList<Object>());

			for(int j = 1; j < apiItems.get(0).size(); j=j+2) {
				tableItems.get(i).add(apiItems.get(i).get(j));

				if(j == 1) {
					j = j+1;
				}
				//System.out.println( apiItems.get(i).get(j));
			}

		}

		for(int i = 0; i < tableItems.size(); i++) {
			arr[i] = tableItems.get(i).toArray();
		}

        JTable table = new JTable(arr, columns);

        return table;
    }
}
