package productivitychecckerapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.util.Objects;

public class Main extends Application {
    static String UserName = "Abdelrahman125"; // will be changed later
    public static void readDb(){
        ArrayList<Todo> todos = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(Main.class.getResource("Todos.csv").getPath()))) {
            String line;
            while ((line = br.readLine()) != null) {
                // assign data to objects
                String[] dataFields = line.split(",");  // Split by comma
                if (Objects.equals(dataFields[0], UserName) || Objects.equals(dataFields[0], "Fares")){
                    Todo todo = new Todo(dataFields[2]);
                    todos.add(todo);
                }
            }
        } catch (IOException e) {
            // Handle exception
            e.printStackTrace();
        }
        System.out.println(todos.get(0).getTitle());
        System.out.println(todos.get(1).getTitle());


    }
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        readDb();
        launch();
    }
}