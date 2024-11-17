package common;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A class used to manage the debug terminal, one used to send messages to worldmanager to execute them.
 * @author thebest12lines
 */
 public interface Terminal {
     JTextArea terminalArea = null;
     String prompt = "> ";
     String currentInput = "";
     ArrayList<String> history = new ArrayList<>();
     int historyIndex = -1;
     Map<String,String> variables = new HashMap<>();
     boolean isProcessing = false;
      boolean isRun = false;
      boolean file = false;
     boolean isInput = false;
      boolean isIf = false;
      boolean isIfTrue = false;
      boolean isIfTrueInside = false;

    private Font getFontWithFallback(String preferredFontName, int style, int size) {
        return null;
    }

    private boolean isFontAvailable(String fontName) {return false;};


     void parseFile(String path);
     final ArrayList<String> queue = new ArrayList<>();
     private void processCommand(String command) {};
     private void handleCondition(String command) {};

     private void executeBufferedCommands() {};
     private void finishProcessing() {};
     private void showHistory() {};

     private void clearCurrentInput() {};

    void setVisible(boolean b);

}
