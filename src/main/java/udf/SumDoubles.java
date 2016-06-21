package udf;

import org.apache.hadoop.hive.ql.exec.UDF;

public class SumDoubles extends UDF
{
  public double evaluate(double fAmount, String[] strs)
  {
    double total = fAmount;
    for (int i = 0; i < strs.length; i++) {
      String curStr = strs[i];
      if ((curStr == null) || 
        (curStr.trim().equals("")) || 
        (curStr.trim().toLowerCase().equals("null")) || 
        (curStr.trim().equals("\\N")))
        continue;
      double dt = 0.0D;
      try {
        dt = Double.parseDouble(curStr);
      } catch (Exception e) {
        e.printStackTrace();
      }
      total += dt;
    }

    return total;
  }
}