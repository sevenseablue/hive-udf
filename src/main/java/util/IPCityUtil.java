package util;

import org.apache.commons.lang3.Range;
import org.apache.hadoop.hive.ql.exec.UDFArgumentException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.UnknownHostException;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by seven on 21/06/16.
 */
public class IPCityUtil {
    private static Map<Range<Integer>, String> ipRangeDepMap;

    static
    {
        ipRangeDepMap = new TreeMap<Range<Integer>, String>(new Comparator<Range<Integer>>() {
            public int compare(Range<Integer> r1, Range<Integer> r2) {
                if ((r1 == null) || (r2 == null)) {
                    throw new NullPointerException();
                }
                if ((r1.containsRange(r2)) || (r2.containsRange(r1)) || (r1.equals(r2))) {
                    System.out.println(IPV4Util.intToIp(r1.getMinimum())+ IPV4Util.intToIp(r1.getMaximum()));
                    System.out.println(IPV4Util.intToIp(r2.getMinimum())+ IPV4Util.intToIp(r2.getMaximum()));
                    return 0;
                }

                if (r1.isAfterRange(r2)) {
                    return 1;
                }
                return -1;
            }
        });

        initMap();
    }


    private static void initMap()
    {
        BufferedReader in = null;
        try
        {
            in = new BufferedReader(new InputStreamReader(IPCityUtil.class.getClassLoader().getResourceAsStream("ip_departure.txt")));
            String line;
            while ((line = in.readLine()) != null) {
                String[] ipDep = line.split(",");
                if ((ipDep != null) && (ipDep.length == 2)) {
                    ipRangeDepMap.put(IPV4Util.getIPRange(ipDep[0]), ipDep[1]);
                }
            }
            System.out.println("Mapsize:" + ipRangeDepMap.size());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != in)
                    in.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String getInfos(String ip){
        try {
            return ipRangeDepMap.get(IPV4Util.singleIpToRange(ip));
        } catch (UnknownHostException e) {
            return "/";
        }
    }


    public static void main(String[] args) throws UDFArgumentException, UnknownHostException {
        System.out.println(ipRangeDepMap.get(IPV4Util.singleIpToRange("224.0.0.1")));
        System.out.println(ipRangeDepMap.get(IPV4Util.singleIpToRange("10.85.0.1")));
        System.out.println(ipRangeDepMap.get(IPV4Util.singleIpToRange("10.84.0.1")));
    }


}
