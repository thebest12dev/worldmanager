package com.thebest12lines.worldmanager;

import javax.swing.UnsupportedLookAndFeelException;

import com.thebest12lines.worldmanager.gui.MainGui;

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
            try {
                MainGui.launch();
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                    | UnsupportedLookAndFeelException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
