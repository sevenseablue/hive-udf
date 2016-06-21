package udf;



import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.exec.UDF;
import util.MapMy4;

public class Map4Get extends UDF {

	@Description(" return fileSet.contains(String... s1) ")
    public String evaluate(final String s, final String defaultString){
        return MapMy4.get(s, defaultString);
    }
}
