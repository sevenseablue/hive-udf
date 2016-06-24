package util;

import java.util.Map;

/**
 * Created by seven on 21/06/16.
 */
public class MapUtils {
    public static String GetOrDef(Map<String, String> map, String key, String defaultStr) {
        if (map.containsKey(key)) {
            return map.get(key);
        } else {
            return defaultStr;
        }
    }
}
