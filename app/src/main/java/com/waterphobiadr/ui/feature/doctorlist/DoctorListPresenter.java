package com.waterphobiadr.ui.feature.doctorlist;

import android.content.Intent;
import android.os.Bundle;
import com.waterphobiadr.data.Repository;
/*
 * Created by shayan.rais on 20/12/2017.
 */

public class DoctorListPresenter implements DoctorListContract.Presenter {

    private final DoctorListContract.View view;
    private final Repository repository;


    DoctorListPresenter(DoctorListContract.View view, Repository repository) {
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
