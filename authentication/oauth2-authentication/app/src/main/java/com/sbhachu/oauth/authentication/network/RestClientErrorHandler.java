package com.sbhachu.oauth.authentication.network;

import android.content.Context;
import android.widget.Toast;

import com.sbhachu.oauth.authentication.util.Logger;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.api.rest.RestErrorHandler;
import org.springframework.core.NestedRuntimeException;
import org.springframework.web.client.RestClientException;

/**
 * Created by sbhachu on 22/11/2014.
 */
@EBean
public class RestClientErrorHandler implements RestErrorHandler {

    public static final String TAG = RestClientErrorHandler.class.getSimpleName();

    @RootContext
    public Context context;

    @Override
    public void onRestClientExceptionThrown(NestedRuntimeException e) {
        Logger.e(TAG, e.getMessage(), e);
        errorToast(e.getMessage());
    }

    @UiThread
    public void errorToast(String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
