package util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class CodeCity {
    /**
     *
     */
    final static HashMap<String, String> mapMy = new HashMap<String, String>(
            20480);

    static {
        BufferedReader br;
        try {
            br = new BufferedReader(new InputStreamReader(CodeCity.class.getClassLoader().getResourceAsStream("codeCity.txt")));
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
        key = key.toUpperCase();
        if (mapMy.containsKey(key)) {
            return mapMy.get(key);
        } else {
            return defaultStr;
        }
    }


    public static void main(String[] args) throws IOException {
        System.out.println(get("AAA", "heihei"));
    }

}
