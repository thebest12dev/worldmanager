package com.thebest12lines;

public class OSInfo {

        public static String osName = System.getProperty("os.name");
        public static String osVersion = System.getProperty("os.version");
        public static boolean isWindows() {
                if (osName.startsWith("Windows")) {
                        return true;
                } else {return false;}
        }
        public static boolean isMac() {
                if (osName.startsWith("Mac")) {
                        return true;
                } else {return false;}
        }
        public static boolean isLinux() {
                if (osName.startsWith("Linux")) {
                        return true;
                } else {return false;}
        }
}
