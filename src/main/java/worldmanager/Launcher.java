package worldmanager;

import com.thebest12lines.worldmanager.DataManager;
import com.thebest12lines.worldmanager.Main;
import com.thebest12lines.worldmanager.annotation.CoreClass;
import com.thebest12lines.worldmanager.annotation.Feature;
import com.thebest12lines.worldmanager.util.Constants;
import com.thebest12lines.worldmanager.util.CoreApplication;
import com.thebest12lines.worldmanager.util.Output;
import net.querz.nbt.tag.StringTag;


/// The de facto main class for launching worldmanager.
/// @author thebest12lines

@CoreClass
public class Launcher {
    public static void main(String[] args) {
        FeatureMain featureMain = new FeatureMain();
        featureMain.onLoad(args);

    }
}
