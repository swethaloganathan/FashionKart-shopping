package com.ideas2it.onlineshopping.utils;

import org.apache.log4j.Logger;

import java.text.SimpleDateFormat; 
import java.util.Date;
import java.sql.Timestamp;
import java.text.ParseException;

public class DateUtils {

    private static Logger logger = Logger.getLogger(DateUtils.class);

    /**
     * <p>
     * It fetches the current date and time from the system 
     * </p>
     * 
     * @param return - it returns the date and time 
     */
    public static java.sql.Timestamp getDateTime() {
        java.util.Date today = new java.util.Date();
        return new java.sql.Timestamp(today.getTime());
    }

    /**
     * <p>
     * Method which converts the date string into timestamp
     * </p>
     *
     * @param dateString - it contains the date in string format
     * @return - it returns the date in time format
     */
    public static java.sql.Timestamp convertDate(String dateString) {
        Timestamp timestamp = null;
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.S");
            Date date = formatter.parse(dateString);
            timestamp = new Timestamp(date.getTime());
        } catch (ParseException e) {
            logger.error("Exception :" + e);
        }
        return timestamp;
    }
}
