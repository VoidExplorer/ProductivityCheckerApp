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
import productivitycheckerapp.database;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

public class SigninController implements Initializable {
    private Stage stage;
    private Scene scene;
    @FXML
    private MFXTextField usernamelimit1;
    @FXML
    private Label usernamelabel;
    @FXML
    private MFXPasswordField passwordField;
    @FXML
    public void switchToSignUp(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(MFXResourcesLoader.loadURL("signup.fxml"));
        Parent root = loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void switchToTodoPage() throws IOException {
        FXMLLoader loader = new FXMLLoader(MFXResourcesLoader.loadURL("TodoPageScreen.fxml"));
        Parent root = loader.load();
        stage = new Stage();
        scene = new Scene(root);
        stage.setScene(scene);
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
                break;
            }
        }
        if (valid){
            switchToTodoPage();
            System.out.println("Valid ya a5oya");
        }else{
            System.out.println("Wrong Username or Password");
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SceneController.setFieldLimit(usernamelimit1, usernamelabel, 15);

    }
}
