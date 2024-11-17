package common;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * The de facto singleton for initializing and using worldmanager.
 * @author thebest12lines
 */


public interface CoreApplication {

    CoreApplication instance = null;
    Map<String, Object> storage = new HashMap<>();


//    public static CoreApplication getCoreApplication() {
//        if (instance == null) {
//            instance = new CoreApplication();
//        }
//        return instance;
//    }

    void start(String[] args);
    void start();
    void exit(int code);
    Terminal getTerminal();
    void forceExit();
    void invokeAction(String action, Object... values);
     void setGlobalValue(String key, Object value) ;
     Object getGlobalValue(String key) ;
     void sendNotification(String title, String text);


}
