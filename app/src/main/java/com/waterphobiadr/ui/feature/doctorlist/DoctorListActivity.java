package com.waterphobiadr.ui.feature.doctorlist;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.MenuItem;
import android.view.View;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.waterphobiadr.App;
import com.waterphobiadr.R;
import com.waterphobiadr.data.Repository;
import com.waterphobiadr.data.model.Patient;
import com.waterphobiadr.databinding.ActivityDoctorListBinding;
import com.waterphobiadr.ui.base.BaseActivity;
import com.waterphobiadr.ui.feature.doctorlist.adapter.DoctorAdapter;

import java.util.ArrayList;

import javax.inject.Inject;
/*
 * Created by shayan.rais on 20/12/2017.
 */

public class DoctorListActivity extends BaseActivity implements DoctorListContract.View {

    //5 lines
    ActivityDoctorListBinding binding;
    @Inject
    Repository repository;
    DoctorListPresenter presenter;

    private DoctorAdapter adapter;
    private DatabaseReference mFirebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_doctor_list);
        mFirebaseDatabase = FirebaseDatabase.getInstance().getReference();
        App.getInstance().getComponent().injectDoctorListActivity(this);
        presenter = new DoctorListPresenter(this, repository);
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

    final ArrayList<Patient> data = new ArrayList();
    @Override
    public void setupLayout() {
        DatabaseReference usersRef = mFirebaseDatabase.child("users");
        usersRef.addValueEventListener(new ValueEventListener() {
               @Override
               public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                   for (DataSnapshot user : dataSnapshot.getChildren()) {
                       data.add(user.getValue(Patient.class));
                       binding.recycler.setVisibility(View.VISIBLE);
                       binding.layoutLoader.loading.setVisibility(View.GONE);
                       adapter.notifyDataSetChanged();
                   }
               }
               @Override
               public void onCancelled(@NonNull DatabaseError databaseError) {
               }
            }
        );
        adapter = new DoctorAdapter(this, data);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        binding.recycler.setAdapter(adapter);
        binding.recycler.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        binding.recycler.setLayoutManager(layoutManager);
    }

    @Override
    public void setupClickListeners() {
    }
}
