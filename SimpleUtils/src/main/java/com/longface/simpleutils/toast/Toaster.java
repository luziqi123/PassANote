package com.longface.simpleutils.toast;

import android.widget.Toast;

import com.longface.simpleutils.Constants;

public class Toaster {

    static {
        // 默认设置
        Toasty.Config.getInstance()
                .setRTL(false)
                .supportDarkTheme(true)
                .allowQueue(false)
                .apply();
    }

    // 通过这个方法获取Config 自定义设置
    public static Toasty.Config config() {
        return Toasty.Config.getInstance();
    }

    private static final RealToast toaster = new RealToast();

    public static RealToast error() {
        toaster.setType(RealToast.TYPE_ERROR);
        return toaster;
    }

    public static RealToast warning() {
        toaster.setType(RealToast.TYPE_WARNING);
        return toaster;
    }

    public static RealToast success() {
        toaster.setType(RealToast.TYPE_SUCCESS);
        return toaster;
    }

    public static void showLong(int msgRes) {
        showLong(Constants.application.getString(msgRes));
    }

    public static void showShort(int msgRes) {
        showShort(Constants.application.getString(msgRes));
    }

    public static void showLong(String msg) {
        toaster.setType(RealToast.TYPE_NORMAL);
        toaster.showLong(msg);
    }

    public static void showShort(String msg) {
        toaster.setType(RealToast.TYPE_NORMAL);
        toaster.showShort(msg);
    }

    public static class RealToast {
        protected RealToast() {
        }

        protected static final int TYPE_NORMAL = 0;
        protected static final int TYPE_ERROR = 1;
        protected static final int TYPE_WARNING = 2;
        protected static final int TYPE_SUCCESS = 3;
        int type;

        protected void setType(int type) {
            this.type = type;
        }

        public void showLong(int msgRes) {
            showLong(Constants.application.getString(msgRes));
        }

        public void showShort(int msgRes) {
            showShort(Constants.application.getString(msgRes));
        }

        public void showLong(String msg) {
            Toast toast = getToastForType(msg);
            toast.setDuration(Toast.LENGTH_LONG);
            toast.show();
        }

        public void showShort(String msg) {
            Toast toast = getToastForType(msg);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.show();
        }

        private Toast getToastForType(String msg) {
            switch (type) {
                case TYPE_NORMAL:
                    return Toasty.normal(Constants.application, msg, 0);
                case TYPE_ERROR:
                    return Toasty.error(Constants.application, msg, 0);
                case TYPE_WARNING:
                    return Toasty.warning(Constants.application, msg, 0);
                case TYPE_SUCCESS:
                    return Toasty.success(Constants.application, msg, 0);
            }
            return Toasty.normal(Constants.application, msg, 0);
        }
    }

}
