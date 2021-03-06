package util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by seven on 28/06/16.
 */
public class FlightLogTouch {
    String pageName;
    String userName;
    String isInter;
    String flightType;
    List<FlightInfo> fInfos;

    public FlightLogTouch() {
    }

    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getIsInter() {
        return isInter;
    }

    public void setIsInter(String isInter) {
        this.isInter = isInter;
    }

    public String getFlightType() {
        return flightType;
    }

    public void setFlightType(String flightType) {
        this.flightType = flightType;
    }

    public List<FlightInfo> getfInfos() {
        return fInfos;
    }

    public void setfInfos(List<FlightInfo> fInfos) {
        this.fInfos = fInfos;
    }

    @Override
    public String toString() {
        return "FlightLogTouch{" +
                "pageName='" + pageName + '\'' +
                ", userName='" + userName + '\'' +
                ", isInter='" + isInter + '\'' +
                ", flightType='" + flightType + '\'' +
                ", fInfos=" + fInfos +
                '}';
    }

    public String toHive() {
        String delim1 = "\001";
        StringBuilder sb = new StringBuilder();
        sb.append(StringUtil.nullToEmpty(pageName)).append(delim1)
                .append(StringUtil.nullToEmpty(userName)).append(delim1)
                .append(StringUtil.nullToEmpty(isInter)).append(delim1)
                .append(StringUtil.nullToEmpty(flightType)).append(delim1)
                .append(FlightInfo.listToHive(fInfos));

        return sb.toString();
    }

    public static FlightLogTouch getLogs(String action, String log) {
//        String log = (url.contains("?") ? url.substring(url.indexOf("?")+1)+"&" : "") + post;
        log = log.replaceAll("&amp;", "&");
        ActionTouch action1 = ActionClsTouch.getAction(action);
        Map<String, String> map1 = WapLogUtils.splitToMap(log);
        FlightLogTouch flightLogTouch = new FlightLogTouch();
        flightLogTouch.setPageName(TouchLogUtils.getPage(action));

        ArrayList<FlightInfo> fInfos = new ArrayList<FlightInfo>();
        FlightInfo flightInfo;
        switch (action1) {
            case OTHER:
                break;
            case LIST1:
            case LIST2:
            case LIST3:
            case UNDEFINEDLIST:
                String flightType = TouchLogUtils.flightTypeCls(MapUtils.getOrDefLower(map1, "flightType", ""));
                flightLogTouch.setFlightType(flightType);
                flightLogTouch.setIsInter(MapUtils.getOrDefLower(map1, "isInterFlight", ""));
                flightLogTouch.setUserName(MapUtils.getOrDefLower(map1, "username", ""));

                flightInfo = new FlightInfo();
                flightInfo.setDepCity(MapUtils.getOrDefLower(map1, "startCity", ""));
                flightInfo.setArrCity(MapUtils.getOrDefLower(map1, "destCity", ""));
                flightInfo.setDepCode(MapUtils.getOrDefLower(map1, "startCode", ""));
                flightInfo.setArrCode(MapUtils.getOrDefLower(map1, "destCode", ""));
                flightInfo.setDepDate(MapUtils.getOrDefLower(map1, "startDate", ""));
                fInfos.add(flightInfo);
                String backDate = MapUtils.getOrDefLower(map1, "backDate", "");
                if (flightType.equals("roundWay") || (!flightType.equals("oneWay") && !backDate.equals(""))) {
                    flightInfo = new FlightInfo();
                    flightInfo.setDepCity(MapUtils.getOrDefLower(map1, "destCity", ""));
                    flightInfo.setArrCity(MapUtils.getOrDefLower(map1, "startCity", ""));
                    flightInfo.setDepCode(MapUtils.getOrDefLower(map1, "destCode", ""));
                    flightInfo.setArrCode(MapUtils.getOrDefLower(map1, "startCode", ""));
                    flightInfo.setDepDate(backDate);
                    fInfos.add(flightInfo);
                }

                break;
            case FLIGHTDETAIL1:
            case INTERFLIGHTDETAIL:
            case IFLIGHTDETAIL:
            case FLIGHTDETAIL2:
                flightLogTouch.setIsInter(action.contains("iflight") ? "1" : "0");
                flightType = TouchLogUtils.flightTypeCls(MapUtils.getOrDefLower(map1, "flightType", ""));
                flightLogTouch.setFlightType(flightType);
                flightLogTouch.setUserName(MapUtils.getOrDefLower(map1, "username", ""));
                String transCity = MapUtils.getOrDefLower(map1, "transCity", "");
                if (transCity.equals("")) {
                    transCity = MapUtils.getOrDefLower(map1, "transferCity", "");
                }
                String[] codeInfos = TouchLogUtils.extractCode(MapUtils.getOrDefLower(map1, "code", ""));
                backDate = MapUtils.getOrDefLower(map1, "backDate", "");
                if (flightType.equals("roundWay") || (transCity.equals("") && !flightType.equals("oneWay") && !backDate.equals(""))) {
                    flightInfo = new FlightInfo();
                    flightInfo.setDepCity(MapUtils.getOrDefLower(map1, "startCity", ""));
                    flightInfo.setArrCity(MapUtils.getOrDefLower(map1, "destCity", ""));
                    flightInfo.setDepCode(MapUtils.getOrDefLower(map1, "startCode", ""));
                    flightInfo.setArrCode(MapUtils.getOrDefLower(map1, "destCode", ""));
                    flightInfo.setDepDate(MapUtils.getOrDefLower(map1, "startDate", ""));
                    flightInfo.setFlightNo(ArrayUtils.getEle(codeInfos, 1));
                    if (codeInfos[0].equals("4") || codeInfos[0].equals("8")) {
                        flightInfo.setDepCode(ArrayUtils.getEle(codeInfos, 2));
                        flightInfo.setArrCode(ArrayUtils.getEle(codeInfos, 3));
                        flightInfo.setDepDate(ArrayUtils.getEle(codeInfos, 4));
                    }
                    fInfos.add(flightInfo);

                    flightInfo = new FlightInfo();
                    flightInfo.setDepCity(MapUtils.getOrDefLower(map1, "destCity", ""));
                    flightInfo.setArrCity(MapUtils.getOrDefLower(map1, "startCity", ""));
                    flightInfo.setDepCode(MapUtils.getOrDefLower(map1, "destCode", ""));
                    flightInfo.setArrCode(MapUtils.getOrDefLower(map1, "startCode", ""));
                    flightInfo.setDepDate(backDate);
                    if (codeInfos[0].equals("8")) {
                        flightInfo.setFlightNo(ArrayUtils.getEle(codeInfos, 5));
                        flightInfo.setDepCode(ArrayUtils.getEle(codeInfos, 6));
                        flightInfo.setArrCode(ArrayUtils.getEle(codeInfos, 7));
                        flightInfo.setDepDate(ArrayUtils.getEle(codeInfos, 8));
                    } else if (codeInfos[0].equals("2")) {
                        flightInfo.setFlightNo(ArrayUtils.getEle(codeInfos, 2));
                    }
                    fInfos.add(flightInfo);
                }
                // oneWay mixWay
                else {
                    flightInfo = new FlightInfo();
                    flightInfo.setDepCity(MapUtils.getOrDefLower(map1, "startCity", ""));
                    flightInfo.setDepCode(MapUtils.getOrDefLower(map1, "startCode", ""));
                    flightInfo.setDepDate(MapUtils.getOrDefLower(map1, "startDate", ""));
                    flightInfo.setFlightNo(ArrayUtils.getEle(codeInfos, 1));
                    if (codeInfos[0].equals("4") || codeInfos[0].equals("8")) {
                        flightInfo.setDepCode(ArrayUtils.getEle(codeInfos, 2));
                        flightInfo.setArrCode(ArrayUtils.getEle(codeInfos, 3));
                        flightInfo.setDepDate(ArrayUtils.getEle(codeInfos, 4));
                    }

                    if (transCity.equals("")) {
                        if(StringUtil.isNENs(flightInfo.getArrCode()))flightInfo.setArrCode(MapUtils.getOrDefLower(map1, "destCode", ""));
                        flightInfo.setArrCity(MapUtils.getOrDefLower(map1, "destCity", ""));
                        fInfos.add(flightInfo);
                    } else {
                        flightInfo.setArrCity(transCity);
                        fInfos.add(flightInfo);

                        flightInfo = new FlightInfo();
                        flightInfo.setDepCity(transCity);
                        flightInfo.setArrCity(MapUtils.getOrDefLower(map1, "destCity", ""));
                        flightInfo.setArrCode(MapUtils.getOrDefLower(map1, "destCode", ""));
                        flightInfo.setDepDate(MapUtils.getOrDefLower(map1, "startDate", ""));
                        if (codeInfos[0].equals("8")) {
                            flightInfo.setFlightNo(ArrayUtils.getEle(codeInfos, 5));
                            flightInfo.setDepCode(ArrayUtils.getEle(codeInfos, 6));
                            flightInfo.setArrCode(ArrayUtils.getEle(codeInfos, 7));
                            flightInfo.setDepDate(ArrayUtils.getEle(codeInfos, 8));
                        } else if (codeInfos[0].equals("2")) {
                            flightInfo.setFlightNo(ArrayUtils.getEle(codeInfos, 2));
                        }
                        fInfos.add(flightInfo);
                    }
                }
                break;
            case FLIGHTAV:
                flightLogTouch.setIsInter("0");
                flightType = TouchLogUtils.flightTypeCls(MapUtils.getOrDefLower(map1, "flightType", ""));
                flightLogTouch.setFlightType(flightType);
                flightLogTouch.setUserName(MapUtils.getOrDefLower(map1, "username", ""));
                transCity = MapUtils.getOrDefLower(map1, "transCity", "");
                if (transCity.equals("")) {
                    transCity = MapUtils.getOrDefLower(map1, "transferCity", "");
                }
                if (transCity.equals("")) {
                    flightInfo = new FlightInfo();
                    flightInfo.setDepCity(MapUtils.getOrDefLower(map1, "startCity", ""));
                    flightInfo.setDepCode(MapUtils.getOrDefLower(map1, "startCityCode", ""));
                    flightInfo.setDepDate(MapUtils.getOrDefLower(map1, "startDate", ""));
                    flightInfo.setDepTime(MapUtils.getOrDefLower(map1, "startTime", ""));
                    flightInfo.setArrCity(MapUtils.getOrDefLower(map1, "destCity", ""));
                    flightInfo.setArrCode(MapUtils.getOrDefLower(map1, "destCityCode", ""));
                    flightInfo.setFlightNo(MapUtils.getOrDefLower(map1, "airLine", ""));
                    flightInfo.setCabin(MapUtils.getOrDefLower(map1, "cabin", ""));
                    flightInfo.setCompanyCode(MapUtils.getOrDefLower(map1, "flightCode", ""));
                    flightInfo.setPlaneType(MapUtils.getOrDefLower(map1, "planeType", ""));
                    fInfos.add(flightInfo);
                } else {
                    flightInfo = new FlightInfo();
                    flightInfo.setDepCity(MapUtils.getOrDefLower(map1, "startCity", ""));
                    flightInfo.setDepCode(MapUtils.getOrDefLower(map1, "startCityCode", ""));
                    flightInfo.setDepDate(MapUtils.getOrDefLower(map1, "startDate", ""));
                    flightInfo.setDepTime(MapUtils.getOrDefLower(map1, "startTime", ""));
                    flightInfo.setArrCity(transCity);
                    flightInfo.setFlightNo(MapUtils.getOrDefLower(map1, "airLine", ""));
                    flightInfo.setCabin(MapUtils.getOrDefLower(map1, "cabin", ""));
                    flightInfo.setCompanyCode(MapUtils.getOrDefLower(map1, "flightCode", ""));
                    flightInfo.setPlaneType(MapUtils.getOrDefLower(map1, "planeType", ""));
                    fInfos.add(flightInfo);

                    flightInfo = new FlightInfo();
                    flightInfo.setDepCity(transCity);
                    flightInfo.setDepDate(MapUtils.getOrDefLower(map1, "backDate", ""));
                    flightInfo.setArrCity(MapUtils.getOrDefLower(map1, "destCity", ""));
                    flightInfo.setArrCode(MapUtils.getOrDefLower(map1, "destCityCode", ""));
                    String originCode = MapUtils.getOrDefLower(map1, "originCode", "");
                    flightInfo.setFlightNo(StringUtil.sub(originCode, '/', originCode.length()));
                    fInfos.add(flightInfo);
                }

                break;

            case FLIGHTAVROUND:
                flightLogTouch.setIsInter("0");
                flightType = TouchLogUtils.flightTypeCls(MapUtils.getOrDefLower(map1, "flightType", ""));
                flightLogTouch.setFlightType(flightType);
                flightLogTouch.setUserName(MapUtils.getOrDefLower(map1, "username", ""));
                flightInfo = new FlightInfo();
                flightInfo.setDepCity(MapUtils.getOrDefLower(map1, "startCity", ""));
                flightInfo.setArrCity(MapUtils.getOrDefLower(map1, "destCity", ""));
                flightInfo.setDepCode(MapUtils.getOrDefLower(map1, "gdeptAirportCode", ""));
                flightInfo.setDepDate(MapUtils.getOrDefLower(map1, "gdeptDate", ""));
                flightInfo.setDepTime(MapUtils.getOrDefLower(map1, "gdeptTime", ""));
                flightInfo.setArrCode(MapUtils.getOrDefLower(map1, "garriAirportCode", ""));
                flightInfo.setFlightNo(MapUtils.getOrDefLower(map1, "gairCode", ""));
                flightInfo.setCabin(MapUtils.getOrDefLower(map1, "gcangwei", ""));
                flightInfo.setCompanyCode(MapUtils.getOrDefLower(map1, "gairCompanyCode", ""));
                flightInfo.setPlaneType(MapUtils.getOrDefLower(map1, "gplanetype", ""));
                fInfos.add(flightInfo);

                flightInfo = new FlightInfo();
                flightInfo.setDepCity(MapUtils.getOrDefLower(map1, "destCity", ""));
                flightInfo.setArrCity(MapUtils.getOrDefLower(map1, "startCity", ""));
                flightInfo.setDepCode(MapUtils.getOrDefLower(map1, "bdeptAirportCode", ""));
                flightInfo.setDepDate(MapUtils.getOrDefLower(map1, "bdeptDate", ""));
                flightInfo.setDepTime(MapUtils.getOrDefLower(map1, "bdeptTime", ""));
                flightInfo.setArrCode(MapUtils.getOrDefLower(map1, "barriAirportCode", ""));
                flightInfo.setFlightNo(MapUtils.getOrDefLower(map1, "bairCode", ""));
                flightInfo.setCabin(MapUtils.getOrDefLower(map1, "bcangwei", ""));
                flightInfo.setCompanyCode(MapUtils.getOrDefLower(map1, "bairCompanyCode", ""));
                flightInfo.setPlaneType(MapUtils.getOrDefLower(map1, "bplanetype", ""));
                fInfos.add(flightInfo);
                break;

            case IFLIGHTAV:
                flightLogTouch.setIsInter("1");
                flightType = TouchLogUtils.flightTypeCls(MapUtils.getOrDefLower(map1, "flightType", ""));
                flightLogTouch.setFlightType(flightType);
                flightLogTouch.setUserName(MapUtils.getOrDefLower(map1, "username", ""));
                int count = 0;
                String[] cabinArr = MapUtils.getOrDefLower(map1, "cabin", "").split("~|/");
                for (String dire : new String[]{"g", "b"}) {
                    for (int i = 1; i <= 2; i++) {
                        String dac = MapUtils.getOrDefLower(map1, dire+"dac" + i, "");
                        if (!dac.equals("")) {
                            flightInfo = new FlightInfo();
                            flightInfo.setDepCode(MapUtils.getOrDefLower(map1, dire + "dac" + i, ""));
                            flightInfo.setArrCode(MapUtils.getOrDefLower(map1, dire + "aac" + i, ""));
                            flightInfo.setDepDate(MapUtils.getOrDefLower(map1, dire + "dd" + i, ""));
                            flightInfo.setDepTime(MapUtils.getOrDefLower(map1, dire + "dt" + i, ""));
                            flightInfo.setArrDate(MapUtils.getOrDefLower(map1, dire + "ad" + i, ""));
                            flightInfo.setArrTime(MapUtils.getOrDefLower(map1, dire + "at" + i, ""));
                            flightInfo.setFlightNo(MapUtils.getOrDefLower(map1, dire + "ac" + i, ""));
                            flightInfo.setCabin(ArrayUtils.getEle(cabinArr, count));
                            flightInfo.setPlaneType(MapUtils.getOrDefLower(map1, dire + "pt" + i, ""));
                            fInfos.add(flightInfo);
                            count += 1;
                        }
                    }
                }
                break;

            case ONEWAY3W:
//                flightType = MapUtils.getOrDefLower(map1, "searchType", "");
                flightType = "oneWay";
                flightLogTouch.setFlightType(flightType);
                flightLogTouch.setIsInter(MapUtils.getOrDefLower(map1, "isInterFlight", ""));
                flightLogTouch.setUserName(MapUtils.getOrDefLower(map1, "username", ""));
                flightInfo = new FlightInfo();
                flightInfo.setDepCity(MapUtils.getOrDefLower(map1, "searchDepartureAirport", ""));
                flightInfo.setArrCity(MapUtils.getOrDefLower(map1, "searchArrivalAirport", ""));
                flightInfo.setDepCode(MapUtils.getOrDefLower(map1, "fromCode", ""));
                flightInfo.setArrCode(MapUtils.getOrDefLower(map1, "toCode", ""));
                flightInfo.setDepDate(MapUtils.getOrDefLower(map1, "searchDepartureTime", ""));
                fInfos.add(flightInfo);
                break;


            case ROUNDWAY3W:
                flightType = "roundWay";
                flightLogTouch.setFlightType(flightType);
                flightLogTouch.setIsInter(MapUtils.getOrDefLower(map1, "isInterFlight", ""));
                flightLogTouch.setUserName(MapUtils.getOrDefLower(map1, "username", ""));
                flightInfo = new FlightInfo();
                flightInfo.setDepCity(MapUtils.getOrDefLower(map1, "fromCity", ""));
                flightInfo.setArrCity(MapUtils.getOrDefLower(map1, "toCity", ""));
                flightInfo.setDepCode(MapUtils.getOrDefLower(map1, "fromCode", ""));
                flightInfo.setArrCode(MapUtils.getOrDefLower(map1, "toCode", ""));
                flightInfo.setDepDate(MapUtils.getOrDefLower(map1, "fromDate", ""));
                fInfos.add(flightInfo);
                flightInfo = new FlightInfo();
                flightInfo.setDepCity(MapUtils.getOrDefLower(map1, "toCity", ""));
                flightInfo.setArrCity(MapUtils.getOrDefLower(map1, "fromCity", ""));
                flightInfo.setDepCode(MapUtils.getOrDefLower(map1, "toCode", ""));
                flightInfo.setArrCode(MapUtils.getOrDefLower(map1, "fromCode", ""));
                flightInfo.setDepDate(MapUtils.getOrDefLower(map1, "toDate", ""));
                fInfos.add(flightInfo);
                break;
            default:
                break;
        }
        WapLogUtils.update(fInfos);
        flightLogTouch.setfInfos(fInfos);

        return flightLogTouch;
    }


    public static FlightLogTouch getLogs(String action, String url, String post) {
        String log = (url.contains("?") ? url.substring(url.indexOf("?")+1)+"&" : "") + post;
        return getLogs(action, log);
    }

}
