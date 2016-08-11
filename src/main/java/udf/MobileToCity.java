package udf;

import org.apache.hadoop.hive.ql.exec.UDF;
import util.MobileLocMap;

public class MobileToCity extends UDF {

    public String evaluate(String code) {
        String res;
        if (code == null || code.length() != 7) {
            res = "";
        } else {
            res = MobileLocMap.getCity(code, "");
        }
        return res;
    }
}