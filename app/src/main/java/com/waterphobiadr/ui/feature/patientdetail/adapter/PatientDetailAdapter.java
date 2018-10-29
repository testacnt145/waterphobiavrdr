package com.waterphobiadr.ui.feature.patientdetail.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.waterphobiadr.R;
import com.waterphobiadr.data.model.PatientDetail;
import com.waterphobiadr.databinding.ItemPatientDetailBinding;
import java.util.ArrayList;
/*
 * Created by shayan.rais on 12/12/2017.
 */

public class PatientDetailAdapter extends RecyclerView.Adapter<PatientDetailAdapter.MyViewHolder> {

    private ArrayList<PatientDetail> data;
    private Context context;

    public PatientDetailAdapter(Context context, ArrayList<PatientDetail> data) {
        this.context = context;
        this.data = data;
        setHasStableIds(true);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemPatientDetailBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.item_patient_detail, parent, false);
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
        PatientDetail item = data.get(position);
        holder.bind(item, position);
        updateUI(holder, item, position);
        clickListeners(holder, item);
    }

    private void updateUI(MyViewHolder holder, PatientDetail item, int position) {

    }

    private void clickListeners(MyViewHolder holder, final PatientDetail item) {
        holder.binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, item.getText(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    //______________________________________________________________________________________________
    class MyViewHolder extends RecyclerView.ViewHolder {
        private final ItemPatientDetailBinding binding;
        MyViewHolder(ItemPatientDetailBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
        void bind(PatientDetail item, final int position) {
            binding.setDetail(item);
            binding.executePendingBindings();
        }
    }
}
