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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
public class Main extends Application {


    @Override
    public void start(Stage stage) throws IOException {
        // to be changed later , the program will check if the user is already signed in and will load main page
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("signup.fxml")));
        Scene scene = new Scene(root);
        stage.setTitle("Productivity Checker App");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws SQLException {
        database.connect();
        database.readDb();
        System.out.println( database.users.get(0).getUsername());
        System.out.println( database.users.get(0).getTodos().get(0).getTitle());
        launch();
    }


}