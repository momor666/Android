package com.sbhachu.audioplayer.data.model.media;

import android.content.ContentUris;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by sbhachu on 21/12/2014.
 */
public class Album implements Parcelable {

    private Long id;
    private String name;
    private String artist;
    private String albumArt;
    private Integer songCount;

    public Album(Parcel parcel) {
        this.id = parcel.readLong();
        this.name = parcel.readString();
        this.artist = parcel.readString();
        this.albumArt = parcel.readString();
        this.songCount = parcel.readInt();
    }

    public Album(Long id, String name, String artist, String albumArt, Integer songCount) {
        this.id = id;
        this.name = name;
        this.artist = artist;
        this.albumArt = albumArt;
        this.songCount = songCount;
    }

    public Uri getAlbumArtUri() {
        Uri uri = Uri.parse("content://media/external/audio/albumart");
        return ContentUris.withAppendedId(uri, id);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getArtist() {
        return artist;
    }

    public String getAlbumArt() {
        return albumArt;
    }

    public Integer getSongCount() {
        return songCount;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeLong(id);
        parcel.writeString(name);
        parcel.writeString(artist);
        parcel.writeString(albumArt);
        parcel.writeInt(songCount);
    }

    public static final Parcelable.Creator<Artist> CREATOR = new Parcelable.Creator<Artist>() {
        public Artist createFromParcel(Parcel in) {
            return new Artist(in);
        }

        public Artist[] newArray(int size) {
            return new Artist[size];
        }
    };

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Album{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", artist='").append(artist).append('\'');
        sb.append(", albumArt='").append(albumArt).append('\'');
        sb.append(", songCount=").append(songCount);
        sb.append('}');
        return sb.toString();
    }
}
