package com.thebest12lines.worldmanager.util;
import com.thebest12lines.worldmanager.annotation.CoreClass;

import java.util.HashMap;
import java.util.Map;

/**
 * The command-line arguments parser to process command-line arguments to more robust types.
 * @author thebest12lines.
 */
@CoreClass
public class CommandArgsParser {
    /**
     * Parses the arguments to a <code>Map</code> that can be used.
     * @param args The array to be used
     * @return The <code>Map</code> to use
     */
    public static Map<String, ArgumentMetadata> parseArgs(String[] args) {
        Map<String, ArgumentMetadata> argMap = new HashMap<>();
        for (int i = 0; i < args.length; i++) {
            String key = args[i];
            String value = (i + 1 < args.length && !args[i + 1].startsWith("--")) ? args[i + 1] : null;
            argMap.put(key, new ArgumentMetadata(value, i));
        }
        return argMap;
    }

    /**
     * Checks with a <code>Map</code> if the object has an argument of the specific name.
     * @param argMap The object to compare the value.
     * @param option The option (argument) to look for.
     * @return The result of the comparison.
     */
    public static boolean hasOption(Map<String, ArgumentMetadata> argMap, String option) {
        return argMap.containsKey(option);
    }

    /**
     * Gets the value of that option/argument.
     * @param argMap The object to get the values from.
     * @param option The option to get from.
     * @return The result.
     */
    public static String getOptionValue(Map<String, ArgumentMetadata> argMap, String option) {
        ArgumentMetadata metadata = argMap.get(option);
        return metadata != null ? metadata.getValue() : null;
    }
    /**
     * Gets the index of that option/argument.
     * @param argMap The object to get the indexes from.
     * @param option The option to get from.
     * @return The result.
     */
    public static int getOptionIndex(Map<String, ArgumentMetadata> argMap, String option) {
        ArgumentMetadata metadata = argMap.get(option);
        return metadata != null ? metadata.getIndex() : -1;
    }
}

