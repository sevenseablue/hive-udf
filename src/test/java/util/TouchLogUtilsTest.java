package util;

import junit.framework.TestCase;

/**
 * Created by seven on 29/06/16.
 */
public class TouchLogUtilsTest extends TestCase {
    public void testExtractCode() throws Exception {
        for(String s: new String[]{
               "HU7649", "HU7649/PN6277", "3U8953_3U8954", "AK168|KUL-NNG|2016-08-08", "AK168|KUL-NNG|2016-08-08;bK168|KUL-NNG|2016-09-08"
        }){
            for(String s1: TouchLogUtils.extractCode(s)) {
                System.out.println(s1);
            }
            System.out.println("####################");
        }
    }

}