package com.thebest12lines.worldmanager.gui;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

import com.thebest12lines.worldmanager.Output;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class FlatTreeCellRenderer extends DefaultTreeCellRenderer {
    private Icon customIcon; // Your custom icon
    private Font customFont; // Your custom font
    private Icon worldIcon;
    private JPopupMenu menu;

    public FlatTreeCellRenderer(Icon customIcon, Font customFont,Icon worldIcon, JPopupMenu menu) {
        this.customIcon = customIcon;
        this.customFont = customFont;
        this.worldIcon = worldIcon;
        this.menu = menu;
        
    }

    @Override
    public Component getTreeCellRendererComponent(
            JTree tree, Object value, boolean selected,
            boolean expanded, boolean leaf, int row, boolean hasFocus) {
        super.getTreeCellRendererComponent(
                tree, value, selected, expanded, leaf, row, hasFocus);

        DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;

        // Check some condition (e.g., node.getUserObject().getStatus())
        // and set the appropriate icon
        if (node != null) {
           // System.out.println(node.getLevel());
            if (node.getLevel() == 0) {
                setIcon(customIcon);
            } else if (node.getLevel() == 1) {
                setIcon(worldIcon);
                setBorder(null);
            } else if (node.getLevel() == 2) {
                setIcon(null);
            }
            
        }
        
        // Set the custom font
        setFont(customFont);
        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e) && e.getClickCount() == 1) {
                    Output.printErr("s");
                    menu.show(MainGui.getMainFrame(),e.getX(),e.getY());
                }
            }
        });

        return this;
    }
    
}
