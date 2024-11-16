package com.thebest12lines.worldmanager.util;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class GlobalListener {
    private static Map<String, List<Consumer<Object[]>>> handlers = new HashMap<>();

    private GlobalListener() {}

    public static void listen(String action, Consumer<Object[]> handler) {
        handlers.computeIfAbsent(action, k -> new ArrayList<>()).add(handler);
    }

    protected static void fire(String action, Object... args) {
        List<Consumer<Object[]>> actionHandlers = handlers.get(action);
        if (actionHandlers != null) {
            for (Consumer<Object[]> handler : actionHandlers) {
                handler.accept(args);
            }
        }
    }
}
