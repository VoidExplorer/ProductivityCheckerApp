package controllers;


import com.jfoenix.controls.JFXButton;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import productivitycheckerapp.MFXResourcesLoader;
import productivitycheckerapp.database;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class SignupController implements Initializable {
    private Stage stage;
    private Scene scene;


    @FXML
    private MFXTextField usernameField;
    @FXML
    private MFXPasswordField passwordField;
    @FXML
    private MFXPasswordField confirmpasswordField;
    @FXML
    private JFXButton signup;
    @FXML
    private Label usernameLimitLabel;
    @FXML
    private Label passwordLimitLabel;

    @FXML
    public void switchToSignIn(ActionEvent event) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader(MFXResourcesLoader.loadURL("signin.fxml"));
        Parent root = loader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void signUp(ActionEvent event) throws SQLException {
        // confirm that the limit label isn't visible to make sure that the length is less the maximum
        if (usernameLimitLabel.isVisible())
            System.out.println("Maximum username limit reached");
        else if (passwordLimitLabel.isVisible())
            System.out.println("Maximum password limit reached");
        else {
            if (passwordField.getText().equals(confirmpasswordField.getText()) ) {
                String username = usernameField.getText();
                String password = passwordField.getText();
                database.addUser(username, password);
            }
            else
                System.out.println("Passwords do not match");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Initializing");
        SceneController.setFieldLimit(usernameField, usernameLimitLabel, 15);
        SceneController.setFieldLimit(passwordField, passwordLimitLabel, 15);
    }
}
