package com.thebest12lines.util;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;

import org.json.JSONObject;

import com.thebest12lines.util.Constants.UpdateCheckResult;

public class Updater {
    
    public static Constants.UpdateCheckResult checkForUpdates() throws UpdateBuildException {

        
        try {
            StringBuilder builder = new StringBuilder();
            Files.lines(Paths.get("worldmanager.json")).forEach(builder::append);
            JSONObject obj = new JSONObject(builder.toString());
            JSONObject meta = obj.getJSONObject("worldmanagerMeta");
            String versionCheck = meta.getString("versionCheck");
            int currentVersion = meta.getInt("build");


            HttpURLConnection httpConn;
            URL url = new URL(versionCheck);
            httpConn = (HttpURLConnection) url.openConnection(); 
            httpConn.setRequestMethod("GET");

            InputStream responseStream = httpConn.getResponseCode() / 100 == 2
                    ? httpConn.getInputStream()
                    : httpConn.getErrorStream();
            try (Scanner s = new Scanner(responseStream).useDelimiter("\\A")) {
                String response = s.hasNext() ? s.next() : "";
                if (currentVersion < Integer.parseInt(response)) {
                    return Constants.UpdateCheckResult.UPDATE_NEEDED;
                } else if (currentVersion > Integer.parseInt(response)) {
                    throw new UpdateBuildException("Invalid version. Reinstalling program...");
                } else if (currentVersion == Integer.parseInt(response)) {
                    return Constants.UpdateCheckResult.UP_TO_DATE;
                }
                s.close();
            } catch (NumberFormatException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
       
        return null;

    }

    public static void downloadAndInstallUpdates() throws UpdateBuildException {

        String localFilePath = "worldmanager_0.jar";
        StringBuilder builder = new StringBuilder();
        try {
            Files.lines(Paths.get("worldmanager.json")).forEach(builder::append);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        JSONObject obj = new JSONObject(builder.toString());
        JSONObject meta = obj.getJSONObject("worldmanagerMeta");
        String fileUrl = meta.getString("updateRepo");
        int currentVersion = meta.getInt("build");

        try (InputStream in = new URL(fileUrl).openStream()) {
            Files.copy(in, Paths.get(localFilePath), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("File downloaded and saved as " + localFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
       
