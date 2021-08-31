package com.longface.simpleutils;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import static com.longface.simpleutils.Constants.Logger_TAG;
import static com.longface.simpleutils.Constants.Logger_endLine__;
import static com.longface.simpleutils.Constants.Logger_startLine;


/**
 * 一个按照key分边框的Log工具
 */
public class Logger {

    private static HashMap<String, StringBuffer> logMap = new HashMap<>();

    public static void put(String key, String value) {
        StringBuffer stringBuffer = logMap.get(key);
        if (stringBuffer == null) {
            stringBuffer = new StringBuffer();
            stringBuffer.append(key).append("\n");
            stringBuffer.append(Logger_startLine).append("\n");
        }
        stringBuffer.append(value).append("\n");
        logMap.put(key, stringBuffer);
    }

    public static void out(String key, String value) {
        StringBuffer stringBuffer = logMap.get(key);
        if (stringBuffer == null) {
            put(key, value);
            out(key,"");
            return;
        }
        stringBuffer.append(value).append("\n");
        stringBuffer.append(Logger_endLine__);
        String s = stringBuffer.toString();
        logMap.remove(key);
        Log.i(Logger_TAG, s);
    }



    public static String toJsonFormat(String msg) {
        String message;
        try {
            if (msg.startsWith("{")) {
                JSONObject jsonObject = new JSONObject(msg);
                message = jsonObject.toString(4);//最重要的方法，就一行，返回格式化的json字符串，其中的数字4是缩进字符数
            } else if (msg.startsWith("[")) {
                JSONArray jsonArray = new JSONArray(msg);
                message = jsonArray.toString(4);
            } else {
                message = msg;
            }
        } catch (JSONException e) {
            message = msg;
        }
        return message;
    }


}
