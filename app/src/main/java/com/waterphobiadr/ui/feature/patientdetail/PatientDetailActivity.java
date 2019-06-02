package com.waterphobiadr.ui.feature.patientdetail;

import android.app.Dialog;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
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
import com.waterphobiadr.data.local.pref.Pref;
import com.waterphobiadr.data.model.Feedback;
import com.waterphobiadr.databinding.ActivityPatientDetailBinding;
import com.waterphobiadr.ui.base.BaseActivity;
import com.waterphobiadr.ui.feature.patientdetail.adapter.PatientDetailAdapter;
import com.waterphobiadr.util.ConversionUtil;
import com.waterphobiadr.util.DateUtil;
import com.waterphobiadr.util.JsonUtil;
import com.waterphobiadr.util.NetworkUtil;
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
    private DatabaseReference db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_patient_detail);
        db = FirebaseDatabase.getInstance().getReference();
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
        binding.aquaphobia.setText("Aquaphobia Score: " + presenter.patient.getAquaphobiaScore());
        binding.astraphobia.setText("Astraphobia Score: " + presenter.patient.getAstraphobiaScore());
        binding.bathophobia.setText("Bathophobia Score: " + presenter.patient.getBathophobiaScore());

        //feedback
        DatabaseReference feedbackRef = db.child("users").child(presenter.patient.getId()).child("feedbacks");
        feedbackRef.addValueEventListener(new ValueEventListener() {
               @Override
               public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                   data.clear();//clear all because it will also be called when new comment is added
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
        binding.fab.setOnClickListener(v -> {
            final Dialog dialog = new Dialog(this);
            dialog.setContentView(R.layout.dialog_comment);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            TextView btn = dialog.findViewById(R.id.comment);
            TextView limit = dialog.findViewById(R.id.limit);
            TextView time = dialog.findViewById(R.id.time);
            EditText input = dialog.findViewById(R.id.input);
            time.setText("On " + DateUtil.getCurrentDate());
            input.addTextChangedListener(new TextWatcher() {
                @Override
                public void afterTextChanged(Editable s) {}
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                   limit.setText(input.length() + "/140");
                }
            });
            btn.setOnClickListener(v1 -> {
                if(!input.getText().toString().equals("")) {
                    if (NetworkUtil.checkInternet()) {
                        Feedback feedback = new Feedback();
                        feedback.setName(JsonUtil.convertDoctorStringToJson(Pref.getDoctor()).getName());
                        feedback.setDegree(JsonUtil.convertDoctorStringToJson(Pref.getDoctor()).getDegree());
                        feedback.setUniversity(JsonUtil.convertDoctorStringToJson(Pref.getDoctor()).getUniversity());
                        feedback.setTimestamp(ConversionUtil.convertLongToString(DateUtil.getCurrentTimestamp()));
                        feedback.setComment(input.getText().toString());
                        //add new feedback to list
                        data.add(feedback);
                        //old HashMap -> db.child("users").child(presenter.patient.getId()).child("feedbacks").push().setValue(feedback)
                        db.child("users").child(presenter.patient.getId()).child("feedbacks").setValue(data)
                                .addOnSuccessListener(aVoid -> {
                                    Toast.makeText(PatientDetailActivity.this, "Comment posted", Toast.LENGTH_SHORT).show();
                                })
                                .addOnFailureListener(e -> Toast.makeText(PatientDetailActivity.this, "Request Failed", Toast.LENGTH_SHORT).show());

                    } else
                        Toast.makeText(PatientDetailActivity.this, getString(R.string.network_not_connected), Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(PatientDetailActivity.this, "Please write comment", Toast.LENGTH_SHORT).show();
                dialog.cancel();
            });
            dialog.show();
        });
    }
}
