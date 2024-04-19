
import java.awt.Font;
import javax.swing.JLabel;

public class BasketballLeaguesModel {

    JLabel l;
    Object nbaApi [];
    Object wnbaApi [];
    Object collegeApi [];

    //String nflApi;
    //String collegeApi;

    public BasketballLeaguesModel() {
        APIInfo nflURL = new APIInfo("https://site.api.espn.com/apis/site/v2/sports/basketball/nba/news"); 
        this.nbaApi = nflURL.getESPNAPI("articles", "description");

        APIInfo collegeURL =  new APIInfo("https://site.api.espn.com/apis/site/v2/sports/basketball/mens-college-basketball/news");
        this.collegeApi = collegeURL.getESPNAPI("articles", "description");

        APIInfo wnbaURL =  new APIInfo("https://site.api.espn.com/apis/site/v2/sports/basketball/wnba/news");
        this.wnbaApi = wnbaURL.getESPNAPI("articles", "description");
    }

    public JLabel getNBANews () {
        String news = "";
        for(int i = 0; i < nbaApi.length; i++) {
            news = news + nbaApi[i] + "<br><br>";
        }

        Font font = new Font("serif", Font.BOLD, 20);
    
        l = new JLabel("<html>"+ news +"<html>");
        l.setBounds(100,0,60,700);
        l.setFont(font);
        
        return l;
    }

    public JLabel getCollegeBasketballNews () {
        String news = "";
        for(int i = 0; i < collegeApi.length; i++) {
            news = news + collegeApi[i] + "<br><br>";
        }

        Font font = new Font("serif", Font.BOLD, 20);
    
        l = new JLabel("<html>"+ news +"<html>");
        l.setBounds(100,0,60,700);
        l.setFont(font);
        
        return l;
    }

    public JLabel getWNBANews () {
        String news = "";
        for(int i = 0; i < wnbaApi.length; i++) {
            news = news + wnbaApi[i] + "<br><br>";
        }

        Font font = new Font("serif", Font.BOLD, 20);
    
        l = new JLabel("<html>"+ news +"<html>");
        l.setBounds(100,0,60,700);
        l.setFont(font);
        
        return l;
    }
}

