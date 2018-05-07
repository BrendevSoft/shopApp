/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.brendev.shopapp.api.utils;

import java.util.Date;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author elvis
 */
public class LogUtils {

    private static final Logger logger = LogManager.getLogger(LogUtils.class);

    public LogUtils() {

    }

    public static boolean init() {

        return true;
    }

    public static boolean write(String loglevel, String event, String response) {
        System.out.println("in logs");
        System.setProperty("org.apache.logging.log4j.simplelog.StatusLogger.level", "TRACE");
        //System.setProperty("-Dlog4j.configurationFile", "/src/main/resources/log4j2.xml");

        logger.debug("TRACE", DateUtils.DateTimeToString(new Date()), loglevel, event,response);

        return true;
    }
    
    /*
    public static void main(String[] args) {
        LogUtils.write("ECO-CCP-API", "INFO", "HISTORY", "15", "0", "0", "2235789", "demande d'historique", "data");

    }
    */
}
