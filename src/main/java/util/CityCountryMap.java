package util;

import java.io.*;
import java.util.HashMap;

public class CityCountryMap {
    /**
     *
     */
    final static HashMap<String, String> mapMy = new HashMap<String, String>(
            2048);

    static {
        BufferedReader br;
        try {
            br = new BufferedReader(new InputStreamReader(CityCountryMap.class.getClassLoader().getResourceAsStream("cityCountry.txt")));
            String line = "";
            while ((line = br.readLine()) != null) {
                String[] spli = line.split("\001");
                if (spli.length >= 2) {
                    mapMy.put(spli[0], spli[1]);
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


    public static void main(String[] args) throws IOException {
    }

}
