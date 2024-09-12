package com.thebest12lines;

import com.thebest12lines.gui.MainGui;

public class Gui {
    public static void start(String[] args) {
       // System.out.println("Main thread is: " + Thread.currentThread().getName());

        // Create a new thread (Thread-0) with our custom Runnable
        Thread t = new Thread(new RunnableImpl());
        t.start();
    }

    private static class RunnableImpl implements Runnable {
        public void run() {
            
            
            Output.print("["+RunnableImpl.class.getCanonicalName()+"]: Launching GUI");
            MainGui.launch();
        }
    }
}
