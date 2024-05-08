package Database;

import java.sql.*;

public class CreateDB {
    Connection con;
    static String DB_URL = "jdbc:mysql://localhost/";
    static final String USER = "root";
    static final String PASS = "perez";

    public CreateDB() {
        createDatabaseTable();
    }

    public void createDatabaseTable() {
        try {
            //Connect to mysql account and create tally database if it does not currently exist.
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.con = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = con.createStatement();
            String query1 = "CREATE DATABASE IF NOT EXISTS tally";
            stmt.executeUpdate(query1);
            System.out.println("Tally Database created successfully..."); 

            //Connect to tally database
            DB_URL = "jdbc:mysql://localhost/tally";
            this.con = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected to Tally DB successfully.."); 

            //Create table "users" if it does not currently exist.
            Statement stmt3 = con.createStatement(); 
            String query3 = "CREATE TABLE IF NOT EXISTS users (username VARCHAR(50), hashToken VARCHAR(50))";
            stmt3.executeUpdate(query3);

            con.close();
        }

        catch(Exception e) {
            System.out.println(e);
        }
    }


    public Connection getConnection() {
        return this.con;
    }

    //Show users table.
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
