package com.sbhachu.audioplayer.data;

import android.content.Context;
import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.content.LocalBroadcastManager;

import com.sbhachu.audioplayer.data.model.media.Track;
import com.sbhachu.audioplayer.util.Logger;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.Collection;
import java.util.LinkedList;

import static org.androidannotations.annotations.EBean.Scope;

/**
 * Created by sbhachu on 25/12/2014.
 */
@EBean(scope = Scope.Singleton)
public class PlayerQueue extends LinkedList<Track> {

    private static final String TAG = PlayerQueue.class.getSimpleName();

    @RootContext
    public Context context;

    public PlayerQueue() {
    }

    public void addList(Collection<Track> tracks) {
        super.addAll(0, tracks);
        Intent intent = new Intent("queueUpdated");
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }



}
