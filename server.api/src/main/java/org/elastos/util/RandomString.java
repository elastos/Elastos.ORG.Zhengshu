package org.elastos.util;

/**
 * Created by wanghan on 2019/1/15.
 */
public class RandomString {
    private static String LettersAndNumbersLib = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    private static String CapitalLettersLib = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private static String SmallLettersLib = "abcdefghijklmnopqrstuvwxyz";

    private static String NumbersLib = "0123456789";

    //获取指定位数的随机字符串(包含小写字母、大写字母、数字,0<length)
    private static String getRandomString(int length, String lib) {
        StringBuffer buffer = new StringBuffer();
        int len = lib.length();
        for (int i = 0; i < length; i++) {
            buffer.append(lib.charAt((int) Math.round(Math.random() * (len - 1))));
        }
        return buffer.toString();
    }

    public static String createN(int length) {
        String lib = NumbersLib;
        return getRandomString(length, lib);
    }

    public static String createSLN(int length) {
        String lib = SmallLettersLib;
        return getRandomString(length, lib);
    }

    public static String createCLN(int length) {
        String lib = CapitalLettersLib;
        return getRandomString(length, lib);
    }

    public static String createCSLN(int length) {
        String lib = LettersAndNumbersLib;
        return getRandomString(length, lib);
    }

}
