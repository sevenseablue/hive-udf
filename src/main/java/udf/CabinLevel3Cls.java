package udf;

import org.apache.hadoop.hive.ql.exec.UDF;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;

public class CabinLevel3Cls extends UDF {

    public String evaluate(String cabin) {
        String res = "";
        if(cabin == null || cabin.equals("") || cabin.toLowerCase().equals("null")){
            res = "";
        }
        else if (cabin.contains("头等")){
            res="头等舱";
        }
        else if(cabin.contains("商务")){
            res="商务舱";
        }
        else{
            res = "经济舱";
        }
        return res;
    }
}