package udf;

import org.apache.hadoop.hive.ql.exec.UDF;

public class SumInts extends UDF {
    public double evaluate(int fCount, String[] strs) {
        int total = 0;
        total += fCount;
        for (int i = 0; i < strs.length; i++) {
            String curStr = strs[i];
            if ((curStr == null) ||
                    (curStr.trim().equals("")) ||
                    (curStr.trim().toLowerCase().equals("null")) ||
                    (curStr.trim().equals("\\N")))
                continue;
            int dt = 0;
            try {
                dt = Integer.parseInt(curStr);
            } catch (Exception e) {
                e.printStackTrace();
            }
            total += dt;
        }

        return total;
    }
}