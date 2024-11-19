package worldmanager.features;

import worldmanager.features.internal.CoreClass;

@CoreClass
public interface FeatureInitializer {
    void onStart();

    default void onListen(String calledListener) {}
}
