package util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class MobileLocMap {
    /**
     *
     */
    final static HashMap<String, String> mapMy = new HashMap<String, String>(
            (int)(308623/0.7));

    static {
        BufferedReader br;
        try {
            br = new BufferedReader(new InputStreamReader(MobileLocMap.class.getClassLoader().getResourceAsStream("mobile_location.txt")));
            String line = "";
            while ((line = br.readLine()) != null) {
                String[] spli = line.split("\001");
                if (spli.length >= 2) {
                    mapMy.put(spli[0], spli[1]+"\002"+spli[2]);
                }
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static String get(String key, String defaultStr) {
        if (mapMy.containsKey(key)) {
            return mapMy.get(key);
        } else {
            return defaultStr;
        }
    }

    public static String getCity(String key, String defaultStr) {
        if (mapMy.containsKey(key)) {
            return mapMy.get(key).split("\001")[1];
        } else {
            return defaultStr;
        }
    }

    public static String getStat(String key, String defaultStr) {
        if (mapMy.containsKey(key)) {
            return mapMy.get(key).split("\001")[0];
        } else {
            return defaultStr;
        }
    }


    public static void main(String[] args) throws IOException {
        System.out.println(MobileLocMap.get("1581053", "h"));
        System.out.println(MobileLocMap.getCity("1581053", "h"));
        System.out.println(MobileLocMap.getStat("1581053", "h"));
    }

}
