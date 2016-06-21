package udf;

import org.apache.hadoop.hive.ql.exec.UDF;

public class MaxColumn extends UDF
{
  public String evaluate(String[] strs)
  {
    String maxStr = (strs[0] == null) || (strs[0].equals("NULL")) ? "0000-00-00 00:00:00" : strs[0];

    for (int i = 1; i < strs.length; i++) {
      String curStr = strs[i];
      if ((curStr == null) || (curStr.equals("NULL")) || 
        (curStr.compareTo(maxStr) < 0)) continue;
      maxStr = curStr;
    }

    return maxStr;
  }
}