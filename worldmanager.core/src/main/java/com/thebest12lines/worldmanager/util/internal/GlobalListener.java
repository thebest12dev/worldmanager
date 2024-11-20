package com.thebest12lines.worldmanager.util.internal;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class GlobalListener {
    public static Map<String, List<Consumer<Object[]>>> handlers = new HashMap<>();

    private GlobalListener() {}



    public static void fire(String action, Object... args) {
        List<Consumer<Object[]>> actionHandlers = handlers.get(action);
        if (actionHandlers != null) {
            for (Consumer<Object[]> handler : actionHandlers) {
                handler.accept(args);
            }
        }
    }
}
