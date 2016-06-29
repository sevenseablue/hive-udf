package util;

import java.util.List;

/**
 * Created by seven on 24/06/16.
 */
public class ListUtil {
    public static String toHive(List<FlightInfo> list1) {
        String delim2 = "\002";
        String delim3 = "\003";
        StringBuilder sb = new StringBuilder();

        for (FlightInfo flightInfo : list1) {
            sb.append(flightInfo.depCity).append(delim3)
                    .append(flightInfo.arrCity).append(delim3)
                    .append(flightInfo.depDate).append(delim3)
                    .append(flightInfo.arrDate).append(delim3)
                    .append(flightInfo.flightNo).append(delim3)
                    .append(flightInfo.cabinDesc).append(delim2);
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }
}
