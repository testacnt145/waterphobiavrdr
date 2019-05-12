package com.waterphobiadr.ui.feature.patientdetail;

import android.content.Intent;
import android.os.Bundle;

import com.waterphobiadr.constant.IntentConstant;
import com.waterphobiadr.data.Repository;
import com.waterphobiadr.data.model.Patient;
/*
 * Created by shayan.rais on 20/12/2017.
 */

public class PatientDetailPresenter implements PatientDetailContract.Presenter {

    private final PatientDetailContract.View view;
    private final Repository repository;
    public Patient patient;


    PatientDetailPresenter(PatientDetailContract.View view, Repository repository) {
        this.view = view;
        this.repository = repository;
    }

    //______________________________________________________________________________________________
    @Override
    public void setupIntent(Intent intent) {
        Bundle bundle = intent.getBundleExtra(IntentConstant.BUNDLE_PATIENT);
        patient = bundle.getParcelable(IntentConstant.PARCELABLE_PATIENT);
        view.setupToolbar();
        view.setupLayout();
        view.setupClickListeners();
    }

    @Override
    public void setupFragmentIntent(Bundle bundle) {

    }
}
