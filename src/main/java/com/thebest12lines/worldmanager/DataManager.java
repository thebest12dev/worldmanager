package com.thebest12lines.worldmanager;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.json.JSONException;
import org.json.JSONObject;

public class DataManager {

    private static JSONObject json;
    public static void setVersion(String version) {
        if (json == null) {
            initialize();
        }
        json.getJSONObject("worldmanagerMeta").put("version", version);
        try {
            Files.write(new File("worldmanager.json").toPath(), json.toString(4).getBytes());
        } catch (JSONException | IOException e) {
            // TODO Auto-generated catch block
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
            // TODO Auto-generated catch block
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
        builder.append(getBranch()+"-"+getVersion());
        builder.append("+build_"+getBuild());
        return builder.toString();
    }
        
    private static void initialize() {
        StringBuilder builder = new StringBuilder();
        try {
            Files.lines(Paths.get("worldmanager.json")).forEach(builder::append);
            json = new JSONObject(builder.toString());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
}
