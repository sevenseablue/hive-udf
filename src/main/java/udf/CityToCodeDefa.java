package udf;

import org.apache.hadoop.hive.ql.exec.UDF;
import util.CityCode;

public class CityToCodeDefa extends UDF {

    public String evaluate(String city, String defaultStr) {
        String res;

        if (city == null || city.equals("") || city.toLowerCase().equals("null")) {
            res = defaultStr;
        }
        else {
                res = CityCode.get(city, defaultStr);
        }
        return res;
    }
}