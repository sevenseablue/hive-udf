package util;

import org.apache.commons.collections.map.CaseInsensitiveMap;
import org.apache.commons.configuration.SystemConfiguration;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by seven on 21/06/16.
 */
public class MapUtils {
    public static String getOrDefLower(Map<String, String> map, String key, String defaultStr) {
        key = key.toLowerCase();
        if (map.containsKey(key)) {
            return map.get(key);
        } else {
            return defaultStr;
        }
    }

    public static String getOrDef(Map<String, String> map, String key, String defaultStr) {
        if (map.containsKey(key)) {
            return map.get(key);
        } else {
            return defaultStr;
        }
    }

    public static void caseInsensMap() {
        Map<String, String> map = new CaseInsensitiveMap();
        map.put("One", "One");
        map.put("Two", "Two");
        map.put(null, "Three");
        System.out.println(map.get("ONE"));
        map.put("one", "Four");
        String s1 = "abcdeFGhijklmN";
        String v1 = "";
        for (int i = 0; i < 30; i++) {
            v1 = v1 + s1;
        }
        for (int i = 0; i < 100; i++) {
            map.put("abcdeFGhijklmN" + i, v1);
        }
        System.out.println(map.get("ONE"));
        long t1 = System.nanoTime();
        for (int i = 0; i < 1000000; i++) {
            map.get("abcdeFGhijklmN" + (i % 99));
        }
        System.out.println(System.nanoTime() - t1);
    }

    public static void caseInsensMap2() {
        Map<String, String> map = new TreeMap<String, String>(String.CASE_INSENSITIVE_ORDER);
        map.put("One", "One");
        map.put("Two", "Two");
        map.put("thrEe", "Three");
        System.out.println(map.get("ONE"));
        map.put("one", "Four");
        String s1 = "abcdeFGhijklmN";
        String v1 = "";
        for (int i = 0; i < 30; i++) {
            v1 = v1 + s1;
        }
        for (int i = 0; i < 100; i++) {
            map.put("abcdeFGhijklmN" + i, v1);
        }
        System.out.println(map.get("ONE"));
        long t1 = System.nanoTime();
        for (int i = 0; i < 1000000; i++) {
            map.get("abcdeFGhijklmN" + (i % 99));
        }
        System.out.println(System.nanoTime() - t1);
    }

    // fastest
    public static void caseInsensMap3() {
        Map<String, String> map = new CaseInsensitiveHM();
        map.put("One", "One");
        map.put("Two", "Two");
        map.put(null, "Three");
        System.out.println(map.get("ONE"));
        map.put("one", "Four");
        String s1 = "abcdeFGhijklmN";
        String v1 = "";
        for (int i = 0; i < 30; i++) {
            v1 = v1 + s1;
        }
        for (int i = 0; i < 100; i++) {
            map.put("abcdeFGhijklmN" + i, v1);
        }
        System.out.println(map.get("ONE"));
        System.out.println(map.get(null));
        long t1 = System.nanoTime();
        for (int i = 0; i < 100000; i++) {
            map.get("abcdeFGhijklmN" + (i % 99));
        }
        System.out.println(System.nanoTime() - t1);
    }

    // fastest
    public static void caseInsensMap4() {
        Map<String, String> map = new CaseInsensitiveHMSimp();
        map.put("One", "One");
        map.put("Two", "Two");
        map.put("", "Three");
        System.out.println(map.get("ONE"));
        map.put("one", "Four");
        String s1 = "abcdeFGhijklmN";
        String v1 = "";
        for (int i = 0; i < 30; i++) {
            v1 = v1 + s1;
        }
        for (int i = 0; i < 100; i++) {
            map.put("abcdeFGhijklmN" + i, v1);
        }
        System.out.println(map.get("ONE"));
        long t1 = System.nanoTime();
        for (int i = 0; i < 100000; i++) {
            map.get("abcdeFGhijklmN" + (i % 99));
        }
        System.out.println(System.nanoTime() - t1);
    }


    public static void main(String[] args) {
        caseInsensMap4();
        caseInsensMap3();
        caseInsensMap4();
        caseInsensMap2();
        caseInsensMap();
    }
}
