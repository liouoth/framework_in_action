package com.leo.shiro_thymeleaf.util;

import java.util.Random;

public class SaltUtil {
    private final static String originString = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ,./;':@!-=";
    private final static Random random = new Random();

    public static String getSalt(int length) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < length; i++) {
            stringBuffer.append(
                    originString.charAt(random.nextInt(originString.length() - 1))
            );
        }
        return stringBuffer.toString();
    }
}
