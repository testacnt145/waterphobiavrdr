package com.waterphobiadr.ui.feature.main;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;
import com.waterphobiadr.App;
import com.waterphobiadr.R;
import com.waterphobiadr.data.Repository;
import com.waterphobiadr.data.local.pref.Pref;
import com.waterphobiadr.data.model.Doctor;
import com.waterphobiadr.databinding.ActivityMainBinding;
import com.waterphobiadr.ui.base.BaseActivity;
import com.waterphobiadr.ui.feature.about.AboutActivity;
import com.waterphobiadr.ui.feature.doctorlist.DoctorListActivity;
import com.waterphobiadr.ui.feature.newdoctor.NewDoctorActivity;
import com.waterphobiadr.ui.feature.patientlist.PatientListActivity;
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
            startActivity(new Intent(this, NewDoctorActivity.class));
            finish();
        } else {
            binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
            App.getInstance().getComponent().injectMainActivity(this);
            presenter = new MainPresenter(this, repository);
            presenter.setupIntent(getIntent());
        }
    }


    //______________________________________________________________________________________________ CREATE
    @Override
    public void setupToolbar() {
    }

    @Override
    public void setupLayout() {
        Doctor doctor = JsonUtil.convertDoctorStringToJson(Pref.getDoctor());
        binding.name.setText("Hi, Dr. " + doctor.getName());
        binding.degree.setText(doctor.getDegree() + ", " + doctor.getUniversity());
        binding.email.setText(doctor.getEmail());
    }

    @Override
    public void setupClickListeners() {
        binding.patient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PatientListActivity.class);
                startActivity(intent);
            }
        });
        binding.doctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DoctorListActivity.class);
                startActivity(intent);
            }
        });
        binding.settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(intent);
            }
        });
        binding.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NewDoctorActivity.class);
                startActivity(intent);
            }
        });
    }
}
