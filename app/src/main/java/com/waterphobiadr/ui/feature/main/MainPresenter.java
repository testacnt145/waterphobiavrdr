package com.waterphobiadr.ui.feature.main;

import android.content.Intent;
import android.os.Bundle;
import com.waterphobiadr.data.Repository;
/*
 * Created by shayan.rais on 20/12/2017.
 */

public class MainPresenter implements MainContract.Presenter {

    private final MainContract.View view;
    private final Repository repository;


    MainPresenter(MainContract.View view, Repository repository) {
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
