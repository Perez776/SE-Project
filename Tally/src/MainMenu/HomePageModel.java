package MainMenu;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import Database.ConnectDB;

public class HomePageModel {

    String leagueName;

    public HomePageModel(String leagueName) {
       this.leagueName = leagueName;
    }


    public void getFollowedTeams() {

    }

    /* 
    public void test() {
        try {
            //Get connection and execute query to check if the username already exists in the DB.
            ConnectDB connectDB = new ConnectDB();
            Connection con = connectDB.getConnection();
            Statement stmt = con.createStatement();
            String query = "SELECT * FROM users"; // WHERE BINARY username = \"" + username + "\";";
            ResultSet rs = stmt.executeQuery(query);

            String league = leagueName.replaceAll("\\s+","");
            System.out.println(league);

            rs.next();
            String userDB = rs.getString(league);
            System.out.println("-------- Item  --------");
            System.out.println( userDB);

            stmt = con.createStatement();
            String updateString = "UPDATE users SET " + league + " = 'true'";
            stmt.executeUpdate(updateString);

            query = "SELECT * FROM users"; // WHERE BINARY username = \"" + username + "\";";
            rs = stmt.executeQuery(query);
            rs.next();
            String s = rs.getString(league);
            System.out.println("-------- Item  --------");
            System.out.println( s);

        }
        //If DB didn't successfully connect
        catch(Exception e)
        {
            System.out.println(e);
        }
    }

    */
}