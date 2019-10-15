package com.players.gif.DataManagers;

import java.math.BigInteger;
import java.security.MessageDigest;

public class DataFormatter {
    public static String getSHA512(String input){
        String result = null;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-512");
            digest.reset();
            digest.update(input.getBytes("utf8"));
            result = String.format("%0128x", new BigInteger(1, digest.digest()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
