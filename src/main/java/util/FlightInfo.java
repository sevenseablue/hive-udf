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
        StringBuilder sb = new StringBuilder();
        sb.append(depCity).append(delim)
                .append(arrCity).append(delim)
                .append(depDate).append(delim)
                .append(arrDate).append(delim)
                .append(flightNo).append(delim)
                .append(cabinDesc);
        return sb.toString();
    }

    public static String listToHive(List<FlightInfo> list1) {
        String delim2 = "\002";
        String delim3 = "\003";
        StringBuilder sb = new StringBuilder();

        for (FlightInfo flightInfo : list1) {
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
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
    }
}
