package productivitycheckerapp;

import javafx.fxml.FXML;

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

    public static void deleteUser(User user) {
        removeUserfromDB();
    }


}
