package com.thebest12lines.worldmanager;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


import worldmanager.features.annotation.CoreClass;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Provides functionality for I/O to the settings file (worldmanager.json).
 * @author thebest12lines
 */
@CoreClass
public class DataManager {

    private static JSONObject json;

    /**
     * Sets the worldmanagerMeta.version string to
     * @param version
     */
    public static void setVersion(String version) {
        if (json == null) {
            initialize();
        }
        json.getJSONObject("worldmanagerMeta").put("version", version);
        try {
            Files.write(new File("worldmanager.json").toPath(), json.toString(4).getBytes());
        } catch (JSONException | IOException e) {
            // 
            e.printStackTrace();
        }
        
    }
    public static void setBuild(int build) {
        if (json == null) {
            initialize();
        }
        json.getJSONObject("worldmanagerMeta").put("build", build);
        try {
            Files.write(new File("worldmanager.json").toPath(), json.toString(4).getBytes());
        } catch (JSONException | IOException e) {
            // 
            e.printStackTrace();
        }
    }
    public static int getBuild() {
        if (json == null) {
            initialize();
        }
        return json.getJSONObject("worldmanagerMeta").getInt("build");
    }
    public static String getVersion() {
        if (json == null) {
            initialize();
        }
        return json.getJSONObject("worldmanagerMeta").getString("version");
    }
    public static String getBranch() {
        if (json == null) {
            initialize();
        }
        return json.getJSONObject("worldmanagerMeta").getString("branch");
    }
    public static String getFullVersion() {
        if (json == null) {
            initialize();
        }
        StringBuilder builder = new StringBuilder();
        builder.append(getVersion()+"-"+getBranch());
        builder.append("+build_"+getBuild());
        return builder.toString();
    }
        
    private static void initialize() {
        StringBuilder builder = new StringBuilder();
        try {
            Files.lines(Paths.get("worldmanager.json")).forEach(builder::append);
            json = new JSONObject(builder.toString());
        } catch (IOException e) {
            // 
            e.printStackTrace();
        }
    }
    public static Object getSetting(String key) {
        if (json == null) {
            initialize();
        }
        if (key == "debug") {
           if ((boolean) json.getJSONObject("worldmanagerPreferences").get(key) == true) {
               return json.getJSONObject("worldmanagerPreferences").get(key);
           } else if (Main.debug) {
               return Main.debug;
           } else {
               return false;
           }
        } else {
        return json.getJSONObject("worldmanagerPreferences").get(key);
        }

    }
    
}
