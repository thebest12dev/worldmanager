package com.thebest12lines.worldmanager.util;

import com.thebest12lines.worldmanager.Main;
import com.thebest12lines.worldmanager.annotation.CoreClass;
/**
 * The de facto singleton for initializing and using worldmanager.
 * @author thebest12lines
 */
@CoreClass

public class CoreApplication {

    private static CoreApplication instance;


    public CoreApplication() {
    }

    public static CoreApplication getCoreApplication() {
        if (instance == null) {
            instance = new CoreApplication();
        }
        return instance;
    }

    public void start(String[] args) {
        Main.init(args);
    }
    public void start() {
        Main.init(new String[0]);
    }
    public void exit(int code) {
        Output.print("["+CoreApplication.class.getCanonicalName()+"]: Stopping worldmanager!");
        System.exit(code);
    }
    public Terminal getTerminal() {
        return Main.mainTerminal;

    }

}
