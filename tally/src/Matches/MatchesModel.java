package Matches;

import java.awt.Font;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;

import javax.swing.JLabel;

import API.APIInfo;

public class MatchesModel {

    ArrayList<ArrayList<String>> apiItems;
    JLabel nbaMatchesLabel;

    public MatchesModel() {
        //Month = yyyyMM
        String month = "202404";
        //APIInfo api = new APIInfo("https://site.api.espn.com/apis/site/v2/sports/soccer/eng.1/scoreboard");
        APIInfo api = new APIInfo("https://site.api.espn.com/apis/site/v2/sports/basketball/nba/scoreboard?limit=500&dates=" + month);
        //api.getESPNMatchesAPI("");
        this.apiItems = api.getESPNMatchesAPI("null");

        //System.out.println(apiItems);
    }

    public String [] getMatchStats() {
        Object stat = "";
        Object stat2 = "";
        Object stat3 = "";
        Object date = "";
        Object newDate = "";
        String progress = "";
        Object teamName = "";
        String [] arr = new String[apiItems.size()/2];
        int j = 0;
        String matchInfo = "";

        ArrayList<String> ar = new ArrayList<String>();

        for(int i = apiItems.size()-1; i >= 0; i--)  {
            matchInfo = "";
            progress = apiItems.get(i).get(3);

           // if(Integer.parseInt(progress) > 500) {

           // }

            //Team 1 Stats
           // for(int j = 0; j < apiItems.get(i).ger)
            teamName = apiItems.get(i).get(2) + "<br>";
            matchInfo = matchInfo + teamName;

            stat = apiItems.get(i).get(3) + "&nbsp; &nbsp;";
            matchInfo = matchInfo + stat;

            stat = apiItems.get(i).get(4) + "<br>";
            matchInfo = matchInfo + stat;

            stat = apiItems.get(i).get(7) + "&nbsp; &nbsp;";
            matchInfo = matchInfo + stat;

            stat = apiItems.get(i).get(8) + "<br>";
            matchInfo = matchInfo + stat;

            stat = apiItems.get(i).get(9) + "&nbsp; &nbsp;";
            matchInfo = matchInfo + stat;

            stat = apiItems.get(i).get(10) + "<br>";
            matchInfo = matchInfo + stat;

            stat = apiItems.get(i).get(11) + "&nbsp; &nbsp;";
            matchInfo = matchInfo + stat;

            stat = apiItems.get(i).get(12) + "<br>";
            matchInfo = matchInfo + stat;

            i--;

            stat = apiItems.get(i).get(2) + "<br>";
            matchInfo = matchInfo + stat;

    
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
            System.out.println(progress);

            if(progress == "Final")
            {
                System.out.println(progress);
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

                System.out.println(teamName);

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

    public String [] getAPIarray() {
        Object teamName = "";
        Object score = "";
        Object fullString = "";
        Object date = "";
        Object newDate = "";
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

            teamName = apiItems.get(i).get(1);
            if(teamName == "Final")
            {
                System.out.println(teamName);
            }
    
            teamName = teamName + "&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; ";
            matchInfo = matchInfo + teamName;

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

    public ArrayList<ArrayList<String>> getApiItems() {
        return apiItems;
    }
    
}
