package com.thebest12lines.worldmanager.gui;

import worldmanager.features.annotation.CoreClass;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPopupMenu;
@CoreClass
public class FlatPopupMenu {
    public static JPopupMenu createFlatPopupMenu() {
        JPopupMenu menu = new JPopupMenu() {
            @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(new Color(0, 0, 0, 50));
     //   g2d.fillRoundRect(5, 5, getWidth() - 10, getHeight() - 10, 15, 15);
        g2d.dispose();
    }
        };

        return menu;
    }
}
