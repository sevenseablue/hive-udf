package util;

import junit.framework.TestCase;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * Created by seven on 24/06/16.
 */
public class FlightLogTest extends TestCase {
    public void testGetLogs() throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("/work/java-projects/hive-udf/src/main/resources/etl_client_sample_3.txt"));
//        BufferedReader br = new BufferedReader(new FileReader("/work/java-projects/hive-udf/src/main/resources/etl_client_sample_error.txt.2"));
        String line = "";
        while((line = br.readLine())!= null){
            String[] spli = line.split("\t");
//            System.out.println(spli[0]); // 3
            System.out.println(spli[3]); // 3
//            System.out.println(FlightLog.getLogs(spli[0], spli[1]).toHive()); //3, 16
            System.out.println(FlightLog.getLogs(spli[3], spli[16]).toHive()); //3, 16
        }

    }

}