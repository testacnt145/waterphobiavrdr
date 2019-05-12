package com.waterphobiadr.ui.feature.newdoctor;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import com.waterphobiadr.App;
import com.waterphobiadr.R;
import com.waterphobiadr.data.Repository;
import com.waterphobiadr.data.local.pref.Pref;
import com.waterphobiadr.data.model.Doctor;
import com.waterphobiadr.databinding.ActivityNewDoctorBinding;
import com.waterphobiadr.ui.base.BaseActivity;
import com.waterphobiadr.ui.feature.main.MainActivity;
import com.waterphobiadr.util.JsonUtil;
import javax.inject.Inject;
/*
 * Created by shayan.rais on 20/12/2017.
 */

public class NewDoctorActivity extends BaseActivity implements NewDoctorContract.View {

    //5 lines
    ActivityNewDoctorBinding binding;
    @Inject
    Repository repository;
    NewDoctorPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_new_doctor);
        App.getInstance().getComponent().injectNewDoctorActivity(this);
        presenter = new NewDoctorPresenter(this, repository);
        presenter.setupIntent(getIntent());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_new_doctor, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.menu_save:
                if(validate()) {
                    //if (!Pref.getIsAtLeastOneDoctor()) {
                        //Pref.putIsAtLeastOneDoctor(true);
                        Doctor doctor = new Doctor();
                        doctor.setId(1);
                        doctor.setName(binding.name.getText().toString());
                        doctor.setDegree(binding.degree.getText().toString());
                        doctor.setUniversity(binding.university.getText().toString());
                        doctor.setEmail(binding.email.getText().toString());
                        doctor.setNumber(binding.number.getText().toString());
                        Pref.putDoctor(JsonUtil.convertDoctorJsonToString(doctor));
                        Toast.makeText(this, "Information saved", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(this, MainActivity.class));
                    //}
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private boolean validate() {
        if(binding.name.getText().toString().equals("")) {
            binding.name.setHintTextColor(getResources().getColor(R.color.red));
            Toast.makeText(this, "Please enter your name", Toast.LENGTH_LONG).show();
            return false;
        } else if(binding.degree.getText().toString().equals("")) {
            binding.degree.setHintTextColor(getResources().getColor(R.color.red));
            Toast.makeText(this, "Please enter your degree", Toast.LENGTH_LONG).show();
            return false;
        } else if(binding.university.getText().toString().equals("")) {
            binding.university.setHintTextColor(getResources().getColor(R.color.red));
            Toast.makeText(this, "Please enter your university", Toast.LENGTH_LONG).show();
            return false;
        } else if(binding.email.getText().toString().equals("")) {
            binding.email.setHintTextColor(getResources().getColor(R.color.red));
            Toast.makeText(this, "Please enter your email", Toast.LENGTH_LONG).show();
            return false;
        } else if(!Patterns.EMAIL_ADDRESS.matcher(binding.email.getText().toString()).matches()) {
            Toast.makeText(this, "Please enter valid email", Toast.LENGTH_LONG).show();
            return false;
        } else if (binding.number.getText().toString().equals("")) {
            binding.number.setHintTextColor(getResources().getColor(R.color.red));
            Toast.makeText(this, "Please enter your number", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    //______________________________________________________________________________________________ CREATE
    @Override
    public void setupToolbar() {
        setSupportActionBar(binding.toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            if (presenter.doctor != null)
                actionBar.setDisplayHomeAsUpEnabled(true);
            else
                actionBar.setDisplayHomeAsUpEnabled(false);
        }
    }

    @Override
    public void setupLayout() {
        if(presenter.doctor!=null) {
            binding.name.setText(presenter.doctor.getName());
            binding.degree.setText(presenter.doctor.getDegree());
            binding.university.setText(presenter.doctor.getUniversity());
            binding.email.setText(presenter.doctor.getEmail());
            binding.number.setText(presenter.doctor.getNumber());
        }
    }

    @Override
    public void setupClickListeners() {
    }
}
