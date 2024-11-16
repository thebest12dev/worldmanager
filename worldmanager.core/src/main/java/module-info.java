/**
 * <code>worldmanager v0.2.0 Alpha</code>
 * <p style="color:#0077ff">
 * The worldmanager.core module for core features present in the program. Without this module, worldmanager would not work. This module has a special worldmanager.json file that is configured to save and get storage from it. This module also depends on 2 open source modules. See <a href="README.md">README.md</a> for attribution.
 * This code is subject to updates, so make sure that worldmanager is updated before launching it.
 * </p>
 * <p>
 * This project is licensed under the MIT License, which means you are free to modify, distribute or do anything with it as LONG you include the MIT license and include the original copyright holder (thebest12lines).
 * </p>
 * @since Initial release
 * @author thebest12lines
 */
module worldmanager.core {

    // requires java.desktop;

    requires worldmanager.features;
    requires common;
    requires jopt.simple;
    requires java.desktop;
    requires org.json;
    requires java.net.http;
    requires NBT;


    exports com.thebest12lines.worldmanager;
    exports com.thebest12lines.worldmanager.gui;
    exports com.thebest12lines.worldmanager.util;
    exports com.thebest12lines.worldmanager.world;
}