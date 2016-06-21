package udf;

import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.exec.UDF;
import util.IPCityUtil;

public class IPCityInfos extends UDF {

	@Description(" return the city of the ip ")
    public String evaluate(final String ip){
        return IPCityUtil.getInfos(ip);
    }
}
