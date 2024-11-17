package com.thebest12lines.worldmanager.gui;

import com.thebest12lines.worldmanager.DataManager;
import com.thebest12lines.worldmanager.Main;
import com.thebest12lines.worldmanager.annotation.CoreClass;
import com.thebest12lines.worldmanager.util.SystemSettings;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URI;
import java.util.ArrayList;
import java.util.Enumeration;

/**
 * The default crash GUI.
 * @author thebest12lines
 */
@CoreClass
public class CrashGui {
    private static int errorCode = 0;
    public static JFrame mainFrame = new JFrame("worldmanager Crash");
    public static void launch(int errorCode) throws Exception {
        CrashGui.errorCode = errorCode;
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
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        // System.setProperty("java.awt.headless", "true");
       mainFrame.setTitle("worldmanager Crash");
       mainFrame.setLayout(new BorderLayout());
        mainFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Choose your desired behavior:
                mainFrame.setVisible(false); // Hide the window
                mainFrame.dispose(); // Dispose of the window's resources
                System.exit(errorCode); // Terminate the application
            }
        });
        var icons = MainGui.icons;

        mainFrame.setIconImages(icons);

        //mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(500, 300);
        mainFrame.setResizable(false);
//        JPanel panel = new JPanel();
//        panel.add(FlatLabel.createFlatLabel("worldmanager crashed!"),BorderLayout.NORTH);
        JTextPane textPane = new JTextPane();
        textPane.setEditable(false);
        textPane.setText("""
                worldmanager crashed!
               
                If you see this, worldmanager has crashed due to an error. If you believe this is a bug, please submit an issue on our Github issue tracker containing the worldmanager.log file.
                
                Error code: 0x""" +Integer.toHexString(errorCode)+"""
                """);
         textPane.setFont(new Font("Segoe UI",Font.PLAIN,16));
        StyledDocument doc = textPane.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);

        JScrollPane scrollPane = new JScrollPane(textPane);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        JPanel wrapPanel = new JPanel();
        wrapPanel.setLayout(new BorderLayout());
        wrapPanel.add(textPane);
       // JScrollPane scrollPane = new JScrollPane(changelog);
        scrollPane.setBorder(null);
//        scrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
//            @Override
//            protected void configureScrollBarColors() {
//                int r = 255;
//                int g = 190;
//                this.thumbColor = new Color(g, g, g);
//                this.trackColor = new Color(r, r, r);
//            }
//
//            @Override
//            protected JButton createDecreaseButton(int orientation) {
//                return createZeroButton();
//            }
//
//            @Override
//            protected JButton createIncreaseButton(int orientation) {
//                return createZeroButton();
//            }
//
//            private JButton createZeroButton() {
//                JButton button = new JButton();
//                button.setPreferredSize(new Dimension(0, 0));
//                button.setMinimumSize(new Dimension(0, 0));
//                button.setMaximumSize(new Dimension(0, 0));
//                return button;
//            }
//        }
        JButton changelogButton = FlatButton.createFlatButton("<html><center>Report</center></html>");
        changelogButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        changelogButton.setMargin(new Insets(10, 10, 10, 10));
        changelogButton.setPreferredSize(new Dimension(90, 30));
        changelogButton.addActionListener(e -> {
            //
            try {
                if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                    URI url = new URI("https://github.com/thebest12dev/worldmanager/issues/new/choose"); // Replace with your desired URL
                    Desktop.getDesktop().browse(url);
                } else {
                    System.out.println("Desktop browsing is not supported on this platform.");
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        });
        JPanel btnPanel = new JPanel();
        btnPanel.add(changelogButton);
        //btnPanel.setLayout(new BorderLayout());

        mainFrame.add(wrapPanel,BorderLayout.NORTH);
        mainFrame.add(btnPanel,"South");
       // mainFrame.add(new JButton("Report"));
       // panel2.add(scrollPane);
       // mainFrame.add(panel2);
//        var text = FlatLabel.createFlatLabel("worldmanager has crashed due to a bug in the underlying code. It is advised to send the worldmanager.log file into our GitHub issue tracker so we can fix the bug right away.");
//        mainFrame.add(FlatLabel.createFlatLabel("textmnemonidsfdsfdsfsdfsdfdsfdsfdsfssadsadsadsadsagydgsadgsah dsa dasgdhsgajd gsajdgsa dac"),BorderLayout.NORTH);
//        mainFrame.add(text);
//        mainFrame.add(panel,BorderLayout.CENTER);
        // mainFrame.setBackground(new Color());
       // mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        mainFrame.setVisible(true);
    }
}
