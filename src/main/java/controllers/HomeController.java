package controllers;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXCheckbox;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import productivitycheckerapp.*;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static controllers.SceneController.scene;

public class HomeController implements Initializable {
    public static Stage tempStage;

    static VBox todosbox_;
    @FXML
    private VBox todosbox;;
    @FXML
    private Label TODOTITLE;
    @FXML
    private Label TODODESC;
    @FXML
    private VBox cboxes;

    static int currentTodoID;
    public static int currentIndex;

    public static ArrayList<Todo> todos =  SigninController.loggedInUser.getTodos();



    public static void refreshTodos(){
        todosbox_.getChildren().clear();

        for (int i = 0; i < todos.size(); i++) {
            HBox hbox = new HBox();
            hbox.setStyle("-fx-start-margin: 20px; -fx-end-margin: 20px");
            String todoTitle = todos.get(i).getTitle();
            MFXButton button = getButton(todoTitle,i);
            hbox.getChildren().add(button);
            todosbox_.getChildren().add(hbox);
        }
        currentTodoID = todos.getFirst().getId();


    }
    static Label TODOTITLE_;
    static Label TODODESC_;
    static VBox cboxes_;


    private static MFXButton getButton(String todoTitle, int indx) {
        MFXButton button = new MFXButton(todoTitle);
        button.setOnMouseClicked(mouseEvent -> {
            TODOTITLE_.setText(todoTitle);
            Todo todo = todos.get(indx);
            currentIndex = indx;
            TODODESC_.setText(todo.getDescription());
            cboxes_.getChildren().clear();
            currentTodoID = todo.getId();
            for (int i = 0; i < todo.getTasks().size(); i++) {
                Task task = todo.getTasks().get(i);
                MFXCheckbox taskCheckbox = new MFXCheckbox(task.getTaskText() + " | Due: " + task.getDueTime());
                String text = taskCheckbox.getText();
                String dueDate = text.substring(text.indexOf("|") + 7, text.lastIndexOf("at")-1);
                System.out.println("dueDate: " + dueDate);
                String dueTime = text.substring(text.lastIndexOf(" ") + 1);
                System.out.println("dueTime: " + dueTime);
                DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMM d, yyyy");
                DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("H:m");
                LocalDate taskDate = LocalDate.parse(dueDate, dateFormatter);
                LocalTime taskTime = LocalTime.parse(dueTime, timeFormatter);
                LocalDateTime taskDateTime = LocalDateTime.of(taskDate, taskTime);
                if (taskDateTime.isBefore(LocalDateTime.now())) {
                    taskCheckbox.setText(taskCheckbox.getText() + "\tDEADLINE HAS PASSED");
                } else {
                    try {
                        NotificationManager notificationManager = new NotificationManager("Task");
                        notificationManager.scheduleNotification(text, "Deadline has been reached", taskDateTime);
                    } catch (AWTException e) {
                        throw new RuntimeException(e);
                    }
                }
                System.out.println(task.isCompleted());
                taskCheckbox.setSelected(task.isCompleted());
                cboxes_.getChildren().add(taskCheckbox);
                int finalI = i;
                taskCheckbox.selectedProperty().addListener((observable, oldValue, newValue) -> {
                    if(observable.getValue()) {
                        System.out.println("Checkbox with title " + taskCheckbox.getText() + " was selected");
                        try {
                            database.updateTaskStatus(currentTodoID, text, true);
                            todos.get(currentIndex).getTasks().get(finalI).checkCompleted();
                        } catch (SQLException e) {
                            System.out.println("Database error: " + e.getMessage());
                        }
                        refreshTodos();
                    }
                    else {
                        System.out.println("Checkbox with title " + taskCheckbox.getText() + " was unselected");
                        try {
                            database.updateTaskStatus(currentTodoID, text, false);
                            todos.get(currentIndex).getTasks().get(finalI).uncheckCompleted();
                        } catch (SQLException e) {
                            System.out.println("Database error: " + e.getMessage());
                        }
                        refreshTodos();
                    }
                });
            }

        });
        return button;
    }

    public static void updateCurrentTodo() {

    }



    public void newTodo() throws IOException {
        FXMLLoader loader = new FXMLLoader(MFXResourcesLoader.loadURL("NewTodo.fxml"));
        Parent root = loader.load();
        scene = new Scene(root);
        tempStage = new Stage();
        tempStage.setResizable(false);
        tempStage.setScene(scene);
        tempStage.show();
    }
    public void newTask() throws IOException {
        FXMLLoader loader = new FXMLLoader(MFXResourcesLoader.loadURL("newTask.fxml"));
        Parent root = loader.load();
        scene = new Scene(root);
        tempStage = new Stage();
        tempStage.setResizable(false);
        tempStage.setScene(scene);
        tempStage.show();
    }

    public void EditTodo() throws IOException {
        FXMLLoader loader = new FXMLLoader(MFXResourcesLoader.loadURL("EditTodo.fxml"));
        Parent root = loader.load();
        scene = new Scene(root);
        tempStage = new Stage();
        tempStage.setResizable(false);
        tempStage.setScene(scene);
        tempStage.show();
    }
    public void deleteTodo() throws SQLException {
        database.deleteTodo(currentTodoID);
        todos.remove(currentIndex);
        refreshTodos();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        todosbox_ = todosbox;
        cboxes_ = cboxes;
        TODOTITLE_ = TODOTITLE;
        TODODESC_ = TODODESC;
        MFXCheckbox chbx = new MFXCheckbox();
        cboxes.getChildren().add(chbx);
        refreshTodos();
    }
}
