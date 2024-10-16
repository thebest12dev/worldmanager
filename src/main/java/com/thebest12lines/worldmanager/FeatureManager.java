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

import com.thebest12lines.worldmanager.annotation.CoreClass;
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
        if (new File("worldmanager/features/"+feature+".jar").exists()) {
            
                try {
                    try (URLClassLoader classLoader = new URLClassLoader(new URL[] {new File("worldmanager/lib/features/"+feature+".jar").toURI().toURL()}, ClassLoader.getSystemClassLoader())) {
                        
                        Class<?> loadedClass = classLoader.loadClass(feature+".Main");

                        Method main = loadedClass.getDeclaredMethod("onStart", (Class<?>[]) null);
                        main.invoke(null, (Object[]) null);
                        return FeatureLoadResult.LOADED;
                    } catch (MalformedURLException e) {
                        throw e;
                    } catch (IOException e) {
                        // 
                        e.printStackTrace();
                    } catch (NoSuchMethodException e) {
                        // 
                        e.printStackTrace();
                        return FeatureLoadResult.CANNOT_FIND_METHOD;
                    } catch (SecurityException e) {
                        // 
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        // 
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        // 
                        e.printStackTrace();
                    } 
                
                    
                } catch (MalformedURLException | ClassNotFoundException e) {
                    // 
                    e.printStackTrace();
                    return FeatureLoadResult.CANNOT_FIND_CLASS;
                }
        } else {
            Output.print("Error: Could not find feature "+feature+". That feature will not be loaded");
            return FeatureLoadResult.NOT_LOADED;
        }
        return FeatureLoadResult.NOT_LOADED;
    }
}
