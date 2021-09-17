package com.longface.passanote

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.longface.simpleutils.app.AppUtils
import com.longface.simpleutils.app.PhoneSettings

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        PhoneSettings().goSetting(AppUtils.getPackageName(this))
    }

}