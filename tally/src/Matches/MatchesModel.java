package Matches;
import java.util.ArrayList;
import javax.swing.JLabel;
import API.APIInfo;

public class MatchesModel {

    APIInfo api;
    ArrayList<ArrayList<String>> apiItems;
    ArrayList<ArrayList<String>> apiItemsDescending;
    String [] apiArr;
    JLabel nbaMatchesLabel;
    String leagueName;
    String date;

    public MatchesModel(String sport, String league, String date) {
        this.leagueName = league;
        
        this.date = date;
     
        this.api = getApiInfo();

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

            newDate = indent() + apiItemsDescending.get(i).get(0);
        
            if(newDate != date) {
                date = newDate;
                matchInfo = matchInfo + indent() + indent() + date + "<br><br><br>";
            }
     
            arr[j] = "<html>"+ matchInfo + line() + "</html> ";
            j++; 
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
    

    public APIInfo getApiInfo() {
        String league = this.leagueName;

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

        return api;
    }
}
