/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.brendev.shopapp.api.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

/**
 *
 * @author elvis
 */
public class DateUtils {

    public static Date stringToDate(String date) throws ParseException {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                .parse(date);
    }

    public static String DateTimeToString(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                .format(date);
    }

    public static String DateToString(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd")
                .format(date);
    }

    public static String DateToISO(String date) throws ParseException {
        Date simpleDate = new SimpleDateFormat("dd/MM/yy")
                .parse(date);
        DateTimeFormatter dateFormat = ISODateTimeFormat.dateTime();
        return dateFormat.print(simpleDate.toInstant().toEpochMilli());
    }
    
    public static String DateToATOM(String date) throws ParseException {
        Date tempDate = new SimpleDateFormat("dd/MM/yy")
                .parse(date);
        return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'+00:00'").format(tempDate);
    }
}
