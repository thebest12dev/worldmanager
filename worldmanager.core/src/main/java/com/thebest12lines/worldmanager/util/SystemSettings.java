package com.thebest12lines.worldmanager.util;

import worldmanager.features.annotation.CoreClass;

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
    public static native String getSystemTheme();
}
