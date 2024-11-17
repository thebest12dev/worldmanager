package com.thebest12lines.worldmanager.annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Used to indicate and define a feature's main class.
 * @author thebest12lines
 */
@CoreClass
@Retention(RetentionPolicy.RUNTIME)
public @interface Feature {
    String value();
}