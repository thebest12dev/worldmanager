package com.thebest12lines.worldmanager.gui;

import java.awt.Color;

import java.awt.Font;


import javax.swing.JLabel;


public class FlatLabel {
    /**
     * Creates a "flat" style JLabel.
     * @param text The text to add to the label
     * @return A "flat" style JLabel.
     */
    public static JLabel createFlatLabel(String text) {
        JLabel menuBar = new JLabel(text);
        menuBar.setBackground(new Color(255,255,255));
       // menuBar.setPreferredSize(new Dimension(0, 35));
        menuBar.setBorder(null);
        menuBar.setFont(new Font("Segoe UI",Font.PLAIN,16));
        
        
        return menuBar;
    }
}
