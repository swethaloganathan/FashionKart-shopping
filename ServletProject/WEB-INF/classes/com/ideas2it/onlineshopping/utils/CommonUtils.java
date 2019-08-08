package com.ideas2it.onlineshopping.utils;

import java.util.Scanner;
import java.util.StringJoiner;

import com.ideas2it.onlineshopping.constant.Constant;

public class CommonUtils {

    /**
     * <p>
     * To generate random id  
     * </p>
     *
     * @return - it returns a six digit random number between the range
     */
    public static int getId() {
        int min = 100000;
        int max = 999999;
        return (int)(Math.random() * (max - min)) + min;
    }

    /**
     * <p>
     * It is used to produce Stock Keeping Unit(SKU) ID
     * </p>
     * 
     * @return - it returns the SKU id
     */
    public static String skuGenerator() {
        StringBuilder stringBuilder = new StringBuilder();
        String AlphaNumeric = Constant.ALPHA_NUMERIC;
        for (int i = 0; i < 6; i++) {
            int index = (int)(AlphaNumeric.length()* Math.random());
            stringBuilder.append(AlphaNumeric.charAt(index)); 
        }
        return stringBuilder.toString();
    }

    /**
     * <p>
     * In this method one or more string arguments are joined using variable
     * length argument feature
     * </p>
     *
     * @param stringToJoin - contains all the string parameters
     * @return - it returns a single string after joining all the strings
     */
    public static String toJoinString(String... stringToJoin) {
        return String.join(" || ", stringToJoin);
    }

    /**
     * <p>
     * In this method one or more string arguments are joined using variable
     * length argument feature
     * </p>
     *
     * @param stringToJoin - contains all the string parameters
     * @return - it returns a single string after joining all the strings
     */
    public static String joinStrings(String... stringToJoin) {
        return String.join("\n", stringToJoin);
    }
}
