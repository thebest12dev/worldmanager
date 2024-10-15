package com.thebest12lines.worldmanager;

import com.thebest12lines.worldmanager.annotation.CoreClass;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.io.PrintStream;
import java.nio.file.Files;
@CoreClass
public class Output {
    
    public static ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    public static PrintStream consoleStream = new PrintStream(outputStream);
    private static Process console;
    public static PrintStream stream;
    private static void initializeConsole() {
        // try {
        //   //  console = Runtime.getRuntime().exec(new String[] {"cmd","/c","start","cmd.exe","/c","prompt", "$S","&"
        //   //  , "cls", "&", ""
        // //});
        //     //consoleStream = new PrintStream(console.getOutputStream());
            
        // } catch (IOException e) {
        //     // 
        //     e.printStackTrace();
        // }
    }
    public static void print(Object object) {
        if (console == null) {
            initializeConsole();
        }
        if (stream != null) {
            System.setOut(stream);
            
            //System.setErr(consoleStream);
            System.out.println(object);
            if (consoleOutput) {
                System.err.println(object);
            }
        } else {
            try {
                
                if (new File("worldmanager.log").exists()) {
                    try {
                        Files.write(new File("worldmanager.log").toPath(), "".getBytes());
                    } catch (IOException e) {
                        // 
                        Output.print("Output1");
                        e.printStackTrace();
                    }
                }
                
                if (!consoleOutput) {
                    stream = new PrintStream(new File("worldmanager.log"));
                } else {
                    stream = new PrintStream(new File("worldmanager.log"));
                    consoleStream = System.out;
                    System.setErr(consoleStream);
                    System.err.println(object);
                }
                
                System.setOut(stream);
                
                System.out.println(object);
                
            } catch (FileNotFoundException e) {
                //
                Output.print("Error while outputting:");
                e.printStackTrace();
            }
        }
    }
    public static void printErr(Object object) {
        System.err.println(object);
    }
    public static void printFile(Object object) {
        System.out.println(object);
    }
    public static void printDebug(Object object) {
        if ((Boolean)DataManager.getSetting("debug")) {
            print(object);
        }
    }
    public static boolean consoleOutput = false;
}
