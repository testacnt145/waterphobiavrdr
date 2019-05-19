package com.waterphobiadr.util;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import com.waterphobiadr.App;
import com.waterphobiadr.constant.IntentConstant;
/*
 * Created by shayan.raees on 8/9/2018.
 */

public class LBM {

    public static void doctorUpdated() {
        Intent in = new Intent(IntentConstant.LBM_DOCTOR_UPDATED);
        LocalBroadcastManager.getInstance(App.getInstance().getApplicationContext()).sendBroadcast(in);
    }
}
