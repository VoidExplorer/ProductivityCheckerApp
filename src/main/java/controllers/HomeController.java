package controllers;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import productivitycheckerapp.MFXResourcesLoader;
import productivitycheckerapp.Todo;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static controllers.SceneController.scene;

public class HomeController implements Initializable {
    public static Stage newTodoStage;

    static VBox todosbox_;
    @FXML
    private VBox todosbox;
    private Stage stage;

    public void refreshTodos(ActionEvent e){
       todosbox.getChildren().clear();
       ArrayList<Todo> todos =  SigninController.loggedInUser.getTodos();
        for (Todo hometodo : todos) {
            HBox hbox = new HBox();
            hbox.setStyle("-fx-start-margin: 20px; -fx-end-margin: 20px");
            String todoTitle = hometodo.getTitle();
            MFXButton button = getButton(todoTitle);
            hbox.getChildren().add(button);
            todosbox.getChildren().add(hbox);
        }


    }
    public void newTodo(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader(MFXResourcesLoader.loadURL("NewTodo.fxml"));
        Parent root = loader.load();
        scene = new Scene(root);
        newTodoStage = new Stage();
        newTodoStage.setResizable(false);
        newTodoStage.setScene(scene);
        newTodoStage.show();
    }

    private static MFXButton getButton(String todoTitle) {
        MFXButton button = new MFXButton(todoTitle);
        button.setOnMouseClicked(mouseEvent -> {
            try {
                FXMLLoader loader = new FXMLLoader(MFXResourcesLoader.loadURL("TodoPageScreen.fxml"));
                Parent root = loader.load();

                Stage stage = (Stage) button.getScene().getWindow();
                scene = new Scene(root, scene.getWidth(), scene.getHeight());
                stage.setScene(scene);
                stage.show();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        return button;
    }
    public void EditTodo(ActionEvent e){

    }
    public void deleteTodo(ActionEvent e){

    }
    public void switchToAddTask(){

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ActionEvent onload = new ActionEvent();
        refreshTodos(onload);
        todosbox_ = todosbox;
    }
}
