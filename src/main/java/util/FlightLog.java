package util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by seven on 24/06/16.
 */
public class FlightLog {
    String latLog;
    String lgtLog;
    String ma;
    String idfa;
    String osVersion;
    List<FlightInfo> fInfos;

    public FlightLog() {
    }

    public String getLatLog() {
        return latLog;
    }

    public void setLatLog(String latLog) {
        this.latLog = latLog;
    }

    public String getLgtLog() {
        return lgtLog;
    }

    public void setLgtLog(String lgtLog) {
        this.lgtLog = lgtLog;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getIdfa() {
        return idfa;
    }

    public void setIdfa(String idfa) {
        this.idfa = idfa;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    public List<FlightInfo> getfInfos() {
        return fInfos;
    }

    public void setfInfos(List<FlightInfo> fInfos) {
        this.fInfos = fInfos;
    }

    @Override
    public String toString() {
        return "FlightLog{" +
                "idfa='" + idfa + '\'' +
                ", latLog='" + latLog + '\'' +
                ", lgtLog='" + lgtLog + '\'' +
                ", ma='" + ma + '\'' +
                ", osVersion='" + osVersion + '\'' +
                ", fInfos=" + fInfos +
                '}';
    }

    public String toHive() {
        String delim1 = "\001";
        StringBuilder sb  = new StringBuilder();
        sb.append(idfa).append(delim1)
                .append(latLog).append(delim1)
                .append(lgtLog).append(delim1)
                .append(ma).append(delim1)
                .append(osVersion).append(delim1)
                .append(FlightInfo.listToHive(fInfos));
        
        return sb.toString();
    }

    public static FlightLog getLogs(String action, String log){
        Action action1 = ActionCls.getAction(action);
        Map<String, String> map1 = WapLogUtils.splitToMap(log);
        FlightLog flightLog = new FlightLog();
        flightLog.setLatLog(MapUtils.GetOrDef(map1, "lgtLog", ""));
        flightLog.setLgtLog(MapUtils.GetOrDef(map1, "latLog", ""));
        flightLog.setMa(MapUtils.GetOrDef(map1, "ma", ""));
        flightLog.setOsVersion(MapUtils.GetOrDef(map1, "osVersion", ""));
        flightLog.setIdfa(MapUtils.GetOrDef(map1, "idfa", ""));


        ArrayList<FlightInfo> fInfos = new ArrayList<FlightInfo>();
        FlightInfo flightInfo;
        ArrayList<String> list1 = new ArrayList<String>();
        list1.add(log);
        switch (action1) {
            case OTHER:
                break;
            case QUERYFMIXWAYLIST:
                flightInfo = new FlightInfo();
                flightInfo.setDepCity(MapUtils.GetOrDef(map1, "begin", ""));
                flightInfo.setArrCity(MapUtils.GetOrDef(map1, "end", ""));
                flightInfo.setDepDate(MapUtils.GetOrDef(map1, "date", ""));
                fInfos.add(flightInfo);
                break;
            case QUERYFROUNDWAYLIST:
                flightInfo = new FlightInfo();
                flightInfo.setDepCity(MapUtils.GetOrDef(map1, "begin", ""));
                flightInfo.setArrCity(MapUtils.GetOrDef(map1, "end", ""));
                flightInfo.setDepDate(MapUtils.GetOrDef(map1, "goDate", ""));
                fInfos.add(flightInfo);
                flightInfo = new FlightInfo();
                flightInfo.setDepCity(MapUtils.GetOrDef(map1, "end", ""));
                flightInfo.setArrCity(MapUtils.GetOrDef(map1, "begin", ""));
                flightInfo.setDepDate(MapUtils.GetOrDef(map1, "backDate", ""));
                fInfos.add(flightInfo);
                break;
            case QUERYOTALIST:
                List<String> list_101 = WapLogUtils.getObjects(log, new String[]{"routes", "fInfos", "goInfos"}, new char[]{'l', 'l', 'l'}, new String[]{"", "", ""}, new String[]{"title=(往返特价包|多程特价包|中转特价包|主飞航班)", "", ""});
                for (String string : list_101) {
                    Map<String, String> map_tmp = WapLogUtils.splitToMap(string);
                    flightInfo = new FlightInfo();
                    flightInfo.setDepCity(MapUtils.GetOrDef(map_tmp, "depCity", ""));
                    flightInfo.setArrCity(MapUtils.GetOrDef(map_tmp, "arrCity", ""));
                    flightInfo.setDepCode(MapUtils.GetOrDef(map_tmp, "depCode", ""));
                    flightInfo.setArrCode(MapUtils.GetOrDef(map_tmp, "arrCode", ""));
                    flightInfo.setDepDate(MapUtils.GetOrDef(map_tmp, "depDate", ""));
                    flightInfo.setArrDate(MapUtils.GetOrDef(map_tmp, "arrDate", ""));
                    flightInfo.setDepTime(MapUtils.GetOrDef(map_tmp, "depTime", ""));
                    flightInfo.setArrTime(MapUtils.GetOrDef(map_tmp, "arrTime", ""));
                    flightInfo.setFlightNo(MapUtils.GetOrDef(map_tmp, "flightNo", ""));
                    flightInfo.setCompanyCode(MapUtils.GetOrDef(map_tmp, "airlineCode", ""));
                    flightInfo.setPlaneType(MapUtils.GetOrDef(map_tmp, "planeType", ""));
                    fInfos.add(flightInfo);
                }
                break;
            case SRVTTSAV:
                flightInfo = new FlightInfo();
                flightInfo.setDepCity(MapUtils.GetOrDef(map1, "deptCity", ""));
                flightInfo.setArrCity(MapUtils.GetOrDef(map1, "arriCity", ""));
                flightInfo.setDepCode(MapUtils.GetOrDef(map1, "depcode", ""));
                flightInfo.setArrCode(MapUtils.GetOrDef(map1, "arrcode", ""));
                flightInfo.setDepDate(MapUtils.GetOrDef(map1, "deptDate", ""));
                flightInfo.setArrDate(MapUtils.GetOrDef(map1, "arriDate", ""));
                flightInfo.setDepTime(MapUtils.GetOrDef(map1, "depTime", ""));
                flightInfo.setArrTime(MapUtils.GetOrDef(map1, "arrTime", ""));
                flightInfo.setFlightNo(MapUtils.GetOrDef(map1, "airline", ""));
                flightInfo.setCompanyCode(MapUtils.GetOrDef(map1, "fcode", ""));
                flightInfo.setCabin(MapUtils.GetOrDef(map1, "cabin", ""));
                flightInfo.setCabinDesc(MapUtils.GetOrDef(map1, "cabin_desc", ""));
                flightInfo.setPlaneType(MapUtils.GetOrDef(map1, "planetype", ""));
                fInfos.add(flightInfo);
                break;
            case INTERTTSAVNEW:
                String cabin = WapLogUtils.getObject(log, new String[]{"cabin"}, new char[]{'o'});
                list_101 = WapLogUtils.getObjects(log, new String[]{"flightInfo", "goInfos"}, new char[]{'l', 'l'});
                List<String> list_102 = WapLogUtils.getObjects(log, new String[]{"flightInfo", "backInfos"}, new char[]{'l', 'l'});
                list_101.addAll(list_102);
                for (String string : list_101) {
                    Map<String, String> map_tmp = WapLogUtils.splitToMap(string);
                    flightInfo = new FlightInfo();
                    flightInfo.setDepCity(MapUtils.GetOrDef(map_tmp, "depCity", ""));
                    flightInfo.setArrCity(MapUtils.GetOrDef(map_tmp, "arrCity", ""));
                    flightInfo.setDepCode(MapUtils.GetOrDef(map_tmp, "depCode", ""));
                    flightInfo.setArrCode(MapUtils.GetOrDef(map_tmp, "arrCode", ""));
                    flightInfo.setDepDate(MapUtils.GetOrDef(map_tmp, "depDate", ""));
                    flightInfo.setArrDate(MapUtils.GetOrDef(map_tmp, "arrDate", ""));
                    flightInfo.setDepTime(MapUtils.GetOrDef(map_tmp, "depTime", ""));
                    flightInfo.setArrTime(MapUtils.GetOrDef(map_tmp, "arrTime", ""));
                    flightInfo.setFlightNo(MapUtils.GetOrDef(map_tmp, "flightNo", ""));
                    String cabin_tmp = MapUtils.GetOrDef(map_tmp, "cabin", "");
                    if (cabin_tmp.equals("")) {
                        cabin_tmp = cabin;
                    }
                    flightInfo.setCabin(cabin_tmp);
                    flightInfo.setCabinDesc(MapUtils.GetOrDef(map_tmp, "cabinDesc", ""));
                    flightInfo.setPlaneType(MapUtils.GetOrDef(map1, "planeType", ""));
                    fInfos.add(flightInfo);
                }
                break;
            case DOMESTICFLIGHTORDERBOOKINGCONTROLLER:
                list_101 = WapLogUtils.getObjects(log, new String[]{"bookingOut", "flightInfo"}, new char[]{'l', 'l'});
                for (String string : list_101) {
                    Map<String, String> map_tmp = WapLogUtils.splitToMap(string);
                    flightInfo = new FlightInfo();
                    flightInfo.setDepCity(MapUtils.GetOrDef(map_tmp, "depCity", ""));
                    flightInfo.setArrCity(MapUtils.GetOrDef(map_tmp, "arrCity", ""));
                    flightInfo.setDepCode(MapUtils.GetOrDef(map_tmp, "depCode", ""));
                    flightInfo.setArrCode(MapUtils.GetOrDef(map_tmp, "arrCode", ""));
                    flightInfo.setDepDate(MapUtils.GetOrDef(map_tmp, "depDate", ""));
                    flightInfo.setArrDate(MapUtils.GetOrDef(map_tmp, "arrDate", ""));
                    flightInfo.setDepTime(MapUtils.GetOrDef(map_tmp, "depTime", ""));
                    flightInfo.setArrTime(MapUtils.GetOrDef(map_tmp, "arrTime", ""));
                    flightInfo.setFlightNo(MapUtils.GetOrDef(map_tmp, "flightNo", ""));
                    flightInfo.setCompanyCode(MapUtils.GetOrDef(map_tmp, "fcode", ""));
                    flightInfo.setCabin(MapUtils.GetOrDef(map_tmp, "cabin", ""));
                    flightInfo.setCabinDesc(MapUtils.GetOrDef(map_tmp, "cabinDesc", ""));
                    flightInfo.setPlaneType(MapUtils.GetOrDef(map_tmp, "planeType", ""));
                    fInfos.add(flightInfo);
                }
                break;
            case SRVTTSAV4PACKAGE:
                flightInfo = new FlightInfo();
                flightInfo.setDepCity(MapUtils.GetOrDef(map1, "deptCity", ""));
                flightInfo.setArrCity(MapUtils.GetOrDef(map1, "arriCity", ""));
                flightInfo.setDepCode(MapUtils.GetOrDef(map1, "depcode", ""));
                flightInfo.setArrCode(MapUtils.GetOrDef(map1, "arrcode", ""));
                flightInfo.setDepDate(MapUtils.GetOrDef(map1, "date", ""));
                flightInfo.setArrDate(MapUtils.GetOrDef(map1, "arriDate", ""));
                flightInfo.setDepTime(MapUtils.GetOrDef(map1, "deptTime", ""));
                flightInfo.setArrTime(MapUtils.GetOrDef(map1, "arriTime", ""));
                flightInfo.setFlightNo(MapUtils.GetOrDef(map1, "airline", ""));
                cabin = MapUtils.GetOrDef(map1, "ccabin", "");
                cabin = StringUtil.sub(cabin, 0, '|', false);
                flightInfo.setCabin(cabin);
                flightInfo.setCabinDesc(MapUtils.GetOrDef(map1, "cabin_desc", ""));
                fInfos.add(flightInfo);
                flightInfo = new FlightInfo();
                flightInfo.setDepCity(MapUtils.GetOrDef(map1, "backDeptCity", ""));
                flightInfo.setArrCity(MapUtils.GetOrDef(map1, "backArriCity", ""));
                flightInfo.setDepCode(MapUtils.GetOrDef(map1, "backdepcode", ""));
                flightInfo.setArrCode(MapUtils.GetOrDef(map1, "backArrcode", ""));
                flightInfo.setDepDate(MapUtils.GetOrDef(map1, "backDate", ""));
                flightInfo.setArrDate(MapUtils.GetOrDef(map1, "backArriDate", ""));
                flightInfo.setDepTime(MapUtils.GetOrDef(map1, "backDeptTime", ""));
                flightInfo.setArrTime(MapUtils.GetOrDef(map1, "backArriTime", ""));
                flightInfo.setFlightNo(MapUtils.GetOrDef(map1, "backAirline", ""));
                cabin = MapUtils.GetOrDef(map1, "bcabin", "");
                cabin = StringUtil.sub(cabin, 0, '|', false);
//                if(!cabin.equals("")){
//                    cabin = cabin.substring(0, cabin.indexOf("|"));
//                }
                cabin = StringUtil.sub(cabin, 0, '|', false);
                flightInfo.setCabin(cabin);
                flightInfo.setCabinDesc(MapUtils.GetOrDef(map1, "backCabin_desc", ""));
                fInfos.add(flightInfo);
                break;

            case FLIGHTBARGAININDEX:
                flightInfo = new FlightInfo();
                flightInfo.setDepCity(MapUtils.GetOrDef(map1, "depCity", ""));
                flightInfo.setArrCity(MapUtils.GetOrDef(map1, "arrCity", ""));
                fInfos.add(flightInfo);
                break;
            case QUERYFLIGHTLOWPRICE:
                flightInfo = new FlightInfo();
                flightInfo.setDepCity(MapUtils.GetOrDef(map1, "c", ""));
                flightInfo.setArrCity(MapUtils.GetOrDef(map1, "arrCity", ""));
                fInfos.add(flightInfo);
                break;
            case QUERYINTERFLIGHTLOWPRICE:
                flightInfo = new FlightInfo();
                flightInfo.setDepCity(MapUtils.GetOrDef(map1, "depCity", ""));
                fInfos.add(flightInfo);
                break;
            case QUERYFMULTIWAYLIST:
                String begin = MapUtils.GetOrDef(map1, "begin", "");
                String end = MapUtils.GetOrDef(map1, "end", "");
                String date = MapUtils.GetOrDef(map1, "date", "");
                String[] begins = new String[2];
                String[] ends = new String[2];
                String[] dates = new String[2];
                boolean multi = true;
                if (begin.contains(",") && end.contains(",") && date.contains(",")) {
                    begins = begin.split(",", 2);
                    ends = end.split(",", 2);
                    dates = date.split(",", 2);
                } else {
                    multi = false;
                    begins[0] = begin;
                    ends[0] = end;
                    dates[0] = date;
                }
                flightInfo = new FlightInfo();
                flightInfo.setDepCity(begins[0]);
                flightInfo.setArrCity(ends[0]);
                flightInfo.setDepDate(dates[0]);
                fInfos.add(flightInfo);
                if (multi) {
                    flightInfo = new FlightInfo();
                    flightInfo.setDepCity(begins[1]);
                    flightInfo.setArrCity(ends[1]);
                    flightInfo.setDepDate(dates[1]);
                    fInfos.add(flightInfo);
                }
                break;
            case QUERYFUZZYWAYLIST:
                begin = MapUtils.GetOrDef(map1, "begin", "");
                end = MapUtils.GetOrDef(map1, "end", "");
                date = MapUtils.GetOrDef(map1, "date", "");
                if (date.contains(",")) {
                    date = date.substring(0, date.indexOf(","));
                }
                flightInfo = new FlightInfo();
                flightInfo.setDepCity(begin);
                flightInfo.setArrCity(end);
                flightInfo.setDepDate(date);
                fInfos.add(flightInfo);
                break;
            case QUERYFONEWAYDETAIL:
                flightInfo = new FlightInfo();
                flightInfo.setDepCity(MapUtils.GetOrDef(map1, "begin", ""));
                flightInfo.setArrCity(MapUtils.GetOrDef(map1, "end", ""));

                String finfo = StringUtil.sub(MapUtils.GetOrDef(map1, "time4log", ""), '{', '}');
                Map<String, String> map2 = WapLogUtils.splitToMap(finfo);
                flightInfo.setDepDate(MapUtils.GetOrDef(map2, "depDate", ""));
                flightInfo.setDepCode(MapUtils.GetOrDef(map2, "depAirportCode", ""));
                flightInfo.setArrCode(MapUtils.GetOrDef(map2, "arrAirportCode", ""));
                flightInfo.setDepTime(MapUtils.GetOrDef(map2, "depTime", ""));
                flightInfo.setArrTime(MapUtils.GetOrDef(map2, "arrTime", ""));
                flightInfo.setFlightNo(MapUtils.GetOrDef(map2, "airCode", ""));
                flightInfo.setPlaneType(MapUtils.GetOrDef(map2, "planeType", ""));
                fInfos.add(flightInfo);
                break;
            case QUERYFROUNDWAYDETAIL:
                flightInfo = new FlightInfo();
                flightInfo.setDepCity(MapUtils.GetOrDef(map1, "depCity", ""));
                flightInfo.setArrCity(MapUtils.GetOrDef(map1, "arrCity", ""));

                finfo = StringUtil.sub(MapUtils.GetOrDef(map1, "time4log", ""), '{', '}');
                if (finfo != null && finfo.length() > 10) {
                    Map<String, String> map_tmp = WapLogUtils.splitToMap(finfo);
                    flightInfo.setDepDate(MapUtils.GetOrDef(map_tmp, "depDate", ""));
                    flightInfo.setDepCode(MapUtils.GetOrDef(map_tmp, "depAirportCode", ""));
                    flightInfo.setArrCode(MapUtils.GetOrDef(map_tmp, "arrAirportCode", ""));
                    flightInfo.setDepTime(MapUtils.GetOrDef(map_tmp, "depTime", ""));
                    flightInfo.setArrTime(MapUtils.GetOrDef(map_tmp, "arrTime", ""));
                    flightInfo.setFlightNo(MapUtils.GetOrDef(map_tmp, "airCode", ""));
                    flightInfo.setPlaneType(MapUtils.GetOrDef(map_tmp, "planeType", ""));
                }
                fInfos.add(flightInfo);

                flightInfo = new FlightInfo();
                flightInfo.setDepCity(MapUtils.GetOrDef(map1, "end", ""));
                flightInfo.setArrCity(MapUtils.GetOrDef(map1, "begin", ""));
                String bfinfo = StringUtil.sub(MapUtils.GetOrDef(map1, "bfinfo", ""), '{', '}');
                if (bfinfo != null && bfinfo.length() > 10) {
                    Map<String, String> map_tmp = WapLogUtils.splitToMap(bfinfo);
                    flightInfo.setDepDate(MapUtils.GetOrDef(map_tmp, "depDate", ""));
                    flightInfo.setDepCode(MapUtils.GetOrDef(map_tmp, "depAirportCode", ""));
                    flightInfo.setArrCode(MapUtils.GetOrDef(map_tmp, "arrAirportCode", ""));
                    flightInfo.setDepTime(MapUtils.GetOrDef(map_tmp, "depTime", ""));
                    flightInfo.setArrTime(MapUtils.GetOrDef(map_tmp, "arrTime", ""));
                    flightInfo.setFlightNo(MapUtils.GetOrDef(map_tmp, "airCode", ""));
                    flightInfo.setPlaneType(MapUtils.GetOrDef(map_tmp, "planeType", ""));
                }
                fInfos.add(flightInfo);
                break;
            case QUERYFMOREWAYDETAIL:
                flightInfo = new FlightInfo();
                flightInfo.setDepCity(MapUtils.GetOrDef(map1, "depCity", ""));
                flightInfo.setArrCity(MapUtils.GetOrDef(map1, "transCity", ""));
                String finfo1 = StringUtil.sub(MapUtils.GetOrDef(map1, "time4log", ""), '{', '}');
                System.out.println(finfo1);
                if (finfo1 != null && finfo1.length() > 10) {
                    Map<String, String> map_tmp = WapLogUtils.splitToMap(finfo1);
                    flightInfo.setDepDate(MapUtils.GetOrDef(map_tmp, "depDate", ""));
                    flightInfo.setDepCode(MapUtils.GetOrDef(map_tmp, "depAirportCode", ""));
                    flightInfo.setArrCode(MapUtils.GetOrDef(map_tmp, "arrAirportCode", ""));
                    flightInfo.setDepTime(MapUtils.GetOrDef(map_tmp, "depTime", ""));
                    flightInfo.setArrTime(MapUtils.GetOrDef(map_tmp, "arrTime", ""));
                    flightInfo.setFlightNo(MapUtils.GetOrDef(map_tmp, "airCode", ""));
                    flightInfo.setPlaneType(MapUtils.GetOrDef(map_tmp, "planeType", ""));

                }
                fInfos.add(flightInfo);

                flightInfo = new FlightInfo();
                flightInfo.setDepCity(MapUtils.GetOrDef(map1, "transCity", ""));
                flightInfo.setArrCity(MapUtils.GetOrDef(map1, "arrCity", ""));
                String finfo2 = StringUtil.sub(MapUtils.GetOrDef(map1, "finfo2", ""), '{', '}');
                if (finfo2 != null && finfo2.length() > 10) {
                    Map<String, String> map_tmp = WapLogUtils.splitToMap(finfo2);
                    flightInfo.setDepDate(MapUtils.GetOrDef(map_tmp, "depDate", ""));
                    flightInfo.setDepCode(MapUtils.GetOrDef(map_tmp, "depAirportCode", ""));
                    flightInfo.setArrCode(MapUtils.GetOrDef(map_tmp, "arrAirportCode", ""));
                    flightInfo.setDepTime(MapUtils.GetOrDef(map_tmp, "depTime", ""));
                    flightInfo.setArrTime(MapUtils.GetOrDef(map_tmp, "arrTime", ""));
                    flightInfo.setFlightNo(MapUtils.GetOrDef(map_tmp, "airCode", ""));
                    flightInfo.setPlaneType(MapUtils.GetOrDef(map_tmp, "planeType", ""));
                }
                fInfos.add(flightInfo);
                break;

            case INTERFOWDETAIL:
                flightInfo = new FlightInfo();
                flightInfo.setDepCity(MapUtils.GetOrDef(map1, "begin", ""));
                flightInfo.setArrCity(MapUtils.GetOrDef(map1, "end", ""));
                flightInfo.setDepDate(MapUtils.GetOrDef(map1, "date", ""));
                flightInfo.setFlightNo(MapUtils.GetOrDef(map1, "airCode", ""));
                fInfos.add(flightInfo);
                break;
            case INTERTTSAV:
                cabin = WapLogUtils.getObject(log, new String[]{"cabin"}, new char[]{'o'});
                String[] cabinArr = cabin.split("/");
                list_101 = WapLogUtils.getObjects(log, new String[]{"goFInfo"}, new char[]{'l'});
                list_102 = WapLogUtils.getObjects(log, new String[]{"backFInfo"}, new char[]{'l'});
                list_101.addAll(list_102);
                int count = 0;
                for (String string : list_101) {
                    Map<String, String> map_tmp = WapLogUtils.splitToMap(string);
                    flightInfo = new FlightInfo();
                    flightInfo.setDepCity(MapUtils.GetOrDef(map_tmp, "depCity", ""));
                    flightInfo.setArrCity(MapUtils.GetOrDef(map_tmp, "arrCity", ""));
                    flightInfo.setDepCode(MapUtils.GetOrDef(map_tmp, "depAirportCode", ""));
                    flightInfo.setArrCode(MapUtils.GetOrDef(map_tmp, "arrAirportCode", ""));
                    flightInfo.setDepDate(MapUtils.GetOrDef(map_tmp, "depDate", ""));
                    flightInfo.setArrDate(MapUtils.GetOrDef(map_tmp, "arrDate", ""));
                    flightInfo.setDepTime(MapUtils.GetOrDef(map_tmp, "depTime", ""));
                    flightInfo.setArrTime(MapUtils.GetOrDef(map_tmp, "arrTime", ""));
                    flightInfo.setFlightNo(MapUtils.GetOrDef(map_tmp, "airCode", ""));
                    flightInfo.setPlaneType(MapUtils.GetOrDef(map_tmp, "planeType", ""));
                    if (count < cabinArr.length) {
                        flightInfo.setCabin(cabinArr[count]);
                    } else {
                        flightInfo.setCabin(cabinArr[0]);
                    }
                    fInfos.add(flightInfo);
                }
                break;

            case INTERTTSAV4ONEBILLNEW:
                list_101 = WapLogUtils.getObjects(log, new String[]{"flightInfo", "goInfos"}, new char[]{'l', 'l'});
                for (String string : list_101) {
                    Map<String, String> map_tmp = WapLogUtils.splitToMap(string);
                    flightInfo = new FlightInfo();
                    flightInfo.setDepCity(MapUtils.GetOrDef(map_tmp, "depCity", ""));
                    flightInfo.setArrCity(MapUtils.GetOrDef(map_tmp, "arrCity", ""));
                    flightInfo.setDepCode(MapUtils.GetOrDef(map_tmp, "depApCode", ""));
                    flightInfo.setArrCode(MapUtils.GetOrDef(map_tmp, "arrApCode", ""));
                    flightInfo.setDepDate(MapUtils.GetOrDef(map_tmp, "depDate", ""));
                    flightInfo.setArrDate(MapUtils.GetOrDef(map_tmp, "arrDate", ""));
                    flightInfo.setDepTime(MapUtils.GetOrDef(map_tmp, "depTime", ""));
                    flightInfo.setArrTime(MapUtils.GetOrDef(map_tmp, "arrTime", ""));
                    flightInfo.setFlightNo(MapUtils.GetOrDef(map_tmp, "flightNo", ""));
                    flightInfo.setCompanyCode(MapUtils.GetOrDef(map_tmp, "airlineCode", ""));
                    flightInfo.setCabin(MapUtils.GetOrDef(map_tmp, "cabin", ""));
                    flightInfo.setCabinDesc(MapUtils.GetOrDef(map_tmp, "cabinDesc", ""));
                    flightInfo.setPlaneType(MapUtils.GetOrDef(map_tmp, "planeType", ""));
                    fInfos.add(flightInfo);
                }
                break;

            case INTERFRWDETAIL:
                flightInfo = new FlightInfo();
                flightInfo.setDepCity(MapUtils.GetOrDef(map1, "depCity", ""));
                flightInfo.setArrCity(MapUtils.GetOrDef(map1, "arrCity", ""));
                flightInfo.setDepDate(MapUtils.GetOrDef(map1, "goDate", ""));
                String backDate = MapUtils.GetOrDef(map1, "backDate", "");
                String airCodeCol = MapUtils.GetOrDef(map1, "airCode", "");
                String[] airCodeColArr = airCodeCol.split("_");
                if (airCodeColArr.length >= 2 && !backDate.equals("")) {
                    flightInfo.setFlightNo(airCodeColArr[0]);
                    fInfos.add(flightInfo);
                    flightInfo = new FlightInfo();
                    flightInfo.setDepCity(MapUtils.GetOrDef(map1, "arrCity", ""));
                    flightInfo.setArrCity(MapUtils.GetOrDef(map1, "depCity", ""));
                    flightInfo.setDepDate(backDate);
                    flightInfo.setFlightNo(airCodeColArr[1]);
                    fInfos.add(flightInfo);
                } else {
                    flightInfo.setFlightNo(airCodeCol);
                    fInfos.add(flightInfo);
                }

                break;

            case INTERFMWDETAIL:
            case INTERMIXFRWDETAIL:
                airCodeCol = MapUtils.GetOrDef(map1, "airCode", "");
                if (airCodeCol.contains(";")) {
                    String[] fInfos_loop = airCodeCol.split(";");
                    int count_tmp = 0;
                    for (String str_tmp : fInfos_loop) {
                        if (count_tmp >= 2) {
                            break;
                        }
                        String[] fInfos_tmp = str_tmp.split("\\|");
                        flightInfo = new FlightInfo();
                        if (count_tmp == 0) {
                            flightInfo.setDepCity(MapUtils.GetOrDef(map1, "depCity", ""));
                            flightInfo.setArrCity(MapUtils.GetOrDef(map1, "transCity", ""));
                        } else {
                            flightInfo.setDepCity(MapUtils.GetOrDef(map1, "transCity", ""));
                            flightInfo.setArrCity(MapUtils.GetOrDef(map1, "arrCity", ""));
                        }
                        if (fInfos_tmp.length >= 3) {
                            flightInfo.setFlightNo(fInfos_tmp[0]);
                            flightInfo.setDepDate(fInfos_tmp[2]);
                            String[] codes_loop = fInfos_tmp[1].split("-");
                            if (codes_loop.length >= 2) {
                                flightInfo.setDepCode(codes_loop[0]);
                                flightInfo.setArrCode(codes_loop[1]);
                            } else {
                                flightInfo.setDepCode(fInfos_tmp[1]);
                            }
                            fInfos.add(flightInfo);
                        } else {
                            flightInfo.setFlightNo(fInfos_tmp[0]);
                            fInfos.add(flightInfo);
                        }

                        count_tmp += 1;
                    }
                } else {
                    flightInfo = new FlightInfo();
                    flightInfo.setDepCity(MapUtils.GetOrDef(map1, "depCity", ""));
                    flightInfo.setArrCity(MapUtils.GetOrDef(map1, "arrCity", ""));
                    flightInfo.setFlightNo(MapUtils.GetOrDef(map1, "airCode", ""));
                    fInfos.add(flightInfo);
                }

                break;

            case QUERYFMULTIWAYDETAIL:
                begin = MapUtils.GetOrDef(map1, "begin", "");
                end = MapUtils.GetOrDef(map1, "end", "");
                date = MapUtils.GetOrDef(map1, "date", "");
                airCodeCol = MapUtils.GetOrDef(map1, "airCode", "");
                begins = new String[2];
                ends = new String[2];
                dates = new String[2];
                multi = true;
                if (begin.contains(",") && end.contains(",") && date.contains(",")) {
                    begins = begin.split(",", 2);
                    ends = end.split(",", 2);
                    dates = date.split(",", 2);
                } else {
                    multi = false;
                    begins[0] = begin;
                    ends[0] = end;
                    dates[0] = date;
                }
                flightInfo = new FlightInfo();
                flightInfo.setDepCity(begins[0]);
                flightInfo.setArrCity(ends[0]);
                flightInfo.setDepDate(dates[0]);
                flightInfo.setFlightNo(StringUtil.sub(airCodeCol, 0, '|', false));
                fInfos.add(flightInfo);
                if (multi) {
                    flightInfo = new FlightInfo();
                    flightInfo.setDepCity(begins[1]);
                    flightInfo.setArrCity(ends[1]);
                    flightInfo.setDepDate(dates[1]);
                    flightInfo.setFlightNo(StringUtil.sub(airCodeCol, '_', '|', false));
                    fInfos.add(flightInfo);
                }
                break;

            case F_INTERTTSAV4ONEBILL:
                list_101 = WapLogUtils.getObjects(log, new String[]{"flightInfo", "goInfo"}, new char[]{'l', 'l'});
                for (String string : list_101) {
                    Map<String, String> map_tmp = WapLogUtils.splitToMap(string);
                    flightInfo = new FlightInfo();
                    flightInfo.setDepCity(MapUtils.GetOrDef(map_tmp, "depCity", ""));
                    flightInfo.setArrCity(MapUtils.GetOrDef(map_tmp, "arrCity", ""));
                    flightInfo.setDepCode(MapUtils.GetOrDef(map_tmp, "depAirportCode", ""));
                    flightInfo.setArrCode(MapUtils.GetOrDef(map_tmp, "arrAirportCode", ""));
                    flightInfo.setDepDate(MapUtils.GetOrDef(map_tmp, "depDate", ""));
                    flightInfo.setArrDate(MapUtils.GetOrDef(map_tmp, "arrDate", ""));
                    flightInfo.setDepTime(MapUtils.GetOrDef(map_tmp, "depTime", ""));
                    flightInfo.setArrTime(MapUtils.GetOrDef(map_tmp, "arrTime", ""));
                    flightInfo.setFlightNo(MapUtils.GetOrDef(map_tmp, "airCode", ""));
                    fInfos.add(flightInfo);
                }
                break;


            default:
                break;
        }

        flightLog.setfInfos(fInfos);

        return flightLog;
    }

}
