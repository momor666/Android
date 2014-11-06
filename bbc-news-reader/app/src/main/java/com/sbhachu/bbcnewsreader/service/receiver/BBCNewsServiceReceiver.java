package com.sbhachu.bbcnewsreader.service.receiver;

import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;

/**
 * Created by sbhachu on 28/10/2014.
 */
public class BBCNewsServiceReceiver extends ResultReceiver {

    private BBCNewsServiceListener newsServiceListener;

    public BBCNewsServiceReceiver(Handler handler) {
        super(handler);
    }

    public void setListener(BBCNewsServiceListener listener) {
        this.newsServiceListener = listener;
    }

    @Override
    protected void onReceiveResult(int resultCode, Bundle resultData) {
        if (newsServiceListener != null)
            newsServiceListener.onResultReceived(resultCode, resultData);
    }

    public interface BBCNewsServiceListener {
        public void onResultReceived(int resultCode, Bundle resultData);
    }
}
