package udf;

import org.apache.hadoop.hive.ql.exec.UDF;
import util.FlightLogTouch;

/**
 * Created by seven on 24/06/16.
 */
public class TouchParams2 extends UDF {
    public String evaluate(String action, String log) {
        return FlightLogTouch.getLogs(action, log).toHive();
    }
}
