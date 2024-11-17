package com.thebest12lines.worldmanager.gui;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

import com.thebest12lines.worldmanager.annotation.CoreClass;
import com.thebest12lines.worldmanager.util.SystemSettings;

import java.awt.*;

@CoreClass
public class FlatTreeCellRenderer extends DefaultTreeCellRenderer {
    private Icon customIcon; // Your custom icon
    private Font customFont; // Your custom font
    private Icon worldIcon;
  //  private JPopupMenu menu;

    public FlatTreeCellRenderer(Icon customIcon, Font customFont,Icon worldIcon, JPopupMenu menu) {
        this.customIcon = customIcon;
        this.customFont = customFont;
        this.worldIcon = worldIcon;
        
       // this.menu = menu;
        
    }

    @Override
    public Component getTreeCellRendererComponent(
            JTree tree, Object value, boolean selected,
            boolean expanded, boolean leaf, int row, boolean hasFocus) {
        Component c = super.getTreeCellRendererComponent(
                tree, value, selected, expanded, leaf, row, hasFocus);

        DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;

        // Check some condition (e.g., node.getUserObject().getStatus())
        // and set the appropriate icon
        if (c instanceof JComponent) {
            ((JComponent) c).setOpaque(true);
        }


        if (node != null) {
            
            setBackground(MainGui.bgColor);
            setForeground(MainGui.fgColor);
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
        

        return this;
    }
    
}

