package com.thebest12lines.worldmanager;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.thebest12lines.worldmanager.util.Constants;
import com.thebest12lines.worldmanager.util.Constants.ANSIColor;



public class Main {
    public static void main(String[] args) {
      //  System.setProperty("java.awt.headless", "true");
      Output.consoleOutput = true;
        //System.out.println(System.getProperty("os.name"));
        String appDataPath = System.getProperty("user.home") + "\\AppData\\Roaming\\.worldmanager";
        if (System.getProperty("os.name") == "Linux") {
            appDataPath = System.getProperty("user.home") + "/.worldmanager";
        }
        
        File Directory = new File(appDataPath);
        Directory.mkdir();
        new File(appDataPath+"\\worlds").mkdir();
        if ((boolean) DataManager.getSetting("debug") == true) {
        Output.printErr(
            Constants.ANSIColor.LIGHT_BLUE+"worldmanager "+ANSIColor.RESET
            +ANSIColor.GRAY+"v"+ANSIColor.RESET+ANSIColor.BOLD+DataManager.getVersion()+ANSIColor.RESET
            +" "+Character.toUpperCase(DataManager.getBranch().charAt(0))
            +DataManager.getBranch().substring(1).toLowerCase()
            +" (Debug Mode)\nLogs will be outputted to file worldmanager.log."
        );
        } else {
            Output.printErr(
                Constants.ANSIColor.LIGHT_BLUE+"worldmanager "+ANSIColor.RESET
                +ANSIColor.GRAY+"v"+ANSIColor.RESET+ANSIColor.BOLD+DataManager.getVersion()+ANSIColor.RESET
                +" "+Character.toUpperCase(DataManager.getBranch().charAt(0))
                +DataManager.getBranch().substring(1).toLowerCase()
                +"\nLogs will be outputted to file worldmanager.log."
            );
        }
        if ((boolean) DataManager.getSetting("verbose") == true) {
            Output.consoleOutput = true;
        }
       
        Output.print("["+Main.class.getCanonicalName()+"]: Starting worldmanager");
        
        Output.print("["+Main.class.getCanonicalName()+"]: Verifying libraries exist...");
        String[] libraries = {
            "3cf6cd6892e32e2b4c1c39e0f52f5248a2f5b37646fdfbb79a66b46b618414ed",
            "5b8e868ea6690d7c606e6deb0d3d6167f4b1c0fcffa2b6170b7cb1e9819b969d"
        };
        boolean isFatal = false;
        for (int i = 0; i < libraries.length; i++) {
            if (libraryExists(libraries[i])) {
                Output.print("["+Main.class.getCanonicalName()+"]: Library \""+libraries[i].substring(0, 16)+"\" found ("+(i+1)+"/"+libraries.length+")");
            } else {
                Output.print("FATAL: Library \""+libraries[i].substring(0, 16)+"\" is not found. worldmanager will not run without this library.");
                isFatal = true;
            
            }
        }
        Output.print("");
        Output.print("["+Main.class.getCanonicalName()+"]: Verifying libraries are not corrupted...");
        for (int i = 0; i < libraries.length; i++) {
            try {
                if (verifyFileHash(libraries[i])) {
                    Output.print("["+Main.class.getCanonicalName()+"]: Library \""+libraries[i].substring(0, 16)+"\" is not corrupted ("+(i+1)+"/"+libraries.length+")");
                } else {
                    Output.print("FATAL: Library \""+libraries[i].substring(0, 16)+"\" is corrupted. worldmanager will not run with these libraries.");
                    isFatal = true;
                
                }
            } catch (NoSuchAlgorithmException e) {
                // 
                e.printStackTrace();
            } catch (IOException e) {
                // 
                e.printStackTrace();
            }
        }
        if (isFatal) {
            Output.print("");
            Output.print("FATAL: Library verification check failed. worldmanager will not launch now.");
            return;
        } else {
            Output.print("");
            Output.print("["+Main.class.getCanonicalName()+"]: Library verification check success, worldmanager will launch now.");
        }
        if (libraryExists(libraries[0])) {
            Output.print("");
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
                    // 
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
        File file = new File("worldmanager/objects/main/1/"+libName+".jar");
        
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
    public static boolean verifyFileHash(String filePath) throws IOException, NoSuchAlgorithmException {
        File file = new File("worldmanager/objects/main/1/"+filePath+".jar");
        String fileName = file.getName();
        String expectedHash = fileName.split("\\.")[0]; // Extract hash from filename

        // Compute the SHA-256 hash of the file contents
        String fileHash = computeSHA256(file);

        // Compare the computed hash with the expected hash
        
        return fileHash.equals(expectedHash);
    }

    private static String computeSHA256(File file) throws IOException, NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        FileInputStream fis = new FileInputStream(file);
        byte[] byteArray = new byte[1024];
        int bytesCount;

        while ((bytesCount = fis.read(byteArray)) != -1) {
            digest.update(byteArray, 0, bytesCount);
        }
        fis.close();

        byte[] bytes = digest.digest();
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
    
}