package com.longface.simpleutils.file;


import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import androidx.annotation.NonNull;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownLoadWorker implements Runnable {

    /**
     * 状态们
     */
    public static final int STATUS_NORMAL = 0;  // 空闲
    public static final int STATUS_FAIL = -1;   // 失败
    public static final int STATUS_ING = 1;     // 下载中
    public static final int STATUS_FINISH= 2;   // 下载完成
    public static final int STATUS_CANCEL= -2;  // 取消
    private int state = STATUS_NORMAL;

    /**
     * 下载连接 和 目标文件路径
     */
    private String downLoadUrl , tagPath;

    /**
     * 是否开启一个新的线程 , 取消下载
     */
    private boolean isBackGround , cancelDownload;

    /**
     * 用于发送回调至主线程的handler
     */
    private Handler statusHandler;

    protected DownLoadWorker(String url, String path , boolean isBackgroundTask) {
        this.downLoadUrl = url;
        this.tagPath = path;
        this.isBackGround = isBackgroundTask;
        statusHandler = new Handler(Looper.getMainLooper() , handlerCall);
    }

    /**
     * 开始
     */
    public void start() {
        if (state == STATUS_ING) {
            return;
        }
        if (isBackGround) {
            new Thread(this).start();
        } else {
            run();
        }
    }

    /**
     * 取消
     */
    public void cancel() {
        cancelDownload = true;
    }

    /**
     * 获取状态
     * @return
     */
    public int getState() {
        return state;
    }

    @Override
    public void run() {
        statusHandler.sendMessage(statusHandler.obtainMessage(state));
        state = STATUS_ING;
        try {
            // 判断SD卡是否存在，并且是否具有读写权限
            if (Environment.getExternalStorageState().equals(
                    Environment.MEDIA_MOUNTED)) {
                URL url = new URL(downLoadUrl);
                // 创建连接
                HttpURLConnection conn = (HttpURLConnection) url
                        .openConnection();
                conn.connect();
                // 获取文件大小
                int length = conn.getContentLength();
                // 创建输入流
                InputStream is = conn.getInputStream();
                File file = new File(tagPath);
                // 判断文件目录是否存在
                if (!file.exists()) {
                    file.mkdir();
                }
                File apkFile = new File(tagPath, downLoadUrl.substring(downLoadUrl.lastIndexOf("/"),
                        downLoadUrl.length()));
                FileOutputStream fos = new FileOutputStream(apkFile);
                int count = 0;
                // 缓存
                byte buf[] = new byte[1024];
                // 写入到文件中
                do {
                    int numread = is.read(buf);
                    count += numread;
                    // 计算进度条位置并发送回调
                    Message message = statusHandler.obtainMessage(state);
                    message.obj = (double) count / length;
                    statusHandler.sendMessage(message);

                    if (numread <= 0) {
                        // 下载完成
                        state = STATUS_FINISH;
                        statusHandler.sendMessage(statusHandler.obtainMessage(state));
                        break;
                    }
                    // 写入文件
                    fos.write(buf, 0, numread);
                } while (!cancelDownload);// 点击取消就停止下载.
                fos.close();
                is.close();

                // 如果是主动取消的 发送取消成功回调
                if (cancelDownload) {
                    state = STATUS_CANCEL;
                    statusHandler.sendMessage(statusHandler.obtainMessage(state));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            state = STATUS_FAIL;
            Message message = statusHandler.obtainMessage(state);
            message.obj = e.getMessage();
            statusHandler.sendMessage(message);
        }
    }

    private Handler.Callback handlerCall = new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message message) {
            if (callback == null) {
                return false;
            }
            switch (message.what) {
                case STATUS_NORMAL: // 开始
                    callback.onStart();
                    break;
                case STATUS_ING: // 进行中
                    callback.onLoading((Double) message.obj);
                    break;
                case STATUS_CANCEL: // 取消
                    callback.onCancel();
                    break;
                case STATUS_FAIL: // 失败
                    callback.onFail((String) message.obj);
                    break;
                case STATUS_FINISH: // 完成
                    callback.onFinish();
                    break;
            }
            return false;
        }
    };

    private DownloadingCallback callback;
    public DownLoadWorker setDownloadingCallback(DownloadingCallback listener) {
        this.callback = listener;
        return this;
    }

    public interface DownloadingCallback {
        void onStart();

        void onLoading(double progress);

        void onCancel();

        void onFinish();

        void onFail(String errorMsg);


    }
}
