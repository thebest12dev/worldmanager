package com.thebest12lines.worldmanager.util;

public class SystemSettings {
    static {
        System.loadLibrary("SystemSettings");
    }
    public static native String getSystemTheme();
}
