import java.sql.*;

public class sql {
public static void main(String[] args) {

    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurant", "perez", "password");
        Statement stmt=con.createStatement();
        ResultSet rs=stmt.executeQuery("select * from foodmenu");

        int i = 1;
        while (rs.next()) {
            System.out.println("-------- Item " + i + " --------");
            System.out.println("Food:  "+ rs.getString(1));
            System.out.println("ID:    " + rs.getString(2));
            i++;
        }

        con.close();
    }
    catch(Exception e)
    {
        System.out.println(e);
    }
}
}
