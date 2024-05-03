package News;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import API.APIInfo;

public class NewsModel {

    APIInfo api;
    JLabel l;
    Object apiItems [][];
    String league;

    public NewsModel(String league) {
        this.league = league;
        if(league == "NBA") {
            api = new APIInfo("https://site.api.espn.com/apis/site/v2/sports/basketball/nba/news");
        }
        if(league == "WNBA") {
            api = new APIInfo("https://site.api.espn.com/apis/site/v2/sports/basketball/wnba/news");
        }
        if(league == "NCAA Men's Basketball") {
            api = new APIInfo("https://site.api.espn.com/apis/site/v2/sports/basketball//mens-college-basketball/news");
        }
        if(league == "NCAA Women's Basketball") {
            api = new APIInfo("https://site.api.espn.com/apis/site/v2/sports/basketball//womens-college-basketball/news");
        }
        if(league == "NFL") {
            api = new APIInfo("https://site.api.espn.com/apis/site/v2/sports/football/nfl/news");
        }
        if(league == "NCAA Football") {
            api = new APIInfo("https://site.api.espn.com/apis/site/v2/sports/football/college-football/news");
        }
        if(league == "MLB") {
            api = new APIInfo("https://site.api.espn.com/apis/site/v2/sports/baseball/mlb/news");
        }
        if(league == "NCAA Baseball") {
            api = new APIInfo("https://site.api.espn.com/apis/site/v2/sports/baseball/college-baseball/news");
        }
        if(league == "MLS") {
            api = new APIInfo("https://site.api.espn.com/apis/site/v2/sports/soccer/usa.1/news");
        }
        if(league == "EPL") {
            api = new APIInfo("https://site.api.espn.com/apis/site/v2/sports/soccer/eng.1/news");
        }     
        if(league == "French Ligue 1") {
            api = new APIInfo("https://site.api.espn.com/apis/site/v2/sports/soccer/fra.1/news");
        }
        if(league == "Mexican Liga BBVA MX") {
            api = new APIInfo("https://site.api.espn.com/apis/site/v2/sports/soccer/mex.1/news");
        }
        if(league == "German Bundesliga") {
            api = new APIInfo("https://site.api.espn.com/apis/site/v2/sports/soccer/ger.1/news");
        }
        if(league == "UEFA Champions League") {
            api = new APIInfo("https://site.api.espn.com/apis/site/v2/sports/soccer/uefa.champions/news");
        }
        if(league == "Spanish La Liga") {
            api = new APIInfo("https://site.api.espn.com/apis/site/v2/sports/soccer/esp.1/news");
        }

        this.apiItems = api.getESPNNews("", "");
    }

    public JLabel getNewsLabel() {
        String news = "";
        for(int i = 0; i < apiItems.length; i++) {
            news = news + apiItems[i][0].toString() + "<br><br><br>";
        }

        Font font = new Font("serif", Font.BOLD, 20);
    
        l = new JLabel("<html>"+ news +"<html>", SwingConstants.CENTER);
        l.setBounds(100,0,60,700);
        l.setFont(font);
        
        return l;
    }

    public String [] getImgURLS() {
        String imgs [] = new String[apiItems.length];

        for(int i = 0; i < apiItems.length; i++) {
            imgs[i] = apiItems[i][1].toString();
        }
        
        return imgs;
    }

    public String [] getNews() {
        String imgs [] = new String[apiItems.length];

        for(int i = 0; i < apiItems.length; i++) {
            imgs[i] = apiItems[i][0].toString();
        }
        
        return imgs;
    }
}

