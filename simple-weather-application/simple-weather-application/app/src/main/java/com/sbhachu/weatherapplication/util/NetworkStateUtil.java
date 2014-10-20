package com.sbhachu.weatherapplication.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by sbhachu on 06/10/2014.
 */
public class NetworkStateUtil {

    public static boolean isNetworkAvailable(Context context)
    {
        ConnectivityManager connMgr = ( ConnectivityManager ) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        return networkInfo != null && networkInfo.isConnected();
    }
}
