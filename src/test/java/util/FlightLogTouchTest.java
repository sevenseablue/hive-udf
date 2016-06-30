package util;

import junit.framework.TestCase;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;

/**
 * Created by seven on 28/06/16.
 */
public class FlightLogTouchTest extends TestCase {
    public void testLogs() {
        BufferedReader br;
        try {
            br = new BufferedReader(new InputStreamReader(FlightLogTouchTest.class.getClassLoader().getResourceAsStream("etl_touch_sample.txt")));
            String line = "";
            int lineNum = 1;
            while ((line = br.readLine()) != null) {
                String[] spli = line.split("\t");
                System.out.println(lineNum);
                System.out.println(spli[0]);
                String action = spli[0];
                String url = spli[1];
                String post = spli[2];
                String log;
                if (url.contains("?")) {
                    log = url.substring(url.indexOf('?') + 1) + "&" + post;
                } else {
                    log = post;
                }
                System.out.println(TouchLogUtils.getPage(action));

                List<String> al = WapLogUtils.splitToList(log);
                Collections.sort(al);
                for (String s : al) {
                    String slower = s.toLowerCase();
                    System.out.println(s);
//                    for (String sFlag : new String[]{
//                            "city", "begin", "end", "no", "ca", "time", "date", "start", "end", "dep", "arr", "info", "city", "name", "type", "air", "flight", "short"
//                    }) {
//                        if (slower.contains(sFlag)) {
//                            System.out.println(s);
//                            break;
//                        }
//                    }
                }
                System.out.println("##################");
                lineNum += 1;
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void testGetLogs() throws Exception {
        BufferedReader br;
        try {
            br = new BufferedReader(new InputStreamReader(FlightLogTouchTest.class.getClassLoader().getResourceAsStream("etl_touch_sample.txt.3")));
            String line = "";
            int lineNum = 1;
            while ((line = br.readLine()) != null) {
                String[] spli = line.split("\t");
                System.out.println(lineNum);

                String action = spli[7];
                String url = spli[14];
                String post = spli[15];
                System.out.println(action);
                System.out.println(TouchLogUtils.getPage(action));
                System.out.println(FlightLogTouch.getLogs(action, url, post).toString());

                String log;
                if (url.contains("?")) {
                    log = url.substring(url.indexOf('?') + 1) + "&" + post;
                } else {
                    log = post;
                }
                List<String> al = WapLogUtils.splitToList(log);
                Collections.sort(al);
                for (String s : al) {
                    System.out.println(s);
                }

                System.out.println("##################");
                lineNum += 1;
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}