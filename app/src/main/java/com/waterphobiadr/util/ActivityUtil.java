package com.waterphobiadr.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.waterphobiadr.constant.IntentConstant;
import com.waterphobiadr.data.model.Doctor;
import com.waterphobiadr.data.model.Patient;
import com.waterphobiadr.ui.feature.about.AboutActivity;
import com.waterphobiadr.ui.feature.main.MainActivity;
import com.waterphobiadr.ui.feature.newdoctor.NewDoctorActivity;
import com.waterphobiadr.ui.feature.patientdetail.PatientDetailActivity;
import com.waterphobiadr.ui.feature.patientlist.PatientListActivity;

import java.io.IOException;

/*
 * Created by shayan.rais on 12/27/17.
 */

public class ActivityUtil {

    public static void openHome(Context ctx) {
        ctx.startActivity(new Intent(ctx, MainActivity.class));
    }

    public static void openNewDoctor(Context ctx, Doctor doctor) {
        ((Activity)ctx).finish();
        Intent intent = new Intent(ctx, NewDoctorActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable(IntentConstant.PARCELABLE_DOCTOR, doctor);
        intent.putExtra(IntentConstant.BUNDLE_DOCTOR, bundle);
        ctx.startActivity(intent);
    }

    public static void openPatientList(Context ctx) {
        Intent intent = new Intent(ctx, PatientListActivity.class);
        ctx.startActivity(intent);
    }

    public static void openAbout(Context ctx) {
        Intent intent = new Intent(ctx, AboutActivity.class);
        ctx.startActivity(intent);
    }

    public static void openPatientDetail(Context ctx, Patient patient) {
        Intent intent = new Intent(ctx, PatientDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable(IntentConstant.PARCELABLE_PATIENT, patient);
        intent.putExtra(IntentConstant.BUNDLE_PATIENT, bundle);
        ctx.startActivity(intent);
    }



}
