package com.waterphobiadr.ui.feature.patientlist.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.waterphobiadr.R;
import com.waterphobiadr.data.remote.model.Patient;
import com.waterphobiadr.databinding.ItemPatientBinding;
import java.util.ArrayList;
/*
 * Created by shayan.rais on 12/12/2017.
 */

public class PatientAdapter extends RecyclerView.Adapter<PatientAdapter.MyViewHolder> {

    private ArrayList<Patient> data;
    private Context context;

    public PatientAdapter(Context context, ArrayList<Patient> data) {
        this.context = context;
        this.data = data;
        setHasStableIds(true);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemPatientBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.item_patient, parent, false);
        return new MyViewHolder(binding);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    //______________________________________________________________________________________________
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Patient item = data.get(position);
        holder.bind(item, position);
        updateUI(holder, item, position);
        clickListeners(holder, item);
    }

    private void updateUI(MyViewHolder holder, Patient item, int position) {

    }

    private void clickListeners(MyViewHolder holder, final Patient segment) {
        holder.binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
    }

    //______________________________________________________________________________________________
    class MyViewHolder extends RecyclerView.ViewHolder {
        private final ItemPatientBinding binding;
        MyViewHolder(ItemPatientBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
        void bind(Patient item, final int position) {
            binding.setPatient(item);
            binding.executePendingBindings();
        }
    }
}
