package com.bici.tsdb.common.util;

/**
 * StringUtil
 * @author: Overload
 * @date: 2018/5/3 9:40
 * @version: 1.0
 */
public class StringUtil {

    private StringUtil(){}

    public static boolean isBlank(String string) {
        return string == null || "".equals(string) || string.matches("[ ]+");
    }
}
