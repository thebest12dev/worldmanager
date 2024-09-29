package main;
import javax.swing.UnsupportedLookAndFeelException;

import com.thebest12lines.worldmanager.gui.MainGui;
import com.thebest12lines.worldmanager.util.UpdateBuildException;
import com.thebest12lines.worldmanager.util.Updater;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UITest {
    @Test
    public void testUI() {
        try {
            System.setProperty("java.awt.headless", "true");

            MainGui.launch();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                | UnsupportedLookAndFeelException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //assertEquals(8, result);
    }


}
