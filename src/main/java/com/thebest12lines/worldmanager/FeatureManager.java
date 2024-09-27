package com.thebest12lines.worldmanager;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONArray;
import org.json.JSONObject;

import com.thebest12lines.worldmanager.util.Constants;
import com.thebest12lines.worldmanager.util.Constants.FeatureLoadResult;
import com.thebest12lines.worldmanager.util.Constants.UpdateCheckResult;

public class FeatureManager {
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
            // TODO Auto-generated catch block
            e.printStackTrace();
            
        }
        return false;
        
    }
    public static Object[] getEnabledFeatures() {
        StringBuilder builder = new StringBuilder();
        try {
            Files.lines(Paths.get("worldmanager.json")).forEach(builder::append);
            JSONObject jsonObject = new JSONObject(builder.toString());
            JSONArray array = jsonObject.getJSONArray("enabledFeatures");
            //Output.print(array.toList().toString());
            return array.toList().toArray();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            
        }
        return null;
    }
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
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (NoSuchMethodException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                        return FeatureLoadResult.CANNOT_FIND_METHOD;
                    } catch (SecurityException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                
                    
                } catch (MalformedURLException | ClassNotFoundException e) {
                    // TODO Auto-generated catch block
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
