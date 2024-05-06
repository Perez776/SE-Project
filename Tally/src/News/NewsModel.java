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
        String params[] = this.getParameters(league);
        String link = "https://site.api.espn.com/apis/site/v2/sports/" + params[0] + "/" + params[1] + "/news";
        api = new APIInfo(link);

        this.apiItems = api.getESPNNews("", "");
    }

    public String [][] getMoreNews() {

        String newLinks [][] = new String[apiItems.length][2];

        for(int i = 0; i < apiItems.length; i++) {

            APIInfo apiInfo = new APIInfo(apiItems[i][2].toString());

            newLinks[i][0] = apiInfo.getMoreNews()[0].toString();
            newLinks[i][1] = apiInfo.getMoreNews()[1].toString();
        }
    
        return newLinks;
    }


    public JLabel getNewsLabel() {
        String news = "";
        for(int i = 0; i < apiItems.length; i++) {
            news = news + apiItems[i][0].toString() + "<br><br><br><br>";
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
            imgs[i] = "<html>" + apiItems[i][0].toString() + "<html>";
        }
        
        return imgs;
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

