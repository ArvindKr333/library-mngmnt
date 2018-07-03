package com.example.lms.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    public static Date getDate(String d) {
        Date date = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd");
        try {
            date = simpleDateFormat.parse(d);
            System.out.println(date);
        } catch (ParseException e) {
        }
        return date;
    }




}
