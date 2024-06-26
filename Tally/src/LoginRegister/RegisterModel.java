package LoginRegister;
import java.sql.*;

import Database.ConnectDB;

public class RegisterModel{
    String username;
    String hashedToken;
    Boolean usernameTaken = false;
    Boolean validUsername = true;
    Boolean validPassword = true;

    public RegisterModel(String username, String password, String hashedToken, ConnectDB db) {
        this.username = username;
        this.hashedToken = hashedToken;

        try {
            //Get connection and execute query to check if the username already exists in the DB.
            Connection con = db.getConnection();
            Statement stmt = con.createStatement();
            String query = "SELECT * FROM users WHERE BINARY username = \"" + username + "\";";
            ResultSet rs = stmt.executeQuery(query);

            //If there is a result, then username already exists (is taken).
            int i = 1;
            while (rs.next()) {
                usernameTaken = true;
                String userDB = rs.getString("username");
                
                i++;
            }
        }
        //If DB didn't successfully connect
        catch(Exception e)
        {
            System.out.println(e);
        }

        //Check if username and password are valid.
        if(username.length() < 3) {
            validUsername = false;
            System.out.println("Username needs to be at least 3 characters !!!");
        }
        if(password.length() < 5) {
            validPassword = false;
            System.out.println("Password needs to be at least 5 characters !!!");
        }
        if(usernameTaken == true) {
            System.out.println("Username Taken !!!");
        }
        
        //If Username and Password are valid, then save into the database.
        if(usernameTaken == false && validUsername == true && validPassword == true) {
            try {
                Connection con = db.getConnection();
                Statement stmt2 = con.createStatement();
                String query2 = "INSERT INTO users (username, hashToken) VALUES(\"" + username + "\", \"" + hashedToken + "\");";
                //ResultSet rs = stmt2.executeUpdate(query2);
                stmt2.executeUpdate(query2);
                System.out.println("Successfully Registered.");
            
                con.close();
            }
            catch(Exception e)
            {
                System.out.println(e);
            }
        }
    }

    //Functions to return whether username/passwords are valid.
    public Boolean usernameTaken() {
        return usernameTaken;
    }
    public Boolean validUsername() {
        return validUsername;
    }
    public Boolean validPassword() {
        return validPassword;
    }
}
