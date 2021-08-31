package com.longface.passanote

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.longface.simpleutils.Logger

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

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

        val toJsonFormat = Logger.toJsonFormat("{\"tradeNo\":null,\"handleTime\":null,\"code\":200,\"data\":{\"version\":\"4.2.3\",\"forceUpdate\":false,\"tipsUpdate\":false,\"appFileUrl\":\"\",\"createTime\":\"2021-03-25T21:08:31\",\"enabled\":true},\"msg\":\"调用成功\",\"succ\":true}")
        Logger.out("fsadfsdf", toJsonFormat)
    }
}