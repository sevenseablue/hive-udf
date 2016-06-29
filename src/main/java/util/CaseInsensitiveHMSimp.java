package util;

import java.util.HashMap;

/**
 * Created by seven on 29/06/16.
 */
public class CaseInsensitiveHMSimp extends HashMap<String, String> {
    @Override
    public String get(Object key) {
        return super.get(((String) key).toLowerCase());
    }

    @Override
    public String put(String key, String value) {
        return super.put(key.toLowerCase(), value);
    }
}
