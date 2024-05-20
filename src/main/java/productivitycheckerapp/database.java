package productivitycheckerapp;

import controllers.SigninController;

import java.sql.*;
import java.util.ArrayList;

import static controllers.HomeController.todos;

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




    public static void addUser(String username, String password, boolean student) throws SQLException {
        String INSERT_USER = "INSERT INTO users(username, password, Student) VALUES(?, ?, ?)";
        int nRows = 0;
        PreparedStatement ps = connection.prepareStatement(INSERT_USER);
        Statement statement = connection.createStatement();
        ps.setString(1, username);
        ps.setString(2, password);
        ps.setBoolean(3, student);
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

    public static void addTodo(String username, String Title, String Description) throws SQLException {
        String INSERT_USER = "INSERT INTO todos(username, Title, Description) VALUES(?, ?, ?)";
        int nRows = 0;
        PreparedStatement ps = connection.prepareStatement(INSERT_USER);
        Statement statement = connection.createStatement();
        ps.setString(1, username);
        ps.setString(2, Title);
        ps.setString(3, Description);
        nRows = ps.executeUpdate();
    }
    public static void deleteTodo(int TodoID) throws SQLException {
        String DELETE_TODO = "DELETE FROM todos WHERE TodoID = ?";
        int nRows = 0;
        PreparedStatement ps = connection.prepareStatement(DELETE_TODO);
        Statement statement = connection.createStatement();
        ps.setInt(1, TodoID);
        nRows = ps.executeUpdate();
    }
    public static void editTodo(String Title, String Description, int todoID) throws SQLException {
        String EDIT_TODO = "UPDATE todos SET Title =?, Description = ? WHERE TodoID= ?";
        PreparedStatement ps = connection.prepareStatement(EDIT_TODO);
        connection.createStatement();
        ps.setString(1, Title);
        ps.setString(2, Description);
        ps.setInt(3, todoID);
        ps.executeUpdate();
        //...
    }
public static void addTask(String task, String dueDate) throws SQLException{
        String INSERT_TASK = "INSERT INTO tasks(Task, DueTime) VALUES(?, ?)";
        int nRows = 0;
        PreparedStatement ps = connection.prepareStatement(INSERT_TASK);
        Statement statement = connection.createStatement();
        ps.setString(1, task);
        ps.setString(2, dueDate);

        nRows = ps.executeUpdate();


}
public static void deleteTask(int TaskID) throws SQLException {
        String DELETE_TASK = "DELETE FROM tasks WHERE TaskID = ?";
        int nRows = 0;
        PreparedStatement ps = connection.prepareStatement(DELETE_TASK);
        Statement statement = connection.createStatement();
        ps.setInt(1, TaskID);
        nRows = ps.executeUpdate();

}
public static void editTask(String task, String dueTime, int TaskID) throws SQLException{
        String EDIT_TASK = "UPDATE tasks SET task=?, dueTime=? WHERE TaskID=?";
        int nRows =0;
        PreparedStatement ps = connection.prepareStatement(EDIT_TASK);
        Statement statement = connection.createStatement();
        ps.setString(1,task);
        ps.setString(2,dueTime);
        ps.setInt(3,TaskID);
        nRows=ps.executeUpdate();
    }
    public static void addLastTodo() throws SQLException {
        String SELECT_TODO = "select * from todos order by rowid desc LIMIT 1";
        PreparedStatement ps = connection.prepareStatement(SELECT_TODO);
        ResultSet rs = ps.executeQuery();
        String username = rs.getString("username");
        int todoID =   rs.getInt("TodoID");
        String Title = rs.getString("Title");
        String description = rs.getString("Description");
        Todo todo = new Todo(todoID, Title, description);
        todos.add(todo);
    }

    public static void reloadTodos() throws SQLException{
        todos.clear();
        String LOAD_TODOS = "SELECT * FROM todos";
        PreparedStatement ps = connection.prepareStatement(LOAD_TODOS);
        ResultSet todoResultSet = ps.executeQuery();
        while (todoResultSet.next()) {
            int todoID =   todoResultSet.getInt("TodoID");
            String Title = todoResultSet.getString("Title");
            String description = todoResultSet.getString("Description");
            Todo todo = new Todo(todoID, Title, description);
            todos.add(todo);
        }
    }
}
   
    



