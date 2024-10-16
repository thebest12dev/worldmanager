package com.thebest12lines.worldmanager;

import com.thebest12lines.worldmanager.annotation.CoreClass;
import com.thebest12lines.worldmanager.gui.MainGui;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
@CoreClass
public class ObjectLibrary {
    private String objectLibraryId;
    private JSONArray obj;
    public ObjectLibrary(String id) {
        this.objectLibraryId = id;
    }
    public Object getResource(String resourceId) {
        String path = getPath(resourceId);
        Object object = new Object();
        if (path.contains("/3/")) {
            try {
                object = MainGui.loadImage(path);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return object;
    }
    public String getPath(String resourceId) {
        String path = "";
        if (obj == null) {
            StringBuilder s = new StringBuilder();
            try {
                Files.lines(Path.of("worldmanager/objects/objects.json")).forEach(s::append);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            obj = new JSONObject(s.toString()).getJSONArray(objectLibraryId);
        }
        for (Object object : obj) {
            if (object instanceof JSONObject) {
                if (((JSONObject) object).getString("name").equals(resourceId)) {
                    // Make the path
                    path = "worldmanager/" + objectLibraryId + "/" + ((JSONObject) object).getInt("id") + "/" + ((JSONObject) object).getString("hash");
                    if (((JSONObject) object).getInt("id") == 1) {
                        path = path.concat(".jar");
                    } else if (((JSONObject) object).getInt("id") == 2) {
                        path = path.concat(".dll");
                    }

                }
            }
        }
        if (path.isEmpty()) {
            throw new NullPointerException("Incorrect resource ID");
        }
        return path;
    }




}
