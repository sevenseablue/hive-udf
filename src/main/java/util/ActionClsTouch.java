package util;

import java.util.HashMap;

/**
 * Created by seven on 21/06/16.
 */
public class ActionClsTouch {
    public static final HashMap<String, ActionTouch> actionHashMap = new HashMap<String, ActionTouch>(57);

    static {
        actionHashMap.put("h5/flight/flightlist", ActionTouch.LIST1);
        actionHashMap.put("h5/flight/flightlist/", ActionTouch.LIST2);
        actionHashMap.put("h5/flight/flightlistoneway3w", ActionTouch.ONEWAY3W);
        actionHashMap.put("h5/flight/flightlistroundway3w", ActionTouch.ROUNDWAY3W);
        actionHashMap.put("/h5/flight/flightlist", ActionTouch.LIST3);
        actionHashMap.put("h5/flight/undefined/h5/flight/flightlist", ActionTouch.UNDEFINEDLIST);
        actionHashMap.put("h5/flight/flightDetail", ActionTouch.FLIGHTDETAIL1);
        actionHashMap.put("inter/iflightDetail", ActionTouch.INTERFLIGHTDETAIL);
        actionHashMap.put("h5/flight/iflightDetail", ActionTouch.IFLIGHTDETAIL);
        actionHashMap.put("/h5/flight/flightDetail", ActionTouch.FLIGHTDETAIL2);
        actionHashMap.put("h5/flight/flightAV", ActionTouch.FLIGHTAV);
        actionHashMap.put("h5/flight/flightAVRound", ActionTouch.FLIGHTAVROUND);
        actionHashMap.put("h5/flight/iflightAV", ActionTouch.IFLIGHTAV);
    }

    public static ActionTouch getAction(String str) {
        if (!actionHashMap.containsKey(str)) {
            return ActionTouch.OTHER;
        } else {
            return actionHashMap.get(str);
        }
    }

    public static void main(String[] args) {
        System.out.println(ActionClsTouch.getAction("h5/flight/flightlist/") == ActionTouch.LIST2);
    }
}
