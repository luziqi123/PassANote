package com.longface.passanote;

import android.app.Application;

import com.longface.simpleutils.Constants;

public class AppApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Constants.application = this;
    }
}
