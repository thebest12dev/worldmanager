package com.thebest12lines.worldmanager;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.zip.ZipOutputStream;

import org.json.JSONObject;

import com.thebest12lines.worldmanager.ZipDirectory;
import com.thebest12lines.worldmanager.util.Constants;

import java.nio.file.*;

public class Main {
    public static void main(String[] args) {
        
        String appDataPath = System.getProperty("user.home") + "\\AppData\\Roaming\\.worldmanager";
        File Directory = new File(appDataPath);
        Directory.mkdir();
        new File(appDataPath+"\\worlds").mkdir();
        
        Output.printErr("worldmanager 0.1.0 Alpha\nLogs will be outputted to file worldmanager.log.");
        Output.print("["+Main.class.getCanonicalName()+"]: Starting worldmanager");
        
        Output.print("["+Main.class.getCanonicalName()+"]: Verifying libraries exist...");
        String[] libraries = {"json-20240303","NBT-6.1"};
        boolean isFatal = false;
        for (int i = 0; i < libraries.length; i++) {
            if (libraryExists(libraries[i])) {
                Output.print("["+Main.class.getCanonicalName()+"]: Library \""+libraries[i]+"\" found ("+(i+1)+"/"+libraries.length+")");
            } else {
                Output.print("FATAL: Library \""+libraries[i]+"\" is not found. worldmanager will not run without this library.");
                isFatal = true;
            
            }
        }
        if (isFatal) {
            Output.print("FATAL: Library verification check failed. worldmanager will not launch now.");
            return;
        } else {
            Output.print("["+Main.class.getCanonicalName()+"]: Library verification check success, worldmanager will launch now.");
        }
        if (libraryExists("json-20240303")) {
            StringBuilder builder = new StringBuilder("["+Main.class.getCanonicalName()+"]: Enabled features: ");
            if (FeatureManager.isFeatureEnabled("worldmanager.core")) {
                builder.append("worldmanager.core");
            } else {
                builder.append("null");
                Output.print(builder.toString());
                return;
            }
            for (Object string : FeatureManager.getEnabledFeatures()) {
                if (string.equals("worldmanager.core")) {
                    continue;
                } else {
                    FeatureManager.loadFeature(string.toString());
                    
                    builder.append(", "+string);
                }
            }
            Output.print(builder.toString());
            
        }
       // System.out.println(OSInfo.osVersion);
        
       if (args.length > 0) {
        boolean zip = false;
       // boolean verbose = false;
        String zipLoc = "";
    
        for (int i = 0; i < args.length; i++) {
            // if (args[i].equals("--verbose")) {
            //     verbose = true;
            //     Output.consoleOutput = true;
            //     Output.print("Verbose enabled, logging to both log file and console.");
            // } else 
             if (new File(args[i]).exists()) {
                zip = true;
                zipLoc = args[i];
                Output.print("["+Main.class.getCanonicalName()+"]: Archive found. Unzipping to .minecraft/saves...");
                try {
                    ZipDirectory.uncompressToSavesFolder(zipLoc);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                // if (verbose) {
                //     // Additional verbose-specific actions (if needed)
                // }
            }
        }
    
        if (!zip) {
            Gui.start(args);
        }
    } else {
        // Launch GUI
        Gui.start(args);
    }
    

        
    }
    public static boolean libraryExists(String libName) {
        File file = new File("worldmanager/lib/"+libName+".jar");
        if (file.exists()) {
            return true;
        }
        // } else if (!file.exists()) {
        //     // file = new File("worldmanager/lib/"+libName+".dll");
        //     // if (file.exists()) {
        //     //     return true;
        //     // } else {
        //         return false;
        //    //  }
        // }
        return false;
    }
    
}