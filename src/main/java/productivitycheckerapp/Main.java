package productivitycheckerapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

public class Main extends Application {
    public static void readDb(){
        ArrayList<Todo> todos = new ArrayList<>();
        ArrayList<User> users = new ArrayList<>();
        
    }
    public static void addUserToDB(){

    }

    public static void removeUserfromDB() {

    }
    public static void addTodoToDB(){

    }
    public static void addTaskToDB(){

    }
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("welcome.fxml")));
        Scene scene = new Scene(root);
        stage.setTitle("Productivity Checker App");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws SQLException {
        database.connect();
        database.addUser("Ali", "bstrma");
        readDb();
        launch();
    }


}