package main;



import org.junit.jupiter.api.Test;

import com.thebest12lines.worldmanager.launcher.Launcher;
import com.thebest12lines.worldmanager.util.Updater;

public class UnzipFileTest {
    @Test
    public void testUnzip() {
        try {
            Launcher.main(new String[] {"test.zip"});
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
            
            // Updater.checkForUpdates();
        
    }
}
