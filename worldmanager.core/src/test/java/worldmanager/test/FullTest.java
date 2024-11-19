package worldmanager.test;
import com.thebest12lines.worldmanager.Gui;
import com.thebest12lines.worldmanager.Main;
import com.thebest12lines.worldmanager.gui.MainGui;
import com.thebest12lines.worldmanager.util.Terminal;
import org.junit.jupiter.api.Test;

import worldmanager.Launcher;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class FullTest {

    @Test
    public void testMain() throws InterruptedException {
            Launcher.main(new String[] {});
        // Add a sleep or wait to allow the GUI to fully launch
        Thread.sleep(1500); // Adjust the time as needed
    }
    @Test
    public void testTerminal() throws InterruptedException, AWTException {
        var terminal = new Terminal();
        terminal.setVisible(true);

        terminal.processCommand("echo Hello world!");
        terminal.processCommand("version");
        terminal.processCommand("worldmanager --version");
        terminal.processCommand("worldmanager -gui");
        terminal.processCommand("@var 2");
        terminal.processCommand("echo @var");
        Robot r = new Robot();
        r.keyPress(KeyEvent.VK_E);
        r.keyRelease(KeyEvent.VK_E);
        Thread.sleep(1500); // Adjust the time as needed
    }

}
