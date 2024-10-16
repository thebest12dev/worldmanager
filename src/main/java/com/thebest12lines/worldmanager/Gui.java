package com.thebest12lines.worldmanager;

import com.thebest12lines.worldmanager.annotation.CoreClass;
import com.thebest12lines.worldmanager.gui.MainGui;
import com.thebest12lines.worldmanager.util.Output;

/**
 * The used class for starting the GUI. It creates a thread-safe GUI that will not cause any problems with the main thread.
 * @author thebest12lines
 */
@CoreClass

public class Gui {
    /**
     * Launches the GUI.
     * @param args The arguments to be passed to the GUI.
     */
    public static void start(String[] args) {
       // System.out.println("Main thread is: " + Thread.currentThread().getName());

        // Create a new thread (Thread-0) with our custom Runnable
        Thread t = new Thread(new RunnableImpl());
        t.start();
    }

    private static class RunnableImpl implements Runnable {
        public void run() {
            
            
            Output.print("["+RunnableImpl.class.getCanonicalName()+"]: Launching GUI");
            try {
                MainGui.launch();
            } catch (Exception e) {
                // 
                e.printStackTrace();
            }
        }
    }
}
