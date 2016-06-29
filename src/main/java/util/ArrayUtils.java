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
}
