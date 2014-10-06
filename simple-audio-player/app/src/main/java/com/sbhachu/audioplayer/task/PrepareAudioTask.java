package com.sbhachu.audioplayer.task;

import android.os.AsyncTask;

import com.sbhachu.audioplayer.data.AudioTrackRetriever;

/**
 * Created by sbhachu on 03/10/2014.
 */
public class PrepareAudioTask extends AsyncTask<Void, Void, Void> {

    private AudioTrackRetriever audioTrackRetriever;
    private AudioTrackRetrieverListener audioTrackRetrieverListener;

    public PrepareAudioTask(AudioTrackRetriever audioTrackRetriever,
                            AudioTrackRetrieverListener audioTrackRetrieverListener) {
        this.audioTrackRetriever = audioTrackRetriever;
        this.audioTrackRetrieverListener = audioTrackRetrieverListener;
    }

    @Override
    protected Void doInBackground(Void... params) {
        audioTrackRetriever.fetch();
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        audioTrackRetrieverListener.onAudioTracksRetrieverCompleted();
    }

    public interface AudioTrackRetrieverListener {
        public void onAudioTracksRetrieverCompleted();
    }

}
