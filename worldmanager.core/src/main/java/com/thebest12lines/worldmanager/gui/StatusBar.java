package com.thebest12lines.worldmanager.gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StatusBar {
    private JLabel statusBar;

    // Public constructor to inject the JLabel dependency
    public StatusBar(JLabel statusBar) {
        if (statusBar == null) {
            throw new IllegalArgumentException("StatusBar JLabel cannot be null");
        }
        this.statusBar = statusBar;
    }

    // Singleton instance initialized later
    private static StatusBar instance = new StatusBar(MainGui.statusLabel);

    public static StatusBar getStatusBar() {
        if (instance == null) {
            throw new IllegalStateException("StatusBar has not been initialized. Call initialize() first.");
        }
        return instance;
    }

    public void show(String text, int duration) {
        statusBar.setVisible(true);
        statusBar.setText(text);
        if (duration != DURATION_INFINITE) {
            new Timer(duration * 1000, new ActionListener() { // duration in milliseconds
                @Override
                public void actionPerformed(ActionEvent e) {
                    statusBar.setVisible(false);
                    ((Timer) e.getSource()).stop();
                }
            }).start();
        }
    }

    public static final int DURATION_SHORT = 5;
    public static final int DURATION_LONG = 10;
    public static final int DURATION_LONGER = 30;
    public static final int DURATION_INFINITE = -1;

}
