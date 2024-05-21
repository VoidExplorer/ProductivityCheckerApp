package productivitycheckerapp;

import java.awt.*;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class launchApp {


    public static void main(String[] args) throws AWTException {
        NotificationManager manager = new NotificationManager("test");
        LocalDateTime time = LocalDateTime.now();
        manager.sendNotification("test", "test");
    }

}