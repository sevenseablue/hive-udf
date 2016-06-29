package udf;

import org.apache.hadoop.hive.ql.exec.UDF;
import util.CodeCity;

public class CodeToCity extends UDF {

    public String evaluate(String code) {
        String res;
        if (code == null || code.equals("") || code.toLowerCase().equals("null")) {
            res = "";
        } else {
            res = CodeCity.get(code, "");
        }
        return res;
    }
}