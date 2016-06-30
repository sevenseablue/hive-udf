package util;

import junit.framework.TestCase;

/**
 * Created by seven on 24/06/16.
 */
public class StringUtilTest extends TestCase {
    public void testSub() throws Exception {
        System.out.println(StringUtil.sub("abc", -1, 2));
        System.out.println(StringUtil.sub("abc", 0, 2));
        System.out.println(StringUtil.sub("abc", 0, 3));
        System.out.println(StringUtil.sub("abc", 0, 4));
        System.out.println(StringUtil.sub("abc", 2, 3));
        System.out.println(StringUtil.sub("abc", 2, 4));
        System.out.println(StringUtil.sub("abc", 3, 4));
        System.out.println(StringUtil.sub("", 3, 4));
        System.out.println(StringUtil.sub(null, 3, 4));
    }

    public void testSplitTest() throws Exception {
        System.out.println();
        StringUtil.splitTest("", "/");
        StringUtil.splitTest("/", "/");
        StringUtil.splitTest("a/", "/");
        StringUtil.splitTest("a/b", "/");

    }

    public void testSubString(){
        System.out.println("abc?".substring("abc?".indexOf("?")+1));
    }

}