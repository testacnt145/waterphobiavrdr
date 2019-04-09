package com.waterphobiadr.ui.feature.main;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.widget.Toast;
import com.waterphobiadr.App;
import com.waterphobiadr.R;
import com.waterphobiadr.data.Repository;
import com.waterphobiadr.databinding.ActivityMainBinding;
import com.waterphobiadr.ui.base.BaseActivity;
import com.waterphobiadr.ui.feature.patientlist.PatientListActivity;

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
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        App.getInstance().getComponent().injectMainActivity(this);
        presenter = new MainPresenter(this, repository);
        presenter.setupIntent(getIntent());
    }


    //______________________________________________________________________________________________ CREATE
    @Override
    public void setupToolbar() {
        setSupportActionBar(binding.toolbar);
        ActionBar actionBar = getSupportActionBar();
        //if (actionBar != null)
        //    actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void setupLayout() {
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
        binding.settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, R.string.about_us, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
