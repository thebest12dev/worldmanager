package worldmanager.features;
import worldmanager.features.annotation.Feature;
import worldmanager.features.internal.CoreClass;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
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
            URLClassLoader classLoader = new URLClassLoader(new URL[]{jarUrl}, FeatureProcessor.class.getClassLoader());
            // Specify the class name you want to load

             // Load the class

            Class<?> loadedClass = classLoader.loadClass(mainClass); // Print some information about the loaded class
            Object inst = loadedClass.getDeclaredConstructor().newInstance();
            if (loadedClass.isAnnotationPresent(Feature.class)) {
                PermissionManager.grantPermission(loadedClass,"");
                loadedClass.getMethod("onStart").invoke(inst);
            } else {
                throw new SecurityException("Cannot run feature "+loadedClass.getCanonicalName()+" as it does not contain the @Feature annotation.");
            }



        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}

