package udf;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import org.apache.hadoop.hive.ql.exec.UDF;

public class CodeToAirComp extends UDF
{
  public static HashMap<String, String> airCompany = new HashMap();
  public static HashMap<String, String> airCompanyReverse = new HashMap();

  public String evaluate(String airComp)
  {
    String res = "";

    String airCompCode = airComp.trim().replace("\"", "");

    if (airCompCode.contains(",")) {
      airCompCode = airCompCode.split(",")[0];
    }

    if (airCompany.containsKey(airCompCode)) {
      res = (String)airCompany.get(airCompCode);
    }

    if (airCompanyReverse.containsKey(airCompCode)) {
      String twoCode = (String)airCompanyReverse.get(airCompCode);
      res = airCompCode;
      if (airCompany.containsKey(twoCode)) {
        res = (String)airCompany.get(twoCode);
      }
    }

    if ((res.equals("")) && 
      (airCompCode.length() > 3)) {
      res = airCompCode;
    }

    return res;
  }

  static
  {
    String airComPath = "airCompany.txt";
    try
    {
      File af = new File(airComPath);
      FileReader afr = new FileReader(af);
      BufferedReader abr = new BufferedReader(afr);
      String atmpStr = null;
      while ((atmpStr = abr.readLine()) != null) {
        String[] arr = atmpStr.split(",");
        airCompany.put(arr[1].trim(), arr[0].trim());
        airCompanyReverse.put(arr[0].trim(), arr[1].trim());
      }
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }
}