package com.thebest12lines.worldmanager.gui;

import javax.swing.*;

public class NativeLookAndFeelExample {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Native Look and Feel Example");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 300);

            JTextArea textArea = new JTextArea("This is a JTextArea inside a JScrollPane.");
            JScrollPane scrollPane = new JScrollPane(textArea);

            frame.add(scrollPane);
            frame.setVisible(true);
        });
    }
}
