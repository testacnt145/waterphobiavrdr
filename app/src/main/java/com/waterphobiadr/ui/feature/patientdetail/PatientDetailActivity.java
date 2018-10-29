package com.waterphobiadr.ui.feature.patientdetail;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.MenuItem;
import com.waterphobiadr.App;
import com.waterphobiadr.R;
import com.waterphobiadr.data.Repository;
import com.waterphobiadr.data.model.PatientDetail;
import com.waterphobiadr.data.remote.model.Patient;
import com.waterphobiadr.databinding.ActivityPatientListBinding;
import com.waterphobiadr.ui.base.BaseActivity;
import com.waterphobiadr.ui.feature.patientdetail.adapter.PatientDetailAdapter;
import java.util.ArrayList;
import javax.inject.Inject;
/*
 * Created by shayan.rais on 20/12/2017.
 */

public class PatientDetailActivity extends BaseActivity implements PatientDetailContract.View {

    //5 lines
    ActivityPatientListBinding binding;
    @Inject
    Repository repository;
    PatientDetailPresenter presenter;

    private PatientDetailAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_patient_list);
        App.getInstance().getComponent().injectPatientDetailActivity(this);
        presenter = new PatientDetailPresenter(this, repository);
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
        ArrayList<PatientDetail> data = new ArrayList();
        PatientDetail d1 = new PatientDetail();
        d1.setText("Call");
        data.add(d1);
        PatientDetail d2 = new PatientDetail();
        d2.setText("Email");
        data.add(d2);
        PatientDetail d3 = new PatientDetail();
        d3.setText("Feedback");
        data.add(d3);
        adapter = new PatientDetailAdapter(this, data);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        binding.recycler.setAdapter(adapter);
        binding.recycler.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        binding.recycler.setLayoutManager(layoutManager);
    }

    @Override
    public void setupClickListeners() {
    }
}
