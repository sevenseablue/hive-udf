package util;

/**
 * Created by seven on 27/06/16.
 */

import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import java.security.MessageDigest;


public class SHA256Sec {
    /**
     * SHA-256
     */
    public static String encode(byte[] data) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] digest = md.digest(data);
        HexBinaryAdapter hba = new HexBinaryAdapter();
        return hba.marshal(digest);
    }

    public static String encode(String str) throws Exception {
        if(null == str || "".equals(str)){
            return "";
        }
        return encode(str.getBytes("UTF-8"));
    }

    public static void main(String[] args) throws Exception {
        for (String str : new String[]{"13888888888", "13888888888", "15810539448", "13041142520"}) {
            System.out.println(str + "\t" + SHA256.encode(str));
        }
        if(args.length > 0) {
            String str = args[0];
            System.out.println(str + "\t" + SHA256.encode(str));
        }
    }
}
