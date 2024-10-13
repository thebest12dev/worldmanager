package com.thebest12lines.worldmanager.gui;

import java.awt.Color;

import javax.swing.tree.DefaultMutableTreeNode;

import com.thebest12lines.worldmanager.util.SystemSettings;
import com.thebest12lines.worldmanager.world.World;

public class WorldMutableTreeNode extends DefaultMutableTreeNode {
    protected World world;
    public void setWorld(World world) {
        this.world = world;
    }
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
