package com.thebest12lines.worldmanager;

import com.thebest12lines.worldmanager.annotation.CoreClass;
import com.thebest12lines.worldmanager.gui.MainGui;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

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
