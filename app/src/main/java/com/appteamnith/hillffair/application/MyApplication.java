package com.appteamnith.hillffair.application;

import android.app.Application;

import com.onesignal.OneSignal;

/**
 * Created by root on 30/9/16.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        OneSignal.startInit(this).init();
    }
}
