package com.thebest12lines.worldmanager.api;

import com.thebest12lines.worldmanager.Main;
import com.thebest12lines.worldmanager.annotation.CoreClass;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;
import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

@CoreClass
public class FeatureProcessor {
    public static boolean featureExists(String featureName)  {
        String jarFilePath = "worldmanager/features/"+featureName+".jar";


        try {

            JarFile jarFile = new JarFile(jarFilePath);
            Manifest manifest = jarFile.getManifest();
            Attributes attributes = manifest.getMainAttributes();
            String mainClass = attributes.getValue("worldmanager-API-MainClass");
            jarFile.close();
            return mainClass != null;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
    public static void loadFeature(String featureName) {
        String jarFilePath = "worldmanager/features/"+featureName+".jar";


        try {

            JarFile jarFile = new JarFile(jarFilePath);
            Manifest manifest = jarFile.getManifest();
            Attributes attributes = manifest.getMainAttributes();
            String mainClass = attributes.getValue("worldmanager-API-MainClass");
            jarFile.close();

            URL jarUrl = new File(jarFilePath).toURI().toURL();
            URLClassLoader classLoader = new URLClassLoader(new URL[]{jarUrl}, Main.class.getClassLoader());
            // Specify the class name you want to load

             // Load the class

            Class<?> loadedClass = classLoader.loadClass(mainClass); // Print some information about the loaded class
            Object inst = loadedClass.getDeclaredConstructor().newInstance();
                    System.out.println("Class Name: " + loadedClass.getName());
             System.out.println("Methods: " + Arrays.toString(loadedClass.getDeclaredMethods())); // Close the class loader classLoader.close(); }
            loadedClass.getMethod("onStart").invoke(inst);


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}

