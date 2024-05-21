package productivitycheckerapp;


import java.sql.*;
import java.util.ArrayList;

import static controllers.HomeController.*;

public class database {

    static Connection connection;

    public static void connect() {
        try {
            String jdbcUrl = "jdbc:sqlite:identifier.sqlite";
            connection = DriverManager.getConnection(jdbcUrl);
        } catch (SQLException e) {
            e.getCause();
        }
    }

    public static void disconnect() {
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
                                        Task task = new Task(rrresultSet.getString("Task"), rrresultSet.getString("DueTime"), rrresultSet.getBoolean("Status"));
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
        PreparedStatement ps = connection.prepareStatement(INSERT_USER);
        connection.createStatement();
        ps.setString(1, username);
        ps.setString(2, password);
        ps.setBoolean(3, student);
        ps.executeUpdate();
    }

    public static void deleteUser(String username) throws SQLException {
        String DELETE_USER = "DELETE FROM users WHERE username = ?";
        PreparedStatement ps = connection.prepareStatement(DELETE_USER);
        connection.createStatement();
        ps.setString(1, username);
        ps.executeUpdate();
    }

    public static void addTodo(String username, String Title, String Description) throws SQLException {
        String INSERT_USER = "INSERT INTO todos(username, Title, Description) VALUES(?, ?, ?)";
        PreparedStatement ps = connection.prepareStatement(INSERT_USER);
        connection.createStatement();
        ps.setString(1, username);
        ps.setString(2, Title);
        ps.setString(3, Description);
        ps.executeUpdate();
    }
    public static void deleteTodo(int TodoID) throws SQLException {
        String DELETE_TODO = "DELETE FROM todos WHERE TodoID = ?";
        PreparedStatement ps = connection.prepareStatement(DELETE_TODO);
        connection.createStatement();
        ps.setInt(1, TodoID);
        ps.executeUpdate();
    }
    public static void editTodo(String Title, String Description, int todoID) throws SQLException {
        String EDIT_TODO = "UPDATE todos SET Title =?, Description = ? WHERE TodoID= ?";
        PreparedStatement ps = connection.prepareStatement(EDIT_TODO);
        connection.createStatement();
        ps.setString(1, Title);
        ps.setString(2, Description);
        ps.setInt(3, todoID);
        ps.executeUpdate();
    }
public static void addTask(int id, String task, String dueDate) throws SQLException{
        String INSERT_TASK = "INSERT INTO tasks(TodoID, Task, DueTime) VALUES(?, ?, ?)";
        PreparedStatement ps = connection.prepareStatement(INSERT_TASK);
        connection.createStatement();
        ps.setInt(1, id);
        ps.setString(2, task);
        ps.setString(3, dueDate);
        ps.executeUpdate();
}
public static void deleteTask(int TaskID) throws SQLException {
        String DELETE_TASK = "DELETE FROM tasks WHERE TaskID = ?";
        PreparedStatement ps = connection.prepareStatement(DELETE_TASK);
        connection.createStatement();
        ps.setInt(1, TaskID);
        ps.executeUpdate();

}
public static void editTask(String task, String dueTime, int TaskID) throws SQLException{
        String EDIT_TASK = "UPDATE tasks SET task=?, dueTime=? WHERE TaskID=?";
        PreparedStatement ps = connection.prepareStatement(EDIT_TASK);
        connection.createStatement();
        ps.setString(1,task);
        ps.setString(2,dueTime);
        ps.setInt(3,TaskID);
        ps.executeUpdate();
    }
    public static void addLastTodo() throws SQLException {
        String SELECT_TODO = "select * from todos order by rowid desc LIMIT 1";
        PreparedStatement ps = connection.prepareStatement(SELECT_TODO);
        ResultSet rs = ps.executeQuery();
        int todoID =   rs.getInt("TodoID");
        String Title = rs.getString("Title");
        String description = rs.getString("Description");
        Todo todo = new Todo(todoID, Title, description);
        todos.add(todo);
    }

    public static void addLastTask() throws SQLException {
        String SELECT_TODO = "select * from tasks order by rowid desc LIMIT 1";
        PreparedStatement ps = connection.prepareStatement(SELECT_TODO);
        ResultSet rs = ps.executeQuery();
        String taskText = rs.getString("Task");
        boolean status =  rs.getBoolean("Status");
        String dueTime = rs.getString("DueTime");
        Task task = new Task(taskText, dueTime, status);
        todos.get(currentIndex).getTasks().add(task);
    }

    public static void reloadTodos(String username) throws SQLException{
        todos.clear();
        String LOAD_TODOS = "SELECT * FROM todos WHERE username = ?";
        PreparedStatement ps = connection.prepareStatement(LOAD_TODOS);
        ps.setString(1, username);
        ResultSet todoResultSet = ps.executeQuery();
        while (todoResultSet.next()) {
            int todoID =   todoResultSet.getInt("TodoID");
            String Title = todoResultSet.getString("Title");
            String description = todoResultSet.getString("Description");
            Todo todo = new Todo(todoID, Title, description);
            todos.add(todo);
        }
    }

    public static void updateTaskStatus(int TodoID, String taskText, boolean status) throws SQLException {
        String UPDATE_TASK = "UPDATE tasks SET Status = ? WHERE (TodoID, Task) = (?,?)";
        PreparedStatement ps = connection.prepareStatement(UPDATE_TASK);
        connection.createStatement();
        ps.setBoolean(1,status);
        ps.setInt(2,TodoID);
        ps.setString(3,taskText);
        ps.executeUpdate();
    }

    public static boolean checkStudent(String username) throws SQLException {
        String check = "SELECT Student FROM Users WHERE username = ?";
        PreparedStatement ps = connection.prepareStatement(check);
        connection.createStatement();
        ps.setString(1, username);
        ResultSet rs = ps.executeQuery();
        return rs.getBoolean("Student");
    }

    public static ArrayList<String> getCourses(String username) throws SQLException {
        String check = "SELECT courses FROM studentCourses WHERE username = ?";
        PreparedStatement ps = connection.prepareStatement(check);
        connection.createStatement();
        ps.setString(1, username);
        ResultSet rs = ps.executeQuery();
        ArrayList<String> courses = new ArrayList<>();
        while (rs.next()) {
            courses.add(rs.getString("Courses"));
        }
        return courses;
    }

    public static void reloadTasks(int todoID) throws SQLException{
        todos.get(todoID).getTasks().clear();
        String LOAD_TASK = "SELECT * FROM tasks WHERE TodoID = ?";
        PreparedStatement ps = connection.prepareStatement(LOAD_TASK);
        ps.setInt(1, todoID);
        ResultSet taskResultSet = ps.executeQuery();
        while (taskResultSet.next()) {
            String taskText = taskResultSet.getString("Task");
            Boolean status = taskResultSet.getBoolean("Status");
            String dueTime = taskResultSet.getString("DueTime");
            Task task = new Task(taskText, dueTime, status);
            todos.get(todoID).getTasks().add(task);
            System.out.println("added " + task.getTaskText());

        }
    }


}
   
    



