package udf;


import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.exec.UDF;
import util.MapMy3;

public class Map3Get extends UDF {

    @Description(" return fileSet.contains(String... s1) ")
    public String evaluate(final String s, final String defaultString) {
        return MapMy3.get(s, defaultString);
    }
}
