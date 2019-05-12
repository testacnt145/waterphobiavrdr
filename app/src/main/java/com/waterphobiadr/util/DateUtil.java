package com.waterphobiadr.util;

/*
 * Created by shayan.rais on 12/27/17.
 */

import android.text.format.DateFormat;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtil {

    public static String getDate(long time) {
        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        cal.setTimeInMillis(time);
        return DateFormat.format("dd-MMMM-yyyy hh:mm a", cal).toString();
    }

    public static String getCurrentDate() {
        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        cal.setTimeInMillis(new Date().getTime());
        String date = DateFormat.format("dd-MMMM-yyyy hh:mm a", cal).toString();
        return date;
    }

    public static long getCurrentTimestamp() {
        return new Date().getTime();
    }

}
