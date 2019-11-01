package com.android.base.application;

import android.app.Application;

import com.android.base.impl.IApp;
import com.android.base.utils.Density;

public class BaseApplication extends Application implements IApplication{

    private static IApplication mIApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        //Density.setDensity(this, Density.WIDTH);//适配屏幕
        mIApplication = this;
    }

    public static IApplication getInstance() {
        return mIApplication;
    }

    @Override
    public Application getApplication() {
        return this;
    }
}
