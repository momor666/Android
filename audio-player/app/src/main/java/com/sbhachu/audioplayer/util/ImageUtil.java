package com.sbhachu.audioplayer.util;

import android.content.Context;
import android.net.Uri;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

/**
 * Created by sbhachu on 29/12/2014.
 */
@EBean
public class ImageUtil {

    @RootContext
    public Context context;

    public final Uri getPlaceholderImageUri() {
        return Uri.parse(
                "android.resource://" + context.getPackageName() + "/drawable/album_placeholder");
    }

}
