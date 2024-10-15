package com.thebest12lines.worldmanager.api;

import com.thebest12lines.worldmanager.annotation.CoreClass;

@CoreClass
public interface FeatureInitializer {
    void onStart();

    default void onListen(String calledListener) {};
}
