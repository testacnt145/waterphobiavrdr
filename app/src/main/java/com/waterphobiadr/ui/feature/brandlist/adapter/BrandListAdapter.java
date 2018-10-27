package com.waterphobiadr.ui.feature.brandlist.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.waterphobiadr.R;
import com.waterphobiadr.data.local.database.table.Brand;

import java.util.Collections;
import java.util.List;

public class BrandListAdapter extends RecyclerView.Adapter<BrandListAdapter.BrandViewHolder> {

    class BrandViewHolder extends RecyclerView.ViewHolder {
        private final TextView brandItemView;

        private BrandViewHolder(View itemView) {
            super(itemView);
            brandItemView = itemView.findViewById(R.id.textView);
        }
    }

    private final LayoutInflater mInflater;
    private List<Brand> mBrands = Collections.emptyList(); // Cached copy of words

    public BrandListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public BrandViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new BrandViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull BrandViewHolder holder, int position) {
        Brand current = mBrands.get(position);
        holder.brandItemView.setText(current.getBrand());
    }

    public void setWords(List<Brand> brands) {
        mBrands = brands;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mBrands.size();
    }
}


