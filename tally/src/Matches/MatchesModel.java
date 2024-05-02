package Matches;

import java.awt.Font;
import java.awt.List;
import java.time.Month;
import java.util.ArrayList;

import javax.swing.JLabel;

import API.APIInfo;

public class MatchesModel {

    APIInfo api;
    ArrayList<ArrayList<String>> apiItems;
    ArrayList<ArrayList<String>> apiItemsDescending;
    String [] apiArr;
    JLabel nbaMatchesLabel;

    public MatchesModel(String sport, String league, String date) {
        //Month = yyyyMM
        if(league == "NBA") {
            api = new APIInfo("https://site.api.espn.com/apis/site/v2/sports/basketball/nba/scoreboard?limit=500&dates=" + date);
        }
        if(league == "WNBA") {
            api = new APIInfo("https://site.api.espn.com/apis/site/v2/sports/basketball/wnba/scoreboard?limit=500&dates=" + date);
        }
        if(league == "NCAA Men's Basketball") {
            api = new APIInfo("https://site.api.espn.com/apis/site/v2/sports/basketball/mens-college-basketball/scoreboard?limit=500&dates=" + date);
        }
        if(league == "NCAA Women's Basketball") {
            api = new APIInfo("https://site.api.espn.com/apis/site/v2/sports/basketball/womens-college-basketball/scoreboard?limit=500&dates=" + date);
        }
        if(league == "NFL") {
            api = new APIInfo("https://site.api.espn.com/apis/site/v2/sports/football/nfl/scoreboard?limit=500&dates=" + date);
        }
        if(league == "NCAA Football") {
            api = new APIInfo("https://site.api.espn.com/apis/site/v2/sports/football/college-football/scoreboard?limit=500&dates=" + date);
        }
        if(league == "MLS") {
            api = new APIInfo("https://site.api.espn.com/apis/site/v2/sports/soccer/usa.1/scoreboard?limit=500&dates=" + date);
        }
        if(league == "EPL") {
            api = new APIInfo("https://site.api.espn.com/apis/site/v2/sports/soccer/eng.1/scoreboard?limit=500&dates=" + date);
        }
        if(league == "MLB") {
            api = new APIInfo("https://site.api.espn.com/apis/site/v2/sports/baseball/mlb/scoreboard?limit=500&dates=" + date);
        }
        if(league == "NCAA Baseball") {
            api = new APIInfo("https://site.api.espn.com/apis/site/v2/sports/baseball/college-baseball/scoreboard?limit=500&dates=" + date);
        }     
        if(league == "French Ligue 1") {
            api = new APIInfo("https://site.api.espn.com/apis/site/v2/sports/soccer/fra.1/scoreboard?limit=500&dates=" + date);
        }
        if(league == "Mexican Liga BBVA MX") {
            api = new APIInfo("https://site.api.espn.com/apis/site/v2/sports/soccer/mex.1/scoreboard?limit=500&dates=" + date);
        }
        if(league == "German Bundesliga") {
            api = new APIInfo("https://site.api.espn.com/apis/site/v2/sports/soccer/ger.1/scoreboard?limit=500&dates=" + date);
        }
        if(league == "EUFA Champions League") {
            api = new APIInfo("https://site.api.espn.com/apis/site/v2/sports/soccer/uefa.champions/scoreboard?limit=500&dates=" + date);
        }
        if(league == "Spanish La Liga") {
            api = new APIInfo("https://site.api.espn.com/apis/site/v2/sports/soccer/esp.1/scoreboard?limit=500&dates=" + date);
        }

        this.apiItems = api.getESPNMatchesAPI("null");
        System.out.println(apiItems.size());

        apiItemsDescending = getApiItemsDescending();
    }

    public ArrayList<ArrayList<String>> getApiItemsDescending() {
        ArrayList<ArrayList<String>> ar = new ArrayList<ArrayList<String>>();

        int size = apiItems.size();

        for(int i = size-1; i >= 0; i--)  {
            ar.add(apiItems.get(i));
        }

        return ar;
    }
    
    public String  getMatchStats(int selectedIndex) {
        String statName = "";
        Object stat = "";
        String teamName = "";
        String matchStats = "Game Stats <br><br><br>";
        int index = selectedIndex*2;

        if(apiItemsDescending.get(index).size() < 5) {
            return "Stats Not Available";
        }

        String checkStat = apiItemsDescending.get(index).get(5);

        teamName = "<br>" + apiItemsDescending.get(index).get(2)+ "<br>";
        matchStats = matchStats + teamName;
        //Team 1 Stats
        for(int k = 4; k < apiItemsDescending.get(index).size(); k++) {
            statName = apiItemsDescending.get(index).get(k);

            for(int j = 0; j < statName.length(); j++) {
                if(statName.charAt(j) > 64 && statName.charAt(j) < 91) {
                    //System.out.println(statName);
                    statName = statName.substring(0, j) + "&nbsp; " + statName.substring(j);
                    break;
                }
            }
            String s = statName.substring(0, 1);
            statName = s.toUpperCase() + statName.substring(1,statName.length());

            matchStats = matchStats + statName;
            k++;
            
            if(Integer.parseInt(checkStat) < 500)
            {
                stat = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + apiItemsDescending.get(index).get(k) + "<br>";
                matchStats = matchStats + stat;
            }
            else 
            {
                matchStats = matchStats + "N/A <br>";
            }
        }

        index++;
        //Team 2 Stats
        teamName = "<br>" + apiItemsDescending.get(index).get(2) + "<br>";
        matchStats = matchStats + teamName;
        for(int k = 4; k < apiItemsDescending.get(index).size()-1; k++) {
            statName = apiItemsDescending.get(index).get(k);

            for(int j = 0; j < statName.length(); j++) {
                if(statName.charAt(j) > 64 && statName.charAt(j) < 91) {
                    //System.out.println(statName);
                    statName = statName.substring(0, j) + "&nbsp; " + statName.substring(j);
                    break;
                }
            }
            String s = statName.substring(0, 1);
            statName = s.toUpperCase() + statName.substring(1,statName.length());

            matchStats = matchStats + statName;
            k++;

            if(Integer.parseInt(checkStat) < 500)
            {
                stat = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + apiItemsDescending.get(index).get(k) + "<br>";
                matchStats = matchStats + stat;
            }
            else 
            {
                matchStats = matchStats + "N/A <br>";
            }
        }
        
        matchStats = "<html>"+ matchStats +"</html> ";

        return matchStats;
    }


    public String [] getMatchInfo() {
        Object teamName = "";
        Object score = "";
        Object date = "";
        Object newDate = "";
        String [] arr = new String[apiItems.size()/2];
        int j = 0;
        String matchInfo = "";

        if(apiItems.size() == 0) {
            matchInfo = "No games Played this Month";
            matchInfo = "<html>"+ matchInfo +"</html>";
            String ar [] = {matchInfo};
            return ar;
        }

        for(int i = 0; i < apiItemsDescending.size(); i++)  {
            matchInfo = "";

            teamName = apiItemsDescending.get(i).get(2) + "&nbsp; &nbsp; At &nbsp; &nbsp;";
            matchInfo = matchInfo + teamName;
            score = apiItemsDescending.get(i).get(3) + "&nbsp; &nbsp; - &nbsp; &nbsp;";

            i++;
 
            teamName = apiItemsDescending.get(i).get(2);
            matchInfo = matchInfo + teamName + "<br>";

            teamName = apiItemsDescending.get(i).get(1);
            teamName = teamName + "&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; ";
            matchInfo = matchInfo + teamName;

            score =  score + apiItemsDescending.get(i).get(3) + "&nbsp; &nbsp;";
            matchInfo = matchInfo + score + "<br>";
            newDate = apiItemsDescending.get(i).get(0);
        
            if(newDate != date) {
                date = newDate;
                matchInfo = matchInfo + date + "<br><br>";
            }
     
            arr[j] = "<html>"+ matchInfo +"</html> ";
            j++; 
        }

        return arr;
    }


    public String [] getInProgressMatches() {
        Object teamName = "";
        Object score = "";
        Object fullString = "";
        Object date = "";
        Object newDate = "";
        String progress = "";
        String [] arr = new String[apiItems.size()/2];
        int j = 0;
        String matchInfo = "";

        ArrayList<String> ar = new ArrayList<String>();

        for(int i = apiItems.size()-1; i >= 0; i--)  {
            progress = apiItems.get(i).get(1);

            if(progress == "Final")
            {
                progress = progress + "&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; ";
                matchInfo = matchInfo + progress;
                
                teamName = apiItems.get(i).get(1);
                matchInfo = "";

                teamName = apiItems.get(i).get(2) + "   at   ";
                matchInfo = matchInfo + teamName;
                score = apiItems.get(i).get(22);

                i--;

                teamName = apiItems.get(i).get(2);
                matchInfo = matchInfo + teamName + "<br>";

                progress = apiItems.get(i).get(1);
                progress = progress + "&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; ";
                matchInfo = matchInfo + progress;


                //if(teamName == "In Progress")

                if(Integer.parseInt(score.toString()) > 500) {
                    score = "";
                }
                else {
                    score = score + "  -  " + apiItems.get(i).get(22).toString();
                }

                matchInfo = matchInfo + score + "<br>";
                newDate = apiItems.get(i).get(0);
                //System.out.println(newDate);
                if(newDate != date) {
                    date = newDate;
                // System.out.println(date);
                    matchInfo = matchInfo + date + "<br><br>";
                }
        
                arr[j] = "<html>"+ matchInfo +"</html> ";
                j++; 
            }
        }
         return arr;
    }
    

    /* 
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

    */
}
