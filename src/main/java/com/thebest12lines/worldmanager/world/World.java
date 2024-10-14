package com.thebest12lines.worldmanager.world;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.zip.ZipOutputStream;

import com.thebest12lines.worldmanager.ZipDirectory;
import com.thebest12lines.worldmanager.annotation.CoreClass;

@CoreClass
public class World extends Object{
    protected String name = "";
    protected ArrayList<Backup> backups = new ArrayList<Backup>();
    protected String version = "1.21";
    protected String path = "";
    protected String lastModified = "1/1/2024 10:10:13PM";
    protected boolean isBackingUp = false;
    
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
    public void backupWorld() {
        if (!isBackingUp) {
            isBackingUp = true;
        new Thread(new Runnable() {

            @Override
            public void run() {
                 String backupPath = Instant.now().atOffset(ZoneOffset.UTC).format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH-mm-ss.SSS'Z'"))+"_"+getWorldName();
                
         new File(path+"\\backups\\").mkdir();
        try {;
            System.err.println(path);
             new File(path+"\\backups\\"+backupPath+".zip").createNewFile();
             ZipDirectory.zipDirectory(getWorldPath(), getWorldPath()+"\\backups\\"+backupPath+".zip", path+"\\backups");
             isBackingUp = false;
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
