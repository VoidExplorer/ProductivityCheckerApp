package productivitycheckerapp;

import java.sql.*;
import java.util.ArrayList;

public class database {

    static Connection connection;

    public static void connect() throws SQLException {
        try {
            String jdbcUrl = "jdbc:sqlite:identifier.sqlite";
            connection = DriverManager.getConnection(jdbcUrl);
        } catch (SQLException e) {
            e.getCause();
        }
    }
    public static void readDb() throws SQLException {
        ArrayList<User> users = new ArrayList<>();
        String SELECT_USER = "SELECT * from users";
        PreparedStatement ps = connection.prepareStatement(SELECT_USER);
        Statement statement = connection.createStatement();
        ResultSet resultSet = ps.executeQuery();
        System.out.println(resultSet.getString(2));
        while (resultSet.next()) {
            System.out.println(
                    resultSet.getString("UserName") + "\t" +
                            resultSet.getString("Password"));
        }
    }
    public static void addUser(String userName, String password) throws SQLException {
        String INSERT_USER = "INSERT INTO users(UserName, Password) VALUES(?, ?)";
        int nRows = 0;
        PreparedStatement ps = connection.prepareStatement(INSERT_USER);
        Statement statement = connection.createStatement();
        ps.setString(1, userName);
        ps.setString(2, password);
        nRows = ps.executeUpdate();
    }

    public static void deleteUser(String userName) throws SQLException {
        String DELETE_USER = "DELETE FROM users WHERE UserName = ?";
        int nRows = 0;
        PreparedStatement ps = connection.prepareStatement(DELETE_USER);
        Statement statement = connection.createStatement();
        ps.setString(1, userName);
        nRows = ps.executeUpdate();
    }
}
