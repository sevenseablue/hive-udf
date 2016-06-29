package util;

import java.util.HashMap;

/**
 * Created by seven on 21/06/16.
 */
public class ActionCls {
    public static final HashMap<String, Action> actionHashMap = new HashMap<String, Action>(127);

    static {
        actionHashMap.put("QueryFMixwayList", Action.QUERYFMIXWAYLIST);
        actionHashMap.put("QueryFRoundwayList", Action.QUERYFROUNDWAYLIST);
        actionHashMap.put("QueryFlightLowPrice", Action.QUERYFLIGHTLOWPRICE);
        actionHashMap.put("QueryInterFlightLowPrice", Action.QUERYINTERFLIGHTLOWPRICE);
        actionHashMap.put("QueryFMultiWayList", Action.QUERYFMULTIWAYLIST);
        actionHashMap.put("QueryFuzzywayList", Action.QUERYFUZZYWAYLIST);
        actionHashMap.put("QueryOTAList", Action.QUERYOTALIST);
        actionHashMap.put("QueryFOnewayDetail", Action.QUERYFONEWAYDETAIL);
        actionHashMap.put("QueryFRoundwayDetail", Action.QUERYFROUNDWAYDETAIL);
        actionHashMap.put("QueryFMorewayDetail", Action.QUERYFMOREWAYDETAIL);
        actionHashMap.put("interfmwdetail", Action.INTERFMWDETAIL);
        actionHashMap.put("interfowdetail", Action.INTERFOWDETAIL);
        actionHashMap.put("intermixfrwdetail", Action.INTERMIXFRWDETAIL);
        actionHashMap.put("interfrwdetail", Action.INTERFRWDETAIL);
        actionHashMap.put("QueryFMultiWayDetail", Action.QUERYFMULTIWAYDETAIL);
        actionHashMap.put("SrvTtsAV", Action.SRVTTSAV);
        actionHashMap.put("SrvTtsAV4Package", Action.SRVTTSAV4PACKAGE);
        actionHashMap.put("interTtsAv", Action.INTERTTSAV);
        actionHashMap.put("DomesticFlightOrderBookingController", Action.DOMESTICFLIGHTORDERBOOKINGCONTROLLER);
        actionHashMap.put("f_interTtsAv4OneBill", Action.F_INTERTTSAV4ONEBILL);
        actionHashMap.put("interTtsAvNew", Action.INTERTTSAVNEW);
        actionHashMap.put("interTtsAv4OneBillNew", Action.INTERTTSAV4ONEBILLNEW);
        actionHashMap.put("flightBargainIndex", Action.FLIGHTBARGAININDEX);
    }

    public static Action getAction(String str) {
        if (!actionHashMap.containsKey(str)) {
            return Action.OTHER;
        } else {
            return actionHashMap.get(str);
        }
    }

    public static void main(String[] args) {
        System.out.println(ActionCls.getAction("DomesticFlightOrderBookingController") == Action.DOMESTICFLIGHTORDERBOOKINGCONTROLLER);
    }
}
