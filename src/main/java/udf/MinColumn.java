package udf;

import org.apache.hadoop.hive.ql.exec.UDF;

public class MinColumn extends UDF
{
  public String evaluate(String[] strs)
  {
    String minStr = (strs[0] == null) || (strs[0].equals("NULL")) ? "9999-99-99 99:99:99" : strs[0];

    for (int i = 1; i < strs.length; i++) {
      String curStr = strs[i];
      if ((curStr == null) || (curStr.equals("NULL")) || 
        (curStr.compareTo(minStr) > 0)) continue;
      minStr = curStr;
    }

    return minStr;
  }
}