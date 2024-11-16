package com.thebest12lines.worldmanager.api;

import worldmanager.features.annotation.CoreClass;

@CoreClass
public interface FeatureInitializer {
    void onStart();

    default void onListen(String calledListener) {};
}
