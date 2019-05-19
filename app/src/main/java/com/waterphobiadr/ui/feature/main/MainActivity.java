package com.waterphobiadr.ui.feature.main;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import com.waterphobiadr.App;
import com.waterphobiadr.R;
import com.waterphobiadr.constant.IntentConstant;
import com.waterphobiadr.data.Repository;
import com.waterphobiadr.data.local.pref.Pref;
import com.waterphobiadr.data.model.Doctor;
import com.waterphobiadr.databinding.ActivityMainBinding;
import com.waterphobiadr.ui.base.BaseActivity;
import com.waterphobiadr.util.ActivityUtil;
import com.waterphobiadr.util.JsonUtil;
import javax.inject.Inject;
/*
 * Created by shayan.rais on 20/12/2017.
 */

public class MainActivity extends BaseActivity implements MainContract.View {

    //5 lines
    ActivityMainBinding binding;
    @Inject
    Repository repository;
    MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(!Pref.getIsAtLeastOneDoctor()) {
            ActivityUtil.openNewDoctor(this, null);
            finish();
        } else {
            binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
            App.getInstance().getComponent().injectMainActivity(this);
            presenter = new MainPresenter(this, repository);
            presenter.setupIntent(getIntent());
            lbmRegister();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        lbmUnregister();
    }

    //______________________________________________________________________________________________ CREATE
    @Override
    public void setupToolbar() {
    }

    @Override
    public void setupLayout() {
        updateDoctor();
    }

    @Override
    public void updateDoctor() {
        Doctor doctor = JsonUtil.convertDoctorStringToJson(Pref.getDoctor());
        binding.name.setText("Hi, Dr. " + doctor.getName());
        binding.degree.setText(doctor.getDegree() + ", " + doctor.getUniversity());
        binding.email.setText(doctor.getEmail());
    }

    @Override
    public void setupClickListeners() {
        binding.patient.setOnClickListener(view -> ActivityUtil.openPatientList(MainActivity.this));
        binding.doctor.setOnClickListener(view -> ActivityUtil.openNewDoctor(MainActivity.this, JsonUtil.convertDoctorStringToJson(Pref.getDoctor())));
        binding.settings.setOnClickListener(view -> ActivityUtil.openAbout(MainActivity.this));
        binding.edit.setOnClickListener(view -> ActivityUtil.openNewDoctor(MainActivity.this, JsonUtil.convertDoctorStringToJson(Pref.getDoctor())));
    }

    void lbmRegister() {
        LocalBroadcastManager.getInstance(this).registerReceiver(lbmDoctorUpdated, new IntentFilter(IntentConstant.LBM_DOCTOR_UPDATED));
    }

    void lbmUnregister() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(lbmDoctorUpdated);
    }

    private BroadcastReceiver lbmDoctorUpdated = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent in) {
            updateDoctor();
        }
    };
}
