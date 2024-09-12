package com.thebest12lines.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;


import javax.swing.*;


public class MainGui {
    public static void launch() {
        JFrame mainFrame = new JFrame("worldmanager Alpha 0.0.1");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(800, 500);
        mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        JMenuBar menuBar = FlatMenuBar.createFlatMenuBar();
        ArrayList<JMenu> jMenus = new ArrayList<JMenu>();
        jMenus.add(FlatMenu.createFlatMenu("File", menuBar));
        jMenus.add(FlatMenu.createFlatMenu("Edit", menuBar));
        jMenus.add(FlatMenu.createFlatMenu("World", menuBar));
        jMenus.add(FlatMenu.createFlatMenu("Help", menuBar));
        
        
        //menuBar.setLayout(new BorderLayout());

        // try {
        //     //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        // } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
        //         | UnsupportedLookAndFeelException e) {
        //     // TODO Auto-generated catch block
        //     e.printStackTrace();
        // }
        mainFrame.setVisible(true);
        JButton button = FlatButton.createFlatButton();
        
        button.setText("text");
        button.setPreferredSize(new Dimension(25, 25));
        button.setVisible(true);
        //mainFrame.add(button);
        mainFrame.setJMenuBar(menuBar);
        mainFrame.revalidate();
        mainFrame.repaint();
        
    }
}
