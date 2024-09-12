package com.thebest12lines.world;

import java.io.File;
import java.util.ArrayList;

public class SaveManager {
    public static ArrayList<World> getWorlds() {
        
        return readWorlds();
        
    }
    private static ArrayList<World> readWorlds() {
        String savesFolder = System.getProperty("user.home") + "\\AppData\\Roaming\\.minecraft\\saves";
        File folder = new File(savesFolder);
        ArrayList<World> worlds = new ArrayList<World>();
        File[] saves = folder.listFiles();
        
        for (File save : saves) {
            
        }
        return null;
    }

}
