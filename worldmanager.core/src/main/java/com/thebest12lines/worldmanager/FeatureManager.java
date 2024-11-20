package com.thebest12lines.worldmanager;

import java.io.File;
import java.io.IOException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Paths;

import worldmanager.features.FeatureProcessor;
import worldmanager.features.internal.CoreClass;
import com.thebest12lines.worldmanager.util.Output;
import org.json.JSONArray;
import org.json.JSONObject;

import com.thebest12lines.worldmanager.util.Constants;
import com.thebest12lines.worldmanager.util.Constants.FeatureLoadResult;

/**
 * A proper way of managing worldmanager features.
 * @author thebest12lines
 */
@CoreClass
public class FeatureManager {
    /**
     * Checks if a feature is enabled.
     * @param feature The feature to check from worldmanager.json
     * @return The result of the feature being enabled.
     */
    public static boolean isFeatureEnabled(String feature) {
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
            // 
            e.printStackTrace();
            
        }
        return false;
        
    }

    /**
     * Gets the list of enabled features
     * @return The array of enabled features.
     */
    public static Object[] getEnabledFeatures() {
        StringBuilder builder = new StringBuilder();
        try {
            Files.lines(Paths.get("worldmanager.json")).forEach(builder::append);
            JSONObject jsonObject = new JSONObject(builder.toString());
            JSONArray array = jsonObject.getJSONArray("enabledFeatures");
            //Output.print(array.toList().toString());
            return array.toList().toArray();
        } catch (Exception e) {
            // 
            e.printStackTrace();
            
        }
        return null;
    }

    /**
     * Loads a feature to worldmanager, thus allowing it to run and modify worldmanager's functionality.
     * @param feature The fully qualified feature name (typically the module name) to load.
     * @return The load result.
     */
    public static Constants.FeatureLoadResult loadFeature(String feature) {
        FeatureProcessor.loadFeature(feature);
        return null;
    }
}
