package com.longface.simpleutils.app;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.provider.Settings;

import java.util.UUID;

public class AppUtils {


    public static PackageInfo getPackageInfo(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            return packageManager.getPackageInfo(
                    context.getPackageName(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取应用程序名称
     */

    public static String getAppName(Context context) {
        int labelRes = getPackageInfo(context).applicationInfo.labelRes;
        return context.getResources().getString(labelRes);
    }


    /**
     * [获取应用程序版本名称信息]
     *
     * @param context
     * @return 当前应用的版本名称
     */

    public static String getVersionName(Context context) {
        return getPackageInfo(context).versionName;
    }


    /**
     * [获取应用程序版本名称信息]
     *
     * @param context
     * @return 当前应用的版本名称
     */

    public static synchronized int getVersionCode(Context context) {
        return getPackageInfo(context).versionCode;
    }


    /**
     * [获取应用程序版本名称信息]
     *
     * @param context
     * @return 当前应用的版本名称
     */

    public static synchronized String getPackageName(Context context) {
        return getPackageInfo(context).packageName;
    }

    /**
     * 获取Mac地址
     * @param context
     * @param isAgree
     * @return
     */
    public static String getDeviceCodeOfAgree(Context context , boolean isAgree) {
        String ANDROID_ID;
        if (isAgree) {
            ANDROID_ID = Settings.System.getString(context.getContentResolver(), Settings.System.ANDROID_ID);
        } else {
            ANDROID_ID = UUID.randomUUID().toString();
        }
        return ANDROID_ID;
    }
    public static String getDeviceCode(Context context) {
        return getDeviceCodeOfAgree(context, true);
    }

}
