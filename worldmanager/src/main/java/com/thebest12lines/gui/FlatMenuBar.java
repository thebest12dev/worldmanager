package com.thebest12lines.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JMenuBar;

public class FlatMenuBar {
    /**
     * Creates a "flat" style JMenuBar.
     * @return A "flat" style JMenuBar.
     */
    public static JMenuBar createFlatMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(new Color(255,255,255));
        menuBar.setPreferredSize(new Dimension(0, 35));
        menuBar.setBorder(null);
        menuBar.setFont(new Font("Segoe UI",Font.PLAIN,25));
        
        
        return menuBar;
    }
}
