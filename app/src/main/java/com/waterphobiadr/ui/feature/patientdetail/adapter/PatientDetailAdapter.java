package com.waterphobiadr.ui.feature.patientdetail.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.waterphobiadr.R;
import com.waterphobiadr.data.model.Feedback;
import com.waterphobiadr.databinding.ItemDoctorFeedbackBinding;
import com.waterphobiadr.util.ConversionUtil;
import com.waterphobiadr.util.DateUtil;

import java.util.ArrayList;
/*
 * Created by shayan.rais on 12/12/2017.
 */

public class PatientDetailAdapter extends RecyclerView.Adapter<PatientDetailAdapter.MyViewHolder> {

    private ArrayList<Feedback> data;
    private Context context;

    public PatientDetailAdapter(Context context, ArrayList<Feedback> data) {
        this.context = context;
        this.data = data;
        setHasStableIds(true);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemDoctorFeedbackBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.item_doctor_feedback, parent, false);
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
        Feedback item = data.get(position);
        updateUI(holder, item, position);
        clickListeners(holder, item);
    }

    private void updateUI(MyViewHolder holder, Feedback item, int position) {
        holder.binding.comment.setText(item.getComment());
        holder.binding.name.setText(item.getName() + " on " + DateUtil.getDate(ConversionUtil.convertStringToLong(item.getTimestamp())));
    }

    private void clickListeners(MyViewHolder holder, final Feedback patient) {
    }

    //______________________________________________________________________________________________
    class MyViewHolder extends RecyclerView.ViewHolder {
        private final ItemDoctorFeedbackBinding binding;
        MyViewHolder(ItemDoctorFeedbackBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
