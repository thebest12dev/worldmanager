package com.thebest12lines.worldmanager.gui;

import java.awt.*;
import com.thebest12lines.worldmanager.DataManager;
import com.thebest12lines.worldmanager.Main;
import com.thebest12lines.worldmanager.ObjectLibrary;
import com.thebest12lines.worldmanager.ObjectManager;
import com.thebest12lines.worldmanager.util.*;
//import com.thebest12lines.worldmanager.util.ZipDirectory;
import com.thebest12lines.worldmanager.annotation.CoreClass;
//import com.thebest12lines.worldmanager.util.Constants.UpdateCheckResult;
import com.thebest12lines.worldmanager.world.SaveManager;
import com.thebest12lines.worldmanager.world.World;
//import com.thebest12lines.worldmanager.util.UpdateBuildException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.*;
import java.util.concurrent.CompletableFuture;
//import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.*;
//import javax.swing.LookAndFeel;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import static com.thebest12lines.worldmanager.Main.mainTerminal;
/**
 * The main GUI responsible for all the GUIs used by worldmanager.
 * It provides static methods to launch and initialize the GUI.
 * @author thebest12lines
 */
@CoreClass
public class MainGui {


    /**
     * Returns the main menu bar.
     */

    protected static Color bgColor;
    protected static Color fgColor;
    protected static boolean safeToClose = true;
    protected static JFrame mainFrame = new JFrame("worldmanager");
    protected static JMenuBar menuBar;
    protected static JFrame infoFrame;
    protected static ArrayList<Image> icons;
    protected static JFrame updateFrame;
    protected static int updateFrameX;
    protected static CompletableFuture<String> guiReady = new CompletableFuture<>();
    protected static int updateFrameY;
    protected static int mainFrameHeight;
    protected static int mainFrameWidth;
    protected static int mainFrameX;
    protected static int mainFrameY;
    /**
     * Launches the main GUI.
     * @return The status code.
     */





        // Getters and Setters
        public static Color getBackgroundColor() {
            return bgColor;
        }

        public static void setBackgroundColor(Color bgColor) {
            MainGui.bgColor = bgColor;
        }

        public static Color getForegroundColor() {
            return fgColor;
        }

        public static void setForegroundColor(Color fgColor) {
            MainGui.fgColor = fgColor;
        }

        public static boolean isSafeToClose() {
            return safeToClose;
        }

        public static void setSafeToClose(boolean safeToClose) {
            MainGui.safeToClose = safeToClose;
        }

        public static JFrame getMainFrame() {
            return mainFrame;
        }

        public static void setMainFrame(JFrame mainFrame) {
            MainGui.mainFrame = mainFrame;
        }

        public static JMenuBar getMenuBar() {
            return menuBar;
        }

        public static void setMenuBar(JMenuBar menuBar) {
            MainGui.menuBar = menuBar;
        }

        public static JFrame getInfoFrame() {
            return infoFrame;
        }

        public static void setInfoFrame(JFrame infoFrame) {
            MainGui.infoFrame = infoFrame;
        }

        public static ArrayList<Image> getIcons() {
            return icons;
        }

        public static void setIcons(ArrayList<Image> icons) {
            MainGui.icons = icons;
        }

        public static JFrame getUpdateFrame() {
            return updateFrame;
        }

        public static void setUpdateFrame(JFrame updateFrame) {
            MainGui.updateFrame = updateFrame;
        }

        public static int getUpdateFrameX() {
            return updateFrameX;
        }



        public static CompletableFuture<String> getGuiReady() {
            return guiReady;
        }



        public static int getUpdateFrameY() {
            return updateFrameY;
        }

        public static int getMainFrameHeight() {
            return mainFrameHeight;
        }

        public static void setMainFrameHeight(int mainFrameHeight) {
            MainGui.mainFrameHeight = mainFrameHeight;
        }





        public static int getMainFrameX() {
            return mainFrameX;
        }



        public static int getMainFrameY() {
            return mainFrameY;
        }




    public static int launch() {
        try {
            initialize();
            initializeFrames();
            drawMenus();
            drawWorlds();
            initializeKeycodes();
            guiReady.complete("");
            Main.mainTerminal.isProcessing = false;
            Main.mainTerminal.terminalArea.append("> ");
            mainTerminal.terminalArea.setCaretPosition(mainTerminal.terminalArea.getText().length());
        } catch (Exception e) {
            if (e instanceof UpdateBuildException) {
                return 0x13f20001;
            } else if (e instanceof RuntimeException) {
                return 0x12f20001;
            } else if (e instanceof ClassNotFoundException) {
                return 0x11f20001;
            }
        }
        return 0;
        //throw new RuntimeException("Test");
    }

    /**
     * Safely closes worldmanager, in the case of a crash.
     */
    public static void safeClose() {

        if (safeToClose) {
            mainFrame.setVisible(false);
            mainFrame.dispose();
            mainFrame = null;
            updateFrame.setVisible(false);
            updateFrame.dispose();
            mainFrame = null;
            infoFrame.setVisible(false);
            infoFrame.dispose();
            infoFrame = null;
        }
    }
    /**
     * Initializes the GUI.
     *
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws UnsupportedLookAndFeelException
     */
    private static void initialize() throws Exception {
        //  mainFrame = new JFrame("worldmanager Alpha 0.1.0");
        safeToClose = false;
        bgColor = new Color(255, 255, 255);
        fgColor = new Color(0, 0, 0);

        // mainFrame.setUndecorated(true);

        if (SystemSettings.getSystemTheme().equals("Dark") && DataManager.getSetting("theme").equals("default")) {
            bgColor = new Color(37, 37, 37);
            fgColor = new Color(255, 255, 255);
            mainFrame.setBackground(bgColor);
            mainFrame.setForeground(fgColor);
        }
        if (DataManager.getSetting("theme").equals("light")) {
            bgColor = new Color(255, 255, 255);
            fgColor = new Color(0, 0, 0);
            mainFrame.setBackground(bgColor);
            mainFrame.setForeground(fgColor);
        } else if (DataManager.getSetting("theme").equals("dark")) {
            bgColor = new Color(37, 37, 37);
            fgColor = new Color(255, 255, 255);
            mainFrame.setBackground(bgColor);
            mainFrame.setForeground(fgColor);
        }
        if (Main.themeExplicit == 1) {
            bgColor = new Color(255, 255, 255);
            fgColor = new Color(0, 0, 0);
            mainFrame.setBackground(bgColor);
            mainFrame.setForeground(fgColor);
        } else if (Main.themeExplicit == 2) {
            bgColor = new Color(37, 37, 37);
            fgColor = new Color(255, 255, 255);
            mainFrame.setBackground(bgColor);
            mainFrame.setForeground(fgColor);
        }
        Enumeration<Object> keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            if (key.toString().endsWith(".background")) {
                UIManager.put(key, bgColor);
            }
            if (key.toString().endsWith(".foreground")) {
                UIManager.put(key, fgColor);
            }
        }
        UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());

        // System.setProperty("java.awt.headless", "true");
        if ((Boolean) DataManager.getSetting("debug")) {
            mainFrame.setTitle("worldmanager " + DataManager.getFullVersion() + " @ " + System.getProperty("os.name"));
        }
        mainFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (!mainTerminal.isVisible()) {

                    mainFrame.setVisible(false); // Hide the window
                    mainFrame.dispose(); // Dispose of the window's resources
                    System.exit(0); // Terminate the application
                } else {
                    mainFrame.setVisible(false); // Hide the window
                    mainFrame.dispose(); // Dispose of the window's resources
                }
                // Choose your desired behavior:

            }
        });
        icons = new ArrayList<>();
        icons.add(createImageIcon("resources/icons/icon16").getImage());
        icons.add(createImageIcon("resources/icons/icon32").getImage());

        mainFrame.setIconImages(icons);

        //mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(800, 500);

        // mainFrame.setBackground(new Color());
        mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        safeToClose = true;
    }
    /**
     * Initializes the frames.
     */
    private static void initializeFrames() throws Exception {
        safeToClose = false;
        updateFrame = new JFrame("Update");
        // UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");

        // mainFrame.setIconImage(null);

        UIManager.put("Tree.drawsFocusBorderAroundIcon", false);
        UIManager.put("Tree.drawDashedFocusIndicator", false);

        // updateFrame.setVisible(true);
        updateFrame.setResizable(false);
        updateFrame.setSize(500, 300);

        mainFrameX = mainFrame.getX();
        mainFrameY = mainFrame.getY();
        mainFrameWidth = mainFrame.getWidth();
        mainFrameHeight = mainFrame.getHeight();

        updateFrameX = mainFrameX + (mainFrameWidth - updateFrame.getWidth()) / 2;
        updateFrameY = mainFrameY + (mainFrameHeight - updateFrame.getHeight()) / 2;

        updateFrame.setLocation(updateFrameX, updateFrameY);
        updateFrame.setAlwaysOnTop(true);
        updateFrame.setType(JFrame.Type.UTILITY);
        updateFrame.setLayout(new BorderLayout());


        JLabel updateVersion = FlatLabel.createFlatLabel("0.1.0 Alpha");
        JPanel panel_ = new JPanel();

        updateVersion.setFont(new Font("Segoe UI", Font.PLAIN, 25));
        panel_.add(updateVersion);
        JTextArea changelog = new JTextArea();
        JPanel panel2 = new JPanel();
        changelog.setFont(new Font("Segoe UI", Font.PLAIN, 16));
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
        JScrollPane scrollPane = new JScrollPane(changelog);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                int r = 255;
                int g = 190;
                this.thumbColor = new Color(g, g, g);
                this.trackColor = new Color(r, r, r);
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
        updateFrame.add(panel_, BorderLayout.NORTH);
        // JButton updateButton = FlatButton.createFlatButton();

        JButton changelogButton = FlatButton.createFlatButton("<html><center>Changelog</center></html>");
        changelogButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        changelogButton.setMargin(new Insets(10, 10, 10, 10));
        changelogButton.setPreferredSize(new Dimension(90, 30));
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


        updateButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        updateButton.setMargin(new Insets(10, 10, 10, 10));
        updateButton.setPreferredSize(new Dimension(70, 30));
        updateButton.setLocation(15, 15);
        updateButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //
                Thread thread = new Thread("UpdateThread") {
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
        cancelButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        cancelButton.setMargin(new Insets(10, 10, 10, 10));
        cancelButton.setPreferredSize(new Dimension(70, 30));
        // cancelButton.setLocation(15, 15);
        btnPanel.add(changelogButton);
        btnPanel.add(updateButton);
        btnPanel.add(cancelButton);
        updateFrame.add(btnPanel, BorderLayout.SOUTH);

            if (Objects.equals(Updater.checkForUpdates(), Constants.UpdateCheckResult.UPDATE_NEEDED)) {
                updateFrame.setVisible(true);
            }

        safeToClose = true;
    }

    /**
     * Draws the menus.
     */
    private static void drawMenus() {
        safeToClose = false;
        UIManager.put("Tree.collapsedIcon", createImageIcon("resources/icons/caret-right-fill"));
        UIManager.put("Tree.expandedIcon", createImageIcon("resources/icons/caret-down-fill"));

        Font normalFont = new Font("Segoe UI", Font.PLAIN, 13);
        // updateFrame.setVisible(true);

        menuBar = FlatMenuBar.createFlatMenuBar();
        ArrayList<JMenu> jMenus = new ArrayList<>();
        jMenus.add(FlatMenu.createFlatMenu("File", menuBar));
        jMenus.add(FlatMenu.createFlatMenu("Edit", menuBar));
        jMenus.add(FlatMenu.createFlatMenu("World", menuBar));
        jMenus.add(FlatMenu.createFlatMenu("Help", menuBar));
        JMenu file = jMenus.get(0);
        JMenu help = jMenus.get(3);
        //  help.add(new JSeparator());

        infoFrame = new JFrame("About worldmanager");
        infoFrame.setSize(480, 640);
        infoFrame.setBackground(bgColor);
        infoFrame.setForeground(fgColor);
        infoFrame.setResizable(false);
        infoFrame.setLayout(new BorderLayout());
        infoFrame.setIconImages(icons);
        infoFrame.setAlwaysOnTop(true);
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        JLabel logo = new JLabel(createImageIcon("resources/icons/icon-about"));
        logo.setPreferredSize(new Dimension(166,256));
        logo.setBackground(bgColor);
        logo.setForeground(fgColor);
        panel.add(logo, BorderLayout.NORTH);
       // panel.setBackground(new Color(1,1,1));
        JPanel panel2 = new JPanel();
        JLabel t = FlatLabel.createFlatLabel("<html>" +
                "<body style='text-align:center'>" +
                "<strong>" +
                "worldmanager" +
                "</strong><br>" +
                "A world manager for Minecraft.<br>" +
                "<br><span style='font-size: 16pt'>Version: "+DataManager.getFullVersion()+"</span><br><span style='font-family: \"Segoe UI\";font-size:16pt'><br><br><br><br>Copyright &copy; 2024 thebest12lines<br><br><span style='font-size:12pt'>worldmanager is not an official Minecraft product <br>and is not endorsed nor affiliated with Mojang. </span></span></body></html>");
        t.setFont(new Font("Segoe UI",Font.PLAIN, 20));
        t.setHorizontalAlignment(SwingConstants.CENTER);
        t.setVerticalAlignment(SwingConstants.NORTH);

       // a.setFont(new Font("Segoe UI",Font.BOLD, 20));


        panel2.add(t);


        panel.add(panel2, BorderLayout.CENTER);
        infoFrame.add(panel,BorderLayout.CENTER);
        JPanel btnPanel = new JPanel();
        btnPanel.setLayout(new FlowLayout());
        JButton a = FlatButton.createFlatButton("<html><center>Repository</center></html>");
        a.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                        URI url = new URI("https://github.com/thebest12dev/worldmanager/");
                        Desktop.getDesktop().browse(url);
                    } else {
                        System.out.println("Desktop browsing is not supported on this platform.");
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        });
        a.setPreferredSize(new Dimension(100,30));
        btnPanel.add(a);

        panel.add(btnPanel,BorderLayout.SOUTH);
       // infoFrame.add(version);
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
                infoFrame.setLocation(updateFrameX, updateFrameY-150);
            }

        });
        help.add(info);
        JFrame backupFrame = new JFrame();

        JMenuItem item1 = FlatMenuItem.createFlatMenuItem("New Backup", "Ctrl+N");
        item1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        item1.setFont(normalFont);
        file.add(item1);
        file.add(new JSeparator());
        JMenuItem item2 = FlatMenuItem.createFlatMenuItem("Open World...", "Ctrl+O");
        //  item2.setFont(normalFont);
        // item2.
        file.add(item2);
        JMenuItem item3 = FlatMenuItem.createFlatMenuItem("Open Backup...", "Ctrl+B");
        item3.setFont(normalFont);
        file.add(item3);
        JMenuItem item5 = FlatMenuItem.createFlatMenuItem("Import World...", "Ctrl+Shift+O");
        item5.setFont(normalFont);
        file.add(item5);
        file.add(new JSeparator());
        JMenuItem item4 = FlatMenuItem.createFlatMenuItem("Exit", "");
        item4.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // 
                System.exit(0);
            }

        });
        item4.setFont(normalFont);
        file.add(item4);
        safeToClose = true;
    }
    public static JLabel statusLabel = FlatLabel.createFlatLabel("");
    /**
     * Draws the worlds.
     *
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws UnsupportedLookAndFeelException
     */
    private static void drawWorlds() throws Exception{

        statusLabel.setVisible(false);
        statusLabel.setPreferredSize(new Dimension(500, 20));
        statusLabel.setFont(new Font("Segoe UI",Font.PLAIN,12));
        mainFrame.add(statusLabel,BorderLayout.SOUTH);
        JPanel worldsList = new JPanel();

        worldsList.setLayout(new BoxLayout(worldsList, BoxLayout.Y_AXIS));
        worldsList.setVisible(true);
        //JPanel content = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        worldsList.setPreferredSize(new Dimension(250, mainFrameHeight));

        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Worlds (Java Edition)");
        // DefaultMutableTreeNode world2 = new DefaultMutableTreeNode("World1");
        //
        JPopupMenu worldMenu1 = FlatPopupMenu.createFlatPopupMenu();
        worldMenu1.setBackground(bgColor);
        worldMenu1.setForeground(fgColor);
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
        Font worldFont = new Font("Segoe UI Light", Font.PLAIN, 13);
        worldMenuItem1.setBackground(bgColor);
        worldMenuItem1.setForeground(fgColor);
        worldMenuItem1.setBorder(null);
        worldMenuItem1.setFont(worldFont);
        worldMenu1.add(worldMenuItem1);

        JMenuItem worldMenuItem2 = new JMenuItem("Delete World");
        worldMenuItem2.setBorder(null);
        worldMenuItem2.setFont(worldFont);
        worldMenuItem2.setBackground(bgColor);
        worldMenuItem2.setForeground(fgColor);
        worldMenu1.add(worldMenuItem2);

        JMenuItem worldMenuItem3 = new JMenuItem("Properties");
        worldMenuItem3.setBorder(null);
        worldMenuItem3.setFont(worldFont);
        worldMenuItem3.setBackground(bgColor);
        worldMenuItem3.setForeground(fgColor);
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
                Output.print("[" + MainGui.class.getCanonicalName() + "]: Right click context open (World)");
                TreePath path = worlds.getPathForLocation(e.getX(), e.getY());
                if (path != null) {


                    treeNodes[0] = (WorldMutableTreeNode) path.getLastPathComponent();
                    worlds.setSelectionPath(path);
                    worldMenu1.show(e.getComponent(), e.getX(), e.getY());
                }
            }

        });
        //boolean[] r = {false};

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
                createImageIcon("resources/icons/folder")
                , new Font("Segoe UI Light", Font.PLAIN, 13)
                , createImageIcon("resources/icons/box")
                , worldMenu1
        );

        worlds.setCellRenderer(renderer);
        jPanel.add(worlds, BorderLayout.WEST);
        worldsList.add(jPanel, BorderLayout.WEST);
        mainFrame.add(worldsList, BoxLayout.X_AXIS);
        // mainFrame.add(content);
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        JScrollPane scrollPane2 = new JScrollPane(worlds);
        scrollPane2.setOpaque(true);

        mainFrame.add(scrollPane2);

        scrollPane2.setBorder(null);
        //    scrollPane2.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
        //            @Override
        //            protected void configureScrollBarColors() {
        //                int r = 255;
        //                int g = 190;
        //                this.thumbColor = new Color(g,g,g);
        //                this.trackColor = new Color(r,r,r);
        //            }

        //            @Override
        //            protected JButton createDecreaseButton(int orientation) {
        //                return createZeroButton();
        //            }

        //            @Override
        //            protected JButton createIncreaseButton(int orientation) {
        //                return createZeroButton();
        //            }

        //            private JButton createZeroButton() {
        //                JButton button = new JButton();
        //                button.setPreferredSize(new Dimension(0, 0));
        //                button.setMinimumSize(new Dimension(0, 0));
        //                button.setMaximumSize(new Dimension(0, 0));
        //                return button;
        //            }
        //        });

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
        safeToClose = true;
    }

    /**
     * Initializes the keycodes used for shortcuts.
     */
    private static void initializeKeycodes() {
        safeToClose = false;

        Set<Integer> pressedKeys = new HashSet<>();
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {

            @Override
            public boolean dispatchKeyEvent(KeyEvent e) {
                if (e.getID() == KeyEvent.KEY_PRESSED) {
                    pressedKeys.add(e.getKeyCode());
                    checkKeyCombination();
                } else if (e.getID() == KeyEvent.KEY_RELEASED) {
                    pressedKeys.remove(e.getKeyCode());
                }
                return false;
            }

            private void checkKeyCombination() {
//                if (pressedKeys.contains(KeyEvent.VK_CONTROL) && pressedKeys.contains(KeyEvent.VK_SHIFT) && pressedKeys.contains(KeyEvent.VK_C)) {
//                    Main.console.setVisible(true);
//                    // Execute your action here
//                }
            }
        });
        safeToClose = true;
    }

    /**
     * Creates an image icon with a description.
     */

    private static ObjectLibrary lib;
    /**
     * Creates an image icon
     */
    public static ImageIcon createImageIcon(String path) {
//        java.net.URL imgURL = MainGui.class.getResource(path);
//        if (imgURL != null) {
//            return new ImageIcon(imgURL);
//        } else {
//            System.err.println("Couldn't find file: " + path);
//            return null;
//        }
        if (lib == null) {
            lib = ObjectManager.getObjectLibrary("objects/main");
        }
        try {
            return loadImage(lib.getPath(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static ImageIcon loadImage(String filePath) throws IOException {
        // Read the file as a BufferedImage
        BufferedImage image = ImageIO.read(new File(filePath));
        // Convert BufferedImage to ImageIcon
        return new ImageIcon(image);

    }
}