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
    public static ObjectLibrary getObjectLibrary(String objectLibraryId) {
        return new ObjectLibrary(objectLibraryId);
    }

    public static void main(String[] args) {
        System.out.println(getObjectLibrary("objects/main").getPath("resources/libraries/json-20240303"));
    }
}
