package com.longface.passanote

import android.util.Pair
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.longface.jhttp.HttpSender
import com.longface.simpleutils.Logger
import okhttp3.OkHttpClient

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import java.security.KeyPair

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.longface.passanote", appContext.packageName)

        Logger.out("fsadfsdf", "toJsonFormat")

        // HTTP
//        var a = KeyPair("ss" , "ss")
//        var b = Pair("" , "")
//
//
//        new LoginApi("111" ,"222").save();
//        new LoginApi("111" ,"222").send();
//        HttpSender.get("login").send();



        /**
         * Method
         * URL
         * UrlParams
         *
         */

    }
}