package com.thebest12lines.worldmanager;

import worldmanager.features.internal.CoreClass;

/**
 * The object manager to manage hashed object files and to parse them.
 * @author thebest12lines
 */
@CoreClass
public class ObjectManager {
    /**
     * Returns the object library with the resource identifier.
     * @param objectLibraryId The fully qualified name.
     * @return The object library.
     */
    public static ObjectLibrary getObjectLibrary(String objectLibraryId) {
        return new ObjectLibrary(objectLibraryId);
    }
}
