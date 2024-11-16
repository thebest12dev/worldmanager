package main;

import java.io.IOException;

import com.thebest12lines.worldmanager.gui.MainGui;
import org.junit.jupiter.api.Test;

import worldmanager.Launcher;

public class FullTest {
    @Test
    public void testFullApp() {
        //  System.setProperty("java.awt.headless", "true");
        //   MainGui.launch();
        Launcher.main(new String[] {});
    }
}
