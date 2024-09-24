package com.thebest12lines.worldmanager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.file.Files;

public class Output {
    private static PrintStream stream;
    private static PrintStream consoleStream;
    public static void print(String object) {
        if (stream != null) {
            System.setOut(stream);
            System.setErr(consoleStream);
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
                        // TODO Auto-generated catch block
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
                // TODO Auto-generated catch block
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
    public static boolean consoleOutput = false;
}
