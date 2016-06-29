package util;

import com.google.common.collect.Maps;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by seven on 22/06/16.
 */
public class UrlToJson {

    public static boolean any(char[] cArr, char char1) {
        for (char c : cArr) {
            if (c == char1) {
                return true;
            }
        }
        return false;
    }

    public static String parse(String log) {

        char[] arr = log.toCharArray();
        int s = 0;

        HashMap<Character, Integer> classMap = Maps.newHashMap();
        classMap.put('[', 0);
        classMap.put(']', 0);
        classMap.put('{', 1);
        classMap.put('}', 1);
        HashMap<Character, Integer> operMap = Maps.newHashMap();
        operMap.put('[', 1);
        operMap.put(']', -1);
        operMap.put('{', 1);
        operMap.put('}', -1);

        char[] startFlags = new char[]{'[', '{'};
        char[] braceFlags = new char[]{'[', '{', ']', '}'};

        ArrayList<String> resultList = new ArrayList<String>();
        int[] flagInts = new int[2];
        StringBuilder sb = new StringBuilder(2048);
        sb.append('{');
        boolean entryed = true;
        if (arr.length < 2) {
            return null;
        }

        for (int i = 1; i < arr.length; i++) {
            char ch = arr[i];
            if (entryed) {
                if (any(startFlags, ch)) {
                    sb.append(ch);
                } else {
                    sb.append("\"")
                            .append(ch);
                }
            } else {

            }

        }
        sb.append('}');
        if (s < arr.length) {
            resultList.add(log.substring(s));
        }
        return null;
    }

    public static void main(String[] args) {
        for (String log : new String[]{
                "&",
                "a=b",
                "a=b&",
                "a=b&c=d",
                "a=b&c=[{d=4&e=[{f=6&g=7}]&e2=[{f2=6&g2=7}]}]"
        }) {
            System.out.println(UrlToJson.parse(log));
        }

    }

}
