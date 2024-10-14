package com.thebest12lines.worldmanager.gui;

import javax.swing.*;

import com.thebest12lines.worldmanager.Output;
import com.thebest12lines.worldmanager.annotation.CoreClass;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
@CoreClass
public class Console extends JFrame {

    private JTextArea consoleArea;
    private JScrollPane scrollPane;

    public Console() {
        super("Console");
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                | UnsupportedLookAndFeelException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
       // setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 500);
        setLocationRelativeTo(null);

        // Create a JTextArea for the console output
        consoleArea = new JTextArea();
        consoleArea.setEditable(false);
        consoleArea.setFont(new Font("Consolas", Font.PLAIN, 13));
        consoleArea.setBackground(Color.BLACK);
        consoleArea.setForeground(Color.white);

        // Create a JScrollPane to enable scrolling
        scrollPane = new JScrollPane(consoleArea);

        // Add the scroll pane to the frame
        add(scrollPane);

        // Redirect System.out and System.err to the console area
        redirectOutput();
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Choose your desired behavior:
                setVisible(false); // Hide the window
                dispose(); // Dispose of the window's resources
            }
        }); 
        // Show the window
        setVisible(true);
    }

    private void redirectOutput() {
        // Create a ByteArrayOutputStream to capture output
        ByteArrayOutputStream outputStream = Output.outputStream;

        // Redirect System.out and System.err to the print stream
        // System.setOut(printStream);
        // System.setErr(printStream);

        // Create a timer to periodically update the console area
        Timer timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the output from the ByteArrayOutputStream
                String output = outputStream.toString();

                // Clear the ByteArrayOutputStream
                outputStream.reset();

                // Append the output to the console area
                consoleArea.append(output);

                // Scroll to the bottom of the console area
                consoleArea.setCaretPosition(consoleArea.getDocument().getLength());
            }
        });
        timer.start();
    }

    
}
