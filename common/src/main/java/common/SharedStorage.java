package common;

import java.util.HashMap;

public class SharedStorage {
    private static HashMap<String,Object> map = new HashMap<>();

    public static void setValue(String key, Object value) {
        map.put(key,value);
    }
    public static Object getValue(String key) {
        return map.get(key);
    }


}
