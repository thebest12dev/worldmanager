package com.thebest12lines.worldmanager.gui;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;

import java.awt.*;
import java.net.URI;
import java.net.URL;

public class FlatTreeCellRenderer extends DefaultTreeCellRenderer {
    private Icon customIcon; // Your custom icon

    public FlatTreeCellRenderer(Icon customIcon) {
        this.customIcon = customIcon;
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
        if (leaf && node != null) {
            setIcon(customIcon);
        }

        return this;
    }
    public static void main(String[] args) {
    }
}
