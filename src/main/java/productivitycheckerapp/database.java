package productivitycheckerapp;

import java.sql.*;

public class database {

    static Connection connection;
    private static final String INSERT_SQL = "INSERT INTO USERS(UserName, Password) VALUES(?, ?)";

    public static void connect() throws SQLException {
        try {
            String jdbcUrl = "jdbc:sqlite:identifier.sqlite";
            connection = DriverManager.getConnection(jdbcUrl);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addUser(String userName, String password) throws SQLException {
        int nRows = 0;
        PreparedStatement ps = connection.prepareStatement(INSERT_SQL);
        Statement statement = connection.createStatement();
        ps.setString(1, userName);
        ps.setString(2, password);
        nRows = ps.executeUpdate();
    }
}
