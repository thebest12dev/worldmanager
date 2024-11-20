package com.thebest12lines.worldmanager.util.internal;

import com.thebest12lines.worldmanager.Main;
import com.thebest12lines.worldmanager.util.NotificationManager;
import com.thebest12lines.worldmanager.util.Output;
import common.SharedStorage;
import common.Terminal;
import worldmanager.features.internal.CoreClass;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * The de facto singleton for initializing and using worldmanager.
 * @author thebest12lines
 */
@CoreClass

public class CoreApplication implements common.CoreApplication {

    private static CoreApplication instance;
    private Map<String, Object> storage = new HashMap<>();

    public CoreApplication() {

    }
    static {
        getCoreApplication();
        SharedStorage.setValue("worldmanager.CoreApplication",getCoreApplication());
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
    public void forceExit() {
        Output.print("["+CoreApplication.class.getCanonicalName()+"]: Force stop (debug key invoked)!");
        System.exit(0x00000003);
    }
    public void invokeAction(String action, Object... values) {
        GlobalListener.fire(action,values);
    }
    public void setGlobalValue(String key, Object value) { storage.put(key, value); }
    public Object getGlobalValue(String key) { return storage.get(key); }
    public void sendNotification(String title, String text) {
        try {
            NotificationManager.createNotification(title, text).show();
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
    }

}
