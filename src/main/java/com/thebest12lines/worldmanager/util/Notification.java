package com.thebest12lines.worldmanager.util;
import java.awt.AWTException;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;
import java.io.IOException;


public class Notification {
    private String title;
    private String text;
    private TrayIcon ti;
    public Notification setText(String text) {
        this.text = text;
        return this;
    }
    public Notification setTitle(String title) {
        this.title = title;
        return this;
    }
    public String getText() {
        return text;
    }
    public String getTitle() {
        return title;
    }
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
