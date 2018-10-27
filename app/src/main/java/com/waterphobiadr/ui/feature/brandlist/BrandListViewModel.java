package com.waterphobiadr.ui.feature.brandlist;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.waterphobiadr.data.RepositoryNew;
import com.waterphobiadr.data.local.database.table.Brand;

import java.util.List;

/**
 * View Model to keep a reference to the word repository and
 * an up-to-date list of all words.
 */

public class BrandListViewModel extends AndroidViewModel {

    private RepositoryNew mRepositoryNew;
    // Using LiveData and caching what getAlphabetizedBrands returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - RepositoryNew is completely separated from the UI through the ViewModel.
    private LiveData<List<Brand>> mAllWords;

    public BrandListViewModel(Application application) {
        super(application);
        mRepositoryNew = new RepositoryNew(application);
        mAllWords = mRepositoryNew.getAllWords();
    }

    public LiveData<List<Brand>> getAllWords() {
        return mAllWords;
    }

    public void insert(Brand word) {
        mRepositoryNew.insert(word);
    }
}