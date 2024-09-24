package com.thebest12lines.worldmanager;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONArray;
import org.json.JSONObject;

public class FeatureManager {
    public static boolean isFeatureLoaded(String feature) {
        StringBuilder builder = new StringBuilder();
        try {
            Files.lines(Paths.get("worldmanager.json")).forEach(builder::append);
            JSONObject jsonObject = new JSONObject(builder.toString());
            JSONArray array = jsonObject.getJSONArray("enabledFeatures");
            boolean result = true;
            for (int i = 0; i < array.length(); i++) {
                if (array.getString(i).equals(feature)) {
                    return true;
                } else {
                    result = false;
                }
            }
            return result;
            
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            
        }
        return false;
        
    }
}
