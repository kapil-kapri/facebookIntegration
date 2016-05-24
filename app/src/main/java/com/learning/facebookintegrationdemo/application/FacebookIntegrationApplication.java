package com.learning.facebookintegrationdemo.application;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.StrictMode;

import com.facebook.FacebookSdk;
import com.facebook.accountkit.AccountKit;
import com.facebook.appevents.AppEventsLogger;

/**
 * Created by kapilkapri on 17/05/16.
 */
public class FacebookIntegrationApplication extends Application {
    static Context appContext;
    @Override
    public void onCreate() {
        super.onCreate();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // Kitkat and lower has a bug that can cause in correct strict mode
            // warnings about expected activity counts
            enableStrictMode();
        }

        AccountKit.initialize(getApplicationContext());
        // Initialize the SDK before executing any other operations,
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        appContext=this;
    }
    public static  Context getAppContext(){
        return appContext;
    }

    public void enableStrictMode() {
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .penaltyDeath()
                .build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .penaltyDeath()
                .build());
    }
}
