package controllers;

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
import javafx.stage.Stage;
import productivitycheckerapp.MFXResourcesLoader;
import productivitycheckerapp.User;
import productivitycheckerapp.database;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;
import static controllers.SceneController.scene;


public class SigninController implements Initializable {
    private Stage stage;
    @FXML
    private MFXTextField usernamelimit1;
    @FXML
    private Label usernamelabel;
    @FXML
    private MFXPasswordField passwordField;
    @FXML
    private Label notvalid;

    public static User loggedInUser;
    @FXML
    public void switchToSignUp(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(MFXResourcesLoader.loadURL("signup.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) usernamelimit1.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
    @FXML
    public void switchToHome() throws IOException {
        FXMLLoader loader = new FXMLLoader(MFXResourcesLoader.loadURL("home.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) usernamelimit1.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
    public void loginClick() throws SQLException, IOException {
        String uname = usernamelimit1.getText();
        String pass = passwordField.getText();
        database.connect();
        database.readDb();
        System.out.println(database.users.get(0).getUsername() + " " + database.users.get(0).getPassword());
        boolean valid = false;
        for (int i = 0; i < database.users.size(); i++) {
            if (Objects.equals(uname, database.users.get(i).getUsername()) && Objects.equals(pass, database.users.get(i).getPassword())) {
                valid = true;
                loggedInUser = database.users.get(i);
                break;
            }
        }
        if (valid){
            switchToHome();
            System.out.println("Valid ya a5oya");
        }else{
            System.out.println("Wrong Username or Password");
            notvalid.setVisible(true);
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SceneController.setFieldLimit(usernamelimit1, usernamelabel, 15);


    }
}
