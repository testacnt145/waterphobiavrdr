package com.waterphobiadr.data;

import com.waterphobiadr.data.local.LocalDataSource;
import com.waterphobiadr.data.remote.RemoteDataInterface;
import com.waterphobiadr.data.remote.RemoteDataSource;
import com.waterphobiadr.di.Local;
import com.waterphobiadr.di.Remote;
import javax.inject.Inject;
/*
 * Created by shayan.rais on 12/9/2017.
 */

public class Repository implements RemoteDataInterface {

    private final RemoteDataSource remote;
    private final LocalDataSource local;

    @Inject
    public Repository(@Remote RemoteDataSource remote, @Local LocalDataSource local) {
        this.remote = remote;
        this.local = local;
    }

}
