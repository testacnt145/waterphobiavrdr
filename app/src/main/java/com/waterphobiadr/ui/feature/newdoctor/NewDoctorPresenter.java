package com.waterphobiadr.ui.feature.newdoctor;

import android.content.Intent;
import android.os.Bundle;

import com.waterphobiadr.constant.IntentConstant;
import com.waterphobiadr.data.Repository;
import com.waterphobiadr.data.model.Doctor;
/*
 * Created by shayan.rais on 20/12/2017.
 */

public class NewDoctorPresenter implements NewDoctorContract.Presenter {

    private final NewDoctorContract.View view;
    private final Repository repository;

    Doctor doctor;

    NewDoctorPresenter(NewDoctorContract.View view, Repository repository) {
        this.view = view;
        this.repository = repository;
    }

    //______________________________________________________________________________________________
    @Override
    public void setupIntent(Intent intent) {
        Bundle bundle = intent.getBundleExtra(IntentConstant.BUNDLE_DOCTOR);
        doctor = bundle.getParcelable(IntentConstant.PARCELABLE_DOCTOR);


        view.setupToolbar();
        view.setupLayout();
        view.setupClickListeners();
    }

    @Override
    public void setupFragmentIntent(Bundle bundle) {

    }
}
