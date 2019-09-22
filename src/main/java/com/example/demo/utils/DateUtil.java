package com.example.demo.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.SimpleFormatter;

public class DateUtil {

    private final static String format = "yyyy-MM-dd HH:mm:ss";

    private static SimpleDateFormat sdf = new SimpleDateFormat(format);

    public static String timestampToString(long timestamp){
        Date date = new Date(timestamp);
        return sdf.format(date);
    }

}
