import java.awt.Font;
import javax.swing.JLabel;

public class BaseballLeaguesModel {

    JLabel l;
    Object nflApi [];
    Object collegeApi [];

    //String nflApi;
    //String collegeApi;

    public BaseballLeaguesModel() {
        APIInfo nflURL = new APIInfo("https://site.api.espn.com/apis/site/v2/sports/baseball/mlb/news"); 
        this.nflApi = nflURL.getESPNAPI("articles", "description");

        APIInfo collegeURL =  new APIInfo("https://site.api.espn.com/apis/site/v2/sports/football/college-football/news");
        this.collegeApi = collegeURL.getESPNAPI("articles", "description");
    }

    public JLabel getMLBNews () {
        String news = "";
        for(int i = 0; i < nflApi.length; i++) {
            news = news + nflApi[i] + "<br><br>";
        }

        Font font = new Font("serif", Font.BOLD, 20);
    
        l = new JLabel("<html>"+ news +"<html>");
        l.setBounds(100,0,60,700);
        l.setFont(font);
        
        return l;
    }

    public JLabel getCollegeBaseballNews () {
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
}

