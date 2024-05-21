package productivitycheckerapp;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.time.*;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class NotificationManager {

    private SystemTray systemTray = SystemTray.getSystemTray();
    private Image icon = Toolkit.getDefaultToolkit().getImage("Todo.png");
    private TrayIcon trayIcon = (new TrayIcon(icon));
    String tooltip = "";
    TrayIcon.MessageType messageType = TrayIcon.MessageType.NONE;

    NotificationManager() throws AWTException {
        trayIcon.setImageAutoSize(true);
        trayIcon.setToolTip(tooltip);
        systemTray.add(trayIcon);
    }

    public NotificationManager(String tooltip) throws AWTException {
        this.tooltip = tooltip;
        trayIcon.setImageAutoSize(true);
        trayIcon.setToolTip(tooltip);
        systemTray.add(trayIcon);
    }

    NotificationManager(String tooltip, TrayIcon.MessageType messageType) throws AWTException {
        this.tooltip = tooltip;
        this.messageType = messageType;
        trayIcon.setImageAutoSize(true);
        trayIcon.setToolTip(tooltip);
        systemTray.add(trayIcon);
    }



    public void sendNotification(String title, String message) {
        trayIcon.displayMessage(title, message, messageType);
    }

    public void scheduleNotification(String title, String message, LocalDateTime dateTime) {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        Runnable task = () -> {
            sendNotification(title, message);
            System.out.println("Notification sent");
            scheduler.shutdown();
        };

        //LocalTime time = LocalTime.parse("05:05:00");
        //LocalDateTime dateTime = LocalDateTime.of(date, time);
        Date executionDate = Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
        long delay = executionDate.getTime() - System.currentTimeMillis();

        if (delay > 0) {
            scheduler.schedule(task, delay, TimeUnit.MILLISECONDS);
        } else {
            System.err.println("The time you have set has already passed");
            scheduler.shutdown();
        }

        //scheduler.schedule(task, delay, TimeUnit.MILLISECONDS);

    }
    }


