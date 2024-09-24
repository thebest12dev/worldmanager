package com.thebest12lines.worldmanager;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.zip.ZipOutputStream;

import com.thebest12lines.worldmanager.ZipDirectory;

import java.nio.file.*;

public class Main {
    public static void main(String[] args) {
        
        String appDataPath = System.getProperty("user.home") + "\\AppData\\Roaming\\.worldmanager";
        File Directory = new File(appDataPath);
        Directory.mkdir();
        new File(appDataPath+"\\worlds").mkdir();
        System.out.println("worldmanager 0.1.0 Alpha\nLogs will be outputted to file worldmanager.log, use --verbose to output logs to console.");
       // System.out.println(OSInfo.osVersion);
        
       if (args.length > 0) {
        boolean zip = false;
        boolean verbose = false;
        String zipLoc = "";
    
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("--verbose")) {
                verbose = true;
                Output.consoleOutput = true;
                Output.print("Verbose enabled, logging to both log file and console.");
            } else if (new File(args[i]).exists()) {
                zip = true;
                zipLoc = args[i];
                Output.print("["+Main.class.getCanonicalName()+"]: Archive found. Unzipping to .minecraft/saves...");
                try {
                    ZipDirectory.uncompressToSavesFolder(zipLoc);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                if (verbose) {
                    // Additional verbose-specific actions (if needed)
                }
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
    
}