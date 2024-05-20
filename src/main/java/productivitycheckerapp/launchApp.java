package productivitycheckerapp;

import java.awt.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class launchApp {
    public static void main(String[] args) throws SQLException, AWTException {
        NotificationManager notificationManager = new NotificationManager("tip");
        notificationManager.sendNotification("في حالة العثور على اي مشكلة", "رستر الراوتر");
        LocalTime d = LocalTime.now();
        System.out.println(d);
        Main.main(args);
    }
}