import java.sql.*;

public class Login {

    String password;
    String username;
    String passwordDB;
    String userDB;
    Boolean valid;
    ConnectDB db;

    Login(String username, String password, ConnectDB db) {
        this.username = username;
        this.password = password;
        this.valid = false;
        this.db = db;
    }

    public Boolean checkLogin() {
        try {
            Connection con = db.getConnection();
            Statement stmt = con.createStatement();
            String query = "SELECT * FROM users WHERE BINARY username = \"" + username + "\" AND BINARY password = \"" + password + "\"" ;
            ResultSet rs=stmt.executeQuery(query);
    
            int i = 1;

            while (rs.next()) {
                this.valid = true;
                this.userDB = rs.getString("username");
                this.passwordDB = rs.getString("password");

                System.out.println("-------- Item " + i + " --------");
                System.out.println("username:  " + userDB);
                System.out.println("password:  " + passwordDB);
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
            System.out.println("Login Successful !");
            return true;
        }

        System.out.println("Login Failed");
        return false;
    }

    public Boolean validUser()
    {
        if(this.valid == true)
        {
            return true;
        }
        return false;
    }
}
