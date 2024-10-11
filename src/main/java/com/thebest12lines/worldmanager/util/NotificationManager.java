package com.thebest12lines.worldmanager.util;

public class NotificationManager {
    public static Notification createNotification(String title,String text) {
        return new Notification(title,text);
    }
    public static Notification createNotification(String text) {
        return new Notification(text);
    }
}
