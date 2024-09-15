package com.thebest12lines.worldmanager.gui;
import java.awt.Color;

import javax.swing.*;
//import org.json.JSONObject;






public class WindowModifier {
    private JFrame frame;
    public WindowModifier(JFrame frame) {
        this.frame = frame;
    }
    public void setBackgroundColor(Color color) {
        frame.getContentPane().setBackground(color);
        
    }
    public JLabel addJLabel(String text) {
        JLabel jLabel = new JLabel(text);
        frame.add(jLabel);
        return jLabel;
    }
    public JLabel addJLabel(String text, Color color) {
        JLabel jLabel = new JLabel(text);
        jLabel.setForeground(color);
        frame.add(jLabel);
        return jLabel;
    }
    public JButton addJButton(String text) {
        JButton jButton = new JButton(text);
        frame.add(jButton);
        return jButton;
    }
    public JButton addJButton(String text,Color color) {
        JButton jButton = new JButton(text);
        jButton.setForeground(color);
        frame.add(jButton);
        return jButton;
    }
    public JButton addJButton(String text,Color backgroundColor, int bg_) {
        JButton jButton = new JButton(text);
        jButton.setBackground(backgroundColor);
        frame.add(jButton);
        return jButton;
    }
    public JButton addJButton(String text,Color color, Color backgroundColor) {
        JButton jButton = new JButton(text);
        jButton.setBackground(backgroundColor);
        jButton.setBackground(color);
        frame.add(jButton);
        return jButton;
    }
}
