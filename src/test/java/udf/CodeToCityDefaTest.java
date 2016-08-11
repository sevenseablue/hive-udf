package udf;

import util.CodeCity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.*;

/**
 * Created by seven on 06/07/16.
 */
public class CodeToCityDefaTest {
    final Pattern p = Pattern.compile("^[A-Z]{3}$");

    @org.junit.Test
    public void evaluate() throws Exception {
        String code="PEK";
        String defaultStr = "default";
        String res;

        if (code == null || code.equals("") || code.toLowerCase().equals("null")) {
            res = "";
        }
        else {
            Matcher m = p.matcher(code);
            if (m.find()){
                res = CodeCity.get(code, "");
            }
            else {
                res = defaultStr;
            }
        }
        System.out.println(res);
    }

}