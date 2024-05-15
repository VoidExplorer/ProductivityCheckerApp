package productivitycheckerapp;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class NotificationManager {

    private SystemTray systemTray = SystemTray.getSystemTray();
    private Image icon = Toolkit.getDefaultToolkit().getImage("icon.png");
    private TrayIcon trayIcon = (new TrayIcon(icon));
    String tooltip = "";
    TrayIcon.MessageType messageType = TrayIcon.MessageType.NONE;

    NotificationManager() throws AWTException {
        trayIcon.setImageAutoSize(true);
        trayIcon.setToolTip(tooltip);
        systemTray.add(trayIcon);
    }

    NotificationManager(String tooltip) throws AWTException {
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
        systemTray.remove(trayIcon);
    }

}
