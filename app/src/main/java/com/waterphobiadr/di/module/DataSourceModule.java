package com.waterphobiadr.di.module;

import android.content.Context;
import com.waterphobiadr.data.Repository;
import com.waterphobiadr.data.local.LocalDataSource;
import com.waterphobiadr.data.remote.RemoteDataSource;
import com.waterphobiadr.di.Singleton;
import dagger.Module;
import dagger.Provides;
/*
 * Created by shayan.rais on 12/9/2017.
 */

@Module(includes = {ApplicationModule.class})
public class DataSourceModule {

    @Provides
    @Singleton
    Repository repository(RemoteDataSource remote, LocalDataSource local) {
        return new Repository(remote, local);
    }

    @Provides
    @Singleton
    RemoteDataSource remoteDataSource() {
        return new RemoteDataSource();
    }

    @Provides
    @Singleton
    LocalDataSource localDataSource(Context context) {
        return new LocalDataSource(context);
    }
}
