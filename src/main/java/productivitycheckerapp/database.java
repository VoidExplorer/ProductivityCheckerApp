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

    public static void disconnect() throws SQLException {
        try {
            connection.close();
        } catch (SQLException e) {
            e.getCause();
        }
    }
    public static ArrayList<User> users = new ArrayList<>();

    public static void readDb() throws SQLException {
        String SELECT_USER = "SELECT * from users";
        try (PreparedStatement ps = connection.prepareStatement(SELECT_USER);
             ResultSet resultSet = ps.executeQuery()) {
            while (resultSet.next()) {
                ArrayList<Todo> todos = new ArrayList<>();
                String SELECT_TODO = "SELECT * from todos WHERE username = ?";
                try (PreparedStatement pss = connection.prepareStatement(SELECT_TODO)) {
                    pss.setString(1, resultSet.getString("username"));
                    try (ResultSet rresultSet = pss.executeQuery()) {
                        while (rresultSet.next()) {
                            Todo todo = new Todo(rresultSet.getInt("TodoId"), rresultSet.getString("Title"), rresultSet.getString("Description"));
                            String SELECT_TASK = "SELECT * from tasks WHERE TodoId = ?";
                            try (PreparedStatement psss = connection.prepareStatement(SELECT_TASK)) {
                                psss.setInt(1, rresultSet.getInt("TodoId"));
                                try (ResultSet rrresultSet = psss.executeQuery()) {
                                    while (rrresultSet.next()) {
                                        Task task = new Task(rrresultSet.getString("Task"), rrresultSet.getString("DueTime"));
                                        todo.addTask(task);
                                    }
                                }
                            }
                            todos.add(todo);
                        }
                    }
                }
                User user = new User(resultSet.getString("username"), resultSet.getString("password"), todos);
                users.add(user);
            }
        }
    }


    public static void readTodos() throws SQLException {
        String SELECT_TODO = "SELECT * from todos";
        PreparedStatement ps = connection.prepareStatement(SELECT_TODO);
        Statement statement = connection.createStatement();
        ResultSet resultSet = ps.executeQuery();
        System.out.println(resultSet.getString(2));
        while (resultSet.next()) {
            System.out.println(
                    resultSet.getString("username") + "\t" +
                            resultSet.getString("TodoID") +"\t" +
                            resultSet.getString("Title") +"\t" +
                            resultSet.getString("Description"));
        }


    }

    public static void addUser(String username, String password) throws SQLException {
        String INSERT_USER = "INSERT INTO users(username, password) VALUES(?, ?)";
        int nRows = 0;
        PreparedStatement ps = connection.prepareStatement(INSERT_USER);
        Statement statement = connection.createStatement();
        ps.setString(1, username);
        ps.setString(2, password);
        nRows = ps.executeUpdate();
    }

    public static void deleteUser(String username) throws SQLException {
        String DELETE_USER = "DELETE FROM users WHERE username = ?";
        int nRows = 0;
        PreparedStatement ps = connection.prepareStatement(DELETE_USER);
        Statement statement = connection.createStatement();
        ps.setString(1, username);
        nRows = ps.executeUpdate();
    }

    public static void addTodo(String username, String TodoID, String Title, String Description) throws SQLException {
        String INSERT_USER = "INSERT INTO users(username, TodoID, Title, Description) VALUES(?, ?, ?, ?)";
        int nRows = 0;
        PreparedStatement ps = connection.prepareStatement(INSERT_USER);
        Statement statement = connection.createStatement();
        ps.setString(1, username);
        ps.setString(2, TodoID);
        ps.setString(3, Title);
        ps.setString(4, Description);
        nRows = ps.executeUpdate();
    }
}
