package main;

import java.io.IOException;

import com.thebest12lines.worldmanager.gui.MainGui;
import org.junit.jupiter.api.Test;

import com.thebest12lines.worldmanager.launcher.Launcher;

public class FullTest {
    @Test
    public void testFullApp() {
        try {
          //  System.setProperty("java.awt.headless", "true");
         //   MainGui.launch();
            Launcher.main(new String[] {});
        } catch (IOException e) {
            // 
            e.printStackTrace();
        }
    }
}
