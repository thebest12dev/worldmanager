package com.thebest12lines.worldmanager.util;

import com.thebest12lines.worldmanager.DataManager;
import worldmanager.features.annotation.CoreClass;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.io.PrintStream;
import java.nio.file.Files;

/**
 * A proper way to log messages for worldmanager.
 * @author thebest12lines
 */
@CoreClass
public class Output {

    public static ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    public static PrintStream consoleStream = new PrintStream(outputStream);
    private static Process console;
    public static PrintStream stream;

    // Severity levels
    public static final int LOG_DEBUG = 0;
    public static final int LOG_INFO = 1;
    public static final int LOG_WARN = 2;
    public static final int LOG_ERROR = 3;
    public static final int LOG_FATAL = 4;
    public static final int LOG = LOG_INFO; // Default severity

    public static boolean consoleOutput = false;

    /**
     * Prints to both console and worldmanager output, depending on the consoleOutput boolean.
     * Uses default severity LOG.
     * @param object The object to print.
     */
    public static void print(Object object) {
        print(object, LOG);
    }

    /**
     * Prints to both console and worldmanager output, depending on the consoleOutput boolean.
     * @param object The object to print.
     * @param severity The severity level of the log.
     */
    public static void print(Object object, int severity) {
        String severityLabel = getSeverityLabel(severity);
        if (object == null || object.toString().trim().isEmpty()) {
            severityLabel = "";
        }

        if (stream != null) {
            System.setOut(stream);
            System.out.println(severityLabel + object);
            if (consoleOutput) {
                System.err.println(severityLabel + object);
            }
        } else {
            try {
                if (new File("worldmanager.log").exists()) {
                    try {
                        Files.write(new File("worldmanager.log").toPath(), "".getBytes());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                if (!consoleOutput) {
                    stream = new PrintStream(new File("worldmanager.log"));
                } else {
                    stream = new PrintStream(new File("worldmanager.log"));
                    consoleStream = System.out;
                    System.setErr(consoleStream);
                    System.err.println(severityLabel + object);
                }

                System.setOut(stream);
                System.out.println(severityLabel + object);

            } catch (FileNotFoundException e) {
                Output.print("Error while outputting:", LOG_ERROR);
                e.printStackTrace();
            }
        }
    }

    /**
     * Prints only to error.
     * @param object The object to print.
     */
    public static void printErr(Object object) {
        System.err.println(object);
    }

    /**
     * Prints only to worldmanager.
     * @param object The object to print.
     */
    public static void printFile(Object object) {
        System.out.println(object);
    }

    /**
     * Prints only if debug is enabled.
     * @param object The object to print.
     */
    public static void printDebug(Object object) {
        if ((Boolean) DataManager.getSetting("debug")) {
            print(object, LOG_DEBUG);
        }
    }

    /**
     * Prints the object as a literal without any severity label.
     * @param object The object to print.
     */
    public static void printLiteral(Object object) {
        if (stream != null) {
            System.setOut(stream);
            System.out.println(object);
        } else {
            try {
                if (new File("worldmanager.log").exists()) {
                    try {
                        Files.write(new File("worldmanager.log").toPath(), "".getBytes());
                    } catch (IOException e) {
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
                Output.print("Error while outputting:", LOG_ERROR);
                e.printStackTrace();
            }
        }
    }

    /**
     * Gets the label for the given severity level.
     * @param severity The severity level.
     * @return The severity label.
     */
    private static String getSeverityLabel(int severity) {
        switch (severity) {
            case LOG_DEBUG:
                return "[DEBUG] ";
            case LOG_INFO:
                return "[INFO] ";
            case LOG_WARN:
                return "[WARN] ";
            case LOG_ERROR:
                return "[ERROR] ";
            case LOG_FATAL:
                return "[FATAL] ";
            default:
                return "[INFO] ";
        }
    }
}
