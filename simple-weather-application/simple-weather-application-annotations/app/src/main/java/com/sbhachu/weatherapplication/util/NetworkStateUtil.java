package com.sbhachu.weatherapplication.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import org.androidannotations.annotations.EBean;

/**
 * Created by sbhachu on 06/10/2014.
 */
@EBean
public class NetworkStateUtil {

    public boolean isNetworkAvailable(Context context)
    {
        ConnectivityManager connMgr = ( ConnectivityManager ) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        return networkInfo != null && networkInfo.isConnected();
    }
}
