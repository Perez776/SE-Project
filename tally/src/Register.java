import java.sql.*;

public class Register{
    String username;
    String password;
    Boolean usernameTaken = false;
    Boolean validUsername = true;
    Boolean validPassword = true;

    Register(String username, String password, ConnectDB db) {
        this.username = username;
        this.password = password;

        try {
            Connection con = db.getConnection();
            Statement stmt = con.createStatement();
            String query = "SELECT * FROM users WHERE username = \"" + username + "\";";
            ResultSet rs = stmt.executeQuery(query);

            int i = 1;
            while (rs.next()) {
                usernameTaken = true;
                String userDB = rs.getString("username");

                System.out.println("-------- Item " + i + " --------");
                System.out.println("username:  " + userDB);
                i++;
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }

        if(username.length() < 3) {
            validUsername = false;
            System.out.println("Username needs to be at least 3 characters !!!");
        }
        if(password.length() < 5) {
            validUsername = false;
            System.out.println("Password needs to be at least 5 characters !!!");
        }
        if(usernameTaken == true) {
            System.out.println("Username Taken !!!");
        }
        
        if(usernameTaken == false && validUsername == true && validPassword == true) {
            try {
                Connection con = db.getConnection();
                Statement stmt2 = con.createStatement();
                String query2 = "INSERT INTO users (username, password) VALUES(\"" + username + "\", \"" + password + "\");";
                //ResultSet rs = stmt2.executeUpdate(query2);
                stmt2.executeUpdate(query2);

                db.showTable();
            
                con.close();
            }
            catch(Exception e)
            {
                System.out.println(e);
            }
        }
    }



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
