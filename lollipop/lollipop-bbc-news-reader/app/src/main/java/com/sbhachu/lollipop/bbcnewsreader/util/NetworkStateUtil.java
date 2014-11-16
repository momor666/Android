package com.sbhachu.lollipop.bbcnewsreader.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

/**
 * Created by sbhachu on 06/10/2014.
 */
@EBean
public class NetworkStateUtil {

    @RootContext
    public Context context;

    public boolean isNetworkAvailable()
    {
        ConnectivityManager connMgr = ( ConnectivityManager ) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        return networkInfo != null && networkInfo.isConnected();
    }
}
