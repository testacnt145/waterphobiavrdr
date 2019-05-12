package com.waterphobiadr.util;

/*
 * Created by shayan.rais on 12/4/2017.
 */

import android.util.ArrayMap;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;
import java.util.Map;

public class ConversionUtil {

    //https://stackoverflow.com/questions/7355024/integer-valueof-vs-integer-parseint
    public static double convertStringToDouble(String value) {
        return Double.valueOf(value);
    }

    public static String convertIntToString(int value) {
        return String.valueOf(value);
    }

    public static int convertStringToInt(String value) {
        return Integer.valueOf(value);
    }

    public static String convertLongToString(long value) {
        return String.valueOf(value);
    }

    public static long convertStringToLong(String value) {
        return Long.valueOf(value);
    }

    public static String convertDoubleToString(Double value) {
        return String.valueOf(value);
    }

    public static int convertStringToInteger(String value) {
        return Integer.valueOf(value);
    }

    public static String convertFloatToString(float value) {
        return String.valueOf(value);
    }

    public static float convertStringToFloat(String value) {
        return Float.valueOf(value);
    }

    public static String convertBooleanToString(boolean value) {
        return String.valueOf(value);
    }


    public static Map<String, String> convertJsonObjectToMap(JSONObject jsonObject) {
        Map<String, String> map = new ArrayMap<>();
        Iterator<String> keyIterator = jsonObject.keys();
        while (keyIterator.hasNext()) {
            String key = keyIterator.next();
            try {
                Object obj = jsonObject.get(key);
                map.put(key, String.valueOf(obj));
            } catch (JSONException ignored) {
            }
        }
        return map;
    }
}
