package productivitycheckerapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class database {

    public static void connect() throws SQLException {
        try {
            String jdbcurl = "jdbc:sqlite:identifier.sqlite";
            Connection connection = DriverManager.getConnection(jdbcurl);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
