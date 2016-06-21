package udf;

import org.apache.hadoop.hive.ql.exec.UDF;
import util.IdNoUtil;

public class BirthDayFromIdNo extends UDF
{
  public String evaluate(String idNo)
  {
    String res = "";
    if (IdNoUtil.ifIdNoOk(idNo)) {
      res = IdNoUtil.getBirthFromIdNo(idNo);
    }
    return res;
  }
}