package worldmanager.features;

import common.CoreApplication;
import common.SharedStorage;

public class CoreModule {
    public static CoreApplication getCoreApplication() {
        return (CoreApplication) SharedStorage.getValue("worldmanager.CoreApplication");
    }
}
