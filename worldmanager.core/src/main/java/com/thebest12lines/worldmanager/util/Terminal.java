package com.thebest12lines.worldmanager.util;
import com.thebest12lines.worldmanager.DataManager;
import com.thebest12lines.worldmanager.Gui;
import worldmanager.features.annotation.CoreClass;
import com.thebest12lines.worldmanager.gui.MainGui;
import com.thebest12lines.worldmanager.world.World;
import worldmanager.DataFile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.List;


/**
 * A class used to manage the debug terminal, one used to send messages to worldmanager to execute them.
 * @author thebest12lines
 */
@CoreClass
public class Terminal extends JFrame implements common.Terminal {
    private static final List<String> commandBuffer = new ArrayList<>();
    public JTextArea terminalArea;
    private String prompt = "> ";
    private String currentInput = "";
    private final List<String> history = new ArrayList<>();
    private int historyIndex = -1;
    public Map<String,String> variables = new HashMap<>();
    public volatile boolean isProcessing = false;
    public volatile boolean isRun = false;
    public volatile boolean file = false;
    public volatile boolean isInput = false;
    public volatile boolean isIf = false;
    public volatile boolean isIfTrue = false;
    public volatile boolean isIfTrueInside = false;
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
        setSize(800, 500);
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

                 if ((!isProcessing && !isRun) || isInput) {


                    int keyCode = e.getKeyCode();
                    int caretPosition = terminalArea.getCaretPosition();
                    String[] lines = terminalArea.getText().split("\n");
                    String thisLine = lines[lines.length - 1];
                    int promptPosition = thisLine.substring(0,prompt.length()).length() + 2; // Position after the prompt
                    int lineStart = terminalArea.getText().lastIndexOf('\n') + 1;

                    switch (keyCode) {
                        case KeyEvent.VK_ENTER:
                            if (isIf && !isIfTrueInside) {

                                //terminalArea.append("\n"+prompt);
                                if (currentInput.isBlank()) {

                                    isProcessing = false;
                                } else {
                                    prompt = "> ";
                                    processCommand(currentInput);
                                }

                                terminalArea.append("\n"+"> ");
                            terminalArea.setCaretPosition(terminalArea.getText().length());
                            currentInput = "";


                            finishProcessing();
                        } else
                            if (!isInput) {
                                terminalArea.append("\n");
                                if (currentInput.isBlank()) {
                                    isProcessing = false;
                                } else {
                                    processCommand(currentInput);
                                }

                                if (!isProcessing && !isInput && !isIfTrue) {

                                    terminalArea.append(prompt);
                                    if (!currentInput.isEmpty()) {
                                        history.add(currentInput);
                                    }
                                    historyIndex = history.size();
                                    terminalArea.setCaretPosition(terminalArea.getText().length());
                                    currentInput = "";
                                    finishProcessing();
                                } else if (isIf) {

                                    terminalArea.append(prompt);
                                    if (!currentInput.isEmpty()) {
                                        history.add(currentInput);
                                    }
                                    historyIndex = history.size();
                                    terminalArea.setCaretPosition(terminalArea.getText().length());
                                    currentInput = "";
                                    finishProcessing();
                                } else if (isInput) {


                                    //   terminalArea.setText(terminalArea.getText().substring(0,terminalArea.getText().length()-4));


//                                if (!currentInput.isEmpty()) {
//                                    history.add(currentInput);
//                                }
                                    historyIndex = history.size();
                                    currentInput = "";
                                    terminalArea.setCaretPosition(terminalArea.getText().length());

                                    finishProcessing();
//                                isInput = false;
                                }
                            } else if (isInput) {

                                variables.put("lastInput",currentInput);

                                if (!file) {
                                    prompt = "> ";
                                    terminalArea.append("\n> ");
                                    historyIndex = history.size();
                                    currentInput = "";
                                    terminalArea.setCaretPosition(terminalArea.getText().length());



                                } else {
                                    terminalArea.append("\n");
                                    isRun = false;
                                }
                                isInput = false;
                                finishProcessing();




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
                        case KeyEvent.VK_CAPS_LOCK:
                            break;
                        default:
                            if (!e.isControlDown() && !e.isAltDown() && !e.isMetaDown()) {
                                if (!(e.getKeyCode() == KeyEvent.VK_SHIFT)) {


                                    String textToInsert = String.valueOf(e.getKeyChar());
                                    try {
                                        terminalArea.getDocument().insertString(caretPosition, textToInsert, null);

                                        // Update currentInput considering the caret position
                                        int relativeCaretPos = caretPosition - (terminalArea.getText().lastIndexOf('\n') + promptPosition-1);

                                        if (!(currentInput.isEmpty())) {

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
    public void parseFile(String path) {
        try {
            isRun = true;
            file = true;
            terminalArea.setText("");
            setVisible(true);
            Files.lines(Path.of(path)).forEach(this::processCommand);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private final ArrayList<String> queue = new ArrayList<>();
    private void processCommand(String command) {
        System.err.println(command);

        if (isProcessing) {

            queue.add(command);
        } else if (!isProcessing && !isIf) {
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


                    finishProcessing();
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
                    finishProcessing();
                } else if (command.startsWith("worldmanager ")) {
                    if (args[1].equalsIgnoreCase("--launch-gui") || args[1].equalsIgnoreCase("-gui")) {
                        terminalArea.append("Launching worldmanager GUI...\n");
                        Gui.start(new String[] {});
                        MainGui.getGuiReady().thenAccept((t)-> {
                            finishProcessing();
                        });
                    } else if (args[1].equalsIgnoreCase("--version")) {
                        terminalArea.append("worldmanager "+DataManager.getFullVersion()+"\nCopyright (c) 2024 thebest12lines\n");
                        finishProcessing();
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
                                finishProcessing();
                            }
                        }).start();


                    } else {
                        terminalArea.append("worldmanager: invalid command "+command);
                        finishProcessing();
                    }
                } else if (command.equalsIgnoreCase("version")) {
                    terminalArea.append("Version: "+DataManager.getFullVersion()+"\n");
                    finishProcessing();
                } else if (command.startsWith("echo")) {
                    if (command.length() > 5) {
                        if (command.substring(5).startsWith("@")) {
                            terminalArea.append(variables.get(command.substring(6))+"\n");
                        } else if (args.length > 1) {
                            terminalArea.append(command.substring(5) + "\n");
                        }
                    } else {
                        terminalArea.append("\n");
                    }


                   finishProcessing();
                } else if (command.startsWith("@")) {
                    String key = args[0].substring(1);
                    String value = command.substring(key.length()+2);
                    variables.put(key,value);
                    finishProcessing();
                } else if (command.startsWith("input")) {
                    if (command.length() > 6 && !isInput) {
                        isRun = false;
                        terminalArea.append(command.substring(6));
                        prompt = command.substring(6);
                        isInput = true;
                    }

                  //  finishProcessing();

                } else if (command.startsWith("log ")) {
                    Output.print("[Debug Console]: "+command.substring(4));
                    finishProcessing();
                } else if (command.startsWith("#")) {
                    finishProcessing();
                } else if (command.equalsIgnoreCase("testIf")) {
                } else if (command.startsWith("if")) {
                    handleCondition(command);
                    finishProcessing();
                } else {
                    terminalArea.append("unknown command: "+command+"\n");
                    finishProcessing();
                }
            //    System.err.println(isProcessing);
        }

        } else if (isIf && isIfTrueInside) {
            System.err.println("!r");
            if (command.equalsIgnoreCase("end")) {
                isProcessing = true;
                System.err.println("!T");

                executeBufferedCommands();
                finishProcessing();


            } else if (command.isBlank()) {finishProcessing();}
            else if (!isProcessing) {
                System.err.println("!T");
                commandBuffer.add(command);
                finishProcessing();
            }
        }
    }
    private void handleCondition(String command) {
        commandBuffer.clear(); // Clear any previous buffered commands

        if (command.contains("do")) {
            String conditionPart = command.substring(command.indexOf("if") + 2, command.indexOf("do")).trim();
            String[] conditionParts = conditionPart.split("==");
            if (conditionParts.length == 2) {
                String variableName = conditionParts[0].trim();
                String expectedValue = conditionParts[1].trim().replace("\"", "");

                prompt = ">>> ";
                isIfTrueInside = true;
                isIf = true;
                System.err.println(variables.containsKey(variableName.substring(1)) && variables.get(variableName.substring(1)).equals(expectedValue));
                if (variables.containsKey(variableName.substring(1)) && variables.get(variableName.substring(1)).equals(expectedValue)) {
                    System.err.println("as");
                    isIfTrue = true;
                }
            }
        } else {
            terminalArea.append("Invalid condition syntax.\n");
        }
    }

    private void executeBufferedCommands() {
        System.err.println("x");
        if (isIfTrue) {
            isIf = false;
//            terminalArea.append("\n");
//            prompt = "> ";
//            terminalArea.append("\n"+prompt);
            isIfTrueInside = false;
            synchronized (commandBuffer) {
                for (String command : commandBuffer) {
                    processCommand(command);
                }

                    commandBuffer.clear(); // Clear the buffer after execution

//                terminalArea.append("\n"+prompt);
            }


        }

//        terminalArea.append("> \n");
        finishProcessing();

        isIfTrue = false;

    }
    public void finishProcessing() {

            isProcessing = false;
            if (!queue.isEmpty()) {
                String lastCommand = queue.getFirst();
                queue.removeFirst();
                processCommand(lastCommand);
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
