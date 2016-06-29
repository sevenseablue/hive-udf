package util;

import java.util.HashMap;

/**
 * Created by seven on 29/06/16.
 */
public class CaseInsensitiveHM extends HashMap<String, String> {
    @Override
    public String get(Object key) {
        if (key instanceof String) {
//        if(key.getClass() == String.class)
            return super.get(((String) key).toLowerCase());
        } else {
            return super.get(key);
        }
    }

    @Override
    public String put(String key, String value) {
        if (key == null) {
            return super.put(key, value);
        } else {
            return super.put(key.toLowerCase(), value);
        }
    }

    public static void main(String[] args) {

    }
}
