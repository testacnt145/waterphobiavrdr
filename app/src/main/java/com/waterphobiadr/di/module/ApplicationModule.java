package com.waterphobiadr.di.module;

import android.app.Application;
import android.content.Context;
import com.waterphobiadr.di.Singleton;
import dagger.Module;
import dagger.Provides;
/*
 * Created by shayan.rais on 12/9/2017.
 */

@Module
public class ApplicationModule {

    private final Context context;

    public ApplicationModule(Context context) {
        this.context = context;
       /* Timber.plant(new Timber.DebugTree() {
            @Override
            protected String createStackElementTag(StackTraceElement element) {
                return super.createStackElementTag(element) + " | " + element.getLineNumber();
            }
        });*/
    }

    @Provides
    @Singleton
    Context getContext() {
        return context;
    }

    @Provides
    @Singleton
    Application getApplication() {
        return (Application) context;
    }


}