package udf;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import org.apache.hadoop.hive.ql.exec.UDF;

public class LocationOfId extends UDF
{
  public static HashMap<String, String> jgMap = new HashMap();

  public String evaluate(String idNo)
  {
    String jiguan = "not_find";

    if ((idNo == null) || (idNo.trim().equals("")) || (idNo.equals("NULL")) || (idNo.equals("None"))) {
      return jiguan;
    }
    if (idNo.length() < 7) {
      return jiguan;
    }
    String idpre = idNo.substring(0, 6);
    jiguan = (String)jgMap.get(idpre);
    return jiguan;
  }

  public static void main(String[] args) {
    System.out.println("1234567".substring(0, 6));
  }

  static
  {
    String path = "NILocation.txt";
    File f = new File(path);
    BufferedReader reader = null;
    FileReader fr = null;
    try {
      fr = new FileReader(path);
      reader = new BufferedReader(fr);

      String tmpStr = null;
      while ((tmpStr = reader.readLine()) != null) {
        try {
          String[] tt = tmpStr.split("\001");
          jgMap.put(tt[0], tt[1]);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
      System.out.println("jgmap len: " + jgMap.size());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}