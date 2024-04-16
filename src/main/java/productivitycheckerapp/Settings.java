package productivitycheckerapp;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;

import static productivitycheckerapp.Main.removeUserfromDB;

public class Settings {

    @FXML
    static String currentpassword;
    @FXML
    static String newpassword1;
    @FXML
    static String newpassword2;
    public static void changePassword(User user) {
        if(user.getPassword() == currentpassword)
            if (newpassword1 == newpassword2)
                user.setPassword(newpassword1);
            else
                System.out.println("Passwords don't match");
        else
            System.out.println("Incorrect password");
    }

    @FXML
    static CheckBox acknowledge;
    public static void deleteUser(User user) {
        if (user.getPassword() == currentpassword && acknowledge.isSelected())
            removeUserfromDB();
    }



}
