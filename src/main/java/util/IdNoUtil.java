package util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IdNoUtil
{
  public static Pattern idNoPattern = Pattern.compile("^\\d+$");
  public static SimpleDateFormat sdfBirth = new SimpleDateFormat("yyyyMMdd");

  public static boolean ifIdNoOk(String idNo) {
    boolean res = false;
    if (idNo == null) {
      return false;
    }
    Matcher m = null;
    if (idNo.length() == 18)
      m = idNoPattern.matcher(idNo.substring(4, 15));
    else if (idNo.length() == 15) {
      m = idNoPattern.matcher(idNo.substring(4, 12));
    }

    if ((m != null) && 
      (m.find())) {
      res = true;
    }

    return res;
  }

  public static String getStarSign(String birthday) {
    String result = "";
    try {
      if (birthday == null)
        return result;
      if (birthday.length() < 10) {
        return result;
      }
      int mon = Integer.parseInt(birthday.subSequence(5, 7).toString());
      int day = Integer.parseInt(birthday.subSequence(8, 10).toString());
      switch (mon) {
      case 1:
        if (day >= 21)
          result = "水瓶座";
        else
          result = "摩羯座";
        break;
      case 2:
        if (day <= 19)
          result = "水瓶座";
        else
          result = "双鱼座";
        break;
      case 3:
        if (day <= 20)
          result = "双鱼座";
        else
          result = "白羊座";
        break;
      case 4:
        if (day <= 20)
          result = "白羊座";
        else
          result = "金牛座";
        break;
      case 5:
        if (day <= 21)
          result = "金牛座";
        else
          result = "双子座";
        break;
      case 6:
        if (day <= 21)
          result = "双子座";
        else
          result = "巨蟹座";
        break;
      case 7:
        if (day <= 22)
          result = "巨蟹座";
        else
          result = "狮子座";
        break;
      case 8:
        if (day <= 22)
          result = "狮子座";
        else
          result = "处女座";
        break;
      case 9:
        if (day <= 22)
          result = "处女座";
        else
          result = "天秤座";
        break;
      case 10:
        if (day <= 23)
          result = "天秤座";
        else
          result = "天蝎座";
        break;
      case 11:
        if (day <= 22)
          result = "天蝎座";
        else
          result = "射手座";
        break;
      case 12:
        if (day <= 21)
          result = "射手座";
        else
          result = "摩羯座";
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
      return "";
    }
    return result;
  }

  public static boolean ifBirthDayOk(String birthDay)
  {
    boolean res = true;
    if (birthDay == null)
      return false;
    birthDay = birthDay.replace("-", "");
    if ((birthDay.trim().equals("")) || (birthDay.length() != 8)) {
      return false;
    }
    String year = birthDay.substring(0, 4);
    String mon = birthDay.substring(4, 6);
    String day = birthDay.substring(6, 8);
    try
    {
      int yInt = Integer.parseInt(year);
      int mInt = Integer.parseInt(mon);
      int dInt = Integer.parseInt(day);
      if ((yInt < 1915) || (yInt > 2015) || (mInt < 1) || (mInt > 12) || (dInt < 1) || (dInt > 31))
        res = false;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    return res;
  }

  public static int getAge(String birthDay) {
    int res = -1;
    if ((birthDay == null) || (birthDay.trim().equals("")) || (birthDay.equals("NULL"))) {
      return res;
    }
    birthDay = birthDay.replace("-", "");
    try
    {
      boolean flag = false;
      Date birDate = sdfBirth.parse(birthDay);
      Date today = new Date();

      int yearDelta = today.getYear() - birDate.getYear();
      int monthDelta = today.getMonth() - birDate.getMonth();
      int dayDelta = today.getDate() - birDate.getDate();

      if (monthDelta < 0)
        flag = true;
      else if ((monthDelta == 0) && 
        (dayDelta < 0)) {
        flag = true;
      }
      if (flag) {
        yearDelta--;
      }
      res = yearDelta;
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    return res;
  }

  public static String getBirthFromIdNo(String idNo) {
    String res = null;
    if ((idNo == null) || ((idNo.trim().length() != 15) && (idNo.trim().length() != 18)))
      return res;
    try
    {
      String birthday = "";
      if (idNo.trim().length() == 15)
        birthday = "19" + idNo.substring(6, 12);
      else if (idNo.trim().length() == 18) {
        birthday = idNo.substring(6, 14);
      }

      if (ifBirthDayOk(birthday))
        res = birthday.substring(0, 4) + "-" + birthday.substring(4, 6) + "-" + birthday.substring(6, 8);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return res;
  }

  public static String getGenderFromIdNo(String idNo) {
    String gender = "未知";
    if ((idNo == null) || (idNo.trim().equals("")) || (idNo.trim().length() < 15)) {
      System.out.println("idNo : " + idNo);
      return gender;
    }

    int genderInt = -1;
    char genderChar = 'x';
    try {
      if (idNo.length() == 15)
        genderChar = idNo.charAt(14);
      else if (idNo.length() == 18) {
        genderChar = idNo.charAt(16);
      }

      if (genderChar != 'x') {
        genderInt = Integer.parseInt(genderChar + "");
        genderInt %= 2;
      }

      if (genderInt == 0)
        gender = "女";
      else if (genderInt == 1)
        gender = "男";
    }
    catch (Exception e) {
      e.printStackTrace();
    }

    return gender;
  }

  public static void main(String[] args) {
    String id = "5137stGyN=qI0pY054";
    String id1 = "35018119810425257X";
    String id2 = "3101kdUKCBjQ003";
    String id3 = "33100219851206314";

    System.out.println(ifIdNoOk(id3) + "\t" + id1.substring(4, 15));
    System.out.println(getStarSign(getBirthFromIdNo(id3)) + "\t" + getGenderFromIdNo(id3) + "\t" + getBirthFromIdNo(id3) + "\t" + getAge(getBirthFromIdNo(id3)));
  }
}