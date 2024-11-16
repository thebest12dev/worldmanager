package com.thebest12lines.worldmanager.util;

import worldmanager.features.annotation.CoreClass;

/**
 * The class that is the initializer for worldmanager. This class contains the code that is executed right after the program is launched.
 * It contains essential methods for the lifecycle of worldmanager. This includes on start, on updating, on stop, and other lifecycles. These methods are handled by the upper-level <code>Launcher</code> class
 * which will call these methods accordingly.
 * @author thebest12lines
 */
@CoreClass
public abstract class Instance {
    /**
     * Executes the "onStart" lifecycle in the Instance.
     * @param arguments The arguments to pass when called.
     */
    public abstract void onStart(String[] arguments);
    public abstract void onStop();
}
