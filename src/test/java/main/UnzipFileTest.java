package main;



import org.junit.jupiter.api.Test;

//import com.thebest12lines.worldmanager.Main;
import com.thebest12lines.worldmanager.launcher.Launcher;
// import com.thebest12lines.worldmanager.util.Updater;

public class UnzipFileTest {
    @Test
    public void testUnzip() {
        try {
          ///  System.setProperty("java.awt.headless", "true");

            Launcher.main(new String[] {"./test.zip"});
        } catch (Exception e) {
            // 
            e.printStackTrace();
        }
            
            // Updater.checkForUpdates();
        
    }

}
