package com.thebest12lines.worldmanager;

import worldmanager.features.annotation.CoreClass;
import com.thebest12lines.worldmanager.gui.MainGui;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * A class for managing "object libraries". Object libraries are special folders used within worldmanager to store assets like JARs and shared libraries.
 * It is also used to store icons and other assets, and are represented by the {@code objects/<libraryName>} resource identifier where {@code libraryName} is the fully qualified
 * name of the object library.
 * @author thebest12lines
 */
@CoreClass
public class ObjectLibrary {
    private String objectLibraryId;
    private JSONArray obj;
    public ObjectLibrary(String id) {
        this.objectLibraryId = id;
    }

    /**
     * Returns a resource from the object library in its object form. Note that this only works on some file types, so it is not guaranteed that this will work.
     * Resource identifiers are represented by anything prefixed with <code>resources/</code>. There are several different types of <strong>resource types</strong>, or
     * the identifier right after <code>resources/</code>. For example <code>resources/libraries/</code> and <code>resources/icons/</code> are valid resource types.
     * Finally, the fully qualified name goes after these, like <code>resources/icons/icon32</code> where <code>icon32</code> is the fully qualified name (excluding file extensions).
     * @param resourceId The fully qualified resource ID to return.
     * @return The resource in its object form.
     */
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
