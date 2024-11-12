package com.thebest12lines.worldmanager.util;
import com.thebest12lines.worldmanager.DataManager;
import com.thebest12lines.worldmanager.Gui;
import com.thebest12lines.worldmanager.ObjectManager;
import com.thebest12lines.worldmanager.annotation.CoreClass;
import com.thebest12lines.worldmanager.gui.MainGui;
import com.thebest12lines.worldmanager.world.SaveManager;
import com.thebest12lines.worldmanager.world.World;
import worldmanager.DataFile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * A class used to manage the debug terminal, one used to send messages to worldmanager to execute them.
 * @author thebest12lines
 */
@CoreClass
public class Terminal extends JFrame {
    public JTextArea terminalArea;
    private String prompt = "> ";
    private String currentInput = "";
    private List<String> history = new ArrayList<>();
    private int historyIndex = -1;
    public volatile boolean isProcessing = false;

    private Font getFontWithFallback(String preferredFontName, int style, int size) {
        Font preferredFont = new Font(preferredFontName, style, size);
        if (!isFontAvailable(preferredFontName)) {
            preferredFont = new Font(Font.MONOSPACED, style, size);
        }
        return preferredFont;
    }

    private boolean isFontAvailable(String fontName) {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();

        String[] availableFontFamilyNames = ge.getAvailableFontFamilyNames();
        for (String availableFont : availableFontFamilyNames) {
            if (availableFont.equalsIgnoreCase(fontName)) {
                return true;
            }
        }
        return false;
    }
    public Terminal() {
        setTitle("Terminal");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setIconImages(MainGui.getIcons());
        terminalArea = new JTextArea();
        terminalArea.setLineWrap(true);
        terminalArea.setEditable(false);

        terminalArea.setBackground(Color.BLACK);
        terminalArea.setCaretColor(Color.WHITE);
        terminalArea.setForeground(Color.WHITE);

        Font font = getFontWithFallback("Consolas", Font.PLAIN, 14);
        terminalArea.setFont(font);

        terminalArea.append("worldmanager debug console, type `help` for info\n");
        terminalArea.append(prompt);
        terminalArea.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (!isProcessing) {
                    isProcessing = true;

                    int keyCode = e.getKeyCode();
                    int caretPosition = terminalArea.getCaretPosition();
                    String[] lines = terminalArea.getText().split("\n");
                    String thisLine = lines[lines.length - 1];
                    int promptPosition = thisLine.indexOf('>') + 2; // Position after the prompt
                    int lineStart = terminalArea.getText().lastIndexOf('\n') + 1;

                    switch (keyCode) {
                        case KeyEvent.VK_ENTER:
                            terminalArea.append("\n");
                            if (currentInput.isBlank()) {
                                isProcessing = false;
                            } else {
                                processCommand(currentInput);
                            }


                            if (!isProcessing ) {
                                terminalArea.append(prompt);
                                if (!currentInput.isEmpty()) {
                                    history.add(currentInput);
                                }
                                historyIndex = history.size();
                                terminalArea.setCaretPosition(terminalArea.getText().length());
                                currentInput = "";
                                isProcessing = false;
                            }

                            break;
                        case KeyEvent.VK_BACK_SPACE:
                            if (caretPosition > lineStart + prompt.length()) {
                                try {
                                    if (thisLine.length() > 2) {
                                        terminalArea.getDocument().remove(caretPosition - 1, 1);
                                    }
                                    if (currentInput.length() > 0) {
                                        currentInput = currentInput.substring(0, currentInput.length() - 1);
                                    }
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                            }
                            isProcessing = false;
                            break;
                        case KeyEvent.VK_UP:
                            if (historyIndex > 0) {
                                historyIndex--;
                                showHistory();
                            }
                            isProcessing = false;
                            break;
                        case KeyEvent.VK_DOWN:
                            if (historyIndex < history.size() - 1) {
                                historyIndex++;
                                showHistory();
                            } else {
                                historyIndex = history.size();
                                clearCurrentInput();
                            }
                            isProcessing = false;
                            break;
                        case KeyEvent.VK_LEFT:
                            if (caretPosition > lineStart + prompt.length()) {
                                terminalArea.setCaretPosition(terminalArea.getCaretPosition() - 1);

                            }
                            isProcessing = false;
                            break;
                        case KeyEvent.VK_RIGHT:
                            if (caretPosition < terminalArea.getText().length()) {
                                terminalArea.setCaretPosition(terminalArea.getCaretPosition() + 1);

                            }
                            isProcessing = false;
                            break;
                        default:
                            if (!e.isControlDown() && !e.isAltDown() && !e.isMetaDown()) {
                                if (!(e.getKeyCode() == KeyEvent.VK_SHIFT)) {


                                    String textToInsert = String.valueOf(e.getKeyChar());
                                    try {
                                        terminalArea.getDocument().insertString(caretPosition, textToInsert, null);

                                        // Update currentInput considering the caret position
                                        int relativeCaretPos = caretPosition - (terminalArea.getText().lastIndexOf('\n') + 3);

                                        if (!(currentInput.length() == 0)) {
                                            System.out.println(relativeCaretPos);
                                            currentInput = currentInput.substring(0, relativeCaretPos) + textToInsert + currentInput.substring(relativeCaretPos);
                                        } else {
                                            currentInput += textToInsert;
                                        }


                                        terminalArea.setCaretPosition(caretPosition + 1);

                                    } catch (Exception ex) {
                                        ex.printStackTrace();
                                        isProcessing = false;
                                    }
                                }

                            }

                            isProcessing = false;
                            break;

                    }
                    e.consume();

                }
            }
        });




        terminalArea.add(new JScrollPane());

        add(terminalArea, BorderLayout.CENTER);

    }

    private void processCommand(String command) {
        isProcessing = true;
        if (!command.isEmpty()) {
            String[] args = command.split(" ");
            if (command.startsWith("exit")) {
                if (args.length > 1) {
                    System.exit(Integer.parseInt(args[1]));
                }
                System.exit(0);
            } else if (command.equalsIgnoreCase("clear")) {
                terminalArea.setText("");


                isProcessing = false;
            } else if (command.equalsIgnoreCase("help")) {
                terminalArea.append("""
                        List of commands:
                            worldmanager:
                                [--]backup <worldName/worldPath> <?backupDest>: Backs up the specified world.
                                [--]version: Shows the version of worldmanager.
                                [-gui][--]launch-gui: Launches the GUI.
                                [-v][--]verify <?objectLibrary>: Verifies the specified library.
                                [-i][--]import <worldPath>: Imports the specified world.
                                [-c][--]clean <type>: Clears and rewrites to the type of file.
                                [--]set-flag <name> <value>: Sets the NBT flag (setting) to the specified value.
                                [-r][--]reload-worlds: Reloads all worlds within the saves folder.
                            clear: Clears the current debug console.
                            exit <?code>: Exits the JVM with the specified code.
                            version: Shows the terminal version.
                        """);
                isProcessing = false;
            } else if (command.startsWith("worldmanager ")) {
                if (args[1].equalsIgnoreCase("--launch-gui") || args[1].equalsIgnoreCase("-gui")) {
                    terminalArea.append("Launching worldmanager GUI...\n");
                    Gui.start(new String[] {});
                } else if (args[1].equalsIgnoreCase("--version")) {
                    terminalArea.append("worldmanager "+DataManager.getFullVersion()+"\nCopyright (c) 2024 thebest12lines\n");
                    isProcessing = false;
                } else if (args[1].equalsIgnoreCase("--set-flag")) {
                    DataFile.setFlag(args[2],args[3]);

                } else if (args[1].equalsIgnoreCase("--backup")) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            World world = new World() {
                                {
                                    this.path = args[2];
                                }
                            };
                            if (!(args.length > 3)) {
                                world.backupWorld();
                            } else {
                                try {
                                    ZipDirectory.zipDirectory(world.getWorldPath(), args[3], world.getWorldPath()+"\\backups");
                                } catch (IOException e) {
                                    isProcessing = false;
                                    throw new RuntimeException(e);

                                }
                            }
                            isProcessing = false;
                        }
                    }).start();


                } else {
                    terminalArea.append("worldmanager: invalid command "+command);
                    isProcessing = false;
                }
            } else if (command.equalsIgnoreCase("version")) {
                terminalArea.append("Version: "+DataManager.getFullVersion()+"\n");
                isProcessing = false;
            } else {
                terminalArea.append("Unknown command: "+command+"\n");
                isProcessing = false;
            }
        }
    }

    private void showHistory() {
        clearCurrentInput();
        currentInput = history.get(historyIndex);
        terminalArea.append(currentInput);

    }

    private void clearCurrentInput() {
        String text = terminalArea.getText();
        int promptPos = text.lastIndexOf(prompt);
        if (promptPos >= 0) {
            terminalArea.setText(text.substring(0, promptPos + prompt.length()));
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Terminal terminal = new Terminal();
            terminal.setVisible(true);
        });
    }
}
