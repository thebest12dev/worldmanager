package main;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import com.thebest12lines.worldmanager.launcher.Launcher;

public class FullTest {
    @Test
    public void testFullApp() {
        try {
            Launcher.main(new String[] {});
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
