package com.thebest12lines.worldmanager.gui;

import java.awt.Dimension;
import java.awt.Font;

//import javax.swing.Box;
//import javax.swing.BoxLayout;
import javax.swing.JMenuBar;

import worldmanager.features.internal.CoreClass;

@CoreClass
public class FlatMenuBar {
    /**
     * Creates a "flat" style JMenuBar.
     * @return A "flat" style JMenuBar.
     */
    public static JMenuBar createFlatMenuBar() {
        JMenuBar menuBar = new MenuBar(MainGui.bgColor);
        
       
        menuBar.setOpaque(true);
         menuBar.setBackground(MainGui.bgColor);
        menuBar.setPreferredSize(new Dimension(MainGui.getMainFrame().getWidth(), 35));
        menuBar.setBorder(null);
        menuBar.setFont(new Font("Segoe UI",Font.PLAIN,25));
        
        
        return menuBar;
    }
}
