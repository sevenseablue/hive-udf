package udf;

import org.apache.hadoop.hive.ql.exec.UDF;
import util.CodeCity;
import util.MobileLocMap;

public class MobileToLoc extends UDF {

    public String evaluate(String code) {
        String res;
        if (code == null || code.length() != 7) {
            res = "\002";
        } else {
            res = MobileLocMap.get(code, "\002");
        }
        return res;
    }
}