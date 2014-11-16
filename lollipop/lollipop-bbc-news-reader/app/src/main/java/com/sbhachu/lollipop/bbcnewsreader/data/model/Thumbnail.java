package com.sbhachu.lollipop.bbcnewsreader.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.simpleframework.xml.Attribute;

/**
 * Created by sbhachu on 28/10/2014.
 */
public class Thumbnail implements Parcelable {

    @Attribute(name = "width")
    private int width;

    @Attribute(name = "height")
    private int height;

    @Attribute(name = "url")
    private String url;

    public Thumbnail() {}

    public Thumbnail(Parcel parcel) {
        this.width = parcel.readInt();
        this.height = parcel.readInt();
        this.url = parcel.readString();
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public static final Creator<Thumbnail> CREATOR = new Creator<Thumbnail>() {
        public Thumbnail createFromParcel(Parcel in) {
            return new Thumbnail(in);
        }

        public Thumbnail[] newArray(int size) {
            return new Thumbnail[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(width);
        parcel.writeInt(height);
        parcel.writeString(url);
    }

    @Override
    public String toString() {
        return "Thumbnail{" +
                "width=" + width +
                ", height=" + height +
                ", url='" + url + '\'' +
                '}';
    }
}
