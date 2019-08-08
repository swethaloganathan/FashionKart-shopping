package com.ideas2it.onlineshopping.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {

    public static Boolean validatePhoneNumber(String phoneNumber) {
        if (phoneNumber.matches("(0/91)?[7-9][0-9]{9}")) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    public static Boolean validateEmail(String email) {
        if (email.matches("^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@"
                             + "(?:[a-zA-Z0-9-]+\\.)+[a-z" + "A-Z]{2,7}$")) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}
