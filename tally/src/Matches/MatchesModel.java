package Matches;

import java.awt.Font;
import java.awt.List;
import java.time.Month;
import java.util.ArrayList;

import javax.swing.JLabel;

import org.json.JSONArray;
import org.json.JSONObject;

import API.APIInfo;

public class MatchesModel {

    APIInfo api;
    ArrayList<ArrayList<String>> apiItems;
    ArrayList<ArrayList<String>> apiItemsDescending;
    String [] apiArr;
    JLabel nbaMatchesLabel;
    String leagueName;

    public MatchesModel(String sport, String league, String date) {
        this.leagueName = league;
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
        if(league == "UEFA Champions League") {
            api = new APIInfo("https://site.api.espn.com/apis/site/v2/sports/soccer/uefa.champions/scoreboard?limit=500&dates=" + date);
        }
        if(league == "Spanish La Liga") {
            api = new APIInfo("https://site.api.espn.com/apis/site/v2/sports/soccer/esp.1/scoreboard?limit=500&dates=" + date);
        }

        this.apiItems = api.getESPNMatchesAPI("null");

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

    public String getLeagueLogo() {
        String link = api.getLeagueLogo();
        if(leagueName == "UEFA Champions League") {
            link = "https://fiu-original.b-cdn.net/fontsinuse.com/use-images/N187/187444/187444.png?filename=uefa-champions-league-logo.png";
        }
        return link;
    }


    public String getMatchStats(int selectedIndex) {
        String statName = "";
        Object stat = "";
        String teamName = "";
        String matchStats = "";
        int index = selectedIndex*2;

        if(apiItemsDescending.get(index).size() < 6) {
            return "Stats Not Available";
        }

        String checkStat = apiItemsDescending.get(index).get(6);

        teamName = "<br>" + "<strong>" + apiItemsDescending.get(index).get(3) + "<strong>" +"<br>";
        matchStats = matchStats + teamName;
        //Team 1 Stats

        for(int k = 5; k < apiItemsDescending.get(index).size(); k++) {
            statName = apiItemsDescending.get(index).get(k);

            for(int j = 0; j < statName.length(); j++) {
                if(statName.charAt(j) > 64 && statName.charAt(j) < 91) {
                    statName = statName.substring(0, j) + "&nbsp; " + statName.substring(j);
                    break;
                }
            }
            String s = statName.substring(0, 1);
            statName = s.toUpperCase() + statName.substring(1,statName.length());

            matchStats = matchStats + indent() + statName;
            k++;
    
            if((leagueName == "MLB" || leagueName == "NCAA Baseball") && Integer.parseInt(checkStat) > 100) {
                matchStats = matchStats + indent() + indent() + "N/A <br>";
            }
            else if(Integer.parseInt(checkStat) < 500)
            {
                stat = indent() + indent() + apiItemsDescending.get(index).get(k) + "<br>";
                matchStats = matchStats + stat;
            }
            else 
            {
                matchStats = matchStats + indent() + indent() + "N/A <br>";
            }
        }

        index++;
        //Team 2 Stats
        teamName = "<br>" + apiItemsDescending.get(index).get(3) + "<br>";
        matchStats = matchStats + teamName;
        for(int k = 5; k < apiItemsDescending.get(index).size()-1; k++) {
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

            matchStats = matchStats + indent() + statName;
            k++;

            if((leagueName == "MLB" || leagueName == "NCAA Baseball") && Integer.parseInt(checkStat) > 100) {
                matchStats = matchStats + indent() + indent() + "N/A <br>";
            }
            else if(Integer.parseInt(checkStat) < 500)
            {
                stat = indent() + indent() + apiItemsDescending.get(index).get(k) + "<br>";
                matchStats = matchStats + stat;
            }
            else 
            {
                matchStats = matchStats + indent() + indent() + "N/A <br>";
            }
        }
        
        matchStats = "<html>"+ matchStats +"</html> ";

        return matchStats;
    }

/*     
    public String getMatchStats(int selectedIndex) {
        String statName = "";
        Object stat = "";
        String teamName = "";
        String matchStats = "";
        int index = selectedIndex*2;

        if(apiItemsDescending.get(index).size() < 6) {
            return "Stats Not Available";
        }

        String checkStat = apiItemsDescending.get(index).get(6);

        teamName = "<br>" + apiItemsDescending.get(index).get(3)+ "<br>";
        matchStats = matchStats + teamName;
        //Team 1 Stats

        for(int k = 5; k < apiItemsDescending.get(index).size(); k++) {
            statName = apiItemsDescending.get(index).get(k);

            for(int j = 0; j < statName.length(); j++) {
                if(statName.charAt(j) > 64 && statName.charAt(j) < 91) {
                    statName = statName.substring(0, j) + "&nbsp; " + statName.substring(j);
                    break;
                }
            }
            String s = statName.substring(0, 1);
            statName = s.toUpperCase() + statName.substring(1,statName.length());

            matchStats = matchStats + statName;
            k++;

            if(leagueName == "MLB" || leagueName == "NCAA Baseball" && Integer.parseInt(checkStat) > 100) {
                matchStats = matchStats + indent() + indent() + "N/A <br>";
            }
            if(Integer.parseInt(checkStat) < 500)
            {
                stat = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + apiItemsDescending.get(index).get(k) + "<br>";
                matchStats = matchStats + stat;
            }
            else 
            {
                matchStats = matchStats + indent() + "N/A <br>";
            }
        }

        index++;
        //Team 2 Stats
        teamName = "<br>" + apiItemsDescending.get(index).get(3) + "<br>";
        matchStats = matchStats + teamName;
        for(int k = 5; k < apiItemsDescending.get(index).size()-1; k++) {
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

            if(leagueName == "MLB" || leagueName == "NCAA Baseball" && Integer.parseInt(checkStat) > 100) {
                matchStats = matchStats + indent() + indent() + "N/A <br>";
            }
            else if(Integer.parseInt(checkStat) < 500)
            {
                stat = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + apiItemsDescending.get(index).get(k) + "<br>";
                matchStats = matchStats + stat;
            }
            else 
            {
                matchStats = matchStats + indent() + "N/A <br>";
            }
        }
        
        matchStats = "<html>"+ matchStats +"</html> ";

        return matchStats;
    }
    */


    public String [] getMatchInfo() {
        Object teamName = "";
        Object score = "";
        Object date = "";
        Object newDate = "";
        String [] arr = new String[apiItems.size()/2];
        int j = 0;
        String matchInfo = "";
        String progress;

        if(apiItems.size() == 0) {
            matchInfo = "No games Played this Month";
            matchInfo = "<html>"+ matchInfo +"</html>";
            String ar [] = {matchInfo};
            return ar;
        }

        for(int i = 0; i < apiItemsDescending.size(); i++)  {
            matchInfo = "";

            teamName = apiItemsDescending.get(i).get(3);
            matchInfo = matchInfo + teamName + indent() + " at " + indent();

            score = apiItemsDescending.get(i).get(4);
            score = indent() + indent() + score + "&nbsp; &nbsp; - &nbsp; &nbsp;";

            i++;
 
            teamName = apiItemsDescending.get(i).get(3);
            matchInfo = matchInfo + teamName + "<br>";

            progress = indent() + indent() + indent() + apiItemsDescending.get(i).get(1) + "<br>";
            matchInfo = matchInfo + progress;

            score =  indent() + score + apiItemsDescending.get(i).get(4) + "&nbsp; &nbsp;";
            matchInfo = matchInfo + score + "<br>";

            newDate = apiItemsDescending.get(i).get(0);
        
            if(newDate != date) {
                date = newDate;
                matchInfo = matchInfo + indent() + indent() + date + "<br><br><br>";
            }
     
            arr[j] = "<html>"+ matchInfo + line() + "</html> ";
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
              
                if(newDate != date) {
                    date = newDate;
                    matchInfo = matchInfo + date + "<br><br>";
                }
        
                arr[j] = "<html>"+ matchInfo +"</html> ";
                j++; 
            }
        }
         return arr;
    }

    public String indent() {
        return "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
    }

    public String line() {
        String line =  "_______________________________________________";
        line = line + line;
        return  line;
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
