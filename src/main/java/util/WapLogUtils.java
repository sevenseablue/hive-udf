package util;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.collections.map.CaseInsensitiveMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by seven.wang on 2015/12/2.
 */
public class WapLogUtils {

    public static boolean allEqualsZero(int[] iArr) {
        for (int i : iArr) {
            if (i != 0) {
                return false;
            }
        }
        return true;
    }

    // [{x}, {y}] to list [x, y]
    // [ startflag, ] endflag, { startsplitter, } endsplitter, []{} escape, [{x}, {y}] log
    public static List<String> sectionToList(char startSplitter, char endSplitter, String startFlag, String endFlag, char[][] escape, String log) {
        int s = log.indexOf(startFlag) + 1;
        int e = log.lastIndexOf(endFlag);
        if (s < 0 || s > e) {
            return Lists.newArrayList();
        }

        char[] arr = log.toCharArray();
        s = 0;

        HashMap<Character, Integer> classMap = Maps.newHashMap();
        HashMap<Character, Integer> operMap = Maps.newHashMap();
        for (int i = 0; i < escape.length; i++) {
            for (int j = 0; j < 2; j++) {
                classMap.put(escape[i][j], i);
                operMap.put(escape[i][j], 1 - j * 2);
            }
        }

        ArrayList<String> eleList = new ArrayList<String>();
        int[] flagInts = new int[escape.length];
        boolean matching = false;
        for (int i = 0; i < arr.length; i++) {
            char ch = arr[i];
            if (matching == false && ch == startSplitter) {
                matching = true;
                s = i + 1;
            }
            if (matching == true && classMap.containsKey(ch)) {
                flagInts[classMap.get(ch)] += operMap.get(ch);
            }
            if (matching == true && (arr[i] == endSplitter) && allEqualsZero(flagInts)) {
                eleList.add(log.substring(s, i));
                s = i + 1;
                matching = false;
            }
        }

        return eleList;
    }


    public static List<String> sectionToList(String startFlag, String endFlag, String log) {
        char startSplitter = '{';
        char endSplitter = '}';
        char[][] escape = new char[][]{{'[', ']'}, {'{', '}'}};
        return sectionToList(startSplitter, endSplitter, startFlag, endFlag, escape, log);
    }

    public static List<String> sectionToList(String log) {
        String startFlag = "[";
        String endFlag = "]";
        return sectionToList(startFlag, endFlag, log);
    }

    public static List<String> splitToList(String log) {
        return splitToList('&', log);
    }


    public static List<String> splitToList(char splitter, String log) {
        char[] arr = log.toCharArray();
        int s = 0;

        HashMap<Character, Integer> classMap = Maps.newHashMap();
        classMap.put('[', 0);
        classMap.put(']', 0);
        classMap.put('{', 1);
        classMap.put('}', 1);
        HashMap<Character, Integer> operMap = Maps.newHashMap();
        operMap.put('[', 1);
        operMap.put(']', -1);
        operMap.put('{', 1);
        operMap.put('}', -1);

        ArrayList<String> resultList = new ArrayList<String>();
        int[] flagInts = new int[2];
        for (int i = 0; i < arr.length; i++) {
            char ch = arr[i];
            if (classMap.containsKey(ch)) {
                flagInts[classMap.get(ch)] += operMap.get(ch);
            }
            if ((arr[i] == splitter) && flagInts[0] == 0 && flagInts[1] == 0) {
                resultList.add(log.substring(s, i));
                s = i + 1;
            }
        }
        if (s < arr.length) {
            resultList.add(log.substring(s));
        }

        return resultList;
    }

    private static void wapLogMapPut(Map<String, String> map, String kv) {
        int flagInd = kv.indexOf("=");
        if (flagInd >= 0) {
            String key = kv.substring(0, flagInd).trim();
            String value = kv.substring(flagInd + 1);
            boolean contains = map.containsKey(key);
            if (!(contains && map.get(key).length() > value.length())) {
                map.put(key, value);
            }
        } else {

            map.put(kv.trim(), "");
        }
    }

    public static Map<String, String> splitToMap(char splitter, String log) {
        char[] arr = log.toCharArray();
        int s = 0;

        HashMap<Character, Integer> classMap = Maps.newHashMap();
        classMap.put('[', 0);
        classMap.put(']', 0);
        classMap.put('{', 1);
        classMap.put('}', 1);
        HashMap<Character, Integer> operMap = Maps.newHashMap();
        operMap.put('[', 1);
        operMap.put(']', -1);
        operMap.put('{', 1);
        operMap.put('}', -1);

        Map<String, String> map = new CaseInsensitiveHM();
        int[] flagInts = new int[2];
        for (int i = 0; i < arr.length; i++) {
            char ch = arr[i];
            if (classMap.containsKey(ch)) {
                flagInts[classMap.get(ch)] += operMap.get(ch);
            }
            if ((arr[i] == splitter) && flagInts[0] == 0 && flagInts[1] == 0) {
                wapLogMapPut(map, StringUtil.nullToEmpty(log.substring(s, i)));
                s = i + 1;
            }
        }
        if (s < arr.length) {
            wapLogMapPut(map, StringUtil.nullToEmpty(log.substring(s)));
        }

        return map;
    }

    public static Map<String, String> splitToMap(String log) {
        return splitToMap('&', log);
    }


//    public static String mapGetOrDefault(Map<String, String> map, String key, String defaultStr) {
//        if (map.containsKey(key)) {
//            return map.get(key);
//        } else {
//            return defaultStr;
//        }
//    }

    public static String mapGetOrDefault(Map<String, String> map, String key) {
        return MapUtils.getOrDef(map, key, "");
    }

    public static final String[] searchActions = new String[]{
            "QueryFMixwayList", "QueryFRoundwayList", "QueryFlightLowPrice", "QueryInterFlightLowPrice", "QueryFMultiWayList", "QueryFuzzywayList", "flightBargainIndex"
    };

    public static final String[] detailActions = new String[]{
            "QueryOTAList", "QueryFOnewayDetail", "QueryFRoundwayDetail", "QueryFMorewayDetail", "interfmwdetail", "interfowdetail", "intermixfrwdetail", "interfrwdetail", "QueryFMultiWayDetail"
    };

    public static final String[] bookingActions = new String[]{
            "SrvTtsAV", "SrvTtsAV4Package", "interTtsAv", "DomesticFlightOrderBookingController", "f_interTtsAv4OneBill", "interTtsAvNew", "interTtsAv4OneBillNew"
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

    public static String getObject(String log, String[] route, char[] type) {
        List<String> list1 = new ArrayList<String>();
        list1.add(log);
        for (int i = 0; i < type.length; i++) {
            List<String> list2 = new ArrayList<String>();
            for (int j = 0; j < list1.size(); j++) {
                String str1 = list1.get(j);
                Map<String, String> map1 = WapLogUtils.splitToMap(str1);
                String str2 = MapUtils.getOrDef(map1, route[i], "");
                if (str2 == null || str2.equals("")) {
                    break;
                }
                if (type[i] == 'o') {
                    list2.add(str2);
                } else if (type[i] == 'l') {
                    list2.addAll(WapLogUtils.sectionToList(str2));
                }
            }
            list1 = list2;
            if (list1.size() == 0) {
                break;
            }
        }
        if (list1.size() > 0) {
            return list1.get(0);
        } else {
            return "";
        }
    }

    public static List<String> getObjects(String log, String[] route, char[] type) {
        List<String> list1 = new ArrayList<String>();
        list1.add(log);
        for (int i = 0; i < type.length; i++) {
            List<String> list2 = new ArrayList<String>();
            for (int j = 0; j < list1.size(); j++) {
                String str1 = list1.get(j);
                Map<String, String> map1 = WapLogUtils.splitToMap(str1);
                String str2 = MapUtils.getOrDef(map1, route[i], "");
                if (str2 == null || str2.equals("")) {
                    break;
                }
                if (type[i] == 'o') {
                    list2.add(str2);
                } else if (type[i] == 'l') {
                    list2.addAll(WapLogUtils.sectionToList(str2));
                }
            }
            list1 = list2;
            if (list1.size() == 0) {
                break;
            }
        }

        return list1;
    }

    public static List<String> getObjects(String log, String[] route, char[] type, String[] on, String[] off) {
        List<String> list1 = new ArrayList<String>();
        list1.add(log);
        for (int i = 0; i < type.length; i++) {
            List<String> list2 = new ArrayList<String>();
            for (int j = 0; j < list1.size(); j++) {
                String str1 = list1.get(j);
                Map<String, String> map1 = WapLogUtils.splitToMap(str1);
                String str2 = MapUtils.getOrDef(map1, route[i], "");
                if (str2 == null || str2.equals("")) {
                    break;
                }
                if (type[i] == 'o') {
                    list2.add(str2);
                } else if (type[i] == 'l') {
                    list2.addAll(WapLogUtils.sectionToList(str2));
                }
            }
            list1 = new ArrayList<String>();

            for (String s : list2) {
                if (on[i].length() == 0 && off[i].length() == 0) {
                    list1.add(s);
                } else if (on[i].length() == 0 && !s.matches(off[i])) {
                    list1.add(s);
                } else if (off[i].length() == 0 && s.matches(on[i])) {
                    list1.add(s);
                } else if (on[i].length() != 0 && off[i].length() != 0 && !s.matches(off[i]) && s.matches(on[i])) {
                    list1.add(s);
                }
            }
            if (list1.size() == 0) {
                break;
            }
        }

        return list1;
    }

    public static void test1() {
        String[] logs = new String[]{
                "a=1&b=2&c=",
                "a=1&b=2&c=[d=4&e=5]&f=6",
                "a=1&b=2&c=[{d=4&e=5},{d=4&e=5&i=[{i1=1&i2=2},{i1=3&i2=4}]},{heihei},ccc]&f=6",
        };
        System.out.println("splitToList");
        for (String log : logs) {
            System.out.println(splitToList(log));
        }
        System.out.println("splitToMap");
        for (String log : logs) {
            System.out.println(splitToMap(log));
        }
        System.out.println("sectionToList");
        // only can be log like "[{} {}]" section to list
        for (String log : logs) {
            System.out.println(sectionToList(log));
        }
    }

    private static boolean notValid(String str) {
        if (str == null || str.equals("") || str.toLowerCase().equals("null")) {
            return true;
        } else {
            return false;
        }
    }

    public static void update(ArrayList<FlightInfo> fInfos) {
        for (FlightInfo flightInfo : fInfos) {
            if (notValid(flightInfo.depCity) && !notValid(flightInfo.depCode)) {
                flightInfo.depCity = CodeCity.get(flightInfo.depCode, "");
            }
            if (notValid(flightInfo.arrCity) && !notValid(flightInfo.arrCode)) {
                flightInfo.arrCity = CodeCity.get(flightInfo.arrCode, "");
            }

            if (notValid(flightInfo.companyCode) && !notValid(flightInfo.flightNo)) {
                flightInfo.companyCode = StringUtil.sub(flightInfo.flightNo, 0, 2);
            }
            if (notValid(flightInfo.cabinDesc) && !notValid(flightInfo.cabin) && !notValid(flightInfo.companyCode)) {
                flightInfo.cabinDesc = CabinLevel.get(flightInfo.companyCode, flightInfo.cabin);
            }
        }
    }

    public static void main(String[] args) {
        test1();
    }
}