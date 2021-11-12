package com.longface.simpleutils.app;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;

import com.longface.simpleutils.Constants;
import com.longface.simpleutils.toast.Toaster;

/**
 * 用于打开各种设置页面
 */
public class PhoneSettings {

    private int settingsIndex = 0;
    String[][] settings = {
            {"com.lbe.security.miui", "com.android.settings.MainSetting"}, // 小米的
            {"com.huawei.systemmanager", "com.huawei.permissionmanager.ui.MainActivity"}, // 华为的
            {"com.miui.securitycenter", "com.miui.appmanager.ApplicationsDetailsActivity"} // 小米的
    };

    /**
     * 忽略电池优化
     */
    public void isIgnoringBatteryOptimizations() {

    }

    /**
     * 自启动
     */
    public void autoStart() {

    }

    /**
     * 设置后台运行机制
     * 关闭智能限制后台运行
     */
    public void backgroundRun() {

    }

    /**
     * 手动设置隐私权限
     */
    public void manualSettingPermission() {

    }

    public void goSetting(String packageName) {
        if (settingsIndex >= settings.length) {
            Toaster.showLong("设置页跳转失败");
            settingsIndex = 0;
            return;
        }
        try {
            Intent intent = new Intent(packageName);
            intent.setData(Uri.parse("package:" + packageName));
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            String pkg = settings[settingsIndex][0];
            String cls = settings[settingsIndex][1];
            ComponentName comp = new ComponentName(pkg, cls);
            intent.setComponent(comp);
            Constants.application.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            settingsIndex++;
            goSetting(packageName);
        }
    }

}
