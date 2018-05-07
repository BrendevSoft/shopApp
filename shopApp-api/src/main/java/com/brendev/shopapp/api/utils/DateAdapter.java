/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.brendev.shopapp.api.utils;

import java.util.Date;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

/**
 *
 * @author elvis
 */
public class DateAdapter extends XmlAdapter<String, Date> {

    //private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private final DateTimeFormatter dateFormat = ISODateTimeFormat.dateTime();
    @Override
    public String marshal(Date v) throws Exception {
        synchronized (dateFormat) {
            return dateFormat.print(v.toInstant().toEpochMilli());
        }
    }

    @Override
    public Date unmarshal(String v) throws Exception {
        synchronized (dateFormat) {
            return new Date(dateFormat.parseDateTime(v).getMillis());
        }
    }

}
