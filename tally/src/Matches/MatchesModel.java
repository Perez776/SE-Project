package Matches;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JLabel;

import API.APIInfo;

public class MatchesModel {

    ArrayList<ArrayList<Object>> apiItems;
    JLabel nbaMatchesLabel;

    public MatchesModel() {
        //Month = yyyyMM
        String month = "202404";
        //APIInfo api = new APIInfo("https://site.api.espn.com/apis/site/v2/sports/soccer/eng.1/scoreboard");
        APIInfo api = new APIInfo("https://site.api.espn.com/apis/site/v2/sports/basketball/nba/scoreboard?limit=500&dates=" + month);
        //api.getESPNMatchesAPI("");

        this.apiItems = api.getESPNMatchesAPI("null");
        //System.out.println(apiItems.size());
    }

    public String [] getAPIarray() {
        Object teamName = "";
        Object score = "";
        Object fullString = "";
        Object date = "";
        String [] arr = new String[apiItems.size()/2];
        int j = 0;
        String matchInfo = "";

        ArrayList<String> ar = new ArrayList<String>();

        for(int i = apiItems.size()-1; i >= 0; i--)  {

            matchInfo = "";

            teamName = apiItems.get(i).get(2) + "   at   ";
            matchInfo = matchInfo + teamName;
            score = apiItems.get(i).get(22);

            i--;

            teamName = apiItems.get(i).get(2);
            matchInfo = matchInfo + teamName + "<br>";

            teamName = apiItems.get(i).get(1) + "     ";
            matchInfo = matchInfo + teamName;

            if(Integer.parseInt(score.toString()) > 500) {
                score = "";
            }
            else {
                score = score + "  -  " + apiItems.get(i).get(22).toString();
            }

            matchInfo = matchInfo + score + "<br>";

            date = apiItems.get(i).get(0);
            matchInfo = matchInfo + date + "<br><br>";
     
            arr[j] = "<html>"+ matchInfo +"</html> ";
            j++; 
        }

        return arr;
    }
    

    public JLabel getNBAMatchesLabel() {
        Object teamName = "";
        Object score = "";
        Object fullString = "";
        Object date = "";

        for(int i = apiItems.size()-1; i >= 0; i--)  {

            teamName = apiItems.get(i).get(2);
            fullString = fullString + teamName.toString() + "  at  ";
            score = apiItems.get(i).get(22);
            i--;
            teamName = apiItems.get(i).get(2);
            fullString = fullString + teamName.toString()  + "<br />";

            teamName = apiItems.get(i).get(1);
            fullString = fullString + teamName.toString()  + "     ";

            if(Integer.parseInt(score.toString()) > 500) {
                score = "";
            }
            else {
                score = score + "  -  " + apiItems.get(i).get(22).toString();
            }

            fullString = fullString + score.toString()  + "<br />";

            date = apiItems.get(i).get(0);
            fullString = fullString + date.toString()  + "<br /><br />";
        
        }
        //System.out.println(teams);
        Font font = new Font("serif", Font.BOLD, 20);
        nbaMatchesLabel = new JLabel("<html>"+ fullString +"</html> ");
        nbaMatchesLabel.setBounds(100,0,10000,10000);
        nbaMatchesLabel.setFont(font);

        return nbaMatchesLabel;
    }

    public ArrayList<ArrayList<Object>> getApiItems() {
        return apiItems;
    }
    
}
