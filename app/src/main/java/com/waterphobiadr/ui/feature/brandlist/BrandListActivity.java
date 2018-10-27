package com.waterphobiadr.ui.feature.brandlist;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;
import com.waterphobiadr.R;
import com.waterphobiadr.constant.IntentConstant;
import com.waterphobiadr.data.local.database.table.Brand;
import com.waterphobiadr.ui.feature.brandlist.adapter.BrandListAdapter;
import com.waterphobiadr.ui.feature.newbrand.NewBrandActivity;

import java.util.List;

public class BrandListActivity extends AppCompatActivity {

    //tutorial link
    //https://codelabs.developers.google.com/codelabs/android-room-with-a-view/#0



    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;

    private BrandListViewModel mBrandListViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand_list);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final BrandListAdapter adapter = new BrandListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Get a new or existing ViewModel from the ViewModelProvider.
        mBrandListViewModel = ViewModelProviders.of(this).get(BrandListViewModel.class);

        // Add an observer on the LiveData returned by getAlphabetizedBrands.
        // The onChanged() method fires when the observed data changes and the activity is
        // in the foreground.
        mBrandListViewModel.getAllWords().observe(this, new Observer<List<Brand>>() {
            @Override
            public void onChanged(@Nullable final List<Brand> words) {
                // Update the cached copy of the words in the adapter.
                adapter.setWords(words);
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BrandListActivity.this, NewBrandActivity.class);
                startActivityForResult(intent, NEW_WORD_ACTIVITY_REQUEST_CODE);
            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            Brand word = new Brand(data.getStringExtra(IntentConstant.WORD));
            mBrandListViewModel.insert(word);
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG).show();
        }
    }
}
