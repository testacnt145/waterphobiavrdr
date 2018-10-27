package com.waterphobiadr.ui.feature.patientlist;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.view.MenuItem;
import com.waterphobiadr.App;
import com.waterphobiadr.R;
import com.waterphobiadr.data.Repository;
import com.waterphobiadr.data.remote.model.Patient;
import com.waterphobiadr.databinding.ActivityPatientListBinding;
import com.waterphobiadr.ui.base.BaseActivity;
import com.waterphobiadr.ui.feature.patientlist.adapter.PatientAdapter;
import java.util.ArrayList;
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

    private PatientAdapter adapter;

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
        ArrayList<Patient> data = new ArrayList();
        Patient p = new Patient();
        p.name = "P";
        data.add(p);
        adapter = new PatientAdapter(this, data);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        binding.recycler.setAdapter(adapter);
        binding.recycler.setLayoutManager(layoutManager);
    }

    @Override
    public void setupClickListeners() {
    }
}
