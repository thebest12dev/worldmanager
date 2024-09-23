package com.thebest12lines.worldmanager.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;
import com.thebest12lines.worldmanager.gui.FlatLabel;
import com.thebest12lines.worldmanager.util.Updater;
import com.thebest12lines.worldmanager.util.Constants.UpdateCheckResult;
import com.thebest12lines.worldmanager.util.UpdateBuildException;

import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;
import java.util.ArrayList;
//import org.json.*;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;


public class MainGui {
    /**
     * Launches the main GUI.
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws UnsupportedLookAndFeelException
     */
    public static void launch() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
        JFrame mainFrame = new JFrame("worldmanager Alpha 0.0.1");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(800, 500);
        mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        JFrame updateFrame = new JFrame("Update");
        mainFrame.setIconImage(Toolkit.getDefaultToolkit().getImage("minecraft.png"));
       // UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        
        // updateFrame.setVisible(true);
        updateFrame.setResizable(false);
        updateFrame.setSize(500, 300);

        int mainFrameX = mainFrame.getX();
        int mainFrameY = mainFrame.getY();
        int mainFrameWidth = mainFrame.getWidth();
        int mainFrameHeight = mainFrame.getHeight();

        int updateFrameX = mainFrameX + (mainFrameWidth - updateFrame.getWidth()) / 2;
        int updateFrameY = mainFrameY + (mainFrameHeight - updateFrame.getHeight()) / 2;

        updateFrame.setLocation(updateFrameX, updateFrameY);
        updateFrame.setAlwaysOnTop(true);
        updateFrame.setType(JFrame.Type.UTILITY);
        updateFrame.setLayout(new BorderLayout());
        

        JLabel updateVersion = FlatLabel.createFlatLabel("0.1.0 Alpha");
        JPanel panel_ = new JPanel();
        
        updateVersion.setFont(new Font("Segoe UI",Font.PLAIN,25));
        panel_.add(updateVersion);
        JTextArea changelog = new JTextArea();
        JPanel panel2 = new JPanel();
        changelog.setFont(new Font("Segoe UI",Font.PLAIN,16));
       // changelog.setPreferredSize(new Dimension(100, 200));
        changelog.setLineWrap(true); // Enable line wrapping
        changelog.setWrapStyleWord(true); // Wrap at word boundaries
        changelog.setRows(8);
        changelog.setColumns(34);
        changelog.setEditable(true);
        changelog.setText("     We recommend you install the latest update to experience the                                       latest bug fixes and features.\r\n" + //
                    "\r\n" + //
                    "                                        - Added new features\r\n" + //
                    "                                        - Added updating (0.1.0+)");
        
      //  changelog.setBackground(Color.lightGray);
        panel2.add(changelog);
        JScrollPane scrollPane =new JScrollPane(changelog);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
                @Override
                protected void configureScrollBarColors() {
                    int r = 255;
                    int g = 190;
                    this.thumbColor = new Color(g,g,g);
                    this.trackColor = new Color(r,r,r);
                }

                @Override
                protected JButton createDecreaseButton(int orientation) {
                    return createZeroButton();
                }

                @Override
                protected JButton createIncreaseButton(int orientation) {
                    return createZeroButton();
                }

                private JButton createZeroButton() {
                    JButton button = new JButton();
                    button.setPreferredSize(new Dimension(0, 0));
                    button.setMinimumSize(new Dimension(0, 0));
                    button.setMaximumSize(new Dimension(0, 0));
                    return button;
                }
            });
        panel2.add(scrollPane);
        updateFrame.add(panel_,BorderLayout.NORTH);
       // JButton updateButton = FlatButton.createFlatButton();
        
        JButton changelogButton = FlatButton.createFlatButton("<html><center>Changelog</center></html>");
        changelogButton.setFont(new Font("Segoe UI",Font.PLAIN,16));
        changelogButton.setMargin(new Insets(10,10,10,10));
        changelogButton.setPreferredSize(new Dimension(90,30));
        changelogButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
               try {
            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                URI url = new URI("https://github.com/thebest12dev/worldmanager/releases/latest"); // Replace with your desired URL
                Desktop.getDesktop().browse(url);
            } else {
                System.out.println("Desktop browsing is not supported on this platform.");
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        }
               
            
            
            
        });
        JPanel btnPanel = new JPanel();
        //btnPanel.setLayout(new BorderLayout());
        updateFrame.add(panel2);
        JButton updateButton = FlatButton.createFlatButton("<html><center>Update</center></html>");

    
        updateButton.setFont(new Font("Segoe UI",Font.PLAIN,16));
        updateButton.setMargin(new Insets(10,10,10,10));
        updateButton.setPreferredSize(new Dimension(70,30));
        updateButton.setLocation(15, 15);
        updateButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                    Thread thread = new Thread("UpdateThread"){
                        @Override
                        public void run() {
                            try {
                                Updater.downloadAndInstallUpdates();
                            } catch (UpdateBuildException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                        }
                    };
                    thread.start();
                
            }
            
        });
        JButton cancelButton = FlatButton.createFlatButton("<html><center>Ignore</center></html>");
       // cancelButton.setText("Ignore");
        cancelButton.setFont(new Font("Segoe UI",Font.PLAIN,16));
        cancelButton.setMargin(new Insets(10,10,10,10));
        cancelButton.setPreferredSize(new Dimension(70,30));
       // cancelButton.setLocation(15, 15);
        btnPanel.add(changelogButton);
        btnPanel.add(updateButton);
        btnPanel.add(cancelButton);
        updateFrame.add(btnPanel,BorderLayout.SOUTH);

        
        
        
       // updateFrame.setVisible(true);
        
        JMenuBar menuBar = FlatMenuBar.createFlatMenuBar();
        ArrayList<JMenu> jMenus = new ArrayList<JMenu>();
        jMenus.add(FlatMenu.createFlatMenu("File", menuBar));
        jMenus.add(FlatMenu.createFlatMenu("Edit", menuBar));
        jMenus.add(FlatMenu.createFlatMenu("World", menuBar));
        jMenus.add(FlatMenu.createFlatMenu("Help", menuBar));
        
        
        
        //menuBar.setLayout(new BorderLayout());

        // try {
        //     //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        // } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
        //         | UnsupportedLookAndFeelException e) {
        //     // TODO Auto-generated catch block
        //     e.printStackTrace();
        // }
        mainFrame.setVisible(true);
        // JButton button = FlatButton.createFlatButton();
        
        // button.setText("text");
        // button.setPreferredSize(new Dimension(25, 25));
        // button.setVisible(true);
        //mainFrame.add(button);
        // Update
        
        mainFrame.setJMenuBar(menuBar);
        mainFrame.revalidate();
        mainFrame.repaint();
        try {
            UpdateCheckResult result = Updater.checkForUpdates();
            if (result == UpdateCheckResult.UPDATE_NEEDED) {
                updateFrame.setVisible(true);
            }
        } catch (UpdateBuildException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
