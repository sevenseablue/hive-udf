package util;

/**
 * Created by seven on 23/06/16.
 */
public class StringUtil {
    public static String sub(String originString, char cs, char ce) {
        return sub(originString, cs, ce, true);
    }

    public static String sub(String originString, char cs, char ce, boolean ceLast) {
        int s = originString.indexOf(cs) + 1;
        int e = -1;

        if (ceLast) {
            e = originString.lastIndexOf(ce);
        } else {
            e = originString.indexOf(ce, s);
        }
        if (s > 0 && e >= s) {
            return originString.substring(s, e);
        } else {
            return "";
        }
    }

    public static String sub(String originString, int s, char ce, boolean ceLast) {
        int e = -1;

        if (ceLast) {
            e = originString.lastIndexOf(ce);
        } else {
            e = originString.indexOf(ce, s);
        }
        if (s >= 0 && e >= s) {
            return originString.substring(s, e);
        } else {
            return "";
        }
    }

    public static String sub(String originString, char cs, int e, boolean ceLast) {
        int s = originString.indexOf(cs) + 1;

        if (s > 0 && e >= s) {
            return originString.substring(s, e);
        } else {
            return "";
        }
    }

    public static String subLeft(String originString, char ce, boolean ceLast) {
        return sub(originString, 0, ce, ceLast);
    }

    public static String sub(String originString, String cs, String ce) {
        return sub(originString, cs, ce, true);
    }

    public static String sub(String originString, String cs, String ce, boolean ceLast) {
        int s = originString.indexOf(cs) + cs.length();
        int e = -1;

        if (ceLast) {
            e = originString.lastIndexOf(ce);
        } else {
            e = originString.indexOf(ce, s);
        }
        if (s > 0 && e >= s) {
            return originString.substring(s, e);
        } else {
            return "";
        }
    }

    public static String sub(String originString, int s, int e) {
        if (originString == null || originString.equals("")) {
            return "";
        } else {
            int sLen = originString.length();
            if (s >= 0 && e >= s) {
                if (sLen >= e) {
                    return originString.substring(s, e);
                } else if (sLen > s) {
                    return originString.substring(s);
                } else {
                    return "";
                }
            } else {
                return "";
            }
        }

    }

    public static String nullToEmpty(String string) {
        if (string == null || string.equals("null")) {
            return "";
        } else {
            return string;
        }
    }

    public static void splitTest(String s, String splitter) {
        System.out.println(s.split(splitter).length);
    }

    public static boolean isNENs(String str) {
        if (str == null || str.equals("") || str.toLowerCase().equals("null")) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {

    }
}
