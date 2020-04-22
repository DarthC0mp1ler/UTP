package Implementation;

import java.sql.*;
public class Connector {

    public static Connection getConnection(){
        Connection connection = null;
        try
        {

            System.out.println("CONNECTING TO DATABASE");
            Class.forName("oracle.jdbc.OracleDriver");
            connection = DriverManager.getConnection(
                    "jdbc:oracle:thin:@db-oracle.pjwstk.edu.pl:1521:baza",
                    "s19204",
                    "oracle12"
            );
            System.out.println("CONNECTED TO DATABASE");

        }
        catch (ClassNotFoundException | SQLException e)
        {
            e.printStackTrace();
        }
        return connection;
    }

}
