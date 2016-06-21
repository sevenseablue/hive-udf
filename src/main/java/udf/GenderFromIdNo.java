package udf;

import org.apache.hadoop.hive.ql.exec.UDF;
import util.IdNoUtil;

public class GenderFromIdNo extends UDF
{
  public String evaluate(String idNo)
  {
    String res = "";
    if (IdNoUtil.ifIdNoOk(idNo)) {
      res = IdNoUtil.getGenderFromIdNo(idNo);
    }
    return res;
  }
}