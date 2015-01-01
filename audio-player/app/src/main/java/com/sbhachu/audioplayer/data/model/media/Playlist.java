package com.sbhachu.audioplayer.data.model.media;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.provider.MediaStore;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by sbhachu on 21/12/2014.
 */
public class Playlist implements Parcelable {

    private Long id;
    private String name;
    private Date dateAdded;
    private Date dateModified;
    private List<Uri> images = new ArrayList<>();

    public Playlist(Parcel parcel) {
        this.id = parcel.readLong();
        this.name = parcel.readString();
        this.dateAdded = new Date(parcel.readLong());
        this.dateModified = new Date(parcel.readLong());

        if (images == null )
            images = new ArrayList<>();
        parcel.readTypedList(images, Uri.CREATOR);
    }

    public Playlist(Long id, String name, Long dateAdded, Long dateModified) {
        this.id = id;
        this.name = name;
        this.dateAdded = new Date(dateAdded);
        this.dateModified = new Date(dateModified);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public Date getDateModified() {
        return dateModified;
    }

    public List<Uri> getImages() {
        return images;
    }

    public void setImages(List<Uri> images) {
        this.images = images;
    }

    public Uri getUri() {
        return MediaStore.Audio.Playlists.Members.getContentUri("external", id);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeLong(id);
        parcel.writeString(name);
        parcel.writeLong(dateAdded.getTime());
        parcel.writeLong(dateModified.getTime());
        parcel.writeTypedList(images);
    }

    public static final Parcelable.Creator<Playlist> CREATOR = new Parcelable.Creator<Playlist>() {
        public Playlist createFromParcel(Parcel in) {
            return new Playlist(in);
        }

        public Playlist[] newArray(int size) {
            return new Playlist[size];
        }
    };

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Playlist{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", dateAdded=").append(dateAdded);
        sb.append(", dateModified=").append(dateModified);
        sb.append(", images=").append(images);
        sb.append('}');
        return sb.toString();
    }
}
