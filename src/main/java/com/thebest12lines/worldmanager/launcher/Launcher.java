package com.thebest12lines.worldmanager.launcher;

import java.io.IOException;

import com.thebest12lines.worldmanager.Main;
import com.thebest12lines.worldmanager.annotation.CoreClass;

/// The de facto main class for launching worldmanager.
/// @author thebest12lines
/// @deprecated Use `worldmanager.Launcher` instead. Currently, the launcher class will not be removed due to compatibility reasons.
///
@Deprecated
@CoreClass
public class Launcher {
    /**
     * Launches worldmanager.
     * @param args The arguments to pass
     * @throws IOException Due to the <code>Main</code> class
     */
    public static void main(String[] args) throws IOException {
    //    System.setProperty("java.awt.headless", "true");
        worldmanager.Launcher.main(args);
    }
}
