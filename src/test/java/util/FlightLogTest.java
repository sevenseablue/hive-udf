package util;

import junit.framework.TestCase;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Collections;
import java.util.List;

/**
 * Created by seven on 24/06/16.
 */
public class FlightLogTest extends TestCase {
    public void testGetLogs() throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("/work/java-projects/hive-udf/src/main/resources/etl_client_sample_3.txt"));
        String line = "";
        BufferedReader brStr = new BufferedReader(new FileReader("/work/java-projects/hive-udf/src/main/resources/etl_client_sample_3.txt.check.String"));
        BufferedReader brHive = new BufferedReader(new FileReader("/work/java-projects/hive-udf/src/main/resources/etl_client_sample_3.txt.check.hive"));
//        BufferedWriter bw = new BufferedWriter(new FileWriter("/work/java-projects/hive-udf/src/main/resources/etl_client_sample_3.txt.check.hive"));
        while ((line = br.readLine()) != null) {
            String[] spli = line.split("\t");
//            System.out.println(spli[0]); // 3
            System.out.println(spli[3]); // 3
            System.out.println(FlightLog.getLogs(spli[3], spli[16]).toHive()); //3, 16
            System.out.println(FlightLog.getLogs(spli[3], spli[16]).toString()); //3, 16
//            bw.write(FlightLog.getLogs(spli[0], spli[3]).toHive()+"\001" + spli[0] +"\n");
//            bw.write(FlightLog.getLogs(spli[3], spli[16]).toString()+"\n");
            assertEquals(FlightLog.getLogs(spli[3], spli[16]).toHive(), brHive.readLine());
            assertEquals(FlightLog.getLogs(spli[3], spli[16]).toString(), brStr.readLine());
            System.out.println(WapLogUtils.getPage(spli[3]));

//            List<String> al = WapLogUtils.splitToList(spli[16]);
//            Collections.sort(al);
//            for (String s : al) {
//                String slower = s.toLowerCase();
////                System.out.println(s);
//                    for (String sFlag : new String[]{
//                            "city", "begin", "end", "no", "ca", "time", "date", "start", "end", "dep", "arr", "info", "city", "name", "type", "air", "flight", "short"
//                    }) {
//                        if (slower.contains(sFlag)) {
//                            System.out.println(s);
//                            break;
//                        }
//                    }
//            }
            System.out.println("##################");
        }
//        bw.close();
        br.close();
        brHive.close();
        brStr.close();
    }

}