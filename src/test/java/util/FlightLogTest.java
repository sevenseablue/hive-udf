package util;

import junit.framework.TestCase;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * Created by seven on 24/06/16.
 */
public class FlightLogTest extends TestCase {
    public void testGetLogs() throws Exception {
//        BufferedReader br = new BufferedReader(new FileReader("/work/java-projects/hive-udf/src/main/resources/etl_client_sample_3.txt"));
        BufferedReader br = new BufferedReader(new FileReader("/work/java-projects/hive-udf/src/main/resources/params.check.txt.2"));
        String line = "";
        BufferedWriter bw = new BufferedWriter(new FileWriter("/work/java-projects/hive-udf/src/main/resources/params.check.txt.2.out"));
        while((line = br.readLine())!= null){
            String[] spli = line.split("\t");
//            System.out.println(spli[0]); // 3
//            System.out.println(spli[3]); // 3
//            System.out.println(FlightLog.getLogs(spli[0], spli[3]).toHive()); //3, 16
//            System.out.println(FlightLog.getLogs(spli[3], spli[16]).toHive()); //3, 16
            bw.write(FlightLog.getLogs(spli[0], spli[3]).toHive()+"\001" + spli[0] +"\n");
        }
        bw.close();
        br.close();

    }

}