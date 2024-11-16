package com.thebest12lines.worldmanager.gui;

import worldmanager.features.annotation.CoreClass;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
//import javax.swing.border.Border;
@CoreClass
public class FlatMenuItem {
    public static JMenuItem createFlatMenuItem(String text, String shortcut) {
        JMenuItem menuItem = new JMenuItem();
        Font worldFont = new Font("Segoe UI Light", Font.PLAIN, 13);
        Font normalFont = new Font("Segoe UI", Font.PLAIN, 13);
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel textLabel = new JLabel("  "+text);
        textLabel.setFont(normalFont);
        JLabel shortcutLabel = new JLabel(shortcut+"  ");
        shortcutLabel.setFont(worldFont);

        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(textLabel, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(shortcutLabel, gbc);

        menuItem.setLayout(new BorderLayout());
        menuItem.add(panel, BorderLayout.CENTER);
        Color defaultBackground = menuItem.getBackground();
        Color hoverBackground = defaultBackground.darker();
        menuItem.setPreferredSize(new Dimension(200, 20));
        menuItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // 
                panel.setBackground(defaultBackground);
                menuItem.setBackground(defaultBackground);
            }
            
        });

        // Custom border and hover effect
   //     Border defaultBorder = BorderFactory.createEmptyBorder(2, 2, 2, 2);
     //   Border hoverBorder = BorderFactory.createLineBorder(Color.DARK_GRAY, 1);
        
        menuItem.setBorder(null);

        
        menuItem.setOpaque(false);

        menuItem.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                
                panel.setBackground(hoverBackground);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                panel.setBackground(defaultBackground);
                menuItem.setBackground(defaultBackground);
            }
        });

        return menuItem;
    }
}
