package util;

/**
 * Created by seven on 29/06/16.
 */
public class ArrayUtils {
    public static <T> T getEle(T[] tArr, int index) {
        if (tArr != null && index >= 0 && index < tArr.length) {
            return tArr[index];
        } else {
            return null;
        }
    }


    public static <T> String joinEle(T[] tArr, String index) {
        if(tArr==null || tArr.length==0){
            return "";
        }
        StringBuffer sb = new StringBuffer();
        sb.append(tArr[0]);
        for(int i=1; i<tArr.length; i++){
            sb.append(index).append(tArr[i]);
        }
        return sb.toString();
    }
}
