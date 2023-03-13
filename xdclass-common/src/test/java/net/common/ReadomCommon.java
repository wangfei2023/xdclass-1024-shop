package net.common;

import java.util.Random;

public class ReadomCommon {
    private static final String ALL_CHAR_NUM = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    public  static  String getRandom(int length){
        Random random = new Random();
        StringBuilder saltString = new StringBuilder(length);
        for (int i = 1; i <= length; ++i) {
        saltString.append(ALL_CHAR_NUM.charAt(random.nextInt(ALL_CHAR_NUM.length())));
        }
        return saltString.toString();
    }

}
