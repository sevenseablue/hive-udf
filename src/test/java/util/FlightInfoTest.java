package util;

import junit.framework.TestCase;

import java.io.BufferedReader;
import java.io.FileReader;


/**
 * Created by seven on 23/06/16.
 */
public class FlightInfoTest extends TestCase {
    public void testGetInfos() throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("/work/java-projects/hive-udf/src/main/resources/etl_client_sample_3.txt"));
        String line = "";
        while ((line = br.readLine()) != null) {
            String[] spli = line.split("\t");
            System.out.println(spli[3]);
        }
    }

}