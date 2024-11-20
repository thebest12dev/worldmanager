package com.thebest12lines.worldmanager.util;

import java.util.ArrayList;
import java.util.function.Consumer;

import static com.thebest12lines.worldmanager.util.internal.GlobalListener.handlers;

public interface Listenable {
     default void listen(String action, Consumer<Object[]> handler) {
         handlers.computeIfAbsent(action, k -> new ArrayList<>()).add(handler);
     }
}
