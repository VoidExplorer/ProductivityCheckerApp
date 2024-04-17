package productivitycheckerapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class database {

    public static void connect() throws SQLException {
        try {
            String jdbcUrl = "jdbc:sqlite:identifier.sqlite";
            Connection connection = DriverManager.getConnection(jdbcUrl);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
