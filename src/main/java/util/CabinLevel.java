package util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class CabinLevel {
    public static Map<String, String> cabinMap = new HashMap();

    public static String get(String airComp, String cabin) {
        String res;
        if ((airComp.equals("AQ"))) {
            res = "经济舱";
        } else {
            String key = airComp.trim().replace("\"", "") + "," + cabin.trim();
            System.out.println(key);
            res = MapUtils.getOrDef(cabinMap, key, "");
        }

        return res;
    }

    static {
        String cabinPath = "cabinInfos.txt";
        BufferedReader br;
        try {
            br = new BufferedReader(new InputStreamReader(CabinLevel.class.getClassLoader().getResourceAsStream(cabinPath)));
            String tmpStr;
            while ((tmpStr = br.readLine()) != null) {
                int i1 = tmpStr.lastIndexOf(",");
                if (i1 <= 0) {
                    continue;
                }
                String[] spli = tmpStr.split(",");
                if(spli[0].equals("MF")){
                    System.out.println(tmpStr.substring(0, i1));
                    System.out.println(tmpStr.substring(i1 + 1));
                }
                cabinMap.put(tmpStr.substring(0, i1), tmpStr.substring(i1 + 1));
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println(get("MF", "S"));
    }
}