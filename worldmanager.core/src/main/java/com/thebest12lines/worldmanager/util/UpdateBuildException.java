package com.thebest12lines.worldmanager.util;

import worldmanager.features.annotation.CoreClass;

/**
 * The exception for update builds if there is an error.
 * @author thebest12lines
 */
@CoreClass
public class UpdateBuildException extends Exception {
    public UpdateBuildException(String errorMessage) {
        super(errorMessage);
    }
}
