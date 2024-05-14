package controllers;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXCheckbox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import productivitycheckerapp.MFXResourcesLoader;
import productivitycheckerapp.Task;
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
    @FXML
    private Label TODOTITLE;
    @FXML
    private Label TODODESC;
    @FXML
    private static HBox hbox1;
    @FXML
    private VBox cboxes;
    @FXML
    private HBox footerbox;

    ArrayList<Todo> todos =  SigninController.loggedInUser.getTodos();
    public void refreshTodos(ActionEvent e){
       todosbox.getChildren().clear();

        for (int i = 0; i < todos.size(); i++) {
            HBox hbox = new HBox();
            hbox.setStyle("-fx-start-margin: 20px; -fx-end-margin: 20px");
            String todoTitle = todos.get(i).getTitle();
            MFXButton button = getButton(todoTitle,i);
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

    private  MFXButton getButton(String todoTitle, int indx) {
        MFXButton button = new MFXButton(todoTitle);
        button.setOnMouseClicked(mouseEvent -> {
            TODOTITLE.setText(todoTitle);
            Todo todo = todos.get(indx);
            TODODESC.setText(todo.getDescription());
            cboxes.getChildren().clear();
            for (int i = 0; i < todo.getTasks().size(); i++) {
                Task task = todo.getTasks().get(i);
                MFXCheckbox mfxcb = new MFXCheckbox(task.getTaskText() + " | Due: " + task.getDueTime());
                mfxcb.setSelected(task.isCompleted());
            }

        });
        return button;
    }
    public void EditTodo(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader(MFXResourcesLoader.loadURL("EditTodo.fxml"));
        Parent root = loader.load();
        scene = new Scene(root);
        newTodoStage = new Stage();
        newTodoStage.setResizable(false);
        newTodoStage.setScene(scene);
        newTodoStage.show();
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
