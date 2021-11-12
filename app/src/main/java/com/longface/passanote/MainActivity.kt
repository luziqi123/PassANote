package com.longface.passanote

import android.os.Bundle
import android.provider.Settings
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.longface.passanote.http.GetPM25
import com.longface.simpleutils.app.AppUtils
import com.longface.simpleutils.app.DeviceIdTool
import com.longface.simpleutils.app.PhoneSettings
import com.monster.http.HttpHelper

class MainActivity : AppCompatActivity() {

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        PhoneSettings().goSetting(AppUtils.getPackageName(this))


        HttpHelper.getInstance().send(GetPM25())

        Log.i("ddddddddddddd", "getAndroidID = " + DeviceIdTool.getAndroidID(this))
        Log.i("ddddddddddddd", "getInstallID = " + DeviceIdTool.getInstallID(this))
        Log.i("ddddddddddddd", "getSerialNumber = " + DeviceIdTool.getSerialNumber())
    }

}