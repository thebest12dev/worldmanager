package worldmanager;

import worldmanager.features.internal.CoreClass;


/// The de facto main class for launching worldmanager.
/// @author thebest12lines

@CoreClass
public class Launcher {
    public static void main(String[] args) {
        FeatureMain featureMain = new FeatureMain();
        featureMain.onLoad(args);

    }
}
