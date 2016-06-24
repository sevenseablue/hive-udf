package udf;


import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.exec.UDF;
import util.CityCountryMap;

public class CityToCountry extends UDF {

    @Description(" return fileSet.contains(String... s1) ")
    public String evaluate(final String s, final String defaultString) {
        return CityCountryMap.get(s, defaultString);
    }
}
