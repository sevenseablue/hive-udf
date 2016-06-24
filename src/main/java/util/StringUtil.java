package util;

/**
 * Created by seven on 23/06/16.
 */
public class StringUtil {
    public static String sub(String originString, char cs, char ce){
        return sub(originString, cs, ce, true);
    }

    public static String sub(String originString, char cs, char ce, boolean ceLast){
        int s = originString.indexOf(cs)+1;
        int e = -1;

        if(ceLast){
            e = originString.lastIndexOf(ce);
        }
        else{
            e = originString.indexOf(ce, s);
        }
        if(s>=0 && e>=s){
            return originString.substring(s, e);
        }
        else{
            return "";
        }
    }

    public static String sub(String originString, int s, char ce, boolean ceLast){
        int e = -1;

        if(ceLast){
            e = originString.lastIndexOf(ce);
        }
        else{
            e = originString.indexOf(ce, s);
        }
        if(s>=0 && e>=s){
            return originString.substring(s, e);
        }
        else{
            return "";
        }
    }

    public static String sub(String originString, char cs, int e, boolean ceLast){
        int s = originString.indexOf(cs)+1;

        if(s>=0 && e>=s){
            return originString.substring(s, e);
        }
        else{
            return "";
        }
    }

    public static String subLeft(String originString, char ce, boolean ceLast){
        return sub(originString, 0, ce, ceLast);
    }

    public static String sub(String originString, String cs, String ce){
        return sub(originString, cs, ce, true);
    }

    public static String sub(String originString, String cs, String ce, boolean ceLast){
        int s = originString.indexOf(cs)+cs.length();
        int e = -1;

        if(ceLast){
            e = originString.lastIndexOf(ce);
        }
        else{
            e = originString.indexOf(ce, s);
        }
        if(s>=0 && e>=s){
            return originString.substring(s, e);
        }
        else{
            return "";
        }
    }
}
