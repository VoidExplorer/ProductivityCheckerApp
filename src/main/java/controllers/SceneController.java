package controllers;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBasicCloseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import productivitycheckerapp.MFXResourcesLoader;
import productivitycheckerapp.database;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

public class SceneController {
    private Stage stage;
    private Scene scene;

    @FXML
    public void switchToSignUp(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(MFXResourcesLoader.loadURL("signup.fxml"));
        Parent root = loader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private void signup() throws SQLException {
        String username = usernameField.getText();
        String password = passwordField.getText();
        database.addUser(username, password);
    }

    public void switchToSettings(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("settings.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void reportabug(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("reportabug.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void addTask(ActionEvent event) throws IOException {
        Stage popup = new Stage();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("settings.fxml")));
        Scene scene1 = new Scene(root);
        popup.setTitle("Add Note");
        popup.setScene(scene1);
        popup.show();
    }

    @FXML
    private JFXHamburger menu;
    @FXML
    private AnchorPane anchorPane;

    @FXML
    private JFXDrawer drawer;

    @FXML
    private VBox drawerPane;

    @FXML
    private AnchorPane drawerBack;
    @FXML
    private VBox drawerPaneTest;
    private static boolean visible;



    }



