package com.waterphobiadr.util;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.waterphobiadr.data.model.Doctor;
import java.io.IOException;

/*
 * Created by asad on 12/27/17.
 */

public class JsonUtil {

    public static String convertDoctorJsonToString(Doctor doctor) {
        Moshi moshi = new Moshi.Builder().build();
        JsonAdapter<Doctor> jsonAdapter = moshi.adapter(Doctor.class);
        return jsonAdapter.toJson(doctor);
    }

    public static  Doctor convertDoctorStringToJson(String json) {
        Moshi moshi = new Moshi.Builder().build();
        JsonAdapter<Doctor> jsonAdapter = moshi.adapter(Doctor.class);
        Doctor doctor = null;
        try {
            doctor = jsonAdapter.fromJson(json);
        } catch (IOException ignored) {
        }
        return doctor;
    }

}
