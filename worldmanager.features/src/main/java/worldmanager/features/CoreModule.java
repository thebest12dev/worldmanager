package worldmanager.features;

import common.CoreApplication;
import common.SharedStorage;
import worldmanager.features.internal.CoreClass;


/// The API class for accessing the `worldmanager.core` module.
/// It contains methods for directly interacting with the worldmanager API including setting and getting from shared storage, invoking listeners etc.
/// @author thebest12lines
@CoreClass
public class CoreModule {
    private static final CoreApplication coreApplication = (CoreApplication) SharedStorage.getValue("worldmanager.CoreApplication");
    public static CoreApplication getCoreApplication() {
       return coreApplication;
    }
    public static void setValue(String key, String node, Object value) {
        if (node.isBlank() || key.isBlank()) {
            throw new IllegalArgumentException("Key and node must be not empty.");
        }
        if (node.equals("worldmanager")) {
            throw new SecurityException("Cannot set "+key+" in node worldmanager: Permission denied");
        }
        if (!key.startsWith(node+".")) {
            coreApplication.setGlobalValue(node+"."+key,value);
        } else {
            throw new IllegalArgumentException("Key cannot be "+key+". Key should be "+key.replace(node+".","")+", not "+key);
        }

    }
    public static Object getValue(String key, String node) {
        return coreApplication.getGlobalValue(node+"."+key);
    }
    public static void invokeAction(String key, String node, Object... values) {
        if (node.isBlank() || key.isBlank()) {
            throw new IllegalArgumentException("Key and node must be not empty.");
        }
        if (!node.equals("worldmanager") && !key.startsWith("worldmanager.")) {
            if (!key.startsWith(node+".")) {
                coreApplication.invokeAction(node+"."+key,values);
            } else {
                throw new IllegalArgumentException("Key cannot be "+key+". Key should be "+key.replace(node+".","")+", not "+key);
            }

        } else {
            throw new SecurityException("Cannot invoke action "+key+" in node worldmanager: Permission denied");
        }
    }
}
