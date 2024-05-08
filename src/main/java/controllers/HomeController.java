package controllers;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import productivitycheckerapp.MFXResourcesLoader;
import productivitycheckerapp.Todo;
import productivitycheckerapp.User;
import productivitycheckerapp.database;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;
import static controllers.SceneController.scene;
public class HomeController implements Initializable {
    private Stage stage;
    @FXML
    private VBox todosbox;

    public void refreshTodos(ActionEvent e){
       todosbox.getChildren().clear();
       ArrayList<Todo> hometodos  =  SigninController.loggedInUser.getTodos();
       for (int i = 0; i < hometodos.size(); i++) {
           HBox hbox = new HBox();
           hbox.setStyle("-fx-start-margin: 20px; -fx-end-margin: 20px");
           MFXButton button = new MFXButton(hometodos.get(i).getTitle());
           hbox.getChildren().add(button);
           todosbox.getChildren().add(hbox);
       }


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
