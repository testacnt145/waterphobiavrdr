package com.waterphobiadr.ui.feature.patientdetail;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.waterphobiadr.App;
import com.waterphobiadr.GlideApp;
import com.waterphobiadr.R;
import com.waterphobiadr.data.Repository;
import com.waterphobiadr.data.model.Feedback;
import com.waterphobiadr.databinding.ActivityPatientDetailBinding;
import com.waterphobiadr.ui.base.BaseActivity;
import com.waterphobiadr.ui.feature.patientdetail.adapter.PatientDetailAdapter;
import java.util.ArrayList;
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

    private PatientDetailAdapter adapter;
    final ArrayList<Feedback> data = new ArrayList();
    private DatabaseReference mFirebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_patient_detail);
        mFirebaseDatabase = FirebaseDatabase.getInstance().getReference();
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
            case R.id.menu_call:
                if(!presenter.patient.getNumber().equals("")) {
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:" + presenter.patient.getNumber()));
                    startActivity(intent);
                } else
                    Toast.makeText(PatientDetailActivity.this, getString(R.string.no_contacts), Toast.LENGTH_LONG).show();
                break;
            case R.id.menu_email:
                if(!presenter.patient.getEmail().equals("")) {
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
                } else
                    Toast.makeText(PatientDetailActivity.this, getString(R.string.no_emails), Toast.LENGTH_LONG).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_patient_detail, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //______________________________________________________________________________________________ CREATE
    @Override
    public void setupToolbar() {
        setSupportActionBar(binding.toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.setTitle(presenter.patient.getName());
            actionBar.setDisplayHomeAsUpEnabled(true);
    }


    @Override
    public void setupLayout() {
        if (!presenter.patient.getImage().equals("")) {
            GlideApp
                    .with(App.getInstance())
                    .load(presenter.patient.getImage())
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .error(R.drawable.placeholder)
                    .into(binding.image);
        }
        binding.aquaphobia.setText("Score: " + presenter.patient.getAquaphobiaScore());
        binding.astraphobia.setText("Score: " + presenter.patient.getAstraphobiaScore());
        binding.bathophobia.setText("Score: " + presenter.patient.getBathophobiaScore());

        //feedback
        DatabaseReference feedbackRef = mFirebaseDatabase.child("users").child(presenter.patient.getId()).child("feedback");
        feedbackRef.addValueEventListener(new ValueEventListener() {
               @Override
               public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                   for (DataSnapshot feedback : dataSnapshot.getChildren()) {
                       data.add(feedback.getValue(Feedback.class));
                       binding.recycler.setVisibility(View.VISIBLE);
                       binding.no.setVisibility(View.GONE);
                       binding.layoutLoader.loading.setVisibility(View.GONE);
                       adapter.notifyDataSetChanged();
                   }
               }
               @Override
               public void onCancelled(@NonNull DatabaseError databaseError) {
               }
           }
        );
        adapter = new PatientDetailAdapter(this, data);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        binding.recycler.setAdapter(adapter);
        binding.recycler.setLayoutManager(layoutManager);
    }

    @Override
    public void setupClickListeners() {
    }
}
