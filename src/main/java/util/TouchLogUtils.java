package util;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by seven on 29/06/16.
 */
public class TouchLogUtils {
    public static final String[] searchActions = new String[]{
            "h5/flight/flightlist", "h5/flight/flightlist/", "h5/flight/flightlistoneway3w", "h5/flight/flightlistroundway3w", "/h5/flight/flightlist", "h5/flight/undefined/h5/flight/flightlist"
    };

    public static final String[] detailActions = new String[]{
            "h5/flight/flightDetail", "inter/iflightDetail", "h5/flight/iflightDetail", "/h5/flight/flightDetail"
    };

    public static final String[] bookingActions = new String[]{
            "h5/flight/flightAV", "h5/flight/flightAVRound", "h5/flight/iflightAV"
    };

    public static final HashMap<String, String> actionMap = new HashMap<String, String>();

    static {
        for (String s : searchActions) {
            actionMap.put(s, "list");
        }
        for (String s : detailActions) {
            actionMap.put(s, "detail");
        }
        for (String s : bookingActions) {
            actionMap.put(s, "booking");
        }
    }

    public static String getPage(String action) {
        if (actionMap.containsKey(action)) {
            return actionMap.get(action);
        } else {
            return "";
        }
    }


    public static final Pattern Patt1 = Pattern.compile("([A-Z0-9]{4,6})");
    public static final Pattern Patt2 = Pattern.compile("([A-Z0-9]{4,6})(/|_)([A-Z0-9]{4,6})");
    public static final Pattern Patt3 = Pattern.compile("([A-Z0-9]{4,6})\\|([A-Z]{3})-([A-Z]{3})\\|(\\d{4}-\\d{2}-\\d{2})");
    public static final Pattern Patt4 = Pattern.compile("([A-Z0-9]{4,6})\\|([A-Z]{3})-([A-Z]{3})\\|(\\d{4}-\\d{2}-\\d{2});([A-Z0-9]{4,6})\\|([A-Z]{3})-([A-Z]{3})\\|(\\d{4}-\\d{2}-\\d{2})");

    public static String[] extractCode(String code) {
        code = code.toUpperCase();
        String[] result;
        if (code.contains(";")) {
            result = new String[9];
            result[0] = "8";
            Matcher m = Patt4.matcher(code);
            if (m.find()) {
                for (int i = 1; i <= 8; i++) {
                    result[i] = m.group(i);
                }
            }
        } else if (code.contains("|")) {
            result = new String[5];
            result[0] = "4";
            Matcher m = Patt3.matcher(code);
            if (m.find()) {
                for (int i = 1; i <= 4; i++) {
                    result[i] = m.group(i);
                }
            }
        } else if (code.contains("_") || code.contains("/")) {
            result = new String[3];
            result[0] = "2";
            Matcher m = Patt2.matcher(code);
            if (m.find()) {
                result[1] = m.group(1);
                result[2] = m.group(3);
            }
        } else {
            result = new String[2];
            result[0] = "1";
            Matcher m = Patt1.matcher(code);
            if (m.find()) {
                for (int i = 1; i <= 1; i++) {
                    result[i] = m.group(i);
                }
            } else {
                result[1] = code;
            }
        }
        return result;
    }

    public static String flightTypeCls(String flightType) {
        if (flightType != null) {
            flightType = flightType.toLowerCase();
            if (flightType.contains("one")) {
                flightType = "oneWay";
            } else if (flightType.contains("round")) {
                flightType = "roundWay";
            } else if (flightType.contains("mix")) {
                flightType = "mixWay";
            } else {
                flightType = "";
            }
        } else {
            flightType = "";
        }
        return flightType;
    }
}
