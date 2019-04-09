package com.waterphobiadr.ui.feature.patientdetail;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import com.waterphobiadr.App;
import com.waterphobiadr.R;
import com.waterphobiadr.data.Repository;
import com.waterphobiadr.databinding.ActivityPatientDetailBinding;
import com.waterphobiadr.ui.base.BaseActivity;
import javax.inject.Inject;
/*
 * Created by shayan.rais on 20/12/2017.
 */

public class PatientDetailActivity extends BaseActivity implements PatientDetailContract.View {

    //5 lines
    ActivityPatientDetailBinding binding;
    @Inject
    Repository repository;
    PatientDetailPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_patient_detail);
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
        actionBar.setTitle(presenter.patient.getName());
        if (actionBar != null)
            actionBar.setDisplayHomeAsUpEnabled(true);
    }


    @Override
    public void setupLayout() {
        binding.number.setText("Call: " + presenter.patient.getNumber());
        binding.email.setText("Email: " + presenter.patient.getEmail());
        binding.aquaphobia.setText("Aquaphobia Score: " + presenter.patient.getAquaphobiaScore());
        binding.astraphobia.setText("Astraphobia Score: " + presenter.patient.getAstraphobiaScore());
        binding.bathophobia.setText("Bathophobia Score: " + presenter.patient.getBathophobiaScore());
    }

    @Override
    public void setupClickListeners() {
        binding.number.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + presenter.patient.getNumber()));
                startActivity(intent);

            }
        });
        binding.email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto",presenter.patient.getEmail(), null));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Patient Report");
                emailIntent.putExtra(Intent.EXTRA_TEXT,
            "Score Details:\n" +
                  "Aquaphobia Score: " + presenter.patient.getAquaphobiaScore() + "\n" +
                  "Astraphobia Score: " + presenter.patient.getAstraphobiaScore() + "\n" +
                  "Bathophobia Score: " + presenter.patient.getBathophobiaScore() + "\n\n"
                );
                startActivity(Intent.createChooser(emailIntent, "Send email..."));
            }
        });
        binding.feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(PatientDetailActivity.this, "Feedback", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
