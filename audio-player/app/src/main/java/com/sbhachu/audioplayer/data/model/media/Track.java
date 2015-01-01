package com.sbhachu.audioplayer.data.model.media;

import android.content.ContentUris;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.provider.MediaStore;

/**
 * Created by sbhachu on 21/12/2014.
 */
public class Track implements Parcelable {

    private Long id;
    private Integer trackNumber;
    private String title;
    private Integer duration;
    private String artist;
    private Integer artistId;
    private String album;
    private Integer albumId;
    private String composer;
    private Integer year;
    private String path;
    private Integer playOrder;

    public Track(Parcel parcel) {
        this.id = parcel.readLong();
        this.trackNumber = parcel.readInt();
        this.title = parcel.readString();
        this.duration = parcel.readInt();
        this.artist = parcel.readString();
        this.artistId = parcel.readInt();
        this.album = parcel.readString();
        this.albumId = parcel.readInt();
        this.composer = parcel.readString();
        this.year = parcel.readInt();
        this.path = parcel.readString();
        this.playOrder = parcel.readInt();
    }

    public Track(Long id, Integer trackNumber, String title, Integer duration, String artist, Integer artistId, String album, Integer albumId, String composer, Integer year, String path) {
        this.id = id;
        this.trackNumber = trackNumber;
        this.title = title;
        this.duration = duration;
        this.artist = artist;
        this.artistId = artistId;
        this.album = album;
        this.albumId = albumId;
        this.composer = composer;
        this.year = year;
        this.path = path;
    }

    public Track(Long id, Integer trackNumber, String title, Integer duration, String artist, Integer artistId, String album, Integer albumId, String composer, Integer year, String path, Integer playOrder) {
        this.id = id;
        this.trackNumber = trackNumber;
        this.title = title;
        this.duration = duration;
        this.artist = artist;
        this.artistId = artistId;
        this.album = album;
        this.albumId = albumId;
        this.composer = composer;
        this.year = year;
        this.path = path;
        this.playOrder = playOrder;
    }

    public Uri getUri() {
        return ContentUris.withAppendedId(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, id);
    }

    public Uri getAlbumArtUri() {
        Uri uri = Uri.parse("content://media/external/audio/albumart");
        return ContentUris.withAppendedId(uri, albumId);
    }

    public Long getId() {
        return id;
    }

    public Integer getTrackNumber() {
        return trackNumber;
    }

    public String getTitle() {
        return title;
    }

    public Integer getDuration() {
        return duration;
    }

    public String getArtist() {
        return artist;
    }

    public Integer getArtistId() {
        return artistId;
    }

    public String getAlbum() {
        return album;
    }

    public Integer getAlbumId() {
        return albumId;
    }

    public String getComposer() {
        return composer;
    }

    public Integer getYear() {
        return year;
    }

    public String getPath() {
        return path;
    }

    public Integer getPlayOrder() {
        return playOrder;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeLong(id);
        parcel.writeInt(trackNumber);
        parcel.writeString(title);
        parcel.writeInt(duration);
        parcel.writeString(artist);
        parcel.writeInt(artistId);
        parcel.writeString(album);
        parcel.writeInt(albumId);
        parcel.writeString(composer);
        parcel.writeInt(year);
        parcel.writeString(path);
        parcel.writeInt(playOrder);
    }

    public static final Parcelable.Creator<Track> CREATOR = new Parcelable.Creator<Track>() {
        public Track createFromParcel(Parcel in) {
            return new Track(in);
        }

        public Track[] newArray(int size) {
            return new Track[size];
        }
    };

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Track{");
        sb.append("id=").append(id);
        sb.append(", trackNumber=").append(trackNumber);
        sb.append(", title='").append(title).append('\'');
        sb.append(", duration=").append(duration);
        sb.append(", artist='").append(artist).append('\'');
        sb.append(", artistId=").append(artistId);
        sb.append(", album='").append(album).append('\'');
        sb.append(", albumId=").append(albumId);
        sb.append(", composer='").append(composer).append('\'');
        sb.append(", year=").append(year);
        sb.append(", path='").append(path).append('\'');
        sb.append(", playOrder=").append(playOrder);
        sb.append('}');
        return sb.toString();
    }

}
