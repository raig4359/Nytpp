package com.firsteconomy.nytapp.util;

/**
 * Created by Gaurav on 13-08-2018.
 */

public class StringUtils {
    public static String capitalize(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
}
