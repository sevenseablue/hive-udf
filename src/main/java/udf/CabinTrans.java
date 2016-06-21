package udf;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import org.apache.hadoop.hive.ql.exec.UDF;

public class CabinTrans extends UDF
{
  public static HashMap<String, String> airCompany = new HashMap();
  public static HashMap<String, HashMap<String, String>> cabinMap = new HashMap();

  public String evaluate(String airComp, String cabin)
  {
    String res = "";
    String airCompCode = airComp.trim().replace("\"", "");
    if (airComp.contains(",")) {
      airCompCode = airCompCode.split(",")[0].trim();
      if (cabin.contains(",")) {
        cabin = cabin.split(",")[0];
      }
    }

    if ((airCompCode.equals("AQ")) || (airCompCode.equals("九元航空公司"))) {
      res = "经济舱";
      return res;
    }

    cabin = cabin.trim();

    if (airCompany.containsKey(airCompCode)) {
      airCompCode = (String)airCompany.get(airCompCode);
    }

    HashMap tmp = (HashMap)cabinMap.get(airCompCode);
    if ((tmp != null) && 
      (tmp.containsKey(cabin))) {
      res = (String)tmp.get(cabin);
    }

    return res;
  }

  static
  {
    String cabinPath = "cabin_all";
    String airComPath = "air_company_new";
    try
    {
      File f = new File(cabinPath);
      FileReader fr = new FileReader(f);
      BufferedReader br = new BufferedReader(fr);
      String tmpStr = null;
      while ((tmpStr = br.readLine()) != null) {
        String[] arr = tmpStr.split(",");
        HashMap tmpMap = null;
        if (cabinMap.containsKey(arr[0].trim())) {
          tmpMap = (HashMap)cabinMap.get(arr[0].trim());
          tmpMap.put(arr[2].trim(), arr[1].trim());
        } else {
          tmpMap = new HashMap();
          tmpMap.put(arr[2].trim(), arr[1].trim());
        }
        cabinMap.put(arr[0].trim(), tmpMap);
      }

      File af = new File(airComPath);
      FileReader afr = new FileReader(af);
      BufferedReader abr = new BufferedReader(afr);
      String atmpStr = null;
      while ((atmpStr = abr.readLine()) != null) {
        String[] arr = atmpStr.split(",");
        airCompany.put(arr[0].trim(), arr[1].trim());
      }
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }
}