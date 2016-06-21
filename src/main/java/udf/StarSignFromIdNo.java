package udf;

import org.apache.hadoop.hive.ql.exec.UDF;
import util.IdNoUtil;

public class StarSignFromIdNo extends UDF
{
  public String evaluate(String idNo)
  {
    String res = "";
    if (IdNoUtil.ifIdNoOk(idNo)) {
      String birthDay = IdNoUtil.getBirthFromIdNo(idNo);
      if (IdNoUtil.ifBirthDayOk(birthDay)) {
        res = IdNoUtil.getStarSign(birthDay);
      }
    }
    return res;
  }

  public static void main(String[] args) {
    String id = "5137stGyN=qI0pY054";
    String id1 = "35018119810425257X";
    String id2 = "3101kdUKCBjQ003";
    String id3 = "440225197206020448";

    System.out.println(IdNoUtil.ifIdNoOk(id3) + "\t" + id1.substring(4, 15));
    System.out.println(IdNoUtil.getStarSign(IdNoUtil.getBirthFromIdNo(id3)) + "\t" + IdNoUtil.getGenderFromIdNo(id3) + "\t" + IdNoUtil.getBirthFromIdNo(id3) + "\t" + IdNoUtil.getAge(IdNoUtil.getBirthFromIdNo(id3)));
  }
}