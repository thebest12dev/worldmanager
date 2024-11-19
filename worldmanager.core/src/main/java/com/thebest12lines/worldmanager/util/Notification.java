package com.thebest12lines.worldmanager.util;
import worldmanager.features.internal.CoreClass;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;
import java.io.IOException;

/**
 * A robust system to sending toast notifications. Works on both Windows and Ubuntu
 * @author thebest12lines
 */
@CoreClass
public class Notification {
    private String title;
    private String text;
    private TrayIcon ti;

    /**
     * Sets the text of the notification object.
     * @param text The text to set.
     * @return The new notification object with the settings applied.
     */
    public Notification setText(String text) {
        this.text = text;
        return this;
    }
    /**
     * Sets the title of the notification object.
     * @param title The text to set.
     * @return The new notification object with the settings applied.
     */
    public Notification setTitle(String title) {
        this.title = title;
        return this;
    }
    /**
     * Gets the text of the notification object.
     * @return The text.
     */
    public String getText() {
        return text;
    }

    /**
     * Gets the title of the notification object.
     * @return The title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Shows the <code>Notification</code> as a toast notification.
     * @throws AWTException
     */
    public void show() throws AWTException{
        //Obtain only one instance of the SystemTray object
        
        if (System.getProperty("os.name").contains("Windows")) {
            SystemTray tray = SystemTray.getSystemTray();
        
        //If the icon is a file
        Image image = Toolkit.getDefaultToolkit().createImage("");
        //Alternative (if the icon is on the classpath):
        //Image image = Toolkit.getDefaultToolkit().createImage(getClass().getResource("icon.png"));
        if (ti == null) {
            ti = new TrayIcon(image, "Program");
        }
        
        //Let the system resize the image if needed
        ti.setImageAutoSize(true);
        //Set tooltip text for the tray icon
        ti.setToolTip("System tray");
        tray.add(ti);

        ti.displayMessage(title, text, MessageType.INFO);
        
        } else if (System.getProperty("os.name").equals("Linux")) {
            String[] cmd = { "/usr/bin/notify-send",
            "-a",title,
                 "-t",
                 "10000",
                 text};
            try {
                Runtime.getRuntime().exec(cmd);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        
    }
    protected Notification(String title, String text) {
        this.text = text;
        this.title = title;
    }
    protected Notification(String text) {
        this.text = text;
        this.title = "Notification";
    }
}
