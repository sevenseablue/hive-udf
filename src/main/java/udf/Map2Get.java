package udf;



import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.exec.UDF;
import util.MapMy2;

public class Map2Get extends UDF {

	@Description(" return fileSet.contains(String... s1) ")
    public String evaluate(final String s, final String defaultString){
        return MapMy2.get(s, defaultString);
    }
}
