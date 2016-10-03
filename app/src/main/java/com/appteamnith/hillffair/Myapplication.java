package com.appteamnith.hillffair;

import android.app.Application;

import com.onesignal.OneSignal;

/**
 * Created by root on 30/9/16.
 */

public class Myapplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        OneSignal.startInit(this).init();
    }
}
