package com.appteamnith.hillffair.application;

import android.app.Application;
import android.content.Context;

import com.onesignal.OneSignal;

/**
 * Created by root on 30/9/16.
 */

public class MyApplication extends Application {
    private static MyApplication myApplication;
    @Override
    public void onCreate() {
        super.onCreate();
        OneSignal.startInit(this).init();
        myApplication=this;
    }

    public static synchronized Context getAppContext(){
        return myApplication.getApplicationContext();
    }

}
