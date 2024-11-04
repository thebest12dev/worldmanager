package worldmanager;

import com.thebest12lines.worldmanager.DataManager;
import com.thebest12lines.worldmanager.Main;
import com.thebest12lines.worldmanager.annotation.CoreClass;
import com.thebest12lines.worldmanager.util.Constants;
import com.thebest12lines.worldmanager.util.CoreApplication;
import com.thebest12lines.worldmanager.util.Output;
import net.querz.nbt.tag.StringTag;


/// The de facto main class for launching worldmanager.
/// @author thebest12lines
@CoreClass
public class Launcher {
    public static void main(String[] args) {
        Output.consoleOutput = true;
        Output.print(
                Constants.ANSIColor.LIGHT_BLUE+"worldmanager "+ Constants.ANSIColor.RESET
                        + Constants.ANSIColor.GRAY+"v"+ Constants.ANSIColor.RESET+ Constants.ANSIColor.BOLD+ DataManager.getVersion()+ Constants.ANSIColor.RESET
                        +" "+Character.toUpperCase(DataManager.getBranch().charAt(0))
                        +DataManager.getBranch().substring(1).toLowerCase()
                        +"\nLogs will be outputted to file worldmanager.log."
        );
        if ((boolean) DataManager.getSetting("debug") == true) {


            // console = new Console();
            Output.print("Debug mode enabled, also forwarding logs to console.");
        }
        DataFile.createIfNotExists();


        CoreApplication.getCoreApplication().start(args);
    }
}
