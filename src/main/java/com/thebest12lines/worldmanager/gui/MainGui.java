package com.thebest12lines.worldmanager.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;
import com.thebest12lines.worldmanager.Output;
import com.thebest12lines.worldmanager.ZipDirectory;
import com.thebest12lines.worldmanager.util.Updater;
import com.thebest12lines.worldmanager.util.Constants.UpdateCheckResult;
import com.thebest12lines.worldmanager.world.SaveManager;
import com.thebest12lines.worldmanager.world.World;
import com.thebest12lines.worldmanager.util.UpdateBuildException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URI;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;




public class MainGui {
    
    public static JFrame getMainFrame()  {
        
            return mainFrame;
        
        
    }
    public static JMenuBar getMenuBar()  {
            return menuBar;
        
        
    }
    protected static JFrame mainFrame = new JFrame("worldmanager");
    protected static JMenuBar menuBar;
    /**
     * Launches the main GUI.
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws UnsupportedLookAndFeelException
     */
    public static void launch() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
      //  mainFrame = new JFrame("worldmanager Alpha 0.1.0");
       // System.setProperty("java.awt.headless", "true");
       mainFrame.addWindowListener(new WindowAdapter() {
        @Override
        public void windowClosing(WindowEvent e) {
            // Choose your desired behavior:
            mainFrame.setVisible(false); // Hide the window
            mainFrame.dispose(); // Dispose of the window's resources
            System.exit(0); // Terminate the application
        }
    }); 
        ArrayList<Image> icons = new ArrayList<>();
        icons.add(createImageIcon("/icon16.png").getImage());
        icons.add(createImageIcon("/icon32.png").getImage());

        mainFrame.setIconImages(icons);
        
        //mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(800, 500);
        
       // mainFrame.setBackground(new Color());
        mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        JFrame updateFrame = new JFrame("Update");
       // UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
       
            // mainFrame.setIconImage(null);
        
        UIManager.put("Tree.drawsFocusBorderAroundIcon", false);
        UIManager.put("Tree.drawDashedFocusIndicator", false);

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
                // 
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
                // 
                    Thread thread = new Thread("UpdateThread"){
                        @Override
                        public void run() {
                            try {
                                Updater.downloadAndInstallUpdates();
                            } catch (Exception e) {
                                // 
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

        UIManager.put("Tree.collapsedIcon",createImageIcon("/caret-right-fill.png", "set"));
        UIManager.put("Tree.expandedIcon", createImageIcon("/caret-down-fill.png", null));
        Font worldFont = new Font("Segoe UI Light", Font.PLAIN, 13);
        Font normalFont = new Font("Segoe UI", Font.PLAIN, 13);
       // updateFrame.setVisible(true);
        
        menuBar = FlatMenuBar.createFlatMenuBar();
        ArrayList<JMenu> jMenus = new ArrayList<JMenu>();
        jMenus.add(FlatMenu.createFlatMenu("File", menuBar));
        jMenus.add(FlatMenu.createFlatMenu("Edit", menuBar));
        jMenus.add(FlatMenu.createFlatMenu("World", menuBar));
        jMenus.add(FlatMenu.createFlatMenu("Help", menuBar));
        JMenu file = jMenus.get(0);
        JMenu help = jMenus.get(3);
      //  help.add(new JSeparator());
      
        JFrame infoFrame = new JFrame("About worldmanager");
        infoFrame.setSize(500,300);
        infoFrame.setResizable(false);
        infoFrame.setLayout(new BorderLayout());
        infoFrame.setIconImages(icons);
        infoFrame.setAlwaysOnTop(true);
        JLabel version = new JLabel("An open source world manager for Minecraft");
        
        version.setFont(normalFont);
        
        infoFrame.add(new JLabel(createImageIcon("/logo.png")),BorderLayout.NORTH);
        infoFrame.add(version);
        JMenuItem info = FlatMenuItem.createFlatMenuItem("About", "");

        info.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int mainFrameX = mainFrame.getX();
                int mainFrameY = mainFrame.getY();
                int mainFrameWidth = mainFrame.getWidth();
                int mainFrameHeight = mainFrame.getHeight();

                int updateFrameX = mainFrameX + (mainFrameWidth - updateFrame.getWidth()) / 2;
                int updateFrameY = mainFrameY + (mainFrameHeight - updateFrame.getHeight()) / 2;
                infoFrame.setVisible(true);
                infoFrame.setLocation(updateFrameX, updateFrameY);
            }
            
        });
        help.add(info);
        JMenuItem item1 = FlatMenuItem.createFlatMenuItem("New Backup","Ctrl+N");
        item1.setFont(normalFont);
        file.add(item1);
        file.add(new JSeparator());
        JMenuItem item2 = FlatMenuItem.createFlatMenuItem("Open World...","Ctrl+O");
      //  item2.setFont(normalFont);
        // item2.
        file.add(item2);
        JMenuItem item3 = FlatMenuItem.createFlatMenuItem("Open Backup...","Ctrl+B");
        item3.setFont(normalFont);
        file.add(item3);
        JMenuItem item5 = FlatMenuItem.createFlatMenuItem("Import World...","Ctrl+Shift+O");
        item5.setFont(normalFont);
        file.add(item5);
        file.add(new JSeparator());
        JMenuItem item4 = FlatMenuItem.createFlatMenuItem("Exit","");
        item4.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // 
                System.exit(0);
            }
            
        });
        item4.setFont(normalFont);
        file.add(item4);
        
        JPanel worldsList = new JPanel();
        worldsList.setLayout(new BoxLayout(worldsList, BoxLayout.Y_AXIS));
        worldsList.setVisible(true);
        //JPanel content = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        worldsList.setPreferredSize(new Dimension(250, mainFrameHeight));
        
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Worlds (Java Edition)");
       // DefaultMutableTreeNode world2 = new DefaultMutableTreeNode("World1");
      //
      JPopupMenu worldMenu1 = FlatPopupMenu.createFlatPopupMenu();
        World[] worldsArray = (World[]) SaveManager.getWorlds();
        for (World object : worldsArray) {
            
            WorldMutableTreeNode world = new WorldMutableTreeNode(object.getWorldName());
            world.setWorld(object);
            
           WorldMutableTreeNode backups = new WorldMutableTreeNode("Backups");
           backups.setWorld(object);
            
            world.add(backups);
            
            root.add(world);
        }
        
      //  root.add(world2);
        
                
      JMenuItem worldMenuItem1 = new JMenuItem("Backup World");
        
      worldMenuItem1.setBorder(null);
      worldMenuItem1.setFont(worldFont);
      worldMenu1.add(worldMenuItem1);
      
        JMenuItem worldMenuItem2 = new JMenuItem("Delete World");
        worldMenuItem2.setBorder(null);
        worldMenuItem2.setFont(worldFont);
        worldMenu1.add(worldMenuItem2);

        JMenuItem worldMenuItem3 = new JMenuItem("Properties");
        worldMenuItem3.setBorder(null);
        worldMenuItem3.setFont(worldFont);
        worldMenu1.add(worldMenuItem3);
        DefaultTreeModel model = new DefaultTreeModel(root);
        JPanel jPanel = new JPanel(new BorderLayout());

        JTree worlds = new JTree(model);
        WorldMutableTreeNode[] treeNodes = new WorldMutableTreeNode[1];
        worlds.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    onContext(e);
                    
                }
            }
            
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    onContext(e);
                }
                // 
                
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                // 
                if (e.isPopupTrigger()) {
                onContext(e);
                }
            }
            public void onContext(MouseEvent e) {
                Output.print("["+MainGui.class.getCanonicalName()+"]: Right click context open (World)");
                    TreePath path = worlds.getPathForLocation(e.getX(), e.getY());
                    if (path != null) {
                        
                        
                        
                
                        
                        treeNodes[0] = (WorldMutableTreeNode) path.getLastPathComponent();
                        worlds.setSelectionPath(path);
                        worldMenu1.show(e.getComponent(), e.getX(), e.getY());
                    }
            }
            
        });
        boolean[] r = {false};
        
        ActionListener t = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                try {
                    if (treeNodes[0] != null) {
                        treeNodes[0].getWorld().backupWorld();
                    }
                   
                } catch (Exception ef) {
                    ef.printStackTrace();
                }
                
            }
            
          };
          worldMenuItem1.addActionListener(t);
        
       // worlds.setBackground(new Color(210, 210, 210));
      //  worlds.setPreferredSize(new Dimension(150, mainFrameHeight));
        FlatTreeCellRenderer renderer = new FlatTreeCellRenderer(
            createImageIcon("/folder.png", "Folder")
            ,new Font("Segoe UI Light", Font.PLAIN, 13)
            ,createImageIcon("/box.png", null)
            ,worldMenu1
        );
        
        worlds.setCellRenderer(renderer);
        jPanel.add(worlds,BorderLayout.WEST);
        worldsList.add(jPanel, BorderLayout.WEST);
        mainFrame.add(worldsList,BoxLayout.X_AXIS);
       // mainFrame.add(content);
       JScrollPane scrollPane2 =new JScrollPane(worlds);
       
       mainFrame.add(scrollPane2);
       scrollPane2.setBorder(null);
       scrollPane2.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
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
        
       // scrollPane2.repaint();
       // scrollPane2.revalidate();
        
        //menuBar.setLayout(new BorderLayout());

        // try {
        //     //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        // } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
        //         | UnsupportedLookAndFeelException e) {
        //     // 
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
           // System.out.println(result);
            if (result == UpdateCheckResult.UPDATE_NEEDED) {
                updateFrame.setVisible(true);
            }
        } catch (UpdateBuildException e) {
            // 
            e.printStackTrace();
        }
    }
        public static ImageIcon createImageIcon(String path, String description) {
            java.net.URL imgURL = MainGui.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL, description);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
    public static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = MainGui.class.getResource(path);
    if (imgURL != null) {
        return new ImageIcon(imgURL);
    } else {
        System.err.println("Couldn't find file: " + path);
        return null;
    }
}
}
