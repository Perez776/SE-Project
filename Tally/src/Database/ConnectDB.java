package Database;
import java.sql.*;
public class ConnectDB {
    Connection con;
    static String DB_URL = "jdbc:mysql://localhost/tally";
    static final String USER = "root";
    static final String PASS = "perez";

    public ConnectDB() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.con = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected To DB successfully");
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }


    public Connection getConnection() {
        return this.con;
    }


    public void showTable() {
        try {
            Connection con = this.getConnection();
            Statement stmt = con.createStatement();
            String query = "SELECT * FROM users";
            ResultSet rs=stmt.executeQuery(query);

            int i = 1;
            System.out.println("Displaying Table");

            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();  

            while (rs.next()) {
                System.out.println("-------- Item " + i + " --------");

                for(int j = 1; j < columnCount + 1; j++)
                {
                    System.out.println(j + ":  " + rs.getString(j));
                }
                i++;
            }
            //con.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
}

