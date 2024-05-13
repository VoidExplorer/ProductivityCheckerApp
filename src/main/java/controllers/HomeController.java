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
import javafx.stage.Stage;
import productivitycheckerapp.MFXResourcesLoader;
import productivitycheckerapp.Todo;

import java.io.IOException;
import javafx.scene.control.Label;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static controllers.SceneController.scene;

public class HomeController implements Initializable {

    static VBox todosbox_; // idk what is that blame fares (faris)
    @FXML
    private VBox todosbox;
    private Stage stage;

    @FXML
    private Label TODOTITLE;
    @FXML
    private Label TODODESC;
    @FXML
    private static HBox hbox1;


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
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();

    }

    private MFXButton getButton(String todoTitle) {
        MFXButton button = new MFXButton(todoTitle);
        button.setOnMouseClicked(mouseEvent -> {
            TODOTITLE.setText("Hello tttttest");
            TODODESC.setText("fucking good description nothing better than this description");
        });

        return button;

    }
    public void EditTodo(ActionEvent e){
        // do nothing
    }
    public void deleteTodo(ActionEvent e){
        //good
    }
    public void switchToAddTask(ActionEvent e){

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ActionEvent onload = new ActionEvent();
        refreshTodos(onload);
        todosbox_ = todosbox;

    }
}
