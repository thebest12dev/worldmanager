package com.thebest12lines.worldmanager.util;

import com.thebest12lines.worldmanager.annotation.CoreClass;

@CoreClass
public class UpdateBuildException extends Exception {
    public UpdateBuildException(String errorMessage) {
        super(errorMessage);
    }
}
