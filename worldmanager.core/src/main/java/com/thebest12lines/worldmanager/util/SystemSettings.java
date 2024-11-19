package com.thebest12lines.worldmanager.util;

import worldmanager.features.internal.CoreClass;

/**
 * A class that retrieves system settings using shared libraries.
 * @author thebest12lines
 */
@CoreClass
public class SystemSettings {
    /**
     * Gets the system theme. Windows only.
     * @return The current system theme ('Light' or 'Dark')
     */
    static {
        System.loadLibrary("5395d4bc7825c78abba04c5ed39b3c5698722a7eb0cd93d46203b9aaa6784316");
    }
    public static native String getSystemTheme();
}
