package LoginRegister;

import java.sql.*;

import Database.ConnectDB;

public class LoginModel {

    String password;
    String username;
    String passwordDB;
    String userDB;
    String token;
    Boolean valid;
    ConnectDB db;


    LoginModel(String username, String password, ConnectDB db) {
        this.username = username;
        this.password = password;
        this.valid = false;
        this.db = db;
    }



    public Boolean checkLogin() {
        try {
            Connection con = db.getConnection();
            Statement stmt = con.createStatement();
            //String query = "SELECT * FROM users WHERE BINARY username = \"" + username + "\" AND BINARY hashToken = \"" + password + "\"" ;
            
            String query = "SELECT * FROM users WHERE BINARY username = \"" + username + "\"" ;

            ResultSet rs=stmt.executeQuery(query);
    
            int i = 1;

            PasswordAuthentication passwordAuthentication = new PasswordAuthentication();

            while (rs.next()) {
                //this.valid = true;
                this.userDB = rs.getString("username");
                this.token = rs.getString("hashToken");
                
                System.out.println("Username: " + this.username);

                this.valid = passwordAuthentication.authenticate(password, this.token);

                i++;
            }

            con.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }

        if(this.valid == true)
        {
            System.out.println("Login Successful !\n");
            return true;
        }

        System.out.println("Login Failed \n");
        return false;
    }

    

    public Boolean validUser()
    {
        return this.valid;
    }
}
