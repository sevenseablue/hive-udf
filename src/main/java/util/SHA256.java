package util;

import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class SHA256 {
    /**
     * SHA-256
     */
    public static MessageDigest md;
    public static HexBinaryAdapter hba;
    static{
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        hba = new HexBinaryAdapter();
    }

    public static String encode(byte[] data) throws Exception {
        byte[] digest = md.digest(data);
        return hba.marshal(digest);
    }

    public static String encode(String str) throws Exception {
        if(null == str || "".equals(str)){
            return "";
        }
        return encode(str.getBytes("UTF-8"));
    }

    public static void main(String[] args) throws Exception {
        for (String str : new String[]{"13888888888", "13888888888", "15810539448", "13041142520", ""}) {
            System.out.println(str + "\t" + SHA256.encode(str));
        }
        if(args.length > 0) {
            String str = args[0];
            System.out.println(str + "\t" + SHA256.encode(str));
        }
    }

}