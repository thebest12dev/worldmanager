module worldmanager.core {
    requires java.base;
 ///   requires java.desktop;
    //requires org.json;
    requires java.net.http;
   // requires NBT;
    requires transitive java.desktop;
    exports com.thebest12lines.worldmanager;
    exports com.thebest12lines.worldmanager.gui;
    exports com.thebest12lines.worldmanager.launcher;
    exports com.thebest12lines.worldmanager.util;
    exports com.thebest12lines.worldmanager.world;
}
