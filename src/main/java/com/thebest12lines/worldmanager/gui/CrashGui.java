package com.thebest12lines.worldmanager.gui;

import com.thebest12lines.worldmanager.DataManager;
import com.thebest12lines.worldmanager.Main;
import com.thebest12lines.worldmanager.annotation.CoreClass;
import com.thebest12lines.worldmanager.util.SystemSettings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Enumeration;

/**
 * The default crash GUI.
 * @author thebest12lines
 */
@CoreClass
public class CrashGui {
    public static JFrame mainFrame = new JFrame("worldmanager Crash");
    public static void launch() throws Exception {
        initialize();
    }
    /**
     * Initializes the GUI.
     *
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws UnsupportedLookAndFeelException
     */
    private static void initialize() throws Exception {
        //  mainFrame = new JFrame("worldmanager Alpha 0.1.0");

       Color bgColor = MainGui.bgColor;
       Color fgColor = MainGui.fgColor;

        // mainFrame.setUndecorated(true);

        if (SystemSettings.getSystemTheme().equals("Dark") && DataManager.getSetting("theme").equals("default")) {
            bgColor = new Color(37, 37, 37);
            fgColor = new Color(255, 255, 255);
            mainFrame.setBackground(bgColor);
            mainFrame.setForeground(fgColor);
        }
        if (DataManager.getSetting("theme").equals("light")) {
            bgColor = new Color(255, 255, 255);
            fgColor = new Color(0, 0, 0);
            mainFrame.setBackground(bgColor);
            mainFrame.setForeground(fgColor);
        } else if (DataManager.getSetting("theme").equals("dark")) {
            bgColor = new Color(37, 37, 37);
            fgColor = new Color(255, 255, 255);
            mainFrame.setBackground(bgColor);
            mainFrame.setForeground(fgColor);
        }
        if (Main.themeExplicit == 1) {
            bgColor = new Color(255, 255, 255);
            fgColor = new Color(0, 0, 0);
            mainFrame.setBackground(bgColor);
            mainFrame.setForeground(fgColor);
        } else if (Main.themeExplicit == 2) {
            bgColor = new Color(37, 37, 37);
            fgColor = new Color(255, 255, 255);
            mainFrame.setBackground(bgColor);
            mainFrame.setForeground(fgColor);
        }
        Enumeration<Object> keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            if (key.toString().endsWith(".background")) {
                UIManager.put(key, bgColor);
            }
            if (key.toString().endsWith(".foreground")) {
                UIManager.put(key, fgColor);
            }
        }
        UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());

        // System.setProperty("java.awt.headless", "true");
       mainFrame.setTitle("worldmanager Crash");
        mainFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Choose your desired behavior:
                mainFrame.setVisible(false); // Hide the window
                mainFrame.dispose(); // Dispose of the window's resources
                System.exit(0); // Terminate the application
            }
        });
        var icons = MainGui.icons;

        mainFrame.setIconImages(icons);

        //mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(500, 300);
        mainFrame.setResizable(false);
        JPanel panel = new JPanel();
        panel.add(FlatLabel.createFlatLabel("worldmanager crashed!"),BorderLayout.NORTH);
        mainFrame.add(panel,BorderLayout.CENTER);
        // mainFrame.setBackground(new Color());
       // mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        mainFrame.setVisible(true);
    }
}
