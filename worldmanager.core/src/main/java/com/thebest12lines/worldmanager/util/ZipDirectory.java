package com.thebest12lines.worldmanager.util;

import worldmanager.features.annotation.CoreClass;

import java.io.*;
import java.util.zip.*;

/**
 * Utility for zipping and unzipping files.
 * @author thebest12lines
 */
@CoreClass
public class ZipDirectory {
    /**
     * Zips a directory.
     * @param sourceDirPath The source directory path.
     * @param zipFilePath The created file path.
     * @param excludeDir Any directories to exclude.
     * @throws IOException Due to FileOutputStream
     */
    public static void zipDirectory(String sourceDirPath, String zipFilePath, String excludeDir) throws IOException {
        FileOutputStream fos = new FileOutputStream(zipFilePath);
    
        ZipOutputStream zipOut = new ZipOutputStream(fos);
        File sourceDir = new File(sourceDirPath);

        zipFile(sourceDir, sourceDir.getName(), zipOut, excludeDir);
        zipOut.close();
        fos.close();
    }

    private static void zipFile(File fileToZip, String fileName, ZipOutputStream zipOut, String excludeDir) throws IOException {
        if (fileToZip.isHidden() || fileToZip.getAbsolutePath().startsWith(excludeDir)) {
            return;
        }
        if (fileToZip.isDirectory()) {
            if (fileName.endsWith("/")) {
                zipOut.putNextEntry(new ZipEntry(fileName));
                zipOut.closeEntry();
            } else {
                zipOut.putNextEntry(new ZipEntry(fileName + "/"));
                zipOut.closeEntry();
            }
            File[] children = fileToZip.listFiles();
            for (File childFile : children) {
                zipFile(childFile, fileName + "/" + childFile.getName(), zipOut, excludeDir);
            }
            return;
        }
        FileInputStream fis = new FileInputStream(fileToZip);
        ZipEntry zipEntry = new ZipEntry(fileName);
        zipOut.putNextEntry(zipEntry);
        byte[] bytes = new byte[1024];
        int length;
        while ((length = fis.read(bytes)) >= 0) {
            zipOut.write(bytes, 0, length);
        }
        fis.close();
    }

    // public static void zipFile(File fileToZip, String fileName, ZipOutputStream zipOut) throws IOException {
    //     if (fileToZip.isHidden()) {
    //         return;
    //     }
    //     if (fileToZip.isDirectory()) {
    //         if (fileName.endsWith("/")) {
    //             zipOut.putNextEntry(new ZipEntry(fileName));
    //             zipOut.closeEntry();
    //         } else {
    //             zipOut.putNextEntry(new ZipEntry(fileName + "/"));
    //             zipOut.closeEntry();
    //         }
    //         File[] children = fileToZip.listFiles();
    //         for (File childFile : children) {
    //             zipFile(childFile, fileName + "/" + childFile.getName(), zipOut);
    //         }
    //         return;
    //     }
    //     FileInputStream fis = new FileInputStream(fileToZip);
    //     ZipEntry zipEntry = new ZipEntry(fileName);
    //     zipOut.putNextEntry(zipEntry);
    //     byte[] bytes = new byte[1024];
    //     int length;
    //     while ((length = fis.read(bytes)) >= 0) {
    //         zipOut.write(bytes, 0, length);
    //     }
    //     fis.close();
    // }

    /**
     * Unzips a directory to the Minecraft saves directory.
     * @param zipFile The archive to unzip.
     * @throws FileNotFoundException If the file was not found.
     * @throws IOException Due to OutputStream.
     */
    public static void uncompressToSavesFolder(String zipFile) throws FileNotFoundException, IOException {
        String string = "";
        string = System.getProperty("user.home") + "\\AppData\\Roaming\\.minecraft\\saves";
        if (System.getProperty("os.name").equals("Linux")) {
            string = System.getProperty("user.home") + "/.minecraft/saves";
        }
        // } else if (OSInfo.isMac()) {
        //     string = System.getProperty("user.home") + "/Library/Application Support/minecraft";
        // } else if (OSInfo.isLinux()) {
        //     string = System.getProperty("user.home") + "/.minecraft";
        // }
        String outputDirectory = string;
        System.out.println(outputDirectory);
        File outputDir = new File(outputDirectory);
        if (!outputDir.exists()) {
            outputDir.mkdir();
        }
    
        try (ZipInputStream zipIn = new ZipInputStream(new FileInputStream(zipFile))) {
            ZipEntry entry;
            while ((entry = zipIn.getNextEntry()) != null) {
                String entryName = entry.getName();
                String entryPath = outputDirectory + File.separator + entryName;
    
                Output.printErr("["+ZipDirectory.class.getCanonicalName()+"]: Extracting file "+entryName); // Debug output
                Output.printFile("["+ZipDirectory.class.getCanonicalName()+"]: Extracting file "+entryName); // Debug output
                if (entry.isDirectory()) {
                    // Create the directory
                    new File(entryPath).mkdirs();
                } else {
                    try (FileOutputStream fos = new FileOutputStream(entryPath)) {
                        byte[] buffer = new byte[1024];
                        int bytesRead;
                        while ((bytesRead = zipIn.read(buffer)) != -1) {
                            fos.write(buffer, 0, bytesRead);
                        }
                    }
                }
                zipIn.closeEntry();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Output.printErr("["+ZipDirectory.class.getCanonicalName()+"]: Files successfully extracted"); // Debug output
        Output.printFile("["+ZipDirectory.class.getCanonicalName()+"]: Files successfully extracted"); // Debug output
    }
    
}
