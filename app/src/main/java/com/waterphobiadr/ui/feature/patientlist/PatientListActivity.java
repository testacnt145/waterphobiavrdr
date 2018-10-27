package com.waterphobiadr.ui.feature.patientlist;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import com.waterphobiadr.App;
import com.waterphobiadr.R;
import com.waterphobiadr.data.Repository;
import com.waterphobiadr.databinding.ActivityPatientListBinding;
import com.waterphobiadr.ui.base.BaseActivity;
import javax.inject.Inject;
/*
 * Created by shayan.rais on 20/12/2017.
 */

public class PatientListActivity extends BaseActivity implements PatientListContract.View {

    //5 lines
    ActivityPatientListBinding binding;
    @Inject
    Repository repository;
    PatientListPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_patient_list);
        App.getInstance().getComponent().injectPatientListActivity(this);
        presenter = new PatientListPresenter(this, repository);
        presenter.setupIntent(getIntent());
    }

    //______________________________________________________________________________________________
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    //______________________________________________________________________________________________ CREATE
    @Override
    public void setupToolbar() {
        setSupportActionBar(binding.toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void setupLayout() {
    }

    @Override
    public void setupClickListeners() {
        binding.patient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(PatientListActivity.this, "Patient Clicked", Toast.LENGTH_SHORT).show();
            }
        });
        binding.settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(PatientListActivity.this, "Settings Clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
