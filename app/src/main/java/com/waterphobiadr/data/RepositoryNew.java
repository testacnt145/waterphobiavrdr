package com.waterphobiadr.data;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.waterphobiadr.data.local.database.dao.BrandDao;
import com.waterphobiadr.data.local.database.table.Brand;
import com.waterphobiadr.data.local.database.BrandDatabase;

import java.util.List;

/**
 * Abstracted RepositoryNew as promoted by the Architecture Guide.
 * https://developer.android.com/topic/libraries/architecture/guide.html
 */

public class RepositoryNew {

    private BrandDao mBrandDao;
    private LiveData<List<Brand>> mAllWords;

    // Note that in order to unit test the RepositoryNew, you have to remove the Application
    // dependency. This adds complexity and much more code, and this sample is not about testing.
    // See the BasicSample in the android-architecture-components repository at
    // https://github.com/googlesamples
    public RepositoryNew(Application application) {
        BrandDatabase db = BrandDatabase.getDatabase(application);
        mBrandDao = db.brandDao();
        mAllWords = mBrandDao.getAlphabetizedBrands();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    public LiveData<List<Brand>> getAllWords() {
        return mAllWords;
    }

    // You must call this on a non-UI thread or your app will crash.
    // Like this, Room ensures that you're not doing any long running operations on the main
    // thread, blocking the UI.
    public void insert(Brand word) {
        new insertAsyncTask(mBrandDao).execute(word);
    }

    private static class insertAsyncTask extends AsyncTask<Brand, Void, Void> {

        private BrandDao mAsyncTaskDao;

        insertAsyncTask(BrandDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Brand... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}
