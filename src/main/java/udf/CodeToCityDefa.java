package udf;

import org.apache.hadoop.hive.ql.exec.UDF;
import util.CodeCity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CodeToCityDefa extends UDF {
    final Pattern p = Pattern.compile("^[A-Z]{3}$");

    public String evaluate(String code, String defaultStr) {
        String res;

        if (code == null || code.equals("") || code.toLowerCase().equals("null")) {
            res = "";
        }
        else {
            Matcher m = p.matcher(code);
            if (m.find()){
                res = CodeCity.get(code, "");
            }
            else {
                res = defaultStr;
            }
        }
        return res;
    }
}