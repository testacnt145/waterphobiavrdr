package com.waterphobiadr.ui.feature.patientlist;

import android.content.Intent;
import android.os.Bundle;
import com.waterphobiadr.data.Repository;
/*
 * Created by shayan.rais on 20/12/2017.
 */

public class PatientListPresenter implements PatientListContract.Presenter {

    private final PatientListContract.View view;
    private final Repository repository;


    PatientListPresenter(PatientListContract.View view, Repository repository) {
        this.view = view;
        this.repository = repository;
    }

    //______________________________________________________________________________________________
    @Override
    public void setupIntent(Intent intent) {
        view.setupToolbar();
        view.setupLayout();
        view.setupClickListeners();
    }

    @Override
    public void setupFragmentIntent(Bundle bundle) {

    }
}
