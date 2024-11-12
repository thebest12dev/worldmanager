package com.thebest12lines.worldmanager;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import java.util.Objects;


import com.thebest12lines.worldmanager.annotation.CoreClass;
import com.thebest12lines.worldmanager.gui.MainGui;
import com.thebest12lines.worldmanager.net.Server;
import com.thebest12lines.worldmanager.util.*;
import com.thebest12lines.worldmanager.util.Constants.ANSIColor;
import joptsimple.OptionParser;
import joptsimple.OptionSet;


/**
 * The main class for initializing worldmanager. It is an extension of the <code>Instance</code> class
 * that provides functionality for initializing applications.
 *
 * @author thebest12lines
 */
@CoreClass
public class Main extends Instance {
  //  public static Console console;

    public static volatile Terminal mainTerminal = new Terminal();
    public static int themeExplicit = 0;
    public static boolean debug = false;
    public static void startServer() {
        new Thread(() -> {
            Server server = new Server();
            try {

                server.listen("action",(socket,string) -> {
                    Output.print(string);
                    if (string.equals("guiStop")) {
                        MainGui.safeClose();
                    } else if (string.equals("guiStart")) {
                        MainGui.launch();
                    }
                });
                server.start(53067);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }
    /**
     * Initializes the <code>Main</code> class with the specified arguments.
     * @param args The arguments to pass to the <code>Instance</code> class.
     */
    public static void init(String[] args) {
        new Main().onStart(args);
    }
    @Override
    public void onStart(String[] args0) {
        OptionParser parser = new OptionParser();


        parser.accepts("force-gui");
        parser.accepts("gui");
        parser.accepts("theme").withRequiredArg();
        parser.accepts("debug-mode");
        parser.accepts("version");
        parser.accepts("run");
        parser.accepts("enabled-features");
        OptionSet options = parser.parse(args0);
        Output.consoleOutput = true;
      //  FeatureProcessor.loadFeature("test");
      //  System.setProperty("java.awt.headless", "true");
        StringBuilder builder = new StringBuilder();
        String[] libraries = {
                "jar3cf6cd6892e32e2b4c1c39e0f52f5248a2f5b37646fdfbb79a66b46b618414ed",
                "jar5b8e868ea6690d7c606e6deb0d3d6167f4b1c0fcffa2b6170b7cb1e9819b969d",
                "jardf26cc58f235f477db07f753ba5a3ab243ebe5789d9f89ecf68dd62ea9a66c28",
                "dll5395d4bc7825c78abba04c5ed39b3c5698722a7eb0cd93d46203b9aaa6784316"
        };
        if (libraryExists(libraries[0].substring(3))) {
           // Output.print("");
            builder = new StringBuilder();
            if (FeatureManager.isFeatureEnabled("worldmanager.core")) {
                builder.append("worldmanager.core");
            } else {
                builder.append("null");
                Output.print(builder.toString());
                return;
            }
            for (Object string : Objects.requireNonNull(FeatureManager.getEnabledFeatures())) {
                if (string.equals("worldmanager.core")) {
                    continue;
                } else {
                    FeatureManager.loadFeature(string.toString());

                    builder.append(", ").append(string);
                }
            }
            

        }

      //  System.out.println(Arrays.toString(args0));


//        ArgumentMetadata arg1 = args.toString();
     //  System.out.println(args.get("version").getValue());
        if (options.has("force-gui")) {
            Gui.start(args0);
            return;

        }
        if (options.has("run")) {
            mainTerminal.parseFile(args0[2]);
        }
        if (options.has("help")) {
            System.err.println("""
                    worldmanager\s""" +DataManager.getVersion()+" "+DataManager.getBranch()+ """
                    
                    List of options:
                        --version: Prints the current version of worldmanager
                        --help: Displays the help screen
                        --enabled-features: Prints the list of enabled features
                        --force-gui: Forces the GUI to be launched without the standard verification process (may not work)
                        <file>: Extracts the zip file to the Minecraft saves directory
                        --theme=<theme>: Explicitly chooses the theme for the GUI
                            dark: Sets the theme to dark
                            light: Sets the theme to light
                    
                    Experimental options:
                    Note that some features might be unstable!
                        --debug-mode: Explicitly turns on debug mode
                    """);
            return;
        }
        if (options.has("version")) {
            System.err.println("worldmanager version "+DataManager.getFullVersion());
            return;
        }
        if (options.has("enabled-features")) {
            System.err.println("Enabled features (worldmanager): "+ builder);
            return;
        }
        if (options.has("theme")) {

            themeExplicit =((String) options.valueOf("--value")).equalsIgnoreCase("light") ? 1 : 2;
        }
        boolean[] t = new boolean[1];
        t[0] = true;
        if (options.has("debug-mode")) {
            Output.print(ANSIColor.BOLD+"Debug mode is turned on, enabling debug console!"+ANSIColor.RESET);
            debug = true;
            new Thread(() -> {


                t[0] = false;



                mainTerminal.setVisible(true);
            }).start();

        }


      System.out.println();
    //  System.loadLibrary("SystemSettings");
        //System.out.println(System.getProperty("os.name"));
        String appDataPath = System.getProperty("user.home") + "\\AppData\\Roaming\\.worldmanager";
        if (Objects.equals(System.getProperty("os.name"), "Linux")) {
            appDataPath = System.getProperty("user.home") + "/.worldmanager";
        }
        
        File Directory = new File(appDataPath);
        Directory.mkdir();
        new File(appDataPath+"\\worlds").mkdir();

       
        Output.print("["+Main.class.getCanonicalName()+"]: Starting worldmanager");
        
        Output.print("["+Main.class.getCanonicalName()+"]: Verifying libraries exist...");
        
        boolean isFatal = false;
        for (int i = 0; i < libraries.length; i++) {
            if (libraryExists(libraries[i].substring(3))) {
                Output.print("["+Main.class.getCanonicalName()+"]: Library \""+libraries[i].substring(3, 19)+"\" found ("+(i+1)+"/"+libraries.length+")");
                if (libraries[i].substring(0,3).equals("dll")) {
                    System.loadLibrary(libraries[i].substring(3));
                }
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
        Output.print("");
        if (isFatal) {
            Output.print("FATAL: Library verification check failed. worldmanager will not launch now.");
            return;
        } else {
            Output.print("["+Main.class.getCanonicalName()+"]: Library verification check success, worldmanager will launch now.");
        }
        Output.print("["+Main.class.getCanonicalName()+"]: Enabled features: "+builder.toString());
       // System.out.println(OSInfo.osVersion);
        
       if (!options.hasOptions()) {
        boolean zip = false;
       // boolean verbose = false;
        String zipLoc = "";

        for (int i = 0; i < args0.length; i++) {
            // if (args[i].equals("--verbose")) {
            //     verbose = true;
            //     Output.consoleOutput = true;
            //     Output.print("Verbose enabled, logging to both log file and console.");
            // } else
             if (new File(args0[i]).exists()) {
                zip = true;
                zipLoc = args0[i];
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

        startServer();

           if (!zip) {
            if (t[0]) {
                // Launch GUI
                Gui.start(args0);
            }
        }
    } else {
           if (t[0]) {
               // Launch GUI
               Gui.start(args0);
           }

    }
    

        
    }
    public static boolean libraryExists(String libName) {
        File file = new File("worldmanager/objects/main/1/"+libName+".jar");
        
        if (file.exists()) {
            return true;
        } else if (!file.exists()) {
            file = new File("worldmanager/objects/main/2/"+libName+".dll");
            return file.exists();
        }
        return false;
    }
    public static boolean verifyFileHash(String filePath) throws IOException, NoSuchAlgorithmException {
        File file = new File("worldmanager/objects/main/1/"+filePath.substring(3)+".jar");
        if (filePath.contains("dll")) {
            file = new File("worldmanager/objects/main/2/"+filePath.substring(3)+".dll");
        }
        String fileName = file.getName();
        String expectedHash = fileName.split("\\.")[0]; // Extract hash from filename

        // Compute the SHA-256 hash of the file contents
        String fileHash = computeSHA256(file);

        // Compare the computed hash with the expected hash
        
        return fileHash.equals(expectedHash);
    }

    public static String computeSHA256(File file) throws IOException, NoSuchAlgorithmException {
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
    public static String computeSHA256(String text) throws IOException, NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] byteArray = text.getBytes();
        digest.update(byteArray,0, text.getBytes().length);
        byte[] bytes = digest.digest();
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    @Override
    public void onStop() {

    }
}