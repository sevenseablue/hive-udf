package udf;

import org.apache.hadoop.hive.ql.exec.UDF;
import util.FlightLog;

/**
 * Created by seven on 24/06/16.
 */
public class AppParams extends UDF {
    public String evaluate(String action, String log) {
        return FlightLog.getLogs(action, log).toHive();
    }
}
