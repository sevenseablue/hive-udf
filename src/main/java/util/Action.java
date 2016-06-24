package util;

import java.util.HashMap;

/**
 * Created by seven on 21/06/16.
 */
public enum Action {

    QUERYFMIXWAYLIST("QueryFMixwayList"),
    QUERYFROUNDWAYLIST("QueryFRoundwayList"),
    QUERYFLIGHTLOWPRICE("QueryFlightLowPrice"),
    QUERYINTERFLIGHTLOWPRICE("QueryInterFlightLowPrice"),
    QUERYFMULTIWAYLIST("QueryFMultiWayList"),
    QUERYFUZZYWAYLIST("QueryFuzzywayList"),
    QUERYOTALIST("QueryOTAList"),
    QUERYFONEWAYDETAIL("QueryFOnewayDetail"),
    QUERYFROUNDWAYDETAIL("QueryFRoundwayDetail"),
    QUERYFMOREWAYDETAIL("QueryFMorewayDetail"),
    INTERFMWDETAIL("interfmwdetail"),
    INTERFOWDETAIL("interfowdetail"),
    INTERMIXFRWDETAIL("intermixfrwdetail"),
    INTERFRWDETAIL("interfrwdetail"),
    QUERYFMULTIWAYDETAIL("QueryFMultiWayDetail"),
    SRVTTSAV("SrvTtsAV"),
    SRVTTSAV4PACKAGE("SrvTtsAV4Package"),
    INTERTTSAV("interTtsAv"),
    DOMESTICFLIGHTORDERBOOKINGCONTROLLER("DomesticFlightOrderBookingController"),
    F_INTERTTSAV4ONEBILL("f_interTtsAv4OneBill"),
    INTERTTSAVNEW("interTtsAvNew"),
    INTERTTSAV4ONEBILLNEW("interTtsAv4OneBillNew"),
    FLIGHTBARGAININDEX("flightBargainIndex"),
    OTHER("other");

    private String name;

    Action(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Action{" +
                "name='" + name + '\'' +
                '}';
    }


}
