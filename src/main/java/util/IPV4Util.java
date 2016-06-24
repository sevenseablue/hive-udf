package util;

import org.apache.commons.lang3.Range;
import org.apache.commons.lang3.StringUtils;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class IPV4Util {
    public static final String SLASH = "/";
    public static final String POINT = ".";
    public static final String BARS = "-";

    public static byte[] ipToBytesByInet(String ipAddr)
            throws UnknownHostException {
        return InetAddress.getByName(ipAddr).getAddress();
    }

    public static int bytesToInt(byte[] bytes) {
        if (bytes.length != 4) {
            throw new IllegalArgumentException("无效的ip byte数组");
        }
        int addr = bytes[3] & 0xFF;
        addr |= bytes[2] << 8 & 0xFF00;
        addr |= bytes[1] << 16 & 0xFF0000;
        addr |= bytes[0] << 24 & 0xFF000000;
        return addr;
    }

    public static int ipToInt(String ipAddr) throws UnknownHostException {
        return bytesToInt(ipToBytesByInet(ipAddr));
    }

    public static String intToIp(int ipInt) {
        return (ipInt >> 24 & 0xFF) + "." + (ipInt >> 16 & 0xFF) + "." + (ipInt >> 8 & 0xFF) + "." + (ipInt & 0xFF);
    }

    public static Range<Integer> getIPRange(String ipAndMask)
            throws UnknownHostException {
        if (StringUtils.isBlank(ipAndMask)) {
            return null;
        }

        if (ipAndMask.contains("/"))
            return cidrIpToRange(ipAndMask);
        if (ipAndMask.contains("-")) {
            return ipSegmentToRange(ipAndMask);
        }
        return singleIpToRange(ipAndMask);
    }

    public static Range<Integer> ipSegmentToRange(String ipSegment)
            throws UnknownHostException {
        if (StringUtils.isBlank(ipSegment)) {
            return null;
        }

        String[] ips = ipSegment.split("-");
        if (ips.length == 2)
            return Range.between(Integer.valueOf(ipToInt(ips[0])), Integer.valueOf(ipToInt(ips[1])));
        if (ips.length == 1) {
            return Range.between(Integer.valueOf(ipToInt(ips[0])), Integer.valueOf(ipToInt(ips[0])));
        }
        return null;
    }

    public static Range<Integer> cidrIpToRange(String ipAndMask)
            throws UnknownHostException {
        if (StringUtils.isBlank(ipAndMask)) {
            throw new IllegalArgumentException("用CIDR方式表示的ip段地址为空");
        }

        String[] ipArr = ipAndMask.split("/");
        if (ipArr.length != 2) {
            throw new IllegalArgumentException(String.format("CIDR IP段地址格式错误%", new Object[]{ipAndMask}));
        }
        int netMask = Integer.valueOf(ipArr[1].trim()).intValue();
        if ((netMask < 0) || (netMask > 31)) {
            throw new IllegalArgumentException(String.format("CIDR IP段地址掩码错误%s", new Object[]{ipAndMask}));
        }
        int ipInt = ipToInt(ipArr[0]);
        int netIP = ipInt & -1 << 32 - netMask;
        int hostScope = -1 >>> netMask;
        return Range.between(Integer.valueOf(netIP), Integer.valueOf(netIP + hostScope));
    }

    public static Range<Integer> singleIpToRange(String ip)
            throws UnknownHostException {
        if (StringUtils.isBlank(ip)) {
            return null;
        }
        return Range.between(Integer.valueOf(ipToInt(ip)), Integer.valueOf(ipToInt(ip)));
    }

    public static void main(String[] args) throws UnknownHostException {
        System.out.println(IPV4Util.getIPRange("1.0.0.0/24"));
    }

}