package util;

import junit.framework.TestCase;

/**
 * Created by seven on 22/06/16.
 */
public class WapLogUtilsTest extends TestCase {


    public void testSplitToMap() throws Exception {
        for (String log : new String[]{
                "&",
                "a=b",
                "finfo={shortName=九元&companyCode=AQ1026&planeType=null&depDate=2016-06-14&depTime=23:40&depAirport=美兰机场&depAirportCode=null&arrTime=01:00&arrAirport=白云机场&arrAirportCode=null}"
        }) {
            System.out.println(MapUtils.getOrDefLower(WapLogUtils.splitToMap(log), "finfo", ""));
        }
    }

    public void testGetObjects() throws Exception {
        for (String log : new String[]{
                "&",
                "a=b",
                "a=b&",
                "a=b&c=d",
                "a=b&c=[{d=4&e=[{f=6&g=7&h=[{f2=6&g2=7},{f2=8&g2=9}]&}, {f=6&g=7&h=[{f2=61&g2=71},{f2=81&g2=91}]&}]&e2=[{f2=6&g2=7}]}, {d=4&e=[{f=6&g=7&h=[{f2=6&g2=7},{f2=8&g2=9}]&}, {f=6&g=7&h=[{f2=61&g2=71},{f2=81&g2=91}]&}]&e2=[{f2=6&g2=7}]}]&heihei"
        }) {
            System.out.println(WapLogUtils.getObjects(log, new String[]{"c", "e", "h"}, new char[]{'l', 'l', 'l'}));
        }
        String log1 = "a=b&c=[{d=5&e=[{f=6&g=7&h=[{f2=6&g2=7},{f2=8&g2=9}]&}, {f=6&g=7&h=[{f2=61&g2=71},{f2=81&g2=91}]&}]&e2=[{f2=6&g2=7}]}, {d=4&e=[{f=6&g=7&h=[{f2=6&g2=7},{f2=8&g2=9}]&}, {f=6&g=7&h=[{f2=61&g2=71},{f2=81&g2=91}]&}]&e2=[{f2=6&g2=7}]}]&heihei";
        assertEquals(WapLogUtils.getObjects(log1, new String[]{"c", "e", "h"}, new char[]{'l', 'l', 'l'}).toString(), "[f2=6&g2=7, f2=8&g2=9, f2=61&g2=71, f2=81&g2=91, f2=6&g2=7, f2=8&g2=9, f2=61&g2=71, f2=81&g2=91]");
        assertEquals(WapLogUtils.getObjects(log1, new String[]{"c", "e", "h", "f2"}, new char[]{'l', 'l', 'l', 'o'}).toString(), "[6, 8, 61, 81, 6, 8, 61, 81]");

        assertEquals(WapLogUtils.getObjects(log1, new String[]{"c", "e", "h"}, new char[]{'l', 'l', 'l'}, new String[]{".*d=4.*", "", ""}, new String[]{"", "", ""}).toString(), "[f2=6&g2=7, f2=8&g2=9, f2=61&g2=71, f2=81&g2=91]");
        assertEquals(WapLogUtils.getObjects(log1, new String[]{"c", "e", "h"}, new char[]{'l', 'l', 'l'}, new String[]{"", "", ""}, new String[]{".*d=[45].*", "", ""}).toString(), "[]");

        for (String log : new String[]{
                "cabin=V&a=1&flightInfo=[{goInfos=[{cabin=&cabinDesc=经济舱&depCity=福州&arrCity=香港&depCode=FOC&arrCode=HKG&depDate=2016-06-17&depTime=19:10&arrDate=2016-06-17&arrTime=20:55&airlineName=香港航空&airlineShortName=香港航空&airlineCode=&flightNo=HX148&mainAirlineName=香港航空&mainAirlineShortName=香港航空&mainAirlineCode=HX148&mainFlightNo=HX148&depAirport=长乐&depApFull=福州长乐机场&depApCode=FOC&arrAirport=香港&arrApFull=香港国际机场&arrApCode=HKG&depTerminal=&arrTerminal=&flightTime=1小时45分&flightTimeDesc=&flightTimes=0&distance=&planeType=空客320(中)&correct=&meal=&fewTickets=&crossDays=&stopNotice=&stopDesc=&isCheckIn=false&isShareFlight=false&logo=&label=&transInfoText=&fuzzyDepTimeArea=&fuzzyArrTimeArea=&fuzzyFlightTime=&}]&backInfos=[{cabin=&cabinDesc=经济舱&depCity=香港&arrCity=福州&depCode=HKG&arrCode=FOC&depDate=2016-06-22&depTime=22:05&arrDate=2016-06-22&arrTime=23:50&airlineName=香港航空&airlineShortName=香港航空&airlineCode=&flightNo=HX173&mainAirlineName=香港航空&mainAirlineShortName=香港航空&mainAirlineCode=HX173&mainFlightNo=HX173&depAirport=香港&depApFull=香港国际机场&depApCode=HKG&arrAirport=长乐&arrApFull=福州长乐机场&arrApCode=FOC&depTerminal=&arrTerminal=&flightTime=1小时45分&flightTimeDesc=&flightTimes=0&distance=&planeType=空客320(中)&correct=&meal=&fewTickets=&crossDays=&stopNotice=&stopDesc=&isCheckIn=false&isShareFlight=false&logo=&label=&transInfoText=&fuzzyDepTimeArea=&fuzzyArrTimeArea=&fuzzyFlightTime=&}]&goCrossDay=&backCrossDay=&goFlightTime=&backFlightTime=&goTransNotice=[]&backTransNotice=[]&combineTransNotice=&desc=&title=&topDesc=&goOptimizeInfo=com.flight.mobile.vo.biz.inter.common.InterFInfo$OptimizeInfo@39a52c4a&backOptimizeInfo=com.flight.mobile.vo.biz.inter.common.InterFInfo$OptimizeInfo@ed3a27&collapsible=true&}]&c="
        }) {
            System.out.println(WapLogUtils.getObjects(log, new String[]{"cabin"}, new char[]{'o'}));
            System.out.println(WapLogUtils.getObjects(log, new String[]{"flightInfo", "goInfos"}, new char[]{'l', 'l'}));
            System.out.println(WapLogUtils.getObjects(log, new String[]{"flightInfo", "backInfos"}, new char[]{'l', 'l'}));
        }
        for (String log : new String[]{
                "a=1&bookingOut=[{tripType=1&childPrintPriceTag=&adultSellPriceTag=BTF3&adultBarePriceTag=BTF1&barePrice=1067&adultSellPrice=982&childPrintPrice=0&flightInfo=[{flightNo=HO1270&fcode=HO&depCity=成都&arrCity=上海&depAirport=双流机场&arrAirport=虹桥机场&depDate=2016-06-13&arrDate=2016-06-13&depTime=12:15&arrTime=16:40&depCode=CTU&arrCode=SHA&depTerminal=T2&arrTerminal=T2&cabin=V&cabinDesc=经济舱&childCabin=&codeShare=false&shareAirLine=&shareAirShortName=&planeType=空客320(中)&meal=true&stopInfo=false&stopCity=&flightTime=4小时25分钟&flightDistance=1782&correct=准点率80%&adultConstructionFee=50&childConstructionFee=0&adultFuelTax=0&childFuelTax=0&adultTgq=成人票 退票费：起飞前2小时之前 ￥212/人 　　　　起飞前2小时之后 ￥318/人 改期费：起飞前2小时之前 ￥106/人 　　　　起飞前2小时之后 ￥212/人 签　转：不可签转 &childTgq=&airName=吉祥航&}]&otaDetailPrice=982&},{tripType=2&childPrintPriceTag=CI&adultSellPriceTag=BTF3&adultBarePriceTag=BTF1&barePrice=733&adultSellPrice=652&childPrintPrice=880&flightInfo=[{flightNo=EU6678&fcode=EU&depCity=上海&arrCity=成都&depAirport=浦东机场&arrAirport=双流机场&depDate=2016-06-15&arrDate=2016-06-16&depTime=21:40&arrTime=01:05&depCode=PVG&arrCode=CTU&depTerminal=T2&arrTerminal=T2&cabin=Z&cabinDesc=经济舱&childCabin=Y&codeShare=false&shareAirLine=&shareAirShortName=&planeType=空客320(中)&meal=false&stopInfo=false&stopCity=&flightTime=3小时25分钟&flightDistance=1782&correct=准点率82%&adultConstructionFee=50&childConstructionFee=0&adultFuelTax=0&childFuelTax=0&adultTgq=成人票 退改签规则以航空公司最新规定为准，可咨询客服电话（10101234） &childTgq=&airName=成都航&}]&otaDetailPrice=652&}]&c="
        }) {
            System.out.println(WapLogUtils.getObjects(log, new String[]{"cabin"}, new char[]{'o'}));
            System.out.println(WapLogUtils.getObjects(log, new String[]{"bookingOut", "flightInfo"}, new char[]{'l', 'l'}));
        }


    }

}