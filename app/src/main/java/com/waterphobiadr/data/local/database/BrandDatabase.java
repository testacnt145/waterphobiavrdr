package com.waterphobiadr.data.local.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import com.waterphobiadr.data.local.database.dao.BrandDao;
import com.waterphobiadr.data.local.database.table.Brand;

/**
 * This is the backend. The database. This used to be done by the OpenHelper.
 * The fact that this has very few comments emphasizes its coolness.
 */

@Database(entities = {Brand.class}, version = 1)
public abstract class BrandDatabase extends RoomDatabase {

    public abstract BrandDao brandDao();

    // marking the instance as volatile to ensure atomic access to the variable
    private static volatile BrandDatabase INSTANCE;

    public static BrandDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (BrandDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            BrandDatabase.class, "brand_database")
                            // Wipes and rebuilds instead of migrating if no Migration object.
                            // Migration is not part of this codelab.
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    /**
     * Override the onOpen method to populate the database.
     * For this sample, we clear the database every time it is created or opened.
     *
     * If you want to populate the database only when the database is created for the 1st time,
     * override RoomDatabase.Callback()#onCreate
     */
    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            // If you want to keep the data through app restarts,
            // comment out the following line.
            new PopulateDbAsync(INSTANCE).execute();
        }
    };

    /**
     * Populate the database in the background.
     * If you want to start with more words, just add them.
     */
    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final BrandDao mDao;

        PopulateDbAsync(BrandDatabase db) {
            mDao = db.brandDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            // Start the app with a clean database every time.
            // Not needed if you only populate on creation.
            mDao.deleteAll();

            Brand word = new Brand("Pizza Max");
            mDao.insert(word);
            word = new Brand("Hardees");
            mDao.insert(word);
            return null;
        }
    }

}
