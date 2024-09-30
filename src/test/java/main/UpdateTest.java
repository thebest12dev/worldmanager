package main;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import com.thebest12lines.worldmanager.util.UpdateBuildException;
import com.thebest12lines.worldmanager.util.Updater;

public class UpdateTest {
    @Test
    public void testDeleteUpdate() {
       // System.setProperty("java.awt.headless", "true");

            new File("worldmanager_0.jar").delete();
         
    }
    @Test
    public void testUpdateCheck() {
        try {
          //  System.setProperty("java.awt.headless", "true");

            Updater.checkForUpdates();
        } catch (UpdateBuildException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    @Test
    public void testUpdateDownload() {
        try {
          //  System.setProperty("java.awt.headless", "true");

            Updater.downloadAndInstallUpdates();
            
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
        
    }
     
}
