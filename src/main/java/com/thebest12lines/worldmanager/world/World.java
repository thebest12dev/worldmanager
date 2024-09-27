package com.thebest12lines.worldmanager.world;

import java.util.ArrayList;

public class World extends Object{
    protected String name = "";
    protected ArrayList<Backup> backups = new ArrayList<Backup>();
    protected String version = "1.21";
    protected String path = "";
    protected String lastModified = "1/1/2024 10:10:13PM";
    
    public String getWorldName() {
        return name;
    }
    public String getVersion() {
        return version;
    }
    public Object[] getBackups() {
        return (Object[]) backups.toArray();
    }
    public String getWorldPath() {
        return path;
    }
    public String getLastModified() {
        return lastModified;
    }

}
