package com.waterphobiadr.data.local.pref;

import android.content.Context;
import android.content.SharedPreferences;
import com.waterphobiadr.App;
import java.util.Set;

public final class Pref {
	//saved in internal location : com.disrupt.savyour/shared_prefs/com.disrupt.savyour.xml

	private static SharedPreferences get() {
        return App.getInstance().getSharedPreferences(App.PACKAGE_NAME, Context.MODE_PRIVATE);
    }

	private static String getPref(String pref, String def) {
		SharedPreferences prefs = Pref.get();
		String val = prefs.getString(pref, def);

		if (val == null || val.equals("") || val.equals("null"))
			return def;
		else
			return val;
	}

	private static void putPref(String pref, String val) {
		SharedPreferences prefs = Pref.get();
		SharedPreferences.Editor editor = prefs.edit();

		editor.putString(pref, val);
		editor.apply();
	}
	
	private static int getIntPref(String pref, int def) {
		SharedPreferences prefs = Pref.get();
        return prefs.getInt(pref, def);
	}

	private static void putIntPref(String pref, int val) {
		SharedPreferences prefs = Pref.get();
		SharedPreferences.Editor editor = prefs.edit();
		editor.putInt(pref, val);
		editor.apply();
	}

	private static long getLongPref(String pref, long def) {
		SharedPreferences prefs = Pref.get();
		return prefs.getLong(pref, def);
	}

	private static void putLongPref(String pref, long val) {
		SharedPreferences prefs = Pref.get();
		SharedPreferences.Editor editor = prefs.edit();
		editor.putLong(pref, val);
		editor.apply();
	}

	private static Set<String> getStringSetPref(String pref, Set<String> def) {
		SharedPreferences prefs = Pref.get();
		return prefs.getStringSet(pref, def);
	}

	private static void putStringSetPref(String pref, Set<String> val) {
		SharedPreferences prefs = Pref.get();
		SharedPreferences.Editor editor = prefs.edit();
		editor.putStringSet(pref, val);
		editor.apply();
	}

	private static float getFloatPref(String pref, float def) {
		SharedPreferences prefs = Pref.get();
		return prefs.getFloat(pref, def);
	}

	private static void putFloatPref(String pref, float val) {
		SharedPreferences prefs = Pref.get();
		SharedPreferences.Editor editor = prefs.edit();

		editor.putFloat(pref, val);
		editor.apply();
	}



//	private static long getLongPref(final Context context, String pref, long def) {
//		SharedPreferences prefs = Pref.get(context);
//        return prefs.getLong(pref, def);
//	}
//
//	private static void putLongPref(final Context context, String pref, long val) {
//		SharedPreferences prefs = Pref.get(context);
//		SharedPreferences.Editor editor = prefs.edit();
//
//		editor.putLong(pref, val);
//		editor.apply();
//    }

	private static Boolean getBooleanPref(String pref, Boolean def) {
		SharedPreferences prefs = Pref.get();
        return prefs.getBoolean(pref, def);
	}

	private static void putBooleanPref(String pref, Boolean val) {
		SharedPreferences prefs = Pref.get();
		SharedPreferences.Editor editor = prefs.edit();

		editor.putBoolean(pref, val);
		editor.apply();
    }

    public static void deleteSharedPref() {
		SharedPreferences prefs = Pref.get();
		SharedPreferences.Editor editor = prefs.edit();
		editor.clear();
		editor.apply();
	}


	//================================================================================================
	private static int encryptInteger(int i) {
		return (((i + 14) * 17) - 23);
	}

	private static int decryptInteger(int i) {
		return (((i + 23) / 17) - 14);
	}

	//++++++++++++++++++++++ ENCRYPTED PREFERENCES START (1)++++++++++++++++++++++++++++++++++++++++
	public static boolean getIsCodeVerified() {
		return Pref.getBooleanPref(PKey.IS_CODE_VERIFIED, PDefaultValue.IS_CODE_VERIFIED);
	}

	public static void putIsCodeVerified(boolean value) {
		Pref.putBooleanPref(PKey.IS_CODE_VERIFIED, value);
	}

	public static boolean getIsAtLeastOneDoctor() {
		return Pref.getBooleanPref(PKey.IS_AT_LEAST_ONE_DOCTOR, PDefaultValue.IS_AT_LEAST_ONE_DOCTOR);
	}

	public static void putIsAtLeastOneDoctor(boolean value) {
		Pref.putBooleanPref(PKey.IS_AT_LEAST_ONE_DOCTOR, value);
	}

	public static int getNewDoctorId() {
		return Pref.getIntPref(PKey.DOCTOR_ID, PDefaultValue.DOCTOR_ID);
	}

	public static void putNewDoctorId(int value) {
		Pref.putIntPref(PKey.DOCTOR_ID, value);
	}

	public static String getDoctor() {
		return Pref.getPref(PKey.DOCTOR_MODEL, PDefaultValue.DOCTOR_MODEL);
	}

	public static void putDoctor(String doctor) {
		Pref.putPref(PKey.DOCTOR_MODEL, doctor);
	}

}
