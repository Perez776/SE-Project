import java.sql.*;

public class Authenticate {

    String password;
    String username;
    String passwordDB;
    String userDB;
    Boolean valid;

    Authenticate(String username, String password) {
        this.username = username;
        this.password = password;
        this.valid = false;
    }

    public Boolean checkLogin() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/tally", "root", "perez");
           
            Statement stmt=con.createStatement();
            String query = "SELECT * FROM users WHERE username = \"" + username + "\" AND password = \"" + password + "\"" ;
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

        System.out.println("Incorrect Password!");
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
