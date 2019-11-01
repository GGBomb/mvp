package com.android.base.utils;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;

public class Density {
    //==================方法一begin=====================//
    private static final String TAG = Density.class.getSimpleName();
    public static float sNoncompatDensity;
    public static float sNoncompatScaleDensity;

    public static void setDensity(final Application application) {
        application.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                setCustomDensity(activity, application);
            }

            @Override
            public void onActivityStarted(Activity activity) {
            }

            @Override
            public void onActivityResumed(Activity activity) {
            }

            @Override
            public void onActivityPaused(Activity activity) {
            }

            @Override
            public void onActivityStopped(Activity activity) {
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
            }

            @Override
            public void onActivityDestroyed(Activity activity) {

            }
        });
    }

    private static void setCustomDensity(@NonNull Activity activity, @NonNull final Application application) {
        final DisplayMetrics appDisplayMetrics = application.getResources().getDisplayMetrics();
        if (sNoncompatDensity == 0) {
            sNoncompatDensity = appDisplayMetrics.density;//原始的分辨率
            sNoncompatScaleDensity = appDisplayMetrics.scaledDensity;//
            application.registerComponentCallbacks(new ComponentCallbacks() {
                @Override
                public void onConfigurationChanged(Configuration newConfig) {
                    if (null != newConfig && newConfig.fontScale > 0) {//设置修改字体大小(无障碍功能，放大比例)
                        sNoncompatScaleDensity = application.getResources().getDisplayMetrics().scaledDensity;//
                    }
                }

                @Override
                public void onLowMemory() {

                }
            });
        }
        final float targetDensity = appDisplayMetrics.widthPixels / 213;//XHIGH 320   xxhdpi:360dp
        final float targetScaleDenisty = targetDensity * (sNoncompatScaleDensity / sNoncompatDensity);
        final int targetDensityDpi = (int) (targetScaleDenisty * 160);

        appDisplayMetrics.density = targetDensity;
        appDisplayMetrics.scaledDensity = targetScaleDenisty;
        appDisplayMetrics.densityDpi = targetDensityDpi;

        final DisplayMetrics activityDisplayMetrics = activity.getResources().getDisplayMetrics();
        activityDisplayMetrics.density = targetDensity;
        activityDisplayMetrics.scaledDensity = targetScaleDenisty;
        activityDisplayMetrics.densityDpi = targetDensityDpi;
    }

    private static void registerActivityLifecycleCallbacks(Application application) {
        application.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                setDefault(activity);
            }

            @Override
            public void onActivityStarted(Activity activity) {
            }

            @Override
            public void onActivityResumed(Activity activity) {
            }

            @Override
            public void onActivityPaused(Activity activity) {
            }

            @Override
            public void onActivityStopped(Activity activity) {
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
            }

            @Override
            public void onActivityDestroyed(Activity activity) {

            }
        });
    }
    //==================方法一end=====================//

    //==================方法二begin=====================//
    private static float appDensity;
    private static float appScaledDensity;
    private static DisplayMetrics appDisplayMetrics;

    /**
     * 用来参照的的width
     */
    public static float WIDTH = 1280;//720*1280 1080 * 1920

    public static void setDensity(@NonNull final Application application, float width) {
        appDisplayMetrics = application.getResources().getDisplayMetrics();
        LogHelper.e(TAG, "[setDensity]" + appDisplayMetrics.widthPixels + "," + appDisplayMetrics.heightPixels);
        WIDTH = width;
        registerActivityLifecycleCallbacks(application);

        if (appDensity == 0) {
            //初始化的时候赋值
            appDensity = appDisplayMetrics.density;
            appScaledDensity = appDisplayMetrics.scaledDensity;

            //添加字体变化的监听
            application.registerComponentCallbacks(new ComponentCallbacks() {
                @Override
                public void onConfigurationChanged(Configuration newConfig) {
                    //字体改变后,将appScaledDensity重新赋值
                    if (newConfig != null && newConfig.fontScale > 0) {
                        appScaledDensity = application.getResources().getDisplayMetrics().scaledDensity;
                    }
                }

                @Override
                public void onLowMemory() {
                }
            });
        }
    }


    private static void setDefault(Activity activity) {
        setAppOrientation(activity);
    }

    private static void setAppOrientation(@NonNull Activity activity) {
        float targetDensity = 0;
        try {
            targetDensity = appDisplayMetrics.widthPixels / WIDTH;
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        float targetScaledDensity = targetDensity * (appScaledDensity / appDensity);
        int targetDensityDpi = (int) (160 * targetDensity);
        /*
         * 最后在这里将修改过后的值赋给系统参数
         *
         * 只修改Activity的density值
         */
        //appDisplayMetrics.density = targetDensity;
        //appDisplayMetrics.scaledDensity = targetScaledDensity;
        //appDisplayMetrics.densityDpi = targetDensityDpi;

        DisplayMetrics activityDisplayMetrics = activity.getResources().getDisplayMetrics();
        activityDisplayMetrics.density = targetDensity;
        activityDisplayMetrics.scaledDensity = targetScaledDensity;
        activityDisplayMetrics.densityDpi = targetDensityDpi;
    }
    //==================方法二end=====================//
}
