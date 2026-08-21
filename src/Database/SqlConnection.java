package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqlConnection {
    public static String getUrl () {
        return "jdbc:oracle:thin:@localhost:1521/XEPDB1";
    }
    public static String getUsername () {
        return "hamid";
    }

    public static String getPassword () {
        return "hamids";
    }

    public static Connection sqlConn () {
        Connection sql = null;
        try {
            sql = DriverManager.getConnection(getUrl(), getUsername(), getPassword());
        }catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
        return sql;
    }
}
