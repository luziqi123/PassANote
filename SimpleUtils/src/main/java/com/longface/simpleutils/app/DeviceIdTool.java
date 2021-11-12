package com.longface.simpleutils.app;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.provider.Settings;

import java.io.File;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.util.UUID;

/**
 * 获取设备唯一标识
 */
public class DeviceIdTool {

    /**
     * 应用安装时生成
     *
     * @return Install ID
     */
    public static String getInstallID(Activity activity) {
        String sID = null;
        if (sID == null) {
            File installation = new File(activity.getFilesDir(), "INSTALLATION");
            try {
                if (!installation.exists()) {
                    FileOutputStream out = new FileOutputStream(installation);
                    String id = UUID.randomUUID().toString();
                    out.write(id.getBytes());
                    out.close();
                }
                RandomAccessFile f = new RandomAccessFile(installation, "r");
                byte[] bytes = new byte[(int) f.length()];
                f.readFully(bytes);
                f.close();
                sID = new String(bytes);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return sID;
    }

    /**
     * Android系统首次运行生成的ID
     *
     * @return ANDROID_ID
     */
    public static String getAndroidID(Context context) {
        return Settings.System.getString(context.getContentResolver(), Settings.System.ANDROID_ID);
    }

    /**
     * Wifi的Mac地址 , 但极不准确
     *
     * @return WIFI_MAC
     */
    @SuppressLint("HardwareIds")
    public static String getWifiMac(Context context) {
        WifiManager wifi = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = wifi.getConnectionInfo();
        return info.getMacAddress();
    }

    /**
     * 序列号
     * @return Serial
     *
     * 1.Android_ID:Settings.System.getString(context.getContentResolver(), Settings.System.ANDROID_ID) //低版本稳定，高版本不稳定 示例：295a4fbf716094ee
     * 2.Build.SERIAL 设备序列号（有的设备无法获取） 示例：WTK7N16923005607
     * 3.Build.FINGERPRINT 设备指纹（同样的新设备该值应该是一样的） 示例：honor/FRD-AL00/HWFRD:6.0/HUAWEIFRD-AL00/C00B171:user/release-keys
     * 4.Build.TIME 固件推出日期 示例：1477442228000
     * 5.Build.VERSION.INCREMENTAL 源码控制版本号 示例： C00B171
     * 6.Build.getRadioVersion() 获取无线电固件版本 示例：21.210.03.00.031,21.210.03.00.031
     * 7.Build.HARDWARE 硬件名称 示例：hi3650
     * 8.Build.VERSION.SECURITY_PATCH 用户可见安全补丁level（这里我得到的是日期，可能是补丁修复的时间）示例：2016-10-01
     * 9.当前设备是12/24时制：Settings.System.getString(context.getContentResolver(), Settings.System.TIME_12_24) 示例：null（有的手机可以获取）
     * 10.Build.VERSION.SDK_INT SDK版本号 （一般讲是与系统版本号一一对应的） 示例：23
     * 11.Build.SUPPORTED_32_BIT_ABIS 支持32位ABIs的列表（数值）示例：[armeabi-v7a,armeabi]
     * 12.Build.SUPPORTED_64_BIT_ABIS 支持64位ABIs的列表（数值）示例：[arm64-v8a]
     * 13.Build.BOOTLOADER 系统启动程序版本号 示例：unknown
     * 14.Build.VERSION.RELEASE 用户可见版本 示例： 6.0
     * 15.Build.SUPPORTED_ABIS 支持ABIs的列表（数值）示例：[arm64-v8a,armeabi-v7a,armeabi]
     * 16.Build.BOARD 主板 示例：FRD-AL00
     * 17.Build.BRAND 系统定制商 示例：honor
     * 18.Build.CPU_ABI CPU指令集 示例：arm64-v8a
     * 19.Build.CPU_ABI2 CPU指令集2 示例：空值
     * 20.Build.DEVICE 设备参数 示例：HWFRD
     * 21.Build.HOST 示例：huawei-RH2288H-V2-12L
     * 22.Build.ID 修订版本列表 示例：HUAWEIFRD-AL00
     * 23.Build.MANUFACTURER 产品/硬件的制造商 示例：HUAWEI
     * 24.Build.MODEL 示例：FRD-AL00
     * 25.Build.PRODUCT 产品的名称 示例：FRD-AL00
     * 26.Build.TAGS 描述Build的标签（Comma-separated tags describing the build, like "unsigned,debug".） 示例：release-keys
     * 27.Build.TYPE 描述Build的类型（The type of build, like "user" or "eng".） 示例：user
     * 28.Build.USER 描述Build的USER 示例：jslave
     * 29.Build.DISPLAY A build ID string meant for displaying to the user 示例：FRD-AL00C00B171
     * 30.Build.VERSION.CODENAME 当前开发代号，或者字符串“REL”（如果是正式的发布版本） 示例： REL
     * 31.Build.VERSION.BASE_OS 基带版本 The base OS build the product is based on. 示例：空值
     */
    public static String getSerialNumber() {
        return android.os.Build.SERIAL;
    }

    /**
     * 获取设备相对位移的ID
     * 如果有则直接用 , 否则则会重新生成一个
     * 这个ID会存储在外部存储及本地SP上
     * @return 一个综合ID
     */
//    public static String getID(Context context) {
//    }
//
//    public static String saveID(String id) {
//
//    }

}
