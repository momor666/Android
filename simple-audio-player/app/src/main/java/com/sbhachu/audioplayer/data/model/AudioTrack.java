package com.sbhachu.audioplayer.data.model;

import android.content.ContentUris;
import android.net.Uri;
import android.provider.MediaStore;

/**
 * Created by sbhachu on 03/10/2014.
 */
public class AudioTrack {

    private Long id;
    private String artist;
    private String title;
    private String album;
    private Integer duration;
    private Long albumId;

    public AudioTrack(Long id, String artist, String title, String album, Integer duration, Long albumId) {
        this.id = id;
        this.artist = artist;
        this.title = title;
        this.album = album;
        this.duration = duration;
        this.albumId = albumId;
    }

    public Long getId() {
        return id;
    }

    public String getArtist() {
        return artist;
    }

    public String getTitle() {
        return title;
    }

    public String getAlbum() {
        return album;
    }

    public Integer getDuration() {
        return duration;
    }

    public Long getAlbumId() {
        return albumId;
    }

    public Uri getURI() {
        return ContentUris.withAppendedId(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, id);
    }

    @Override
    public String toString() {
        return "AudioTrack[" +
                "id=" + id +
                ", album-id=" + albumId + '\'' +
                ", artist='" + artist + '\'' +
                ", title='" + title + '\'' +
                ", album='" + album + '\'' +
                ", duration=" + duration +
                ", uri=" + getURI() +
                ']';
    }
}
