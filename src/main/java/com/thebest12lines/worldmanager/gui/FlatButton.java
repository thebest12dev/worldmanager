package com.thebest12lines.worldmanager.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;


import javax.swing.JButton;

import javax.swing.JLabel;
import javax.swing.SwingConstants;


public class FlatButton {
    /**
     * Creates a "flat" style JButton.
     * @param text The text to add to the button
     * @return A "flat" style JButton.
     */
    public static JButton createFlatButton(String text) {
         // Create a flat button
         JButton flatButton = new JButton("") {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g); // Call the superclass method first
        
                Graphics2D g2 = (Graphics2D) g; // Cast to Graphics2D
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
               // g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                if (getModel().isArmed()) {
                    // Change the color for hover effect
                    g2.setColor(new Color(150,150,150));
                } else if (getModel().isRollover()) {
                    g2.setColor(Color.lightGray);
                } else {
                    g2.setColor(new Color(210, 210, 210));
                }
                
        
                g2.fillRoundRect(0, 0, getSize().width - 1, getSize().height - 1, 4, 4);
                g2.setColor(getForeground());
                g2.drawString(getText(), 0,25);

            }
        };
        JLabel label = FlatLabel.createFlatLabel(text);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        flatButton.setBackground(Color.WHITE); // Set initial background color
        flatButton.setForeground(Color.BLACK); // Set initial text color
        flatButton.setFont(new Font("Segoe UI", Font.PLAIN, 25));
        flatButton.setBorderPainted(false); // Remove border
        flatButton.add(label);

        // Add a hover effect using MouseListener
        

        // Add an ActionListener to the button
        // flatButton.addActionListener(new ActionListener() {
        //     @Override
        //     public void actionPerformed(ActionEvent e) {
        //         // Handle button click here
        //         System.out.println("Flat button clicked!");
        //     }
        // });
        
        flatButton.setBorder(null);
        flatButton.setBackground(new Color(210,210,210));
        flatButton.setBorder(null);
        flatButton.setContentAreaFilled(false);
        flatButton.setFocusPainted(false);
        flatButton.repaint();
        return flatButton;
    }
}
