package com.thebest12lines.worldmanager.launcher;

import java.io.IOException;

import com.thebest12lines.worldmanager.Main;

public class Launcher {
    public static void main(String[] args) throws IOException {
        System.setProperty("java.awt.headless", "true");
        Main.main(args);
    }
}
