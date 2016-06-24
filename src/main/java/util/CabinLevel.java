package util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.HashMap;

public class CabinLevel {
    public static HashMap<String, String> cabinMap = new HashMap();

    public static String get(String airComp, String cabin) {
        String res;
        if ((airComp.equals("AQ"))) {
            res = "经济舱";
        }
        else {
            String key = airComp.trim().replace("\"", "") + "," + cabin.trim();
            res = MapUtils.getOrDef(cabinMap, key, "");
        }

        return res;
    }

    static {
        String cabinPath = "cabinInfos.txt";
        BufferedReader br;
        try {
            br =new BufferedReader(new InputStreamReader(CodeCity.class.getClassLoader().getResourceAsStream(cabinPath)));
            String tmpStr;
            while ((tmpStr = br.readLine()) != null) {
                int i1 = tmpStr.lastIndexOf(",");
                if( i1<=0){
                    continue;
                }
                cabinMap.put(tmpStr.substring(0, i1), tmpStr.substring(i1+1));
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}