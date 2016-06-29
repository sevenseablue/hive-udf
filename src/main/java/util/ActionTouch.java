package util;

/**
 * Created by seven on 21/06/16.
 */
public enum ActionTouch {

    LIST1("h5/flight/flightlist"),
    LIST2("h5/flight/flightlist/"),
    ONEWAY3W("h5/flight/flightlistoneway3w"),
    ROUNDWAY3W("h5/flight/flightlistroundway3w"),
    LIST3("/h5/flight/flightlist"),
    UNDEFINEDLIST("h5/flight/undefined/h5/flight/flightlist"),
    FLIGHTDETAIL1("h5/flight/flightDetail"),
    INTERFLIGHTDETAIL("inter/iflightDetail"),
    IFLIGHTDETAIL("h5/flight/iflightDetail"),
    FLIGHTDETAIL2("/h5/flight/flightDetail"),
    FLIGHTAV("h5/flight/flightAV"),
    FLIGHTAVROUND("h5/flight/flightAVRound"),
    IFLIGHTAV("h5/flight/iflightAV"),
    OTHER("other");

    private String name;

    ActionTouch(String name) {
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
