package com.sbhachu.weatherapplication.util;

import android.util.Log;

/**
 * Created by sbhachu on 01/08/2014.
 */
public class Logger {

    private static final String TAG = "SIMPLE-AUDIO-PLAYER";

    public static void i(String tag, String message) {
        Log.i(TAG + "[" + tag + "]", "[I] -> " + message);
    }

    public static void d(String tag, String message) {
        Log.d(TAG + "[" + tag + "]", "[D] -> " + message);
    }

    public static void e(String tag, String message) {
        Log.e(TAG + "[" + tag + "]", "[E] -> " + message);
    }

    public static void e(String tag, String message, Throwable throwable) {
        Log.e(TAG + "[" + tag + "]", "[E] -> " + message + "\n" + throwable);
    }
}
