package com.sbhachu.audioplayer.data.model.media;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by sbhachu on 22/12/2014.
 */
public class Artist implements Parcelable {

    private Long id;
    private String artist;
    private String key;
    private Integer albumCount;
    private Integer trackCount;
    private Uri imageUri;

    public Artist(Parcel parcel) {
        this.id = parcel.readLong();
        this.artist = parcel.readString();
        this.key = parcel.readString();
        this.albumCount = parcel.readInt();
        this.trackCount = parcel.readInt();
        this.imageUri = parcel.readParcelable(Uri.class.getClassLoader());
    }

    public Artist(Long id, String artist, String key, Integer albumCount, Integer trackCount) {
        this.id = id;
        this.artist = artist;
        this.key = key;
        this.albumCount = albumCount;
        this.trackCount = trackCount;
    }

    public Long getId() {
        return id;
    }

    public String getArtist() {
        return artist;
    }

    public String getKey() {
        return key;
    }

    public Integer getAlbumCount() {
        return albumCount;
    }

    public Integer getTrackCount() {
        return trackCount;
    }

    public Uri getImageUri() {
        return imageUri;
    }

    public void setImageUri(Uri imageUri) {
        this.imageUri = imageUri;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeLong(id);
        parcel.writeString(artist);
        parcel.writeString(key);
        parcel.writeInt(albumCount);
        parcel.writeInt(trackCount);
        parcel.writeParcelable(imageUri, flags);
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
        final StringBuffer sb = new StringBuffer("Artist{");
        sb.append("id=").append(id);
        sb.append(", artist='").append(artist).append('\'');
        sb.append(", key='").append(key).append('\'');
        sb.append(", albumCount=").append(albumCount);
        sb.append(", trackCount=").append(trackCount);
        sb.append(", imageUri=").append(imageUri);
        sb.append('}');
        return sb.toString();
    }
}
