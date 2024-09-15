package com.thebest12lines.gui;

import javax.swing.*;


import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FlatMenu {
    /**
     * Creates a "flat" style JMenu.
     * @param text The text to add to the JMenu.
     * @param menuBar The JMenuBar to add to.
     * @return A "flat" style JMenu.
     */
    public static JMenu createFlatMenu(String text, JMenuBar menuBar) {
        JMenu menu = new JMenu(text);
        menu.setBorder(null);
        menu.setFont(new Font("Segoe UI", Font.PLAIN, 12));
      //  menu.setHorizontalAlignment(SwingConstants.CENTER); // Center the text

        // Set margins (adjust as needed)
        Insets margins = new Insets(5, 30, 5, 30); // Example: 5px top, 10px left, 5px bottom, 10px right
        
        menu.setMargin(margins);
        menu.addMouseListener(new MouseAdapter() {
            Color originalColor = menu.getForeground();
            Color hoverColor = Color.lightGray;
            @Override
            public void mouseEntered(MouseEvent e) {
                
                menu.setForeground(hoverColor);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                
                menu.setForeground(originalColor);
            }
        });
         

        menuBar.add(menu);
        menu.revalidate();
        menu.repaint();
        
        return menu;
    }
}
