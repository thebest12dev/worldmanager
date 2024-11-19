package com.thebest12lines.worldmanager.util;

import worldmanager.features.internal.CoreClass;

/**
 * Class to create the <code>Notification</code> object.
 * @author thebest12lines
 */
@CoreClass
public class NotificationManager {
    /**
     * Creates the notification.
     * @param title The title of the notification.
     * @param text the text of the notification.
     * @return The notification created.
     */
    public static Notification createNotification(String title,String text) {
        return new Notification(title,text);
    }
    /**
     * Creates the notification.
     * @param text The text of the notification.
     * @return The notification created.
     */
    public static Notification createNotification(String text) {
        return new Notification(text);
    }
}
