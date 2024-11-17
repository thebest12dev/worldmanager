package com.thebest12lines.worldmanager.gui;

import java.awt.Color;

import javax.swing.tree.DefaultMutableTreeNode;

import com.thebest12lines.worldmanager.annotation.CoreClass;
import com.thebest12lines.worldmanager.util.SystemSettings;
import com.thebest12lines.worldmanager.world.World;

/**
 * A world-compatible TreeNode. Useful for storing worlds associated with these nodes.
 * @author thebest12lines
 */
@CoreClass
public class WorldMutableTreeNode extends DefaultMutableTreeNode {
    protected World world;

    /**
     * Sets the world.
     * @param world The world to set.
     */
    public void setWorld(World world) {
        this.world = world;
    }

    /**
     * Gets the world.
     * @return The world to get.
     */
    public World getWorld() {
        return world;
    }
    public WorldMutableTreeNode(Object userObject) {
        this.userObject = userObject;
        if (SystemSettings.getSystemTheme().equals("Dark")) {
            
        } 
    }
    public WorldMutableTreeNode() {}
}
