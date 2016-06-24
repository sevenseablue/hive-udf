package util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by seven on 24/06/16.
 */
public class FlightInfo {
    String depCity;
    String arrCity;
    String depCode;
    String arrCode;
    String depDate;
    String arrDate;
    String depTime;
    String arrTime;
    String companyCode;
    String flightNo;
    String cabin;
    String cabinDesc;
    String planeType;

    public FlightInfo() {
    }


    public String getPlaneType() {
        return planeType;
    }

    public void setPlaneType(String planeType) {
        this.planeType = planeType;
    }

    public String getDepCode() {
        return depCode;
    }

    public void setDepCode(String depCode) {
        this.depCode = depCode;
    }

    public String getArrCode() {
        return arrCode;
    }

    public void setArrCode(String arrCode) {
        this.arrCode = arrCode;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getCabin() {
        return cabin;
    }

    public void setCabin(String cabin) {
        this.cabin = cabin;
    }

    public String getDepCity() {
        return depCity;
    }

    public void setDepCity(String depCity) {
        this.depCity = depCity;
    }

    public String getArrCity() {
        return arrCity;
    }

    public void setArrCity(String arrCity) {
        this.arrCity = arrCity;
    }

    public String getDepDate() {
        return depDate;
    }

    public void setDepDate(String depDate) {
        this.depDate = depDate;
    }

    public String getArrDate() {
        return arrDate;
    }

    public void setArrDate(String arrDate) {
        this.arrDate = arrDate;
    }

    public String getDepTime() {
        return depTime;
    }

    public void setDepTime(String depTime) {
        this.depTime = depTime;
    }

    public String getArrTime() {
        return arrTime;
    }

    public void setArrTime(String arrTime) {
        this.arrTime = arrTime;
    }

    public String getFlightNo() {
        return flightNo;
    }

    public void setFlightNo(String flightNo) {
        this.flightNo = flightNo;
    }

    public String getCabinDesc() {
        return cabinDesc;
    }

    public void setCabinDesc(String cabinDesc) {
        this.cabinDesc = cabinDesc;
    }

    @Override
    public String toString() {
        return "FlightInfo{" +
                "depCity='" + depCity + '\'' +
                ", arrCity='" + arrCity + '\'' +
                ", depCode='" + depCode + '\'' +
                ", arrCode='" + arrCode + '\'' +
                ", depDate='" + depDate + '\'' +
                ", arrDate='" + arrDate + '\'' +
                ", depTime='" + depTime + '\'' +
                ", arrTime='" + arrTime + '\'' +
                ", companyCode='" + companyCode + '\'' +
                ", flightNo='" + flightNo + '\'' +
                ", cabin='" + cabin + '\'' +
                ", cabinDesc='" + cabinDesc + '\'' +
                ", planeType='" + planeType + '\'' +
                '}';
    }

    public String toHive() {
        String delim = "\003";
        StringBuilder sb  = new StringBuilder();
        sb.append(depCity).append(delim)
                .append(arrCity).append(delim)
                .append(depDate).append(delim)
                .append(arrDate).append(delim)
                .append(flightNo).append(delim)
                .append(cabinDesc);
        return sb.toString();
    }

    public static String listToHive(List<FlightInfo> list1){
        String delim2 = "\002";
        String delim3 = "\003";
        StringBuilder sb  = new StringBuilder();

        for(FlightInfo flightInfo: list1){
            sb.append(StringUtil.nullToEmpty(flightInfo.depCity)).append(delim3)
                    .append(StringUtil.nullToEmpty(flightInfo.arrCity)).append(delim3)
                    .append(StringUtil.nullToEmpty(flightInfo.depCode)).append(delim3)
                    .append(StringUtil.nullToEmpty(flightInfo.arrCode)).append(delim3)
                    .append(StringUtil.nullToEmpty(flightInfo.depDate)).append(delim3)
                    .append(StringUtil.nullToEmpty(flightInfo.arrDate)).append(delim3)
                    .append(StringUtil.nullToEmpty(flightInfo.depTime)).append(delim3)
                    .append(StringUtil.nullToEmpty(flightInfo.arrTime)).append(delim3)
                    .append(StringUtil.nullToEmpty(flightInfo.companyCode)).append(delim3)
                    .append(StringUtil.nullToEmpty(flightInfo.flightNo)).append(delim3)
                    .append(StringUtil.nullToEmpty(flightInfo.cabin)).append(delim3)
                    .append(StringUtil.nullToEmpty(flightInfo.cabinDesc)).append(delim2);
        }
        if(sb.length()>0){
            sb.deleteCharAt(sb.length()-1);
        }
        return sb.toString();
    }

    public static List<FlightInfo> getInfos(String action, String log) {
        Action action1 = ActionCls.getAction(action);
        Map<String, String> map1 = WapLogUtils.splitToMap(log);
        String result = "";
        String delim = "\001";
        FlightInfo flightInfo = new FlightInfo();
        ArrayList<FlightInfo> fInfos = new ArrayList<FlightInfo>();
        ArrayList<String> list1 = new ArrayList<String>();
        list1.add(log);
        switch (action1) {
            case OTHER:
                break;
            case QUERYFMIXWAYLIST:
                flightInfo = new FlightInfo();
                flightInfo.setDepCity(MapUtils.getOrDef(map1, "begin", ""));
                flightInfo.setArrCity(MapUtils.getOrDef(map1, "end", ""));
                flightInfo.setDepDate(MapUtils.getOrDef(map1, "date", ""));
                fInfos.add(flightInfo);
                break;
            case QUERYFROUNDWAYLIST:
                flightInfo = new FlightInfo();
                flightInfo.setDepCity(MapUtils.getOrDef(map1, "begin", ""));
                flightInfo.setArrCity(MapUtils.getOrDef(map1, "end", ""));
                flightInfo.setDepDate(MapUtils.getOrDef(map1, "goDate", ""));
                fInfos.add(flightInfo);
                flightInfo = new FlightInfo();
                flightInfo.setDepCity(MapUtils.getOrDef(map1, "end", ""));
                flightInfo.setArrCity(MapUtils.getOrDef(map1, "begin", ""));
                flightInfo.setDepDate(MapUtils.getOrDef(map1, "backDate", ""));
                fInfos.add(flightInfo);
                break;
            case QUERYOTALIST:
                List<String> list_101 = WapLogUtils.getObjects(log, new String[]{"routes", "fInfos", "goInfos"}, new char[]{'l', 'l', 'l'}, new String[]{"", "", ""}, new String[]{"title=(往返特价包|多程特价包|中转特价包|主飞航班)", "", ""});
                for (String string : list_101) {
                    Map<String, String> map_tmp = WapLogUtils.splitToMap(string);
                    flightInfo = new FlightInfo();
                    flightInfo.setDepCity(MapUtils.getOrDef(map_tmp, "depCity", ""));
                    flightInfo.setArrCity(MapUtils.getOrDef(map_tmp, "arrCity", ""));
                    flightInfo.setDepCode(MapUtils.getOrDef(map_tmp, "depCode", ""));
                    flightInfo.setArrCode(MapUtils.getOrDef(map_tmp, "arrCode", ""));
                    flightInfo.setDepDate(MapUtils.getOrDef(map_tmp, "depDate", ""));
                    flightInfo.setArrDate(MapUtils.getOrDef(map_tmp, "arrDate", ""));
                    flightInfo.setDepTime(MapUtils.getOrDef(map_tmp, "depTime", ""));
                    flightInfo.setArrTime(MapUtils.getOrDef(map_tmp, "arrTime", ""));
                    flightInfo.setFlightNo(MapUtils.getOrDef(map_tmp, "flightNo", ""));
                    flightInfo.setCompanyCode(MapUtils.getOrDef(map_tmp, "airlineCode", ""));
                    flightInfo.setPlaneType(MapUtils.getOrDef(map_tmp, "planeType", ""));
                    fInfos.add(flightInfo);
                }
                break;
            case SRVTTSAV:
                flightInfo = new FlightInfo();
                flightInfo.setDepCity(MapUtils.getOrDef(map1, "deptCity", ""));
                flightInfo.setArrCity(MapUtils.getOrDef(map1, "arriCity", ""));
                flightInfo.setDepCode(MapUtils.getOrDef(map1, "depcode", ""));
                flightInfo.setArrCode(MapUtils.getOrDef(map1, "arrcode", ""));
                flightInfo.setDepDate(MapUtils.getOrDef(map1, "deptDate", ""));
                flightInfo.setArrDate(MapUtils.getOrDef(map1, "arriDate", ""));
                flightInfo.setDepTime(MapUtils.getOrDef(map1, "depTime", ""));
                flightInfo.setArrTime(MapUtils.getOrDef(map1, "arrTime", ""));
                flightInfo.setFlightNo(MapUtils.getOrDef(map1, "airline", ""));
                flightInfo.setCompanyCode(MapUtils.getOrDef(map1, "fcode", ""));
                flightInfo.setCabin(MapUtils.getOrDef(map1, "cabin", ""));
                flightInfo.setCabinDesc(MapUtils.getOrDef(map1, "cabin_desc", ""));
                flightInfo.setPlaneType(MapUtils.getOrDef(map1, "planetype", ""));
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
                    flightInfo.setDepCity(MapUtils.getOrDef(map_tmp, "depCity", ""));
                    flightInfo.setArrCity(MapUtils.getOrDef(map_tmp, "arrCity", ""));
                    flightInfo.setDepCode(MapUtils.getOrDef(map_tmp, "depCode", ""));
                    flightInfo.setArrCode(MapUtils.getOrDef(map_tmp, "arrCode", ""));
                    flightInfo.setDepDate(MapUtils.getOrDef(map_tmp, "depDate", ""));
                    flightInfo.setArrDate(MapUtils.getOrDef(map_tmp, "arrDate", ""));
                    flightInfo.setDepTime(MapUtils.getOrDef(map_tmp, "depTime", ""));
                    flightInfo.setArrTime(MapUtils.getOrDef(map_tmp, "arrTime", ""));
                    flightInfo.setFlightNo(MapUtils.getOrDef(map_tmp, "flightNo", ""));
                    String cabin_tmp = MapUtils.getOrDef(map_tmp, "cabin", "");
                    if (cabin_tmp.equals("")) {
                        cabin_tmp = cabin;
                    }
                    flightInfo.setCabin(cabin_tmp);
                    flightInfo.setCabinDesc(MapUtils.getOrDef(map_tmp, "cabinDesc", ""));
                    flightInfo.setPlaneType(MapUtils.getOrDef(map1, "planeType", ""));
                    fInfos.add(flightInfo);
                }
                break;
            case DOMESTICFLIGHTORDERBOOKINGCONTROLLER:
                list_101 = WapLogUtils.getObjects(log, new String[]{"bookingOut", "flightInfo"}, new char[]{'l', 'l'});
                for (String string : list_101) {
                    Map<String, String> map_tmp = WapLogUtils.splitToMap(string);
                    flightInfo = new FlightInfo();
                    flightInfo.setDepCity(MapUtils.getOrDef(map_tmp, "depCity", ""));
                    flightInfo.setArrCity(MapUtils.getOrDef(map_tmp, "arrCity", ""));
                    flightInfo.setDepCode(MapUtils.getOrDef(map_tmp, "depCode", ""));
                    flightInfo.setArrCode(MapUtils.getOrDef(map_tmp, "arrCode", ""));
                    flightInfo.setDepDate(MapUtils.getOrDef(map_tmp, "depDate", ""));
                    flightInfo.setArrDate(MapUtils.getOrDef(map_tmp, "arrDate", ""));
                    flightInfo.setDepTime(MapUtils.getOrDef(map_tmp, "depTime", ""));
                    flightInfo.setArrTime(MapUtils.getOrDef(map_tmp, "arrTime", ""));
                    flightInfo.setFlightNo(MapUtils.getOrDef(map_tmp, "flightNo", ""));
                    flightInfo.setCompanyCode(MapUtils.getOrDef(map_tmp, "fcode", ""));
                    flightInfo.setCabin(MapUtils.getOrDef(map_tmp, "cabin", ""));
                    flightInfo.setCabinDesc(MapUtils.getOrDef(map_tmp, "cabinDesc", ""));
                    flightInfo.setPlaneType(MapUtils.getOrDef(map_tmp, "planeType", ""));
                    fInfos.add(flightInfo);
                }
                break;
            case SRVTTSAV4PACKAGE:
                flightInfo = new FlightInfo();
                flightInfo.setDepCity(MapUtils.getOrDef(map1, "deptCity", ""));
                flightInfo.setArrCity(MapUtils.getOrDef(map1, "arriCity", ""));
                flightInfo.setDepCode(MapUtils.getOrDef(map1, "depcode", ""));
                flightInfo.setArrCode(MapUtils.getOrDef(map1, "arrcode", ""));
                flightInfo.setDepDate(MapUtils.getOrDef(map1, "date", ""));
                flightInfo.setArrDate(MapUtils.getOrDef(map1, "arriDate", ""));
                flightInfo.setDepTime(MapUtils.getOrDef(map1, "deptTime", ""));
                flightInfo.setArrTime(MapUtils.getOrDef(map1, "arriTime", ""));
                flightInfo.setFlightNo(MapUtils.getOrDef(map1, "airline", ""));
                cabin = MapUtils.getOrDef(map1, "ccabin", "");
                cabin = StringUtil.sub(cabin, 0, '|', false);
                flightInfo.setCabin(cabin);
                flightInfo.setCabinDesc(MapUtils.getOrDef(map1, "cabin_desc", ""));
                fInfos.add(flightInfo);
                flightInfo = new FlightInfo();
                flightInfo.setDepCity(MapUtils.getOrDef(map1, "backDeptCity", ""));
                flightInfo.setArrCity(MapUtils.getOrDef(map1, "backArriCity", ""));
                flightInfo.setDepCode(MapUtils.getOrDef(map1, "backdepcode", ""));
                flightInfo.setArrCode(MapUtils.getOrDef(map1, "backArrcode", ""));
                flightInfo.setDepDate(MapUtils.getOrDef(map1, "backDate", ""));
                flightInfo.setArrDate(MapUtils.getOrDef(map1, "backArriDate", ""));
                flightInfo.setDepTime(MapUtils.getOrDef(map1, "backDeptTime", ""));
                flightInfo.setArrTime(MapUtils.getOrDef(map1, "backArriTime", ""));
                flightInfo.setFlightNo(MapUtils.getOrDef(map1, "backAirline", ""));
                cabin = MapUtils.getOrDef(map1, "bcabin", "");
                cabin = StringUtil.sub(cabin, 0, '|', false);
//                if(!cabin.equals("")){
//                    cabin = cabin.substring(0, cabin.indexOf("|"));
//                }
                cabin = StringUtil.sub(cabin, 0, '|', false);
                flightInfo.setCabin(cabin);
                flightInfo.setCabinDesc(MapUtils.getOrDef(map1, "backCabin_desc", ""));
                fInfos.add(flightInfo);
                break;

            case FLIGHTBARGAININDEX:
                flightInfo = new FlightInfo();
                flightInfo.setDepCity(MapUtils.getOrDef(map1, "depCity", ""));
                flightInfo.setArrCity(MapUtils.getOrDef(map1, "arrCity", ""));
                fInfos.add(flightInfo);
                break;
            case QUERYFLIGHTLOWPRICE:
                flightInfo = new FlightInfo();
                flightInfo.setDepCity(MapUtils.getOrDef(map1, "c", ""));
                flightInfo.setArrCity(MapUtils.getOrDef(map1, "arrCity", ""));
                fInfos.add(flightInfo);
                break;
            case QUERYINTERFLIGHTLOWPRICE:
                flightInfo = new FlightInfo();
                flightInfo.setDepCity(MapUtils.getOrDef(map1, "depCity", ""));
                fInfos.add(flightInfo);
                break;
            case QUERYFMULTIWAYLIST:
                String begin = MapUtils.getOrDef(map1, "begin", "");
                String end = MapUtils.getOrDef(map1, "end", "");
                String date = MapUtils.getOrDef(map1, "date", "");
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
                begin = MapUtils.getOrDef(map1, "begin", "");
                end = MapUtils.getOrDef(map1, "end", "");
                date = MapUtils.getOrDef(map1, "date", "");
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
                flightInfo.setDepCity(MapUtils.getOrDef(map1, "begin", ""));
                flightInfo.setArrCity(MapUtils.getOrDef(map1, "end", ""));

                String finfo = StringUtil.sub(MapUtils.getOrDef(map1, "time4log", ""), '{', '}');
                Map<String, String> map2 = WapLogUtils.splitToMap(finfo);
                flightInfo.setDepDate(MapUtils.getOrDef(map2, "depDate", ""));
                flightInfo.setDepCode(MapUtils.getOrDef(map2, "depAirportCode", ""));
                flightInfo.setArrCode(MapUtils.getOrDef(map2, "arrAirportCode", ""));
                flightInfo.setDepTime(MapUtils.getOrDef(map2, "depTime", ""));
                flightInfo.setArrTime(MapUtils.getOrDef(map2, "arrTime", ""));
                flightInfo.setFlightNo(MapUtils.getOrDef(map2, "airCode", ""));
                flightInfo.setPlaneType(MapUtils.getOrDef(map2, "planeType", ""));
                fInfos.add(flightInfo);
                break;
            case QUERYFROUNDWAYDETAIL:
                flightInfo = new FlightInfo();
                flightInfo.setDepCity(MapUtils.getOrDef(map1, "depCity", ""));
                flightInfo.setArrCity(MapUtils.getOrDef(map1, "arrCity", ""));

                finfo = StringUtil.sub(MapUtils.getOrDef(map1, "time4log", ""), '{', '}');
                if (finfo != null && finfo.length() > 10) {
                    Map<String, String> map_tmp = WapLogUtils.splitToMap(finfo);
                    flightInfo.setDepDate(MapUtils.getOrDef(map_tmp, "depDate", ""));
                    flightInfo.setDepCode(MapUtils.getOrDef(map_tmp, "depAirportCode", ""));
                    flightInfo.setArrCode(MapUtils.getOrDef(map_tmp, "arrAirportCode", ""));
                    flightInfo.setDepTime(MapUtils.getOrDef(map_tmp, "depTime", ""));
                    flightInfo.setArrTime(MapUtils.getOrDef(map_tmp, "arrTime", ""));
                    flightInfo.setFlightNo(MapUtils.getOrDef(map_tmp, "airCode", ""));
                    flightInfo.setPlaneType(MapUtils.getOrDef(map_tmp, "planeType", ""));
                }
                fInfos.add(flightInfo);

                flightInfo = new FlightInfo();
                flightInfo.setDepCity(MapUtils.getOrDef(map1, "end", ""));
                flightInfo.setArrCity(MapUtils.getOrDef(map1, "begin", ""));
                String bfinfo = StringUtil.sub(MapUtils.getOrDef(map1, "bfinfo", ""), '{', '}');
                if (bfinfo != null && bfinfo.length() > 10) {
                    Map<String, String> map_tmp = WapLogUtils.splitToMap(bfinfo);
                    flightInfo.setDepDate(MapUtils.getOrDef(map_tmp, "depDate", ""));
                    flightInfo.setDepCode(MapUtils.getOrDef(map_tmp, "depAirportCode", ""));
                    flightInfo.setArrCode(MapUtils.getOrDef(map_tmp, "arrAirportCode", ""));
                    flightInfo.setDepTime(MapUtils.getOrDef(map_tmp, "depTime", ""));
                    flightInfo.setArrTime(MapUtils.getOrDef(map_tmp, "arrTime", ""));
                    flightInfo.setFlightNo(MapUtils.getOrDef(map_tmp, "airCode", ""));
                    flightInfo.setPlaneType(MapUtils.getOrDef(map_tmp, "planeType", ""));
                }
                fInfos.add(flightInfo);
                break;
            case QUERYFMOREWAYDETAIL:
                flightInfo = new FlightInfo();
                flightInfo.setDepCity(MapUtils.getOrDef(map1, "depCity", ""));
                flightInfo.setArrCity(MapUtils.getOrDef(map1, "transCity", ""));
                String finfo1 = StringUtil.sub(MapUtils.getOrDef(map1, "time4log", ""), '{', '}');
                if (finfo1 != null && finfo1.length() > 10) {
                    Map<String, String> map_tmp = WapLogUtils.splitToMap(finfo1);
                    flightInfo.setDepDate(MapUtils.getOrDef(map_tmp, "depDate", ""));
                    flightInfo.setDepCode(MapUtils.getOrDef(map_tmp, "depAirportCode", ""));
                    flightInfo.setArrCode(MapUtils.getOrDef(map_tmp, "arrAirportCode", ""));
                    flightInfo.setDepTime(MapUtils.getOrDef(map_tmp, "depTime", ""));
                    flightInfo.setArrTime(MapUtils.getOrDef(map_tmp, "arrTime", ""));
                    flightInfo.setFlightNo(MapUtils.getOrDef(map_tmp, "airCode", ""));
                    flightInfo.setPlaneType(MapUtils.getOrDef(map_tmp, "planeType", ""));

                }
                fInfos.add(flightInfo);

                flightInfo = new FlightInfo();
                flightInfo.setDepCity(MapUtils.getOrDef(map1, "transCity", ""));
                flightInfo.setArrCity(MapUtils.getOrDef(map1, "arrCity", ""));
                String finfo2 = StringUtil.sub(MapUtils.getOrDef(map1, "finfo2", ""), '{', '}');
                if (finfo2 != null && finfo2.length() > 10) {
                    Map<String, String> map_tmp = WapLogUtils.splitToMap(finfo2);
                    flightInfo.setDepDate(MapUtils.getOrDef(map_tmp, "depDate", ""));
                    flightInfo.setDepCode(MapUtils.getOrDef(map_tmp, "depAirportCode", ""));
                    flightInfo.setArrCode(MapUtils.getOrDef(map_tmp, "arrAirportCode", ""));
                    flightInfo.setDepTime(MapUtils.getOrDef(map_tmp, "depTime", ""));
                    flightInfo.setArrTime(MapUtils.getOrDef(map_tmp, "arrTime", ""));
                    flightInfo.setFlightNo(MapUtils.getOrDef(map_tmp, "airCode", ""));
                    flightInfo.setPlaneType(MapUtils.getOrDef(map_tmp, "planeType", ""));
                }
                fInfos.add(flightInfo);
                break;

            case INTERFOWDETAIL:
                flightInfo = new FlightInfo();
                flightInfo.setDepCity(MapUtils.getOrDef(map1, "begin", ""));
                flightInfo.setArrCity(MapUtils.getOrDef(map1, "end", ""));
                flightInfo.setDepDate(MapUtils.getOrDef(map1, "date", ""));
                flightInfo.setFlightNo(MapUtils.getOrDef(map1, "airCode", ""));
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
                    flightInfo.setDepCity(MapUtils.getOrDef(map_tmp, "depCity", ""));
                    flightInfo.setArrCity(MapUtils.getOrDef(map_tmp, "arrCity", ""));
                    flightInfo.setDepCode(MapUtils.getOrDef(map_tmp, "depAirportCode", ""));
                    flightInfo.setArrCode(MapUtils.getOrDef(map_tmp, "arrAirportCode", ""));
                    flightInfo.setDepDate(MapUtils.getOrDef(map_tmp, "depDate", ""));
                    flightInfo.setArrDate(MapUtils.getOrDef(map_tmp, "arrDate", ""));
                    flightInfo.setDepTime(MapUtils.getOrDef(map_tmp, "depTime", ""));
                    flightInfo.setArrTime(MapUtils.getOrDef(map_tmp, "arrTime", ""));
                    flightInfo.setFlightNo(MapUtils.getOrDef(map_tmp, "airCode", ""));
                    flightInfo.setPlaneType(MapUtils.getOrDef(map_tmp, "planeType", ""));
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
                    flightInfo.setDepCity(MapUtils.getOrDef(map_tmp, "depCity", ""));
                    flightInfo.setArrCity(MapUtils.getOrDef(map_tmp, "arrCity", ""));
                    flightInfo.setDepCode(MapUtils.getOrDef(map_tmp, "depApCode", ""));
                    flightInfo.setArrCode(MapUtils.getOrDef(map_tmp, "arrApCode", ""));
                    flightInfo.setDepDate(MapUtils.getOrDef(map_tmp, "depDate", ""));
                    flightInfo.setArrDate(MapUtils.getOrDef(map_tmp, "arrDate", ""));
                    flightInfo.setDepTime(MapUtils.getOrDef(map_tmp, "depTime", ""));
                    flightInfo.setArrTime(MapUtils.getOrDef(map_tmp, "arrTime", ""));
                    flightInfo.setFlightNo(MapUtils.getOrDef(map_tmp, "flightNo", ""));
                    flightInfo.setCompanyCode(MapUtils.getOrDef(map_tmp, "airlineCode", ""));
                    flightInfo.setCabin(MapUtils.getOrDef(map_tmp, "cabin", ""));
                    flightInfo.setCabinDesc(MapUtils.getOrDef(map_tmp, "cabinDesc", ""));
                    flightInfo.setPlaneType(MapUtils.getOrDef(map_tmp, "planeType", ""));
                    fInfos.add(flightInfo);
                }
                break;

            case INTERFRWDETAIL:
                flightInfo = new FlightInfo();
                flightInfo.setDepCity(MapUtils.getOrDef(map1, "depCity", ""));
                flightInfo.setArrCity(MapUtils.getOrDef(map1, "arrCity", ""));
                flightInfo.setDepDate(MapUtils.getOrDef(map1, "goDate", ""));
                String backDate = MapUtils.getOrDef(map1, "backDate", "");
                String airCodeCol = MapUtils.getOrDef(map1, "airCode", "");
                String[] airCodeColArr = airCodeCol.split("_");
                if (airCodeColArr.length >= 2 && !backDate.equals("")) {
                    flightInfo.setFlightNo(airCodeColArr[0]);
                    fInfos.add(flightInfo);
                    flightInfo = new FlightInfo();
                    flightInfo.setDepCity(MapUtils.getOrDef(map1, "arrCity", ""));
                    flightInfo.setArrCity(MapUtils.getOrDef(map1, "depCity", ""));
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
                airCodeCol = MapUtils.getOrDef(map1, "airCode", "");
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
                            flightInfo.setDepCity(MapUtils.getOrDef(map1, "depCity", ""));
                            flightInfo.setArrCity(MapUtils.getOrDef(map1, "transCity", ""));
                        } else {
                            flightInfo.setDepCity(MapUtils.getOrDef(map1, "transCity", ""));
                            flightInfo.setArrCity(MapUtils.getOrDef(map1, "arrCity", ""));
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
                    flightInfo.setDepCity(MapUtils.getOrDef(map1, "depCity", ""));
                    flightInfo.setArrCity(MapUtils.getOrDef(map1, "arrCity", ""));
                    flightInfo.setFlightNo(MapUtils.getOrDef(map1, "airCode", ""));
                    fInfos.add(flightInfo);
                }

                break;

            case QUERYFMULTIWAYDETAIL:
                begin = MapUtils.getOrDef(map1, "begin", "");
                end = MapUtils.getOrDef(map1, "end", "");
                date = MapUtils.getOrDef(map1, "date", "");
                airCodeCol = MapUtils.getOrDef(map1, "airCode", "");
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
                    flightInfo.setDepCity(MapUtils.getOrDef(map_tmp, "depCity", ""));
                    flightInfo.setArrCity(MapUtils.getOrDef(map_tmp, "arrCity", ""));
                    flightInfo.setDepCode(MapUtils.getOrDef(map_tmp, "depAirportCode", ""));
                    flightInfo.setArrCode(MapUtils.getOrDef(map_tmp, "arrAirportCode", ""));
                    flightInfo.setDepDate(MapUtils.getOrDef(map_tmp, "depDate", ""));
                    flightInfo.setArrDate(MapUtils.getOrDef(map_tmp, "arrDate", ""));
                    flightInfo.setDepTime(MapUtils.getOrDef(map_tmp, "depTime", ""));
                    flightInfo.setArrTime(MapUtils.getOrDef(map_tmp, "arrTime", ""));
                    flightInfo.setFlightNo(MapUtils.getOrDef(map_tmp, "airCode", ""));
                    fInfos.add(flightInfo);
                }
                break;


            default:
                break;
        }

        return fInfos;
    }

    public static void main(String[] args) {
        BufferedReader br;
        try {
            br = new BufferedReader(new InputStreamReader(CityCountryMap.class.getClassLoader().getResourceAsStream("etl_client_sample_3.txt")));
            String line = "";
            int lineNum = 1;
            while ((line = br.readLine()) != null) {
                String[] spli = line.split("\t");
                System.out.println(lineNum);
                System.out.println(spli[3]);
                System.out.println(WapLogUtils.getPage(spli[3]));
                List<String> al = WapLogUtils.splitToList(spli[16]);
                for (String s : al) {
                    String slower = s.toLowerCase();
                    for (String sFlag : new String[]{
                            "city", "begin", "end", "no", "ca", "time", "date", "start", "end", "dep", "arr", "info", "city", "name", "type", "air", "flight", "short"
                    }) {
                        if (slower.contains(sFlag)) {
                            System.out.println(s);
                            break;
                        }
                    }
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
