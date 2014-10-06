package com.sbhachu.audioplayer.data;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import com.sbhachu.audioplayer.data.model.AudioTrack;
import com.sbhachu.audioplayer.util.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by sbhachu on 03/10/2014.
 */
public class AudioTrackRetriever {

    public static final String TAG = AudioTrackRetriever.class.getSimpleName();

    private ContentResolver contentResolver;

    private List<AudioTrack> audioTrackList = new ArrayList<AudioTrack>();

    private Random random = new Random();

    public AudioTrackRetriever(ContentResolver contentResolver) {
        this.contentResolver = contentResolver;
    }

    public void fetch() {

        final Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Cursor cursor = contentResolver.query(uri, null, MediaStore.Audio.Media.IS_MUSIC + " = 1", null, null);

        if (cursor == null) {
            Logger.e(TAG, "cursor is null");
            return;
        }

        if (!cursor.moveToFirst()) {
            Logger.e(TAG, "no query results");
            return;
        }

        while (cursor.moveToNext()) {

            final AudioTrack audioTrack = new AudioTrack(cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media._ID)),
                    cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST)),
                    cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE)),
                    cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM)),
                    cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media.DURATION)),
                    cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID)));

            Logger.i(TAG, audioTrack.toString());

            audioTrackList.add(audioTrack);
        }
    }

    public AudioTrack getRandomItem() {

        if (audioTrackList.size() <= 0)
            return null;

        return audioTrackList.get(random.nextInt(audioTrackList.size()));
    }
}
