package com.thebest12lines.worldmanager.gui;

import worldmanager.features.internal.CoreClass;

import javax.swing.*;
import java.awt.*;

/**
 * A more better JMenuBar.
 * @author thebest12lines
 */
@CoreClass
public class MenuBar extends JMenuBar {
    private Color backgroundColor;

    public MenuBar(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(backgroundColor);
        g2d.fillRect(0, 0, getWidth(), getHeight());
    }
}

