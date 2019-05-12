package com.waterphobiadr.util;

/*
 * Created by shayan.rais on 12/27/17.
 */

import android.text.format.DateFormat;

import java.util.Calendar;
import java.util.Locale;

public class DateUtil {

    public static String getDate(long time) {
        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        cal.setTimeInMillis(time * 1000);
        String date = DateFormat.format("dd-MM-yyyy", cal).toString();
        return date;
    }

}
