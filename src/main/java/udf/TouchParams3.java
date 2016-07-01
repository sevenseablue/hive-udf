package udf;

import org.apache.hadoop.hive.ql.exec.UDF;
import util.FlightLog;
import util.FlightLogTouch;

/**
 * Created by seven on 24/06/16.
 */
public class TouchParams3 extends UDF {
    public String evaluate(String action, String url, String post) {
        return FlightLogTouch.getLogs(action, url, post).toHive();
    }
}
