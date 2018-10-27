package com.waterphobiadr;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.StrictMode;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;
import android.util.Log;
import com.google.firebase.database.logging.LogWrapper;
import com.waterphobiadr.di.component.ApplicationComponent;
import com.waterphobiadr.di.component.DaggerApplicationComponent;
import com.waterphobiadr.di.module.ApplicationModule;
import timber.log.Timber;

import static com.waterphobiadr.BuildConfig.DEBUG;

public class App extends MultiDexApplication {

    public static String PACKAGE_NAME; //com.disrupt.savyour
    private static String APP_VERSION = "";
    private static int APP_VERSION_CODE;

    private static App mInstance;

    private ApplicationComponent component;


    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;

        //https://stackoverflow.com/questions/18100161/strictmode-threadpolicy-builder-purpose-and-advantages
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
//        MultiDex.install(this);
        setupApplication();

        component = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();



        if (DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    //__________________________________________________________________________________________________
    //application context
    public static synchronized App getInstance() {
        return mInstance;
    }

    //__________________________________________________________________________________________________
    void setupApplication() {
        PACKAGE_NAME = getPackageName();
        try {
            PackageInfo pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            APP_VERSION = pInfo.versionName;
            APP_VERSION_CODE = pInfo.versionCode;
        } catch (PackageManager.NameNotFoundException ignored) {

        }
    }

    public static int getAppVersionCode() {
        return APP_VERSION_CODE;
    }

    public static String getAppVersion() {
        return APP_VERSION;
    }

    public static int getAndroidOsVersion() {
        return Build.VERSION.SDK_INT;
    }

    //______________________________________________________________________________________________
    public ApplicationComponent getComponent() {
        return component;
    }

}
