package com.longface.simpleutils.file;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.util.SparseArray;

import androidx.core.content.FileProvider;

import com.longface.simpleutils.app.AppUtils;

import java.io.File;

public class FileUtils {

    private FileUtils() {}
    private volatile static FileUtils instance;
    public static FileUtils getInstance() {
        if (instance != null) {
            return instance;
        } else {
            synchronized (FileUtils.class) {
                if (instance == null) {
                    instance = new FileUtils();
                }
            }
        }
        return instance;
    }

    /**
     * 获取/新建 一个文件下载任务
     */
    private SparseArray<DownLoadWorker> downLoadWorkerArray;
    public DownLoadWorker getDownloadWorker(String url, String path, boolean isBackTask) {
        if (downLoadWorkerArray == null) {
            downLoadWorkerArray = new SparseArray<>();
        }
        DownLoadWorker downLoadWorker = downLoadWorkerArray.get((url + path).hashCode());
        if (downLoadWorker == null) {
            downLoadWorker = new DownLoadWorker(url, path, isBackTask);
        }
        return downLoadWorker;
    }


    /**
     * 安装apk
     */
    public void installApk(Activity activity, File apkFile) {
        // 通过Intent安装APK文件
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            Uri contentUri = FileProvider.getUriForFile(activity
                    , AppUtils.getPackageName(activity) + ".fileProvider"
                    , apkFile);
            intent.setDataAndType(contentUri, "application/vnd.android.package-archive");
        } else {
            intent.setDataAndType(Uri.parse("file://" + apkFile.toString()),
                    "application/vnd.android.package-archive");
        }
        activity.startActivity(intent);
    }


    /**
     * 内部使用，外部程序无法访问。卸载应用时删除。系统空间不足时可能会删除
     * 无需申请权限
     * @param context
     * @return 应用内部存储缓存目录
     */
    public File fileOfCacheDir(Context context) {
        return context.getCacheDir();
    }

    /**
     * 内部使用，外部程序无法访问。卸载应用时删除。
     * 无需申请权限
     * @param context
     * @return 应用内部存储文件目录
     */
    public File fileOfDir(Context context) {
        return context.getFilesDir();
    }


    /**
     * 外部程序可以访问。卸载应用可能会删除。系统空间不足时可能会删除
     * 读写需要申请权限
     * @return 应用外部存储缓存目录
     */
    public File fileOfExternalCacheDir(Context context) {
        return context.getExternalCacheDir();
    }

    /**
     * 外部程序可以访问。卸载应用不会删除。
     * 读写需要申请权限
     * @return 外部存储空间目录
     */
    public File fileOfExternalDirectory() {
        return Environment.getExternalStorageDirectory();
    }

    /**
     * 外部程序可以访问。卸载应用可能会删除。
     * 读写需要申请权限
     * @param DIRECTORY
     *      DIRECTORY_MUSIC,
     *      DIRECTORY_PODCASTS,
     *      DIRECTORY_ALARMS,
     *      DIRECTORY_RINGTONES,
     *      DIRECTORY_NOTIFICATIONS,
     *      DIRECTORY_PICTURES,
     *      DIRECTORY_MOVIES,
     *      DIRECTORY_DOWNLOADS,
     *      DIRECTORY_DCIM,
     *      DIRECTORY_DOCUMENTS
     * @return 应用外部存储文件目录
     */
    public File fileOfExternalFilesDir(Context context , String DIRECTORY) {
        return context.getExternalFilesDir(DIRECTORY);
    }

    /**
     * 外部程序可以访问。卸载应用不会删除。
     * 读写需要申请权限
     * @param DIRECTORY
     *      DIRECTORY_MUSIC,
     *      DIRECTORY_PODCASTS,
     *      DIRECTORY_ALARMS,
     *      DIRECTORY_RINGTONES,
     *      DIRECTORY_NOTIFICATIONS,
     *      DIRECTORY_PICTURES,
     *      DIRECTORY_MOVIES,
     *      DIRECTORY_DOWNLOADS,
     *      DIRECTORY_DCIM,
     *      DIRECTORY_DOCUMENTS
     * @return 外部存储空间分类目录
     */
    public File fileOfExternalPublicFilesDir(Context context , String DIRECTORY) {
        return Environment.getExternalStoragePublicDirectory(DIRECTORY);
    }



}
