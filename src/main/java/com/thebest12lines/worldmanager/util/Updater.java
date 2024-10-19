package com.thebest12lines.worldmanager.util;
import java.io.IOException;
import java.io.InputStream;

import java.net.URI;
import java.net.URISyntaxException;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;


import com.thebest12lines.worldmanager.annotation.CoreClass;
import org.json.JSONObject;

import com.thebest12lines.worldmanager.DataManager;
@CoreClass
/**
 * The de facto standard updater for worldmanager.
 * @author thebest12lines
 */
public class Updater {
    /**
     * Checks for updates
     * @return update check result
     * @throws UpdateBuildException
     */
    public static Constants.UpdateCheckResult checkForUpdates() throws UpdateBuildException  {

        
        try {
            StringBuilder builder = new StringBuilder();
            Files.lines(Paths.get("worldmanager.json")).forEach(builder::append);
            JSONObject obj = new JSONObject(builder.toString());
            JSONObject meta = obj.getJSONObject("worldmanagerMeta");
            String versionCheck = meta.getString("versionCheck");
            int currentVersion = DataManager.getBuild();
            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(versionCheck))
                .GET()
                .build();

            HttpResponse<String> response_ = client.send(request, HttpResponse.BodyHandlers.ofString());

           
            
            try {
                String response = response_.body();
                if (currentVersion < Integer.parseInt(response)) {
                    return Constants.UpdateCheckResult.UPDATE_NEEDED;
                } else if (currentVersion > Integer.parseInt(response)) {
                    throw new UpdateBuildException("Invalid version. Reinstalling program...");
                } else if (currentVersion == Integer.parseInt(response)) {
                    return Constants.UpdateCheckResult.UP_TO_DATE;
                }
              //  s.close();
            } catch (NumberFormatException e) {
                // 
                e.printStackTrace();
            }
            
        } catch (IOException | InterruptedException e) {
            // 
            e.printStackTrace();
        }
       
        return null;

    }

    /**
     * downloads and install updates
     * @throws UpdateBuildException
     * @throws URISyntaxException
     */
    public static void downloadAndInstallUpdates() throws UpdateBuildException, URISyntaxException {

        String localFilePath = "worldmanager_0.jar";
        StringBuilder builder = new StringBuilder();
        try {
            Files.lines(Paths.get("worldmanager.json")).forEach(builder::append);
        } catch (IOException e) {
            // 
            e.printStackTrace();
        }
        JSONObject obj = new JSONObject(builder.toString());
        JSONObject meta = obj.getJSONObject("worldmanagerMeta");
        String fileUrl = meta.getString("updateRepo");


        try (InputStream in = new URI(fileUrl).toURL().openStream()) {
            Files.copy(in, Paths.get(localFilePath), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("File downloaded and saved as " + localFilePath);
            System.exit(0x00000002);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
       
