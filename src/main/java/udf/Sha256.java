package udf;

/**
 * Created by seven on 27/06/16.
 */

import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.exec.UDF;
import util.SHA256;


public class Sha256 extends UDF {

    @Description(" encode phone ")
    public String evaluate(final String phone) {

        try {
            return SHA256.encode(phone);
        } catch (Exception e) {
            e.printStackTrace();
            return "EXCEPTION";
        }
    }
}
