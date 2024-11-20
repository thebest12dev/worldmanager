package com.thebest12lines.worldmanager.world;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import com.thebest12lines.worldmanager.gui.StatusBar;
import com.thebest12lines.worldmanager.util.Output;
import com.thebest12lines.worldmanager.util.ZipDirectory;
import worldmanager.features.internal.CoreClass;
import net.querz.nbt.io.NBTUtil;
import net.querz.nbt.io.NamedTag;
import net.querz.nbt.tag.CompoundTag;
import net.querz.nbt.tag.ListTag;

/**
 * Base class for a Minecraft world.
 * @author thebest12lines
 */
@CoreClass

public class World extends Object{
    protected String name = "";
    protected ArrayList<Backup> backups = new ArrayList<>();
    protected String version = "1.21";
    protected String path = "";
    protected String lastModified = "1/1/2024 10:10:13PM";
    protected boolean isBackingUp = false;

    /**
     * Gets world name.
     * @return World name.
     */
    public String getWorldName() {
        return name;
    }

    /**
     * Gets version
     * @return Version
     */
    public String getVersion() {
        return version;
    }

    /**
     * Backup
     */
    public Backup[] getBackups() {
        return backups.toArray(new Backup[0]);
    }

    /**
     * Get world path
     * @return world path
     */
    public String getWorldPath() {
        return path;
    }

    /**
     * gets the last modified date from the current world's metadata.
     * @return last modified
     */
    public String getLastModified() {
        return lastModified;
    }

    /**
     * backup world
     */
    public void backupWorld() {
        if (!isBackingUp) {
            isBackingUp = true;
        new Thread(new Runnable() {

            @Override
            public void run() {
                 String backupPath = Instant.now().atOffset(ZoneOffset.UTC).format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH-mm-ss.SSS'Z'"))+"_"+getWorldName();
                
         var t_ =  new File(path+"\\backups\\").mkdir();
        try {
            Output.print("["+World.class.getCanonicalName()+"]: Backing up world '"+name+"'...");
             var t$ = new File(path+"\\backups\\"+backupPath+".zip").createNewFile();
             ZipDirectory.zipDirectory(getWorldPath(), getWorldPath()+"\\backups\\"+backupPath+".zip", path+"\\backups");
            CompoundTag worldInfo = (CompoundTag) NBTUtil.read(new File(path+"\\worldmanager.dat")).getTag();
            ListTag<CompoundTag> tag = (ListTag<CompoundTag>) worldInfo.getListTag("Backups");
            CompoundTag t = new CompoundTag();
            t.putString("BackupDate", Instant.now().atOffset(ZoneOffset.UTC).format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")));
            t.putString("BackupPath", path+"\\backups\\"+backupPath+".zip");
            tag.add(t);
            NBTUtil.write(new NamedTag("Data", worldInfo), path+"/worldmanager.dat");
             isBackingUp = false;
             StatusBar.getStatusBar().show("Backed up world to "+getWorldPath()+"\\backups\\"+" ("+(int) (Files.size(Paths.get(getWorldPath()+"\\backups\\"+backupPath+".zip")) / (1024.0 * 1024.0))+"MB)",StatusBar.DURATION_SHORT);
            Output.print("["+World.class.getCanonicalName()+"]: Backed up world '"+name+"'");

        } catch (IOException e) {
            e.printStackTrace();
        }
            }
            
        }).start();
        } else {
            throw new RuntimeException("Currently backing up!");
        }
        
        
        
        
         
         
    }

}
