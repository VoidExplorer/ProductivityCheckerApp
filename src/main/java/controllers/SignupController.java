package controllers;


import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.controls.MFXToggleButton;
import io.github.palexdev.materialfx.validation.Constraint;
import io.github.palexdev.materialfx.validation.Severity;
import javafx.beans.binding.Bindings;
import javafx.css.PseudoClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import productivitycheckerapp.*;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

import static controllers.HomeController.refreshTodos;
import static controllers.SceneController.scene;
import static controllers.SigninController.isStudent;
import static controllers.SigninController.loggedInUser;
import static io.github.palexdev.materialfx.utils.StringUtils.containsAny;


public class SignupController implements Initializable {


    @FXML
    private MFXTextField usernameField;
    @FXML
    private MFXPasswordField passwordField;
    @FXML
    private MFXPasswordField confirmpasswordField;
    @FXML
    private Label passwordLimitLabel;
    @FXML
    private Label passwordMatchLabel;
    @FXML
    private MFXToggleButton studentToggle;


    private static final PseudoClass INVALID = PseudoClass.getPseudoClass("invalid");
    // define string patterns
    private static final String[] upperChar = "A B C D E F G H I J K L M N O P Q R S T U V W X Y Z".split(" ");
    private static final String[] lowerChar = "a b c d e f g h i j k l m n o p q r s t u v w x y z".split(" ");
    private static final String[] digits = "0 1 2 3 4 5 6 7 8 9".split(" ");
    private static final String[] specialCharacters = "! @ # & ( ) – [ { } ]: ; ' , ? / * ~ $ ^ + = < > -".split(" ");
    boolean strongPassword = false;
    boolean passwordMatch = false;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Constraint lengthConstraint = Constraint.Builder.build()
                .setSeverity(Severity.ERROR)
                .setMessage("Password must be at least 5 characters long")
                .setCondition(passwordField.textProperty().length().greaterThanOrEqualTo(5))
                .get();

        Constraint specialCharConstraint = Constraint.Builder.build()
                .setSeverity(Severity.ERROR)
                .setMessage("Password must contain at least 1 special character")
                .setCondition(Bindings.createBooleanBinding(
                        () -> containsAny(passwordField.getText(), "", specialCharacters),
                        passwordField.textProperty()
                ))
                .get();

        Constraint digitConstraint = Constraint.Builder.build()
                .setSeverity(Severity.ERROR)
                .setMessage("Password must contain at least 1 digit")
                .setCondition(Bindings.createBooleanBinding(
                        () -> containsAny(passwordField.getText(), "", digits),
                        passwordField.textProperty()
                ))
                .get();

        Constraint lowerCharConstraint = Constraint.Builder.build()
                .setSeverity(Severity.ERROR)
                .setMessage("Password must at least contain 1 lowercase character")
                .setCondition(Bindings.createBooleanBinding(
                        () -> containsAny(passwordField.getText(), "", lowerChar),
                        passwordField.textProperty()
                ))
                .get();

        Constraint upperCharConstraint = Constraint.Builder.build()
                .setSeverity(Severity.ERROR)
                .setMessage("Password must at least contain 1 uppercase character")
                .setCondition(Bindings.createBooleanBinding(
                        () -> containsAny(passwordField.getText(), "", upperChar),
                        passwordField.textProperty()
                ))
                .get();

        passwordField.getValidator()
                .constraint(lengthConstraint)
                .constraint(specialCharConstraint)
                .constraint(digitConstraint)
                .constraint(lowerCharConstraint)
                .constraint(upperCharConstraint);

        passwordField.getValidator().validProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                passwordLimitLabel.setVisible(false);
                passwordField.pseudoClassStateChanged(INVALID, false);
                strongPassword = true;
            }
        });

        passwordField.delegateFocusedProperty().addListener((observable, oldValue, newValue) -> {
            if(oldValue && !newValue) {
                List<Constraint> constraints = passwordField.validate();
            if(!constraints.isEmpty()) {
                passwordField.pseudoClassStateChanged(INVALID, true);
                passwordLimitLabel.setText(constraints.getFirst().getMessage());
                passwordLimitLabel.setVisible(true);
                strongPassword = false;
            }
            }
        });

        studentToggle.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if(observable.getValue()) {
                studentToggle.setText("Student");
            }
            else {
                studentToggle.setText("User");
            }
        });


        confirmpasswordField.textProperty().addListener((observable, oldValue, newValue) -> {
            if(observable.getValue().equals(passwordField.getText()) || observable.getValue().isEmpty()) {
                passwordMatchLabel.setVisible(false);
                confirmpasswordField.setStyle("-fx-border-color: #594BE8");
                passwordMatch = true;
            }
            else {
                passwordMatchLabel.setVisible(true);
                confirmpasswordField.setStyle("-fx-border-color: red");
                passwordMatch = false;
            }
        });
    }

    @FXML
    public void switchToSignIn(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(MFXResourcesLoader.loadURL("signin.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root, scene.getWidth(), scene.getHeight());
        stage.setScene(scene);
    }
    @FXML
    public void switchToTodoPage() throws IOException {
        FXMLLoader loader = new FXMLLoader(MFXResourcesLoader.loadURL("home.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) usernameField.getScene().getWindow();
        scene = new Scene(root, scene.getWidth(), scene.getHeight());
        stage.setScene(scene);
    }

    public void register() throws SQLException, IOException {
        String uname = usernameField.getText();
        String pass = passwordField.getText();
        String cpass = confirmpasswordField.getText();
        database.readDb();
        boolean userExist = false;
        if (strongPassword && passwordMatch) {
            for (int i = 0; i < database.users.size(); i++) {
                if (Objects.equals(database.users.get(i).getUsername(), uname)) {
                    userExist = true;
                    break;
                }
            }
            if (!userExist && Objects.equals(pass, cpass)) {
                database.addUser(uname, pass, studentToggle.selectedProperty().getValue());
                database.addTodo(uname, ("Welcome, " + uname),
                        "This is a sample Todo to help get you started");
                database.readDb();
                loggedInUser = database.users.getLast();
                System.out.println("Logged in user: " + loggedInUser.getUsername());
                isStudent = database.checkStudent(uname);
                database.reloadTodos(uname);
                switchToTodoPage();
                refreshTodos();
            } else {
                if (userExist) {
                    System.out.println("user already exist");
                } else if (!Objects.equals(pass, cpass)) {
                    System.out.println("Passwords doesn't match");
                }
            }
        }
    }
}
