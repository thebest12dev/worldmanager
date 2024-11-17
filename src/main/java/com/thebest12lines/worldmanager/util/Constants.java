package com.thebest12lines.worldmanager.util;

import com.thebest12lines.worldmanager.annotation.CoreClass;

/**
 * The main class for storing constants, such as enumerations and string and int constants.
 * @author thebest12lines
 */
@CoreClass
public class Constants {
    /**
     * The enum for the result of an update check.
     */
    public static enum UpdateCheckResult {
        UPDATE_NEEDED,UP_TO_DATE
    }
    // public static enum WorldReadResult {
    //     SUCCESS,FAIL,UNKNOWN
    // }
    // public static enum WorldBackupResult {
    //     INSUFFICIENT_DISK_SPACE, BACKED_UP, FAIL, UNKNOWN
    // }

    /**
     * The enum for the result of a feature load.
     */
    public static enum FeatureLoadResult {
        LOADED,CANNOT_FIND_METHOD,CANNOT_FIND_CLASS,NOT_LOADED
    }

    /**
     * The specific string constants for ANSI colors.
     */
    public static class ANSIColor {
        public static final String RESET = "\u001B[0m";
        public static final String BLACK = "\u001B[30m";
        public static final String RED = "\u001B[31m";
        public static final String GREEN = "\u001B[32m";
        public static final String YELLOW = "\u001B[33m";
        public static final String BLUE = "\u001B[34m";
        public static final String PURPLE = "\u001B[35m";
        public static final String CYAN = "\u001B[36m";
        public static final String BOLD = "\u001B[37m";
        public static final String GRAY = "\u001B[90m";
        public static final String BRIGHT_BLUE = "\u001B[94m";
        public static final String LIGHT_BLUE = "\u001B[38;2;0;135;255m";

    }
    // public static enum BackupType {
    //     TEMPORARY, PERMANENT
    // }
    // public static enum WorldVersionType {
    //     PRE_1_2_1, POST_1_2_1
    // }
    // public static enum FeatureType {
    //     BUILT_IN_FEATURE,PLUGIN_FEATURE,EXTERNAL_LIBRARY
    // }
    // public static enum WorldEditionType {
    //     JAVA,BEDROCK
    // }
    // public static enum WorldBackupResolveType {
    //     GLOBAL_BACKUPS,IN_WORLD_BACKUPS
    // }
    
}