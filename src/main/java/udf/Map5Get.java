package udf;



import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.exec.UDF;
import util.MapMy5;

public class Map5Get extends UDF {

	@Description(" return fileSet.contains(String... s1) ")
    public String evaluate(final String s, final String defaultString){
        return MapMy5.get(s, defaultString);
    }
}
