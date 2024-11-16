package com.thebest12lines.worldmanager.world;

import java.io.File;

import java.io.PrintWriter;
import java.io.StringWriter;

import java.text.DecimalFormat;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import com.thebest12lines.worldmanager.DataManager;
import worldmanager.features.annotation.CoreClass;

import com.thebest12lines.worldmanager.util.Output;


import net.querz.nbt.io.NBTUtil;
import net.querz.nbt.io.NamedTag;
import net.querz.nbt.tag.*;

/**
 * The world manager for <code>worldmanager</code> to read and get worlds.
 * @author thebest12lines.
 */
@CoreClass
public class SaveManager {
    /**
     * Gets the worlds from the saves directory.
     * @return An array.
     */
    public static World[] getWorlds() {

            System.out.println();
            System.err.println();

            Output.printDebug("["+SaveManager.class.getCanonicalName()+"]: Reading worlds (DEBUG)...");
        return readWorlds().toArray(new World[0]);
        
    }
    private static ArrayList<World> readWorlds() {
        long initialTimeNanos = System.nanoTime();
        String savesFolder = System.getProperty("user.home") + "\\AppData\\Roaming\\.minecraft\\saves";
        if (System.getProperty("os.name").equals("Linux")) {
            Output.printDebug("["+SaveManager.class.getCanonicalName()+"]: Using Linux!");
            savesFolder =   System.getProperty("user.home")+"/.minecraft/saves/";
        }

        Output.printDebug("["+SaveManager.class.getCanonicalName()+"]: Saves folder is "+savesFolder);
       // System.out.println(savesFolder);
        File folder = new File(savesFolder);
        ArrayList<World> worlds = new ArrayList<>();

        //System.out.println(folder.getAbsolutePath());
        File[] saves = folder.listFiles();
       // Output.printDebug("========================== DEBUG START ==============================");

        for (File save : saves) {
            try {
               // Output.printDebug("========================================================");
                if ((boolean) DataManager.getSetting("debug")) {
                    System.out.println();
                    System.err.println();
                }

                Output.printDebug("File: "+save.toString());
                if (save.getAbsolutePath().endsWith("/worldmanager.dat") | save.getAbsolutePath().endsWith("\\worldmanager.dat") ) {
                    continue;
                }
                NamedTag worldInfo;
                if (new File(save.getAbsolutePath()+"\\level.dat").exists()) {
                    if (System.getProperty("os.name").equals("Linux")) {
                        worldInfo = NBTUtil.read(new File(save.getAbsolutePath() + "/level.dat"));
                    } else {
                        worldInfo = NBTUtil.read(new File(save.getAbsolutePath() + "\\level.dat"));
                    }
                } else {
                    continue;
                }
                CompoundTag tag = (CompoundTag) worldInfo.getTag();
                

                CompoundTag data = tag.getCompoundTag("Data");
                StringTag stringTag = data.getStringTag("LevelName");
                Output.printDebug("Level name: "+stringTag.getValue());
            
                
                World world = new World();
                world.name = stringTag.getValue();
                world.path = save.getAbsolutePath();

                try {
                     CompoundTag versionTag = data.getCompoundTag("Version");
                    
                     world.version = versionTag.getStringTag("Name").getValue();
                     Output.printDebug("Version: "+world.version);
                } catch (Exception e) {
                    Output.print("["+SaveManager.class.getCanonicalName()+"]: World \""+world.name+"\" is an old version! This world might not work with worldmanager.",Output.LOG_WARN);
                    StringWriter sw = new StringWriter();
                    PrintWriter  pw = new PrintWriter(sw);
                    e.printStackTrace(pw);
                    Output.printDebug(sw.toString());
    
                }
                
                world.lastModified = Instant.now().atOffset(ZoneOffset.UTC).format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"));

                 if (!new File(save.getAbsolutePath()+"\\worldmanager.dat").exists()) {
                    CompoundTag worldTag = new CompoundTag();
                    CompoundTag meta = new CompoundTag();
                    worldTag.put("Metadata", meta);
                    meta.put("LastLoaded", new StringTag(Instant.now().atOffset(ZoneOffset.UTC).format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"))));
                    meta.put("Version", new LongTag(DataManager.getBuild()));
                   // meta.put("",);
                    worldTag.put("Backups",ListTag.createUnchecked(CompoundTag.class));
                    if (System.getProperty("os.name").equals("Linux")) {
                       NBTUtil.write(new NamedTag("Data", worldTag), save.getAbsolutePath()+"/worldmanager.dat");
                    } else {
                        NBTUtil.write(new NamedTag("Data", worldTag), save.getAbsolutePath()+"\\worldmanager.dat");
                    }
                    
                
                }
                worlds.add(world);


            } catch (Exception e) {
                // 
                e.printStackTrace();
            }
        }
       // Output.printDebug("========================== DEBUG END ==============================");
        long currentTimeNanos = System.nanoTime();
        float elapsedTime = (currentTimeNanos - initialTimeNanos) / 1000000000.0f;
        String formattedTime = new DecimalFormat("#.00").format(elapsedTime);
        float time = Float.parseFloat(formattedTime);
        Output.print("["+SaveManager.class.getCanonicalName()+"]: Loading worlds took "+time+"s");
        if ((boolean) DataManager.getSetting("debug")) {
            System.out.println();
            System.err.println();
        }
        return worlds;
    }
    public static void restoreBackup() {}
}


